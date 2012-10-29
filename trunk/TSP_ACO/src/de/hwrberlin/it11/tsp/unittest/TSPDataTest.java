/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.unittest;

import java.io.File;

import de.hwrberlin.it11.tsp.model.TSPData;
import de.hwrberlin.it11.tsp.persistence.Persister;
import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * 
 * 
 * @author Patrick Szostack
 * 
 */
public class TSPDataTest extends TestCase {

	public TSPDataTest(String pName) {
		super(pName);
	}



	public void testTSPData() {
		TSPData data = Persister.loadTSPFile(new File("C:\\Users\\Patrick\\Desktop\\berlin52.tsp"));
		assertNotNull(data);
		assertNotNull(data.getName());
		assertNotNull(data.getType());
		assertNotNull(data.getComment());
		assertNotNull(data.getEdgeWeightType());
		assertNotNull(data.getNodeList());
	}



	public static void main(String[] args) {
		TestRunner.run(TSPDataTest.class);
	}

}
