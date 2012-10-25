/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.interfaces;

/**
 * Dieser Listener wird benachrichtig, wenn sich der Gesamtvalidierungszustand einer Menge von AntText ändert.
 * 
 * @author Patrick Szostack
 * 
 */
public interface AllInputValidListener {

	/**
	 * Der Gesamtvalidierungszustand hat sich geändert.
	 * 
	 * @param pValid
	 *            der neue Gesamtvalidierungszustand
	 */
	public void isValid(boolean pValid);

}
