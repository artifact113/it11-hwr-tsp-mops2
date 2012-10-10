/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.model;

import java.util.HashMap;
import java.util.Map;

import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;

/**
 * Die Node stellt einen Knoten in dem Netz der zu besuchenden Orte dar.
 * 
 * @author Patrick Szostack
 * 
 */
public class Node extends APropertyChangeSupport {

	/** Eine eindeutige ID-Nummer */
	private int _id;

	/** Die X Koordinate auf der Karte */
	private int _xCoordinate;

	/** Die Y Koordinate auf der Karte */
	private int _yCoordinate;

	/**
	 * Eine Map der Edges, die von diesem Node zu allen anderen Nodes verlaufen. Key ist dabei der Node, zu dem die Edge verläuft, Value ist die Edge
	 */
	private Map<Node, Edge> _edgeMap;



	/**
	 * Erzeugt einen neuen Node.
	 * 
	 * @param pId
	 *            eine eindeutige ID-Nummer
	 * @param pXCoordinate
	 *            die X Koordinate des Node
	 * @param pYCoordinate
	 *            die Y Koordinate des Node
	 * @see AntProject#getUnusedNodeID()
	 */
	public Node(int pId, int pXCoordinate, int pYCoordinate) {
		_id = pId;
		_xCoordinate = pXCoordinate;
		_yCoordinate = pYCoordinate;
		_edgeMap = new HashMap<Node, Edge>();
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return _id;
	}



	/**
	 * @return the xCoordinate
	 */
	public int getxCoordinate() {
		return _xCoordinate;
	}



	/**
	 * @param pXCoordinate
	 *            the xCoordinate to set
	 */
	public void setxCoordinate(int pXCoordinate) {
		firePropertyChange(PropertyChangeTypes.NODE_XCOORDINATE, _xCoordinate, _xCoordinate = pXCoordinate);
	}



	/**
	 * @return the yCoordinate
	 */
	public int getyCoordinate() {
		return _yCoordinate;
	}



	/**
	 * @param pYCoordinate
	 *            the yCoordinate to set
	 */
	public void setyCoordinate(int pYCoordinate) {
		firePropertyChange(PropertyChangeTypes.NODE_YCOORDINATE, _yCoordinate, _yCoordinate = pYCoordinate);
	}



	/**
	 * Fügt eine Edge der Map dieser Node hinzu. Die angegebene Node ist hierbei die Node, zu der die Edge von dieser Node aus verläuft (welche dann
	 * auch gleichzeitig Key in der Map ist), die angegebene Edge ist die Value in der Map.
	 * 
	 * @param pNode
	 *            die Node, zu der die Kante verläuft
	 * @param pEdge
	 *            die Edge, die hinzugefügt werden soll
	 */
	public void putEdge(Node pNode, Edge pEdge) {
		_edgeMap.put(pNode, pEdge);
	}



	/**
	 * Holt die Edge aus der Map dieser Node, die zu der angegebenen Node verläuft.
	 * 
	 * @param pNode
	 *            die Node, zu der die gesuchte Edge verläuft
	 * @return die Edge, die zu der angegebenen Node verläuft, oder null, falls für diese Node keine Edge exisitiert
	 */
	public Edge getEdge(Node pNode) {
		return _edgeMap.get(pNode);
	}



	/**
	 * Entfernt alle Edges aus der Map dieser Node.
	 */
	public void clearEdges() {
		_edgeMap.clear();
	}



	/**
	 * Ermittelt die Distant zwischen dieser Node und der angegebenen Node.
	 * 
	 * @param pNode
	 *            die Node, zu der die Distanz ermittelt werden soll
	 * @return die Distanz zu der angegebenen Node
	 */
	public double getDistanceToNode(Node pNode) {
		int dX = Math.abs(_xCoordinate - pNode.getxCoordinate());
		int dY = Math.abs(_yCoordinate - pNode.getyCoordinate());
		return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
	}

}
