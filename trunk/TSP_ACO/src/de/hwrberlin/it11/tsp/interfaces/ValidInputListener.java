/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.interfaces;

/**
 * Dieser Listener wartet auf �nderungen des Validierungszustandes eines AntText.
 * 
 * @author Patrick Szostack
 * 
 */
public interface ValidInputListener {

	/**
	 * Der Validierungszustand hat sich ge�ndert.
	 * 
	 * @param pValid
	 *            der neue Validierzngszustand
	 */
	public void validStatusChanged(boolean pValid);

}
