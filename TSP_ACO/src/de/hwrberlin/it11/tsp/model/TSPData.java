/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.model;

import java.util.List;

/**
 * 
 * 
 * @author Patrick Szostack
 * 
 */
public class TSPData extends APropertyChangeSupport {

	private String _name;

	private String _type;

	private String _comment;

	private String _edgeWeightType;

	private List<Node> _nodeList;



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
		_name = pName;
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
		_type = pType;
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
		_comment = pComment;
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
		_edgeWeightType = pEdgeWeightType;
	}



	/**
	 * @return the nodeList
	 */
	public List<Node> getNodeList() {
		return _nodeList;
	}



	/**
	 * @param pNodeList
	 *            the nodeList to set
	 */
	public void setNodeList(List<Node> pNodeList) {
		_nodeList = pNodeList;
	}

}
