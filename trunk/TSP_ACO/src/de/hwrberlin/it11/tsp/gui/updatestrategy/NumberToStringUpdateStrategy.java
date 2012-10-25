/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.updatestrategy;

import org.eclipse.core.databinding.UpdateValueStrategy;

import de.hwrberlin.it11.tsp.constant.Utility;

/**
 * UpdateValueStrategy, die eine Zahl in einen String konvertiert.
 * 
 * @author Patrick Szostack
 * 
 */
public class NumberToStringUpdateStrategy extends UpdateValueStrategy {

	/** Die einzige Instanz dieser Klasse */
	private static NumberToStringUpdateStrategy _instance;



	/**
	 * Überschreibt den public default Konstruktor. Es soll nur eine Instanz dieser Klasse geben, die über die Methode getInstance() referenzierbar
	 * ist.
	 */
	private NumberToStringUpdateStrategy() {
	}



	/**
	 * Gibt die einzige Instanz dieser Klasse zurück.
	 * 
	 * @return die einzige Instanz dieser Klasse
	 */
	public static NumberToStringUpdateStrategy getInstance() {
		return _instance == null ? new NumberToStringUpdateStrategy() : _instance;
	}



	@Override
	public Object convert(Object pValue) {
		return Utility.FORMAT.format(pValue);
	}

}
