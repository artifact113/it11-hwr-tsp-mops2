/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.widgets;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Control;

import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * Diese Klasse wrappt ein SWT Control und stellt einige extra Funktionalitäten bereit.
 */
public abstract class AAntControl {

	/** Das AntProject, für das dieser Dialog erstellt wird */
	private AntProject _project;

	/** Das Control, das von diesem AAntControl gewrappt wird */
	private Control _control;



	/**
	 * Erzeugt ein neues AAntControl.
	 * 
	 * @param pControl
	 *            das zu wrappende Control
	 * @param pProject
	 *            das benutzte AntProject
	 */
	public AAntControl(Control pControl, AntProject pProject) {
		_control = pControl;
		_project = pProject;
	}



	/**
	 * Gibt das gewrappte Control zurück.
	 * 
	 * @return das gewrappte Control
	 */
	protected Control getControl() {
		return _control;
	}



	/**
	 * Setzt den Tooltip Text des Controls auf den angegebenen Tooltip. Dabei handelt es sich nicht um einen normalen Tooltip Text. Am Control wird
	 * ein MouseTrackListener registriert, der, wenn sich der Mauszeiger über das Control bewegt, den Tooltip Text ins Datenmodell schreibt. Per
	 * PropertyChengeEvent kann dieser Text dann an beliebiger Stelle angezeigt werden.
	 * 
	 * @param pTooltipText
	 *            der zu setzende Tooltip Text
	 */
	public void setTooltipText(final String pTooltipText) {
		_control.addMouseTrackListener(new MouseTrackAdapter() {

			@Override
			public void mouseEnter(MouseEvent pE) {
				_project.setStatusText(pTooltipText);
			}



			@Override
			public void mouseExit(MouseEvent pE) {
				_project.setStatusText("");
			}

		});
	}

}
