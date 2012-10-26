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

	@Override
	public Object convert(Object pValue) {
		double value = (Double) pValue;
		return (int) (value * 100);
	}

}
