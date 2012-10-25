/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.gui.updatestrategy;

import java.text.ParseException;

import org.eclipse.core.databinding.UpdateValueStrategy;

import de.hwrberlin.it11.tsp.constant.Utility;

/**
 * UpdateValueStrategy, die einen String in einen int konvertiert.
 * 
 * @author Patrick Szostack
 * 
 */
public class StringToIntegerUpdateStrategy extends UpdateValueStrategy {

	/** Die einzige Instanz dieser Klasse */
	private static StringToIntegerUpdateStrategy _instance;



	/**
	 * �berschreibt den public default Konstruktor. Es soll nur eine Instanz dieser Klasse geben, die �ber die Methode getInstance() referenzierbar
	 * ist.
	 */
	private StringToIntegerUpdateStrategy() {
	}



	/**
	 * Gibt die einzige Instanz dieser Klasse zur�ck.
	 * 
	 * @return die einzige Instanz dieser Klasse
	 */
	public static StringToIntegerUpdateStrategy getInstance() {
		return _instance == null ? new StringToIntegerUpdateStrategy() : _instance;
	}



	@Override
	public Object convert(Object pValue) {
		try {
			return Utility.FORMAT.parse((String) pValue).intValue();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
