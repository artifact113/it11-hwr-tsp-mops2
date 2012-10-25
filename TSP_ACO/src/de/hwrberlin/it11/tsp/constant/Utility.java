/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.constant;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 * Diese Klasse stellt einige Utitlity-Konstanten und -Methoden bereit.
 * 
 * @author Patrick Szostack
 * 
 */
public class Utility {

	/** Ein NumberFormat um mit Zahlen zu rechnen. Der Tausender-Separator ist abgeschaltet */
	public static final NumberFormat FORMAT = NumberFormat.getNumberInstance();

	static {
		FORMAT.setGroupingUsed(false);
	}



	/**
	 * Gibt das Zeichen zur�ck, das in Zahlen als Dezimaltrenner dient (abh�ngig vom momentan benutzten Locale).
	 * 
	 * @return das Dezimaltrennerzeichen
	 */
	public static char getDecimalSeparator() {
		return DecimalFormatSymbols.getInstance().getDecimalSeparator();
	}

}
