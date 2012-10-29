/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.unittest;

import de.hwrberlin.it11.tsp.model.Node;
import de.hwrberlin.it11.tsp.model.TSPData;
import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * 
 * 
 * @author Patrick Szostack
 * 
 */
public class NodeAddTest extends TestCase {

	public NodeAddTest(String pName) {
		super(pName);
	}



	public void testNodeAdd() {
		TSPData data = new TSPData();
		int count = data.getNodeList().size();
		data.addNode(new Node(0, 0));
		assertTrue(count + 1 == data.getNodeList().size());
	}



	public static void main(String[] args) {
		TestRunner.run(NodeAddTest.class);
	}

}
