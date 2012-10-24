/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.constant;

/**
 * Eine Auflistung der verschiedenen Iterationsmodi.
 * 
 * @author Patrick Szostack
 * 
 */
public enum IterationMode {
	/** Es wird eine bestimmte Anzahl mal iteriert */
	COUNT,
	/** Es wird iteriert, bis eine Länge unterschritten wurde */
	LENGTH,
	/** Es wird iteriert, bis eine angegebene Lösung gefunden wurde */
	SOLUTION

}
