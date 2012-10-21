/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.constant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Enth�lt verschiedene Color als Konstanten.
 * 
 * @author Patrick Szostack
 * 
 */
public class Colors {

	/** Das default Display */
	private static Display _display = Display.getDefault();

	// SWT Basisfarben

	/** Schwarze Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color BLACK = _display.getSystemColor(SWT.COLOR_BLACK);

	/** Blaue Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color BLUE = _display.getSystemColor(SWT.COLOR_BLUE);

	/** Cyan Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color CYAN = _display.getSystemColor(SWT.COLOR_CYAN);

	/** Dunkelblaue Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color DARK_BLUE = _display.getSystemColor(SWT.COLOR_DARK_BLUE);

	/** Dunkelcyan Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color DARK_CYAN = _display.getSystemColor(SWT.COLOR_DARK_CYAN);

	/** Dunkelgraue Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color DARK_GREY = _display.getSystemColor(SWT.COLOR_DARK_GRAY);

	/** Dunkelgr�ne Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color DARK_GREEN = _display.getSystemColor(SWT.COLOR_DARK_GREEN);

	/** Dunkelmagenta Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color DARK_MAGENTA = _display.getSystemColor(SWT.COLOR_DARK_MAGENTA);

	/** Dunkelrote Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color DARK_RED = _display.getSystemColor(SWT.COLOR_DARK_RED);

	/** Dunkelgelbe Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color DARK_YELLOW = _display.getSystemColor(SWT.COLOR_DARK_YELLOW);

	/** Graue Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color GREY = _display.getSystemColor(SWT.COLOR_GRAY);

	/** Gr�ne Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color GREEN = _display.getSystemColor(SWT.COLOR_GREEN);

	/** Magenta Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color MAGENTA = _display.getSystemColor(SWT.COLOR_MAGENTA);

	/** Rote Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color RED = _display.getSystemColor(SWT.COLOR_RED);

	/** Wei�e Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color WHITE = _display.getSystemColor(SWT.COLOR_WHITE);

	/** Wei�e Farbe. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color YELLOW = _display.getSystemColor(SWT.COLOR_YELLOW);

	// SWT Komponentenfarben

	/** Farbverlauf f�r inaktive Titel. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color TITLE_INACTIVE_BACKGROUND_GRADIENT = _display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT);

	/** Farbe f�r Titelhintergr�nde. Dies ist eine SWT Systemfarbe, sie sollte nicht freigegeben werden (Aufruf der dispose()-Methode) */
	public static final Color TITLE_BACKGROUND = _display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND);

}
