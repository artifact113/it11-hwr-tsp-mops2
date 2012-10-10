/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.constant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Enthält verschiedene Color als Konstanten.
 * 
 * @author Patrick Szostack
 * 
 */
public class Colors {

	/** Das default Display */
	private static Display _display = Display.getDefault();

	/** Weiße Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color WHITE = _display.getSystemColor(SWT.COLOR_WHITE);

	/** Rote Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color RED = _display.getSystemColor(SWT.COLOR_RED);

	/** Grüne Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color GREEN = _display.getSystemColor(SWT.COLOR_GREEN);

	/** Blaue Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color BLUE = _display.getSystemColor(SWT.COLOR_BLUE);

	/** Dunkelgraue Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color DARK_GREY = _display.getSystemColor(SWT.COLOR_DARK_GRAY);

	/** Farbverlauf für inaktive Titel. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color TITLE_INACTIVE_BACKGROUND_GRADIENT = _display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT);

	/** Farbe für Titelhintergründe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color TITLE_BACKGROUND = _display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND);

}
