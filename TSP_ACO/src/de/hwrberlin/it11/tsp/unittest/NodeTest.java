/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.unittest;

import de.hwrberlin.it11.tsp.model.Node;
import de.hwrberlin.it11.tsp.model.TSPData;
import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * JUnitTest um Operationen auf Nodes zu �berpr�fen.
 * 
 */
public class NodeTest extends TestCase {

	public NodeTest(String pName) {
		super(pName);
	}



	/**
	 * Testet das Hinzuf�gen einer Node zum Projekt.
	 */
	public void testNodeAdd() {
		TSPData data = new TSPData();
		int count = data.getNodeList().size();
		data.addNode(new Node(0, 0));
		assertTrue(count + 1 == data.getNodeList().size());
	}



	/**
	 * Testet das L�schen einer Node aus dem Projekt.
	 */
	public void testNodeRemove() {
		TSPData data = new TSPData();
		Node node = new Node(0, 0);
		data.addNode(node);
		int count = data.getNodeList().size();
		data.removeNode(node);
		assertTrue(count - 1 == data.getNodeList().size());
	}



	/**
	 * Testet das Bewegen einer Node.
	 */
	public void testNodeMove() {
		TSPData data = new TSPData();
		Node node = new Node(100, 100);
		data.addNode(node);
		node.setxCoordinate(150);
		node.setyCoordinate(30);
		assertTrue(node.getxCoordinate() == 150);
		assertTrue(node.getyCoordinate() == 30);
	}



	public static void main(String[] args) {
		TestRunner.run(NodeTest.class);
	}

}
