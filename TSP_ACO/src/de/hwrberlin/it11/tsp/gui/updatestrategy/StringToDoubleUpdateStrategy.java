/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.updatestrategy;

import java.text.ParseException;

import org.eclipse.core.databinding.UpdateValueStrategy;

import de.hwrberlin.it11.tsp.constant.Utility;

/**
 * UpdateValueStrategy, die einen String in einen double konvertiert.
 */
public class StringToDoubleUpdateStrategy extends UpdateValueStrategy {

	@Override
	public Object convert(Object pValue) {
		try {
			return Utility.FORMAT.parse((String) pValue).doubleValue();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
