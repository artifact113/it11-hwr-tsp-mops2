/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.unittest;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;
import junit.textui.TestRunner;
import de.hwrberlin.it11.tsp.model.Node;
import de.hwrberlin.it11.tsp.model.Parameter;
import de.hwrberlin.it11.tsp.model.TSPData;
import de.hwrberlin.it11.tsp.persistence.Persister;

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
	public void testWriteAndReadTSP() {
		File file = new File("tspTemp.tsp");
		TSPData data = new TSPData();
		data.addNode(new Node(12.5425235464, 52352.245252));
		data.addNode(new Node(535252.52352352, 25252523.52352352));

		Persister.saveTSPFile(file, data);

		TSPData otherData = Persister.loadTSPFile(file);

		assertEquals(data.getComment(), otherData.getComment());
		assertEquals(data.getEdgeWeightType(), otherData.getEdgeWeightType());
		assertEquals(data.getName(), otherData.getName());
		assertEquals(data.getType(), otherData.getType());
		for (int i = 0; i < data.getNodeList().size(); i++) {
			Node node = data.getNodeList().get(i);
			Node otherNode = otherData.getNodeList().get(i);
			assertEquals(node.getxCoordinate(), otherNode.getxCoordinate());
			assertEquals(node.getyCoordinate(), otherNode.getyCoordinate());
		}

		file.delete();
	}



	/**
	 * Testet das speichern und lesen von Konfigurationsdateien.
	 * 
	 * @throws IOException
	 */
	public void testWriteAndReadConfig() {
		File file = new File("configTemp.tspconfig");
		Parameter parameter = new Parameter();

		Persister.saveParameterFile(file, parameter);

		Parameter otherParameter = Persister.loadParameterFile(file);

		assertEquals(parameter.getAntCount(), otherParameter.getAntCount());
		assertEquals(parameter.getEvaporationParameter(), otherParameter.getEvaporationParameter());
		assertEquals(parameter.getInitialPheromonParameter(), otherParameter.getInitialPheromonParameter());
		assertEquals(parameter.getIterationCount(), otherParameter.getIterationCount());
		assertEquals(parameter.getLocalInformation(), otherParameter.getLocalInformation());
		assertEquals(parameter.getMaximumTourLength(), otherParameter.getMaximumTourLength());
		assertEquals(parameter.getPheromonParameter(), otherParameter.getPheromonParameter());
		assertEquals(parameter.getPheromonUpdateParameter(), otherParameter.getPheromonUpdateParameter());
		assertEquals(parameter.getZoomFactor(), otherParameter.getZoomFactor());

		file.delete();
	}



	public static void main(String[] args) {
		TestRunner.run(PersisterTest.class);
	}

}
