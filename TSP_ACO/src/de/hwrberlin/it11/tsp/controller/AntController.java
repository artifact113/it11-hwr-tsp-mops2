/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hwrberlin.it11.tsp.constant.IterationMode;
import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.interfaces.AlgorithmListener;
import de.hwrberlin.it11.tsp.model.AntProject;
import de.hwrberlin.it11.tsp.model.Edge;
import de.hwrberlin.it11.tsp.model.Node;
import de.hwrberlin.it11.tsp.model.Result;

/**
 * Verwaltet den Ameisenalgorithmus.
 * 
 * @author Patrick Szostack
 * 
 */
public class AntController {

	/** Das Ameisenprojekt */
	private AntProject _project;

	/** Der momentan benutzte Iterationsmodus */
	private IterationMode _iterationMode;

	/** Iterationszähler, zählt mit wie viele Iterationen vorüber sind */
	private int _iterationCounter;

	/** Referenz auf den Thread, der momentan den Algorithmus bearbeitet */
	private volatile Thread _algorithm;

	/** Die Liste an AlgorithmListener */
	private List<AlgorithmListener> _listenerList;



	/**
	 * Erzeugt einen neuen AntController mit dem angegebenen AntProject.
	 * 
	 * @param pProject
	 *            das zu benutzende AntProject
	 */
	public AntController(AntProject pProject) {
		_project = pProject;
		_iterationMode = IterationMode.COUNT;
		_listenerList = new ArrayList<AlgorithmListener>();
	}



	/**
	 * Gibt das AntProject zurück.
	 * 
	 * @return das AntProject
	 */
	public AntProject getProject() {
		return _project;
	}



	/**
	 * Setzt den angegebenen Iterationsmodus auf den benutzten Iterationsmodus.
	 * 
	 * @param pMode
	 *            der zu benutzende Iterationsmodus
	 */
	public void setIterationMode(IterationMode pMode) {
		_iterationMode = pMode;
	}



	/**
	 * Initialisiert den Algorithmus. Das umfasst das Zurücksetzen der Kanten (Pheromonwert auf den initialen Pheromonwert setzen), Reinitialisierung
	 * des Results, Setzen des Iterationszählers auf 0.
	 */
	public void init() {
		_project.initEdges();
		_project.getResult().init();
		_iterationCounter = 0;
	}



