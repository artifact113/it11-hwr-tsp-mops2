/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.widgets;

import org.eclipse.swt.widgets.Scale;

import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * Die Scale-Implementierung des AAntControl.
 * 
 * @author Patrick Szostack
 * 
 */
public class AntScale extends AAntControl {

	/**
	 * Erzeugt eine neue AntScale.
	 * 
	 * @param pScale
	 *            die zu wrappende Scale
	 * @param pProject
	 *            das benutzte AntProject
	 */
	public AntScale(Scale pScale, AntProject pProject) {
		super(pScale, pProject);
	}



	/**
	 * Gibt die gewrappte Scale zurück.
	 * 
	 * @return die gewrappte Scale
	 */
	public Scale getScale() {
		return (Scale) getControl();
	}

}
