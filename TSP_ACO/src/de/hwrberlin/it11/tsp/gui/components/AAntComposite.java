/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.components;

import org.eclipse.swt.widgets.Composite;

import de.hwrberlin.it11.tsp.controller.AntController;

/**
 * Ein abstraktes Composite das sich zusätzlich das Ameisenprojekt merkt.
 */
public abstract class AAntComposite extends Composite {

	/** Der Controller */
	private AntController _controller;



	/**
	 * Erzeugt ein neues AAntComposite.
	 * 
	 * @param pParent
	 *            das Elternelement
	 * @param pStyle
	 *            der Style, den dieses Composite haben soll
	 * @param pProject
	 *            das Ameisenprojekt
	 */
	public AAntComposite(Composite pParent, int pStyle, AntController pController) {
		super(pParent, pStyle);
		_controller = pController;
	}



	/**
	 * Gibt den AntController zurück.
	 * 
	 * @return the controller
	 */
	public AntController getController() {
		return _controller;
	}

}
