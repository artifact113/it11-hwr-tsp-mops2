/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.widgets;

import org.eclipse.swt.widgets.Label;

import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * Die Label-Implementierung des AAntControl.
 * 
 * @author Patrick Szostack
 * 
 */
public class AntLabel extends AAntControl {

	/**
	 * Erzeugt ein neues AntLabel.
	 * 
	 * @param pLabel
	 *            das zu wrappende Label
	 * @param pProject
	 *            das benutzte AntProject
	 */
	public AntLabel(Label pLabel, AntProject pProject) {
		super(pLabel, pProject);
	}



	/**
	 * Gibt das gewrappte Label zurück.
	 * 
	 * @return das gewrappte Label
	 */
	public Label getLabel() {
		return (Label) getControl();
	}

}
