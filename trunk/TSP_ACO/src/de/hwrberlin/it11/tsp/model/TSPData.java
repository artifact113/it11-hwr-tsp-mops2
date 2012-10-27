/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.model;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;

/**
 * Diese Klasse speichert Daten, die in eine .tsp Datei gespeichert oder aus einer .tsp Datei gelesen werden.
 * 
 * @author Patrick Szostack
 * 
 */
public class TSPData extends APropertyChangeSupport {

	/** Name des TSP */
	private String _name;

	/** Typ des TSP (normalerweise "TSP", wird trotzdem extra zwischengespeichert, da es in der .tsp Datei vorkommt) */
	private String _type;

	/** Kommentar des TSP */
	private String _comment;

	/** Der EdgeWeightType des TSP */
	private String _edgeWeightType;

	/** Die NodeList des TSP */
	private List<Node> _nodeList;



	/**
	 * Erstellt eine neue TSPData-Instanz mit default Werten.
	 */
	public TSPData() {
		_name = "Neues Projekt";
		_type = "TSP";
		_comment = "Neu erstelltes Projekt";
		_edgeWeightType = "EUC_2D";
		_nodeList = new ArrayList<Node>();
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return _name;
	}



	/**
	 * @param pName
	 *            the name to set
	 */
	public void setName(String pName) {
		firePropertyChange(PropertyChangeTypes.TSPDATA_NAME, _name, _name = pName);
	}



	/**
	 * @return the type
	 */
	public String getType() {
		return _type;
	}



	/**
	 * @param pType
	 *            the type to set
	 */
	public void setType(String pType) {
		firePropertyChange(PropertyChangeTypes.TSPDATA_TYPE, _type, _type = pType);
	}



	/**
	 * @return the comment
	 */
	public String getComment() {
		return _comment;
	}



	/**
	 * @param pComment
	 *            the comment to set
	 */
	public void setComment(String pComment) {
		firePropertyChange(PropertyChangeTypes.TSPDATA_COMMENT, _comment, _comment = pComment);
	}



	/**
	 * @return the edgeWeightType
	 */
	public String getEdgeWeightType() {
		return _edgeWeightType;
	}



	/**
	 * @param pEdgeWeightType
	 *            the edgeWeightType to set
	 */
	public void setEdgeWeightType(String pEdgeWeightType) {
		firePropertyChange(PropertyChangeTypes.TSPDATA_EDGEWEIGHTTYPE, _edgeWeightType, _edgeWeightType = pEdgeWeightType);
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
	 * @param pNodeList
	 */
	public void setNodeList(List<Node> pNodeList) {
		firePropertyChange(PropertyChangeTypes.TSPDATA_NODELIST, _nodeList, _nodeList = pNodeList);
	}



	/**
	 * Fügt die angegebene Node der Liste an Nodes hinzu.
	 * 
	 * @param pNode
	 *            die hinzuzufügende Node
	 */
	public void addNode(Node pNode) {
		_nodeList.add(pNode);
		firePropertyChange(PropertyChangeTypes.TSPDATA_NODELIST_ADD, null, _nodeList);
	}



	/**
	 * Entfernt die angegebene Node aus der Liste der Nodes.
	 * 
	 * @param pNode
	 *            die zu entfernende Node
	 */
	public void removeNode(Node pNode) {
		_nodeList.remove(pNode);
		firePropertyChange(PropertyChangeTypes.TSPDATA_NODELIST_REMOVE, null, _nodeList);
	}

}
