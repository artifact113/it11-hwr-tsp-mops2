/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.updatestrategy;

import org.eclipse.core.databinding.UpdateValueStrategy;

/**
 * UpdateValueStrategy, die von double nach int konvertiert (indem mit 100 multipliziert wird).
 * 
 * @author Patrick Szostack
 * 
 */
public class ZoomFactorModelToTargetUpdateStrategy extends UpdateValueStrategy {

	/** Die einzige Instanz dieser Klasse */
	private static ZoomFactorModelToTargetUpdateStrategy _instance;



	/**
	 * Überschreibt den public default Konstruktor. Es soll nur eine Instanz dieser Klasse geben, die über die Methode getInstance() referenzierbar
	 * ist.
	 */
	private ZoomFactorModelToTargetUpdateStrategy() {
	}



	/**
	 * Gibt die einzige Instanz dieser Klasse zurück.
	 * 
	 * @return die einzige Instanz dieser Klasse
	 */
	public static ZoomFactorModelToTargetUpdateStrategy getInstance() {
		return _instance == null ? new ZoomFactorModelToTargetUpdateStrategy() : _instance;
	}



	@Override
	public Object convert(Object pValue) {
		double value = (double) pValue;
		return (int) (value * 100);
	}

}
