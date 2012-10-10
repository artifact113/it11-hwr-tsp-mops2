/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.controller;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.it11.tsp.constant.IterationMode;
import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.model.AntProject;
import de.hwrberlin.it11.tsp.model.Node;
import de.hwrberlin.it11.tsp.model.Edge;
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

	private IterationMode _iterationMode;

	private int _iterationCounter;

	private volatile Thread _algorithm;



	public AntController(AntProject pProject) {
		_project = pProject;
		_iterationMode = IterationMode.COUNT;
	}



	public AntProject getProject() {
		return _project;
	}



	public void setIterationMode(IterationMode pMode) {
		_iterationMode = pMode;
	}



	public void init() {
		_project.resetEdges();
		_project.getResult().init();
		_iterationCounter = 0;
	}



	private boolean isConditionMet() {
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

		}
		return false;
	}



	public void start() {
		_algorithm = new Thread() {

			@Override
			public void run() {
				init();
				long startTime = System.currentTimeMillis();
				double bestTourLengthGlobal = -1;
				List<Double> averageLengthGlobalValues = new ArrayList<Double>();
				while (!isConditionMet()) {
					Thread currentThread = Thread.currentThread();
					if (currentThread != _algorithm) {
						return;
					}
					List<Ant> antList = new ArrayList<Ant>();
					for (int j = 0; j < _project.getParameter().getAntCount(); j++) {
						antList.add(new Ant(_project.getNodeList().get((int) (Math.random() * _project.getNodeList().size())), _project));
					}
					Result result = _project.getResult();
					double bestTourLengthIteration = -1;
					List<Double> averageLengthIterationValues = new ArrayList<Double>();
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
					for (Edge edge : _project.getEdgeList()) {
						edge.setPheromone((1 - _project.getParameter().getEvaporationParameter()) * edge.getPheromone());
					}
					for (Ant ant : antList) {
						for (int j = 0; j < ant.getVisitedNodes().size() - 1; j++) {
							Node node = ant.getVisitedNodes().get(j);
							Node otherNode = ant.getVisitedNodes().get(j + 1);
							Edge edge = node.getEdge(otherNode);
							edge.setPheromone(edge.getPheromone() + _project.getParameter().getPheromonUpdateParameter() / ant.getTravelledDistance());
						}
					}
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
					_project.firePropertyChange(PropertyChangeTypes.PROJECT_ITERATIONFINISHED, null, ++_iterationCounter);
				}
				AntController.this.stop();
			}

		};
		_algorithm.start();
	}



	public void stop() {
		_algorithm = null;
	}

}
