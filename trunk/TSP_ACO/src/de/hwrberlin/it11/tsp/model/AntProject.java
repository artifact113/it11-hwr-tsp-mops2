/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.model;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;

/**
 * Das AntProject ist die Hauptklasse des Datenmodells. Hier werden alle Parameter, Ergebnisse und Daten zum TSP Problem gespeichert.
 * 
 * @author Patrick Szostack
 * 
 */
public class AntProject extends APropertyChangeSupport {

	/** Die momentan beutzten Parameter */
	private Parameter _parameter;

	/** Die momentan benutzten TSP Daten */
	private TSPData _tspData;

	/** Die errechneten Touren */
	private Result _result;

	/** Die Liste aller Nodes */
	private List<Node> _nodeList;

	/** Die Liste aller Edges */
	private List<Edge> _edgeList;

	/** Ein kleiner Statustext, der am unteren Bildschirmrand dargestellt wird */
	private String _statusText;

	/** Die Indeces der optimalen Tour */
	private List<Integer> _optimalTourIndeces;



	/**
	 * Erzeugt ein neues AntProject und initialisiert es mit neuen Parametern, einem neuen Ergebnis und leeren Node- und Edgelisten.
	 */
	public AntProject() {
		_parameter = new Parameter();
		_tspData = new TSPData();
		_result = new Result();
		_nodeList = new ArrayList<Node>();
		_edgeList = new ArrayList<Edge>();
	}



	/**
	 * Gibt die Parameter zurück.
	 * 
	 * @return the parameter
	 */
	public Parameter getParameter() {
		return _parameter;
	}



	/**
	 * Setzt den angegebenen Parameter. Alle PropertyChangeListener, die am alten Parameter registriert waren, werden entfernt. Anschließend wir ein
	 * PropertyChangeEvent gefeuert.
	 * 
	 * @param pParameter
	 *            the parameter to set
	 */
	public void setParameter(Parameter pParameter) {
		firePropertyChange(PropertyChangeTypes.PROJECT_PARAMETER, _parameter, _parameter = pParameter);
	}



	/**
	 * Gibt die TSP Daten zurück.
	 * 
	 * @return the data
	 */
	public TSPData getTSPData() {
		return _tspData;
	}



	/**
	 * Setzt die TSP Daten auf die angegebenen TSP Daten.
	 * 
	 * @param pTSPData
	 *            the data to set
	 */
	public void setTSPData(TSPData pTSPData) {
		firePropertyChange(PropertyChangeTypes.PROJECT_TSPDATA, _tspData, _tspData = pTSPData);
		setNodeList(pTSPData.getNodeList());
	}



	/**
	 * Gibt das Result zurück.
	 * 
	 * @return the result
	 */
	public Result getResult() {
		return _result;
	}



	/**
	 * Gibt die Liste der Nodes zurück.
	 * <p>
	 * ACHTUNG: Auf diese Liste dürfen keine Methoden aufgerufen werden, die dessen Struktur verändern (add, remove, usw.). Hierzu sollen die addNode
	 * und removeNode Methoden des AntProject benutzt werden.
	 * 
	 * @return the nodeList
	 */
	public List<Node> getNodeList() {
		return _nodeList;
	}



	/**
	 * Setzt die NodeList auf die angegebene NodeList und löst ein PropertyChangeEvent aus. Ein Setzen der NodeList führt zum Initialisieren der
	 * Egdes.
	 * 
	 * @param pNodeList
	 *            the nodeList to set
	 * @see AntProject#initEdges()
	 */
	public void setNodeList(List<Node> pNodeList) {
		firePropertyChange(PropertyChangeTypes.PROJECT_NODELIST, _nodeList, _nodeList = pNodeList);
		// _tspData.setNodeList(_nodeList);
		initEdges();
	}



	/**
	 * Fügt die angegebene Node zu der nodeList hinzu und feuert anschließend ein PropertyChangeEvent.
	 * 
	 * @param pNode
	 */
	public void addNode(Node pNode) {
		_nodeList.add(pNode);
		firePropertyChange(PropertyChangeTypes.PROJECT_NODELIST_ADD, null, _nodeList);
	}



