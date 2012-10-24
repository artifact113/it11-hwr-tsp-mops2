/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.controller;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.it11.tsp.model.AntProject;
import de.hwrberlin.it11.tsp.model.Node;

/**
 * Die Ameise l�uft alle Edges und Nodes im Netz der zu besuchenden Orte ab und ermittelt die k�rzeste Route.
 * 
 * @author Patrick Szostack
 * 
 */
public class Ant {

	/** Das Ameisenprojekt */
	private AntProject _project;

	/** Die L�nge des Weges, den diese Ameise gelaufen ist */
	private double _travelledDistance;

	/** Die Liste der bisher besuchten Orte */
	private List<Node> _visitedNodes;



	/**
	 * Erzeugt eine neue Ant mit der angegebenen Node als Start-Node.
	 * 
	 * @param pNode
	 *            die Node, auf die die Ant starten soll
	 * @param pProject
	 *            das Ameisenprojekt
	 */
	public Ant(Node pNode, AntProject pProject) {
		_visitedNodes = new ArrayList<Node>();
		_visitedNodes.add(pNode);
		_project = pProject;
	}



	/**
	 * Gibt die L�nge des Weges zur�ck, die die Ant bisher gelaufen ist.
	 * 
	 * @return die L�nge des Weges, die die Ant bisher gelaufen ist
	 */
	public double getTravelledDistance() {
		return _travelledDistance;
	}



	/**
	 * Gibt die Liste der Nodes zur�ck, die die Ant bisher abgegangen ist. Dabei steht an Index 0 die Node, auf der die Ameise startete. Danach sind
	 * die Nodes in der Reihenfolge hinzugef�gt, in der die Ant die Nodes besuchte.
	 * <p>
	 * ACHTUNG: Auf diese Liste d�rfen keine add, remove oder sort-Methoden aufgerufen werden.
	 * 
	 * @return die Liste der Nodes, die die Ant bisher abgegangen ist
	 */
	public List<Node> getVisitedNodes() {
		return _visitedNodes;
	}



	/**
	 * Gibt die Node zur�ck, in der sich die Ant momentan befindet (namentlich die letzte Node in der Liste der besuchten Nodes).
	 * 
	 * @return die Node, in der sich die Ant momentan befindet
	 */
	public Node getCurrentNode() {
		return _visitedNodes.get(_visitedNodes.size() - 1);
	}



	/**
	 * Ermittelt die Summe der Transitionswahrscheinlichkeiten der noch zu besuchenden Nodes.
	 * 
	 * @return die Summ der Transitionswahrscheinlichkeiten der noch zu besuchenden Nodes.
	 */
	public double getProbabilitySum() {
		double returnValue = 0;
		for (Node node : _project.getNodeList()) {
			if (!_visitedNodes.contains(node)) {
				double pheromone = Math.pow(getCurrentNode().getEdge(node).getPheromone(), _project.getParameter().getPheromonParameter());
				double local = Math.pow(1 / getCurrentNode().getDistanceToNode(node), _project.getParameter().getLocalInformation());
				returnValue += pheromone * local;
			}
		}
		return returnValue;
	}



	/**
	 * Ermittelt die Node, die als n�chstes besucht werden soll.
	 * 
	 * @return die Node, die als n�chstes besucht werden soll
	 */
	private Node evaluateNextNode() {
		if (_visitedNodes.size() == _project.getNodeList().size()) {
			return _visitedNodes.get(0); // Alle Nodes schon einmal besucht? Die Start-Node ist das n�chste Ziel
		}
		List<Node> nodesToVisit = new ArrayList<Node>();
		List<Double> probabilities = new ArrayList<Double>();
		double probabilitySum = 0;
		double dividerSum = getProbabilitySum();
		for (Node node : _project.getNodeList()) {
			if (!_visitedNodes.contains(node)) {
				double pheromone = Math.pow(getCurrentNode().getEdge(node).getPheromone(), _project.getParameter().getPheromonParameter());
				double local = Math.pow(1 / getCurrentNode().getDistanceToNode(node), _project.getParameter().getLocalInformation());
				double probability = (pheromone * local) / dividerSum;
				nodesToVisit.add(node);
				probabilities.add(probability);
				probabilitySum += probability;
			}
		}

		// Java bietet noch keinen Algorithmus f�r gewichtete Wahrscheinlichkeiten, also m�ssen wir uns selbst drum k�mmern
		int randomIndex = -1;
		double random = Math.random() * probabilitySum;
		for (int i = 0; i < probabilities.size(); ++i) {
			random -= probabilities.get(i);
			if (random <= 0) {
				randomIndex = i;
				break;
			}
		}
		return nodesToVisit.get(randomIndex);
	}



	/**
	 * L�sst diese Ant zu der angegebenen Node wandern. Dadurch wird die gelaufene Distanz um die Entfernung der beiden Nodes erh�ht und die neue Node
	 * der Liste der besuchten Nodes hinzugef�gt.
	 * 
	 * @param pNode
	 */
	private void travelTo(Node pNode) {
		_travelledDistance += getCurrentNode().getDistanceToNode(pNode);
		_visitedNodes.add(pNode);
	}



	/**
	 * Veranlasst die Berechnung der n�chsten Node und l�sst sie dort hinwandern.
	 */
	public void next() {
		travelTo(evaluateNextNode());
	}



	/**
	 * Gibt zur�ck, ob diese Ant mit ihrer Route fertig ist.
	 * 
	 * @return true, wenn diese Ant alle Nodes besucht hat und wieder an ihrer Start-Node steht, andernfalls false
	 */
	public boolean isFinished() {
		// return _visitedNodes.get(0) == _visitedNodes.get(_visitedNodes.size() - 1) && _visitedNodes.size() != 1;
		return _visitedNodes.size() == _project.getTSPData().getNodeList().size() + 1;
	}

}