	/**
	 * Überprüft anhand des momentan benutzten Iterationsmodus', ob der Algorithmus stoppen muss oder nicht.
	 * 
	 * @return true, wenn die Abbruchbedingung erreicht ist, andernfalls false
	 */
	private boolean isConditionMet() {
		if (_iterationCounter > 0) {
			if (_iterationMode == IterationMode.COUNT) {
				if (_iterationCounter == _project.getParameter().getIterationCount()) {
					return true;
				}
			}
			if (_iterationMode == IterationMode.LENGTH) {
				if (_project.getResult().getBestTourLengthGlobal() <= _project.getParameter().getMaximumTourLength() && _iterationCounter != 0) {
					return true;
				}
			}
			if (_iterationMode == IterationMode.SOLUTION) {
				// Liste der besten Tour klonen, um die Ausgangsliste nicht in ihrer Struktur zu verändern
				List<Node> bestTourGlobalCopy = new ArrayList<Node>(_project.getResult().getBestTourGlobal());

				// Die Liste der Knoten, so wie sie in der Ausgangsdatei gespeichert waren
				List<Node> nodeList = _project.getTSPData().getNodeList();

				List<Integer> indexList = _project.getOptimalTourIndeces();

				// Die Liste so rotieren, dass die erste Node in der nodeList dem ersten Index der indexList entspricht
				Node startNode = nodeList.get(indexList.get(0) - 1);
				int startNodeIndex = bestTourGlobalCopy.indexOf(startNode);
				Collections.rotate(bestTourGlobalCopy, startNodeIndex * -1);
				System.out.println("LIST CHECK");

				for (int i = 0; i < bestTourGlobalCopy.size(); i++) {
					Node node = bestTourGlobalCopy.get(i);
					Node otherNode = nodeList.get(indexList.get(i) - 1);
					System.out.println(i);
					System.out.println(node);
					System.out.println(otherNode);
					System.out.println();
					if (node != otherNode) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}



	/**
	 * Startet den Algorithmus. Ein neuer Thread wird erstellt, der diesen Algorithmus abarbeitet.
	 */
	public void start() {
		for (AlgorithmListener listener : _listenerList) {
			listener.algorithmStarted();
		}

		_algorithm = new Thread() {

			@Override
			public void run() {
				init();
				long startTime = System.currentTimeMillis();
				double bestTourLengthGlobal = -1;
				List<Double> averageLengthGlobalValues = new ArrayList<Double>();
				Result result = _project.getResult();

				while (!isConditionMet()) {
					if (Thread.currentThread() != _algorithm) {
						return;
					}

					// Ameisen erzeugen
					List<Ant> antList = new ArrayList<Ant>();
					for (int j = 0; j < _project.getParameter().getAntCount(); j++) {
						antList.add(new Ant(_project.getTSPData().getNodeList()
								.get((int) (Math.random() * _project.getTSPData().getNodeList().size())), _project));
					}

					double bestTourLengthIteration = -1;
					List<Double> averageLengthIterationValues = new ArrayList<Double>();

					// Jede Ameise laufen lassen und auf eine neue beste Tour überprüfen
					for (Ant ant : antList) {
						while (!ant.isFinished()) {
							ant.next();
						}

						double travelledDistance = ant.getTravelledDistance();
						averageLengthIterationValues.add(travelledDistance);

						if (travelledDistance < bestTourLengthIteration || bestTourLengthIteration == -1) {
							bestTourLengthIteration = travelledDistance;
							result.setBestTourLengthIteration(travelledDistance);
							result.setBestTourIteration(ant.getVisitedNodes());

							if (bestTourLengthIteration < bestTourLengthGlobal || bestTourLengthGlobal == -1) {
								bestTourLengthGlobal = bestTourLengthIteration;
								averageLengthGlobalValues.add(bestTourLengthGlobal);
								result.setBestTourLengthGlobal(bestTourLengthIteration);
								result.setBestTourGlobal(result.getBestTourIteration());
							}
						}
					}

					// Verdunsten der Pheromone auf den Kanten
					for (Edge edge : _project.getEdgeList()) {
						edge.setPheromone((1 - _project.getParameter().getEvaporationParameter()) * edge.getPheromone());
					}

					// Jede Ameise platziert Pheromone auf den Kanten, die sie besucht hat
					for (Ant ant : antList) {
						for (int j = 0; j < ant.getVisitedNodes().size() - 1; j++) {
							Node node = ant.getVisitedNodes().get(j);
							Node otherNode = ant.getVisitedNodes().get(j + 1);
							Edge edge = node.getEdge(otherNode);
							edge.setPheromone(edge.getPheromone() + _project.getParameter().getPheromonUpdateParameter() / ant.getTravelledDistance());
						}
					}

					// Berechnung der Durchschnittswerte der Tourlängen
					double iterationLengthSum = 0;
					for (Double iterationLengthValue : averageLengthIterationValues) {
						iterationLengthSum += iterationLengthValue;
					}
					result.setAverageTourLengthIteration(iterationLengthSum / averageLengthIterationValues.size());

					double globalLengthSum = 0;
					for (Double globalLengthValue : averageLengthGlobalValues) {
						globalLengthSum += globalLengthValue;
					}
					result.setAverageTourLengthGlobal(globalLengthSum / averageLengthGlobalValues.size());

					result.setElapsedTime(System.currentTimeMillis() - startTime);

					// Eine Iteration ist vorüber, Event am AntProject feuern
					_project.firePropertyChange(PropertyChangeTypes.PROJECT_ITERATIONFINISHED, null, ++_iterationCounter);
				}
				AntController.this.stop();
			}

		};
		_algorithm.start();
	}



	/**
	 * Stoppt den Algorithmus, indem die Referenz auf den ausführenden Thread auf null gesetzt wird.
	 */
	public void stop() {
		_algorithm = null;

		for (AlgorithmListener listener : _listenerList) {
			listener.algorithmStopped();
		}
	}



	/**
	 * Gibt an, ob der Algorithmus gerade läuft.
	 * 
	 * @return true wenn der Algorithmus läuft, andernfalls false
	 */
	public boolean isRunning() {
		return _algorithm != null;
	}



	/**
	 * Fügt den angegebenen AlgorithmListener der ListenerList hinzu.
	 * 
	 * @param pListener
	 *            der hinzuzufügende AlgorithmListener
	 */
	public void addAlgorithmListener(AlgorithmListener pListener) {
		_listenerList.add(pListener);
	}



	/**
	 * Entfernt den angegebenen AlgorithmListener aus der ListenerList.
	 * 
	 * @param pListener
	 *            der zu entfernende AlgorithmListener
	 */
	public void removeAlgorithmListener(AlgorithmListener pListener) {
		_listenerList.remove(pListener);
	}

}