	/**
	 * Entfernt die angegebene Node aus der nodeList, entfernt alle PropertyChangeListenerder Node und feuert anschließend ein PropertyChangeEvent.
	 * 
	 * @param pNode
	 */
	public void removeNode(Node pNode) {
		_nodeList.remove(pNode);
		firePropertyChange(PropertyChangeTypes.PROJECT_NODELIST_ADD, null, _nodeList);
	}



	/**
	 * Gibt die Edgeliste zurück.
	 * <p>
	 * ACHTUNG: Auf diese Liste dürfen keine Methoden aufgerufen werden, die dessen Struktur verändern (add, remove, usw.).
	 * 
	 * @return the edgeList
	 */
	public List<Edge> getEdgeList() {
		return _edgeList;
	}



	/**
	 * Gibt den Statustext zurück.
	 * 
	 * @return the statusText
	 */
	public String getStatusText() {
		return _statusText;
	}



	/**
	 * Setzt den Statustext auf den angegebenen Statustext.
	 * 
	 * @param pStatusText
	 *            the statusText to set
	 */
	public void setStatusText(String pStatusText) {
		firePropertyChange(PropertyChangeTypes.PROJECT_STATUSTEXT, _statusText, _statusText = pStatusText);
	}



	/**
	 * Gibt die Indeces der optimalen Tour zurück.
	 * 
	 * @return the optimalTourLength
	 */
	public List<Integer> getOptimalTourIndeces() {
		return _optimalTourIndeces;
	}



	/**
	 * Setzt die Indeces der optimalen Tour auf die angegebenen Indeces.
	 * 
	 * @param pOptimalTourIndeces
	 *            the optimalTourLength to set
	 */
	public void setOptimalTourIndeces(List<Integer> pOptimalTourIndeces) {
		firePropertyChange(PropertyChangeTypes.PROJECT_OPTIMALTOURLENGTH, _optimalTourIndeces, _optimalTourIndeces = pOptimalTourIndeces);
	}



	/**
	 * Gibt eine unbenutzte (das heißt eine um 1 größere Nummer als die größte ID-Nummer in der NodeList) ID-Nummer wieder.
	 * 
	 * @return eine unbenutzte ID-Nummer
	 */
	// public int getUnusedNodeID() {
	// int id = 0;
	// for (Node node : _nodeList) {
	// if (node.getId() > id) {
	// id = node.getId();
	// }
	// }
	// return id + 1;
	// }

	/**
	 * Ermittelt anhand der angegebenen X und Y Koordinaten eine Node. Dabei wird eine Toleranz von 5 Pixeln gewährt.
	 * 
	 * @param pX
	 *            die X Koordinate
	 * @param pY
	 *            die Y Koordinate
	 * @return eine Node, wenn sie in der Nähe der angegebenen Koordinaten liegt, andernfalls null.
	 */
	public Node getNodeForCoordinates(int pX, int pY) {
		for (Node node : _nodeList) {
			if ((pX > node.getxCoordinate() - 5 && pX < node.getxCoordinate() + 5)
					&& (pY > node.getyCoordinate() - 5 && pY < node.getyCoordinate() + 5)) {
				return node;
			}
		}
		return null;
	}



	/**
	 * Initialisiert alle Kanten. Das umfasst das Erzeugen der Kanten, das Setzen des initialen Pheromonwertes und das registrieren der Kanten in den
	 * Maps der Nodes
	 */
	public void initEdges() {
		_edgeList.clear();
		for (int i = 0; i < _nodeList.size(); i++) {
			Node node = _nodeList.get(i);
			for (int j = i; j < _nodeList.size(); j++) {
				Node otherNode = _nodeList.get(j);
				if (node != otherNode) {
					Edge edge = new Edge(_parameter.getInitialPheromonParameter());
					node.putEdge(otherNode, edge);
					otherNode.putEdge(node, edge);
					_edgeList.add(edge);
				}
			}
		}
	}



	/**
	 * Reinitialisiert die Kanten. Entfernt zunächste alle Kanten aus den Maps der Nodes und initialisiert sie erneut.
	 * 
	 * @see AntProject#initEdges()
	 */
	public void resetEdges() {
		for (Node node : _nodeList) {
			node.clearEdges();
		}
		initEdges();
	}

}
