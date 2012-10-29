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
 * Testet den Persister auf seine Persistenzmethoden.
 */
public class PersisterTest extends TestCase {

	public PersisterTest(String pName) {
		super(pName);
	}



	/**
	 * Testet das Laden von .tsp Dateien.
	 */
	public void testTSPData() {
		TSPData data = Persister.loadTSPFile(new File("C:\\Ameise\\tsp\\berlin52.tsp"));
		assertNotNull(data);
		assertNotNull(data.getName());
		assertNotNull(data.getType());
		assertNotNull(data.getComment());
		assertNotNull(data.getEdgeWeightType());
		assertNotNull(data.getNodeList());
	}



	public static void main(String[] args) {
		TestRunner.run(PersisterTest.class);
	}

}
