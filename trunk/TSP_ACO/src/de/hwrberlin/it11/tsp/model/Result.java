/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.model;

import java.util.ArrayList;
import java.util.List;

import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;

/**
 * Speichert die Ergebnisse des Algorithmus'.
 */
public class Result extends APropertyChangeSupport {

	/** Länge der kürzesten gefundenen Tour */
	private double _bestTourLengthGlobal;

	/** Länge der kürzesten gefundenen Tour der Iteration */
	private double _bestTourLengthIteration;

	/** Durchschnittliche Länge aller gefundenen kürzesten Touren */
	private double _averageTourLengthGlobal;

	/** Durchschnittliche Länge aller gefundenen Touren der Iteration */
	private double _averageTourLengthIteration;

	/** Liste der Knoten der zur Zeit kürzesten Tour */
	private List<Node> _bestTourGlobal;

	/** Liste der Knoten der zur Zeit kürzesten Tour der Iteration */
	private List<Node> _bestTourIteration;

	/** Vergangene Zeit seit Start des Algorithmus */
	private long _elapsedTime;



	/**
	 * Erzeugt eine neue Result-Instanz und initialisiert alle Werte.
	 */
	public Result() {
		init();
	}



	/**
	 * Initialisiert die Result-Instanz, indem alle Werte auf 0 gesetzt werden.
	 */
	public void init() {
		_bestTourLengthGlobal = 0;
		_bestTourLengthIteration = 0;
		_bestTourGlobal = new ArrayList<Node>();
		_bestTourIteration = new ArrayList<Node>();
		_elapsedTime = 0;
	}



	/**
	 * @return the bestTourLengthGlobal
	 */
	public double getBestTourLengthGlobal() {
		return _bestTourLengthGlobal;
	}



	/**
	 * @param pBestTourLengthGlobal
	 *            the bestTourLengthGlobal to set
	 */
	public void setBestTourLengthGlobal(double pBestTourLengthGlobal) {
		firePropertyChange(PropertyChangeTypes.RESULT_BESTTOURLENGTHGLOBAL, _bestTourLengthGlobal, _bestTourLengthGlobal = pBestTourLengthGlobal);
	}



	/**
	 * @return the bestTourLengthIteration
	 */
	public double getBestTourLengthIteration() {
		return _bestTourLengthIteration;
	}



	/**
	 * @param pBestTourLengthIteration
	 *            the bestTourLengthIteration to set
	 */
	public void setBestTourLengthIteration(double pBestTourLengthIteration) {
		firePropertyChange(PropertyChangeTypes.RESULT_BESTTOURLENGTHITERATION, _bestTourLengthIteration,
				_bestTourLengthIteration = pBestTourLengthIteration);
	}



	/**
	 * @return the averageTourLengthGlobal
	 */
	public double getAverageTourLengthGlobal() {
		return _averageTourLengthGlobal;
	}



	/**
	 * @param pAverageTourLengthGlobal
	 *            the averageTourLengthGlobal to set
	 */
	public void setAverageTourLengthGlobal(double pAverageTourLengthGlobal) {
		firePropertyChange(PropertyChangeTypes.RESULT_AVERAGETOURLENGTHGLOBAL, _averageTourLengthGlobal,
				_averageTourLengthGlobal = pAverageTourLengthGlobal);
	}



	/**
	 * @return the averageTourLengthIteration
	 */
	public double getAverageTourLengthIteration() {
		return _averageTourLengthIteration;
	}



	/**
	 * @param pAverageTourLengthIteration
	 *            the averageTourLengthIteration to set
	 */
	public void setAverageTourLengthIteration(double pAverageTourLengthIteration) {
		firePropertyChange(PropertyChangeTypes.RESULT_AVERAGETOURLENGTHITERATION, _averageTourLengthIteration,
				_averageTourLengthIteration = pAverageTourLengthIteration);
	}



	/**
	 * @return the bestTourGlobal
	 */
	public List<Node> getBestTourGlobal() {
		return _bestTourGlobal;
	}



	/**
	 * @param pBestTourGlobal
	 *            the bestTourGlobal to set
	 */
	public void setBestTourGlobal(List<Node> pBestTourGlobal) {
		firePropertyChange(PropertyChangeTypes.RESULT_BESTTOURGLOBAL, _bestTourGlobal, _bestTourGlobal = pBestTourGlobal);
	}



	/**
	 * @return the bestTourIteration
	 */
	public List<Node> getBestTourIteration() {
		return _bestTourIteration;
	}



	/**
	 * @param pBestTourIteration
	 *            the bestTourIteration to set
	 */
	public void setBestTourIteration(List<Node> pBestTourIteration) {
		firePropertyChange(PropertyChangeTypes.RESULT_BESTTOURITERATION, _bestTourIteration, _bestTourIteration = pBestTourIteration);
	}



	/**
	 * @return the elapsedTime
	 */
	public long getElapsedTime() {
		return _elapsedTime;
	}



	/**
	 * @param pElapsedTime
	 *            the elapsedTime to set
	 */
	public void setElapsedTime(long pElapsedTime) {
		firePropertyChange(PropertyChangeTypes.RESULT_ELAPSEDTIME, _elapsedTime, _elapsedTime = pElapsedTime);
	}

}
