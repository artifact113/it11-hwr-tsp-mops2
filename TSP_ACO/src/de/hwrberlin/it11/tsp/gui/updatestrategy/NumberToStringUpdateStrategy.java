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

	@Override
	public Object convert(Object pValue) {
		return Utility.FORMAT.format(pValue);
	}

}
