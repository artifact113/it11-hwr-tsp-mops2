/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.interfaces;

/**
 * Dieser Listener wartet auf Änderungen des Validierungszustandes eines AntText.
 * 
 * @author Patrick Szostack
 * 
 */
public interface ValidInputListener {

	/**
	 * Der Validierungszustand hat sich geändert.
	 * 
	 * @param pValid
	 *            der neue Validierzngszustand
	 */
	public void validStatusChanged(boolean pValid);

}
