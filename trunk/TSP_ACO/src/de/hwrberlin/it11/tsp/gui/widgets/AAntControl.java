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
 * 
 * @author Patrick Szostack
 * 
 */
public class AAntControl {

	/** Das AntProject, für das dieser Dialog erstellt wird */
	private AntProject _project;

	/** Das Control, das von diesem AAntControl gewrappt wird */
	private Control _control;



	/**
	 * Erzeugt ein neues AAntControl. Der angegebene Tooltip Text wird im DrawComposite angezeigt, wenn sich der Mauszeiger über dem Control befindet
	 * und wieder ausgeblendet wenn er es nicht tut.
	 * 
	 * @param pControl
	 *            das zu wrappende Control
	 * @param pProject
	 *            das benutzte AntProject
	 * @param pTooltipText
	 *            der anzuzeigende Tooltip Text
	 */
	public AAntControl(Control pControl, AntProject pProject, final String pTooltipText) {
		_control = pControl;
		_project = pProject;

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
