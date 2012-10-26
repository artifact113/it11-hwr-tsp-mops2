/**
 * Copyright (c) 2012 mops² Productions
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

	@Override
	public Object convert(Object pValue) {
		int value = (Integer) pValue;
		return ((double) value) / 100;
	}

}
