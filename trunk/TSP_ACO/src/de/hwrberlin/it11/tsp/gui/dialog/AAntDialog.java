/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;

import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * Basisklasse f�r Dialoge.
 * 
 * @author Patrick Szostack
 * 
 */
public abstract class AAntDialog extends Dialog {

	/** Das AntProject, f�r das dieser Dialog erstellt wird */
	private AntProject _project;



	/**
	 * Konstruktor.
	 * 
	 * @param pParent
	 *            die Parent-Shell des zu erstellenden Dialoges
	 * @param pProject
	 *            das AntProject des zu erstellenden Dialoges
	 */
	public AAntDialog(Shell pParent, AntProject pProject) {
		super(pParent);
		_project = pProject;
	}



	/**
	 * Gibt das verwendete AntProject wieder.
	 * 
	 * @return the project
	 */
	protected AntProject getProject() {
		return _project;
	}

}
