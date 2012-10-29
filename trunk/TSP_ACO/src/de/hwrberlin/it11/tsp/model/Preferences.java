/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.model;

import org.eclipse.swt.graphics.Color;

import de.hwrberlin.it11.tsp.constant.Colors;
import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;

/**
 * Die Preferences-Klasse dient zum Speichern der eingestellten Eigenschaften. Eigenschaften gelten für das ganze Programm, nicht nur für ein Projekt,
 * deshalb gibt es nur eine einzige Instanz.
 */
public class Preferences extends APropertyChangeSupport {

	/** Die einzige Instanz dieser Klasse */
	private static Preferences _instance;

	/** Indiziert das Anwenden von Antialiasing */
	private boolean _antialias;

	/** Gibt an, nach wie vielen Iterationen ein Redraw stattfinden soll */
	private int _redrawInterval;

	/** Farbe, mit der nicht ausgewählte Nodes gezeichnet werden sollen */
	private Color _nodeColor;

	/** Farbe, mit der ausgewählte Nodes gezeichnet werden sollen */
	private Color _currentNodeColor;

	/** Farbe, mit der die Strecke der besten globalen Tour gezeichnet werden soll */
	private Color _bestTourGlobalColor;

	/** Farbe, mit der die Strecke der besten Tour der Iteration gezeichnet werden soll */
	private Color _bestTourIterationColor;

	/** Hintergrundfarbe der Malfläche */
	private Color _backgroundColor;



	/**
	 * Erstellt eine neue Preferences-Instanz mit default-Werten.
	 */
	private Preferences() {
		_antialias = false;
		_redrawInterval = 1;
		_nodeColor = Colors.RED;
		_currentNodeColor = Colors.GREEN;
		_bestTourGlobalColor = Colors.BLUE;
		_bestTourIterationColor = Colors.DARK_GREY;
		_backgroundColor = Colors.WHITE;
	}



	/**
	 * Gibt die einzig existierende Instanz dieser Klasse zurück.
	 * 
	 * @return
	 */
	public static Preferences getInstance() {
		return _instance == null ? _instance = new Preferences() : _instance;
	}



	/**
	 * @return
	 */
	public boolean isAntialias() {
		return _antialias;
	}



	/**
	 * @param pAntialias
	 */
	public void setAntialias(boolean pAntialias) {
		firePropertyChange(PropertyChangeTypes.PREFERENCES_ANTIALIAS, _antialias, _antialias = pAntialias);
	}



	/**
	 * @return
	 */
	public int getRedrawInterval() {
		return _redrawInterval;
	}



	/**
	 * @param pRedrawInterval
	 */
	public void setRedrawInterval(int pRedrawInterval) {
		firePropertyChange(PropertyChangeTypes.PREFERENCES_REDRAWINTERVAL, _redrawInterval, _redrawInterval = pRedrawInterval);
	}



	/**
	 * @return
	 */
	public Color getNodeColor() {
		return _nodeColor;
	}



	/**
	 * @param pNodeColor
	 */
	public void setNodeColor(Color pNodeColor) {
		firePropertyChange(PropertyChangeTypes.PREFERENCES_NODECOLOR, _nodeColor, _nodeColor = pNodeColor);
	}



	/**
	 * @return
	 */
	public Color getCurrentNodeColor() {
		return _currentNodeColor;
	}



	/**
	 * @param pCurrentNodeColor
	 */
	public void setCurrentNodeColor(Color pCurrentNodeColor) {
		firePropertyChange(PropertyChangeTypes.PREFERENCES_CURRENTNODECOLOR, _currentNodeColor, _currentNodeColor = pCurrentNodeColor);
	}



	/**
	 * @return
	 */
	public Color getBestTourGlobalColor() {
		return _bestTourGlobalColor;
	}



	/**
	 * @param pBestTourGlobalColor
	 */
	public void setBestTourGlobalColor(Color pBestTourGlobalColor) {
		firePropertyChange(PropertyChangeTypes.PREFERENCES_BESTTOURGLOBALCOLOR, _bestTourGlobalColor, _bestTourGlobalColor = pBestTourGlobalColor);
	}



	/**
	 * @return
	 */
	public Color getBestTourIterationColor() {
		return _bestTourIterationColor;
	}



	/**
	 * @param pBestTourIterationColor
	 */
	public void setBestTourIterationColor(Color pBestTourIterationColor) {
		firePropertyChange(PropertyChangeTypes.PREFERENCES_BESTTOURITERATIONCOLOR, _bestTourIterationColor,
				_bestTourIterationColor = pBestTourIterationColor);
	}



	/**
	 * @return
	 */
	public Color getBackgroundColor() {
		return _backgroundColor;
	}



	/**
	 * @param pBackgroundColor
	 */
	public void setBackgroundColor(Color pBackgroundColor) {
		firePropertyChange(PropertyChangeTypes.PREFERENCES_BACKGROUNDCOLOR, _backgroundColor, _backgroundColor = pBackgroundColor);
	}

}
