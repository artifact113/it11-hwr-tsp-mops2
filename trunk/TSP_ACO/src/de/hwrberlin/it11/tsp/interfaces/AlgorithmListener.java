/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.interfaces;

/**
 * Dieser Listener �berwacht den Status des Algorithmus.
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

}
