/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.model.Node;
import de.hwrberlin.it11.tsp.model.Parameter;
import de.hwrberlin.it11.tsp.model.TSPData;

/**
 * Diese Klasse stellt Methoden zum Öffnen und Speichern von Dateien bereit.
 * 
 * @author Patrick Szostack
 * 
 */
public class Persister {

	/**
	 * Lädt eine .tsp Datei.
	 * 
	 * @param pFile
	 *            die File-Instanz der zu ladenden Datei
	 * @return eine TSPData Instanz mit Werten, so wie sie in der Datei angegeben waren
	 * @throws IllegalArgumentException
	 *             wenn die angegebene Datei nicht der .tsp Norm entspricht
	 */
	public static TSPData loadTSPFile(File pFile) throws IllegalArgumentException {
		if (!pFile.exists()) {
			throw new IllegalArgumentException("Die angegebene Datei existiert nicht.");
		}

		TSPData data = new TSPData();
		BufferedReader reader = null;
		try {
			List<Node> nodeList = new ArrayList<Node>();
			reader = new BufferedReader(new FileReader(pFile));
			String name = null;
			String type = null;
			String comment = null;
			String edgeWeightType = null;
			String line = "";
			boolean nodeSection = false;
			while (!"EOF".equals(line = reader.readLine())) {
				try {
					line = line.trim();
					if (line.contains("NAME:")) {
						name = line.split(":")[1].trim();
					}
					if (line.contains("TYPE:")) {
						type = line.split(":")[1].trim();
					}
					if (line.contains("COMMENT:")) {
						comment = line.split(":")[1].trim();
					}
					if (line.contains("EDGE_WEIGHT_TYPE:")) {
						edgeWeightType = line.split(":")[1].trim();
					}
					if (nodeSection) {
						String[] nodeData = line.split("\\s+");
						Node node = new Node((int) Double.parseDouble(nodeData[1]), (int) Double.parseDouble(nodeData[2]));
						nodeList.add(node);
					}
					if (line.contains("NODE_COORD_SECTION")) {
						nodeSection = true;
					}
				}
				catch (ArrayIndexOutOfBoundsException pEx) {
					throw new IllegalArgumentException("Die angegebene Datei entspricht nicht der Norm. Die Norm können Sie in der Hilfe nachlesen.");
				}
			}
			if (name == null || type == null || comment == null || edgeWeightType == null) {
				throw new IllegalArgumentException("Die angegebene Datei entspricht nicht der Norm. Die Norm können Sie in der Hilfe nachlesen.");
			}
			data.setName(name);
			data.setType(type);
			data.setComment(comment);
			data.setEdgeWeightType(edgeWeightType);
			data.setNodeList(nodeList);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}



	/**
	 * Speichert eine .tsp Datei an den Ort, der in der Parameter-File spezifiziert ist.
	 * 
	 * @param pFile
	 *            die File der zu speichernden Datei
	 * @param pData
	 *            die zu speichernden TSP Daten
	 */
	public static void saveTSPFile(File pFile, TSPData pData) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(pFile));
			writer.write("NAME: " + pData.getName() + "\r\n");
			writer.write("TYPE: " + pData.getType() + "\r\n");
			writer.write("COMMENT: " + pData.getComment() + "\r\n");
			writer.write("DIMENSION: " + pData.getNodeList().size() + "\r\n");
			writer.write("EDGE_WEIGHT_TYPE: " + pData.getEdgeWeightType() + "\r\n");
			writer.write("NODE_COORD_SECTION\r\n");
			for (int i = 0; i < pData.getNodeList().size(); i++) {
				Node node = pData.getNodeList().get(i);
				writer.write((i + 1) + " " + (double) node.getxCoordinate() + " " + (double) node.getyCoordinate() + "\r\n");
			}
			writer.write("EOF");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}



	/**
	 * Lädt eine Konfigurationsdatei.
	 * 
	 * @param pFile
	 *            die File-Instanz der zu ladenden Datei
	 * @return eine Parameter-Instanz mit Werten, wie sie in der Datei gespeichert waren
	 * @throws IllegalArgumentException
	 *             wenn die Datei nicht der .tspconfig Norm entspricht
	 */
	public static Parameter loadParameterFile(File pFile) throws IllegalArgumentException {
		if (!pFile.exists()) {
			throw new IllegalArgumentException("Die angegebene Datei existiert nicht.");
		}

		Parameter parameter = new Parameter();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(pFile));
			Properties properties = new Properties();
			properties.load(reader);

			parameter.setAntCount(Integer.valueOf(properties.getProperty(PropertyChangeTypes.PARAMETER_ANTCOUNT)));
			parameter.setIterationCount(Integer.valueOf(properties.getProperty(PropertyChangeTypes.PARAMETER_ITERATIONCOUNT)));
			parameter.setEvaporationParameter(Double.valueOf(properties.getProperty(PropertyChangeTypes.PARAMETER_EVAPORATIONPARAMETER)));
			parameter.setInitialPheromonParameter(Double.valueOf(properties.getProperty(PropertyChangeTypes.PARAMETER_INITIALPHEROMONPARAMETER)));
			parameter.setLocalInformation(Double.valueOf(properties.getProperty(PropertyChangeTypes.PARAMETER_LOCALINFORMATION)));
			parameter.setMaximumTourLength(Double.valueOf(properties.getProperty(PropertyChangeTypes.PARAMETER_MAXIMUMTOURLENGTH)));
			parameter.setPheromonParameter(Double.valueOf(properties.getProperty(PropertyChangeTypes.PARAMETER_PHEROMONPARAMETER)));
			parameter.setPheromonUpdateParameter(Double.valueOf(properties.getProperty(PropertyChangeTypes.PARAMETER_PHEROMONUPDATEPARAMETER)));
			parameter.setZoomFactor(Double.valueOf(properties.getProperty(PropertyChangeTypes.PARAMETER_ZOOMFACTOR)));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return parameter;
	}



	/**
	 * Speichert eine Konfigurationsdatei an den Ort, der in der Parameter-File spezifiziert ist.
	 * 
	 * @param pFile
	 *            die File der zu speichernden Datei
	 * @param pParameter
	 *            die zu speichernden Parameter
	 */
	public static void saveParameterFile(File pFile, Parameter pParameter) {
		Properties properties = new Properties();
		properties.setProperty(PropertyChangeTypes.PARAMETER_ANTCOUNT, String.valueOf(pParameter.getAntCount()));
		properties.setProperty(PropertyChangeTypes.PARAMETER_ITERATIONCOUNT, String.valueOf(pParameter.getIterationCount()));
		properties.setProperty(PropertyChangeTypes.PARAMETER_EVAPORATIONPARAMETER, String.valueOf(pParameter.getEvaporationParameter()));
		properties.setProperty(PropertyChangeTypes.PARAMETER_INITIALPHEROMONPARAMETER, String.valueOf(pParameter.getInitialPheromonParameter()));
		properties.setProperty(PropertyChangeTypes.PARAMETER_LOCALINFORMATION, String.valueOf(pParameter.getLocalInformation()));
		properties.setProperty(PropertyChangeTypes.PARAMETER_MAXIMUMTOURLENGTH, String.valueOf(pParameter.getMaximumTourLength()));
		properties.setProperty(PropertyChangeTypes.PARAMETER_PHEROMONPARAMETER, String.valueOf(pParameter.getPheromonParameter()));
		properties.setProperty(PropertyChangeTypes.PARAMETER_PHEROMONUPDATEPARAMETER, String.valueOf(pParameter.getPheromonUpdateParameter()));
		properties.setProperty(PropertyChangeTypes.PARAMETER_ZOOMFACTOR, String.valueOf(pParameter.getZoomFactor()));

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(pFile));
			properties.store(writer, null);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}



	/**
	 * Liest eine .opttour Datei ein und gibt eine Liste an Indeces zurück. Jeder Index beschreibt dabei die Position einer Node in der Liste der
	 * TSPData-Instanz. Die Reihenfolge der Indeces beschreibt somit die Reihenfolge der Nodes in der optimalen Tour.
	 * <p>
	 * ACHTUNG: Auf diese Liste darf keine strukturverändernde Methode (sort(), add(), remove()) aufgerufen werden!
	 * 
	 * @param pFile
	 *            die File Instanz der .opttour Datei
	 * @return eine Liste an Indeces, in der Reihenfolge der besten Tour
	 * @throws IllegalArgumentException
	 *             wenn die Datei nicht der .opt.tour Norm entspricht
	 */
	public static List<Integer> loadOptTourFile(File pFile) throws IllegalArgumentException {
		if (!pFile.exists()) {
			throw new IllegalArgumentException("Die angegebene Datei existiert nicht.");
		}

		List<Integer> indexList = new ArrayList<Integer>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(pFile));
			String line = "";
			boolean nodeSection = false;
			while (!"EOF".equals(line = reader.readLine())) {
				line = line.trim();
				if (nodeSection) {
					int index = Integer.valueOf(line);
					if (index != -1) {
						indexList.add(index);
					}
				}
				if (line.contains("TOUR_SECTION")) {
					nodeSection = true;
				}
			}
			indexList.add(indexList.get(0));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return indexList;
	}
}
