/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.gui.updatestrategy;

import org.eclipse.core.databinding.UpdateValueStrategy;

/**
 * UpdateValueStrategy, die von int nach double konvertiert (indem durch 100 geteilt wird).
 * 
 * @author Patrick Szostack
 * 
 */
public class ZoomFactorTargetToModelUpdateStrategy extends UpdateValueStrategy {

	/** Die einzige Instanz dieser Klasse */
	private static ZoomFactorTargetToModelUpdateStrategy _instance;



	/**
	 * �berschreibt den public default Konstruktor. Es soll nur eine Instanz dieser Klasse geben, die �ber die Methode getInstance() referenzierbar
	 * ist.
	 */
	private ZoomFactorTargetToModelUpdateStrategy() {
	}



	/**
	 * Gibt die einzige Instanz dieser Klasse zur�ck.
	 * 
	 * @return die einzige Instanz dieser Klasse
	 */
	public static ZoomFactorTargetToModelUpdateStrategy getInstance() {
		return _instance == null ? new ZoomFactorTargetToModelUpdateStrategy() : _instance;
	}



	@Override
	public Object convert(Object pValue) {
		int value = (Integer) pValue;
		return ((double) value) / 100;
	}

}
