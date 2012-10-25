/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.widgets;

import org.eclipse.swt.widgets.Button;

import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * Die Button-Implemetierung des AAntControl.
 * 
 * @author Patrick Szostack
 * 
 */
public class AntButton extends AAntControl {

	/**
	 * Erzeugt einen neuen AntButton.
	 * 
	 * @param pButton
	 *            der zu wrappende Button
	 * @param pProject
	 *            das benutzte AntProject
	 */
	public AntButton(Button pButton, AntProject pProject) {
		super(pButton, pProject);
	}



	/**
	 * Gibt den gewrappten Button zurück.
	 * 
	 * @return den gewrappten Button
	 */
	public Button getButton() {
		return (Button) getControl();
	}

}
