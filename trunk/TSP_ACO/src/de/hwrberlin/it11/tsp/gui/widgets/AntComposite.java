/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.widgets;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * Die Composite-Implementierung des AAntControl.
 * 
 * @author Patrick Szostack
 * 
 */
public class AntComposite extends AAntControl {

	/** Hintergrundfarbe dieses Composite */
	private Color _background;



	/**
	 * Erzeugt ein neues AntComposite.
	 * 
	 * @param pComposite
	 *            das zu wrappende Composite
	 * @param pProject
	 *            das benutzte AntProject
	 */
	public AntComposite(Composite pComposite, AntProject pProject) {
		super(pComposite, pProject);
	}



	/**
	 * Gibt das gewrappte Composite zurück.
	 * 
	 * @return das gewrappte Composite
	 */
	public Composite getComposite() {
		return (Composite) getControl();
	}



	/**
	 * Setzt die Hintergrundfarbe dieses AntComposite auf die angegebene Farbe.
	 * <p>
	 * ACHTUNG: Dies setzt außerdem die Hintergrundfarbe des gewrappten Composite. Um Unstimmigkeiten zu vermeiden wird empfohlen
	 * {@link Composite#setBackground(Color)} nicht mehr aufzurufen, sondern stattdessen diese Methode zu benutzen.
	 * 
	 * @param pColor
	 *            die zu benutzende Hintergrundfarbe
	 */
	public void setBackground(Color pColor) {
		_background = pColor;
		getComposite().setBackground(pColor);
	}



	/**
	 * Gibt die Hintergrundfarbe des Composite zurück.
	 * 
	 * @return die Hintergrundfarbe des Composite
	 */
	public Color getBackground() {
		return _background;
	}

}
