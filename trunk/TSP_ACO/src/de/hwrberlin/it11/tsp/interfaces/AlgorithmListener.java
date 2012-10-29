/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.interfaces;

import de.hwrberlin.it11.tsp.constant.IterationMode;

/**
 * Dieser Listener überwacht den Status des Algorithmus.
 * 
 * @author Patrick Szostack
 * 
 */
public interface AlgorithmListener {

	/**
	 * Der Algorithmus wurde soeben gestartet.
	 */
	public void algorithmStarted();



	/**
	 * Der Algorithmus wurde soeben gestoppt.
	 */
	public void algorithmStopped();



	/**
	 * Der Algorithmus wurde soeben pausiert.
	 */
	public void algorithmPaused();



	/**
	 * Der Algorithmus wurde soeben fortgesetzt.
	 */
	public void algorithmResumed();



	/**
	 * Der Iterationsmodus hat sich geändert.
	 * 
	 * @param pMode
	 *            der neue Iterationsmodus
	 */
	public void iterationModeChanged(IterationMode pMode);

}
