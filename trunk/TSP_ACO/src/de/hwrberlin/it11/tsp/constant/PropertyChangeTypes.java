/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.constant;

/**
 * Diese Klasse hält nur Konstanten für den PropertyChangeSupport.
 * 
 * @author Patrick Szostack
 * 
 */
public class PropertyChangeTypes {

	// Project

	public static final String PROJECT_ITERATIONFINISHED = "iterationFinished";

	public static final String PROJECT_PARAMETER = "parameter";

	public static final String PROJECT_NODELIST = "nodeList";

	public static final String PROJECT_NODELIST_ADD = "nodeList_add";

	public static final String PROJECT_NODELIST_REMOVE = "nodeList_remove";

	// Parameter

	public static final String PARAMETER_ANTCOUNT = "antCount";

	public static final String PARAMETER_ITERATIONCOUNT = "iterationCount";

	public static final String PARAMETER_PHEROMONPARAMETER = "pheromonParameter";

	public static final String PARAMETER_LOCALINFORMATION = "localInformation";

	public static final String PARAMETER_EVAPORATIONPARAMETER = "evaporationParameter";

	public static final String PARAMETER_INITIALPHEROMONPARAMETER = "initialPheromonParameter";

	public static final String PARAMETER_PHEROMONUPDATEPARAMETER = "pheromonUpdateParameter";

	public static final String PARAMETER_MAXIMUMTOURLENGTH = "maximumTourLength";

	public static final String PARAMETER_ZOOMFACTOR = "zoomFactor";

	// Result

	public static final String RESULT_BESTTOURLENGTHGLOBAL = "bestTourLengthGlobal";

	public static final String RESULT_BESTTOURLENGTHITERATION = "bestTourLengthIteration";

	public static final String RESULT_AVERAGETOURLENGTHGLOBAL = "averageTourLengthGlobal";

	public static final String RESULT_AVERAGETOURLENGTHITERATION = "averageTourLengthIteration";

	public static final String RESULT_BESTTOURGLOBAL = "bestTourGlobal";

	public static final String RESULT_BESTTOURITERATION = "bestTourIteration";

	public static final String RESULT_ELAPSEDTIME = "elapsedTime";

	// Node

	public static final String NODE_XCOORDINATE = "xCoordinate";

	public static final String NODE_YCOORDINATE = "yCoordinate";

	// Edge

	public static final String EDGE_PHEROMONE = "pheromone";

}
