/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.components;

import java.io.File;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.hwrberlin.it11.tsp.constant.Images;
import de.hwrberlin.it11.tsp.controller.AntController;
import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * Der TabContent ist der Inhalt eines Tabs und hat jeweils seinen eigenen AntController, eigenes AntProject und eigene UI.
 * 
 * @author Patrick Szostack
 * 
 */
public class TabContent extends AAntComposite {

	private File _tspFile;

	private File _tspConfigFile;



	/**
	 * @param pParent
	 * @param pStyle
	 */
	public TabContent(Composite pParent, int pStyle) {
		super(pParent, pStyle, new AntController(new AntProject()));
	}



	public void createComponent() {
		Composite comp = new Composite(this, SWT.NONE);
		comp.setLayout(new MigLayout("fill, ins 0", "[][pref!]"));
		comp.setLayoutData("hmin pref, wmin pref, grow");

		DrawComposite draw = new DrawComposite(comp, SWT.BORDER, getController());
		draw.setLayout(new MigLayout("ins 0"));
		draw.setLayoutData("hmin 500, wmin 500, grow");

		Composite right = new Composite(comp, SWT.NONE);
		right.setLayout(new MigLayout("wrap, ins 0", "[pref!]"));
		right.setLayoutData("hmin pref, wmin pref, growy");

		Label picture = new Label(right, SWT.NONE);
		picture.setImage(Images.COWBOY);
		picture.setLayoutData("hmin pref, wmin pref");

		InputComposite input = new InputComposite(right, SWT.BORDER, getController());
		input.setLayout(new MigLayout());
		input.setLayoutData("hmin pref, wmin pref, grow");

		StopCriteriaComposite criteria = new StopCriteriaComposite(right, SWT.BORDER, getController());
		criteria.setLayout(new MigLayout());
		criteria.setLayoutData("hmin pref, wmin pref, grow");

		OutputComposite output = new OutputComposite(right, SWT.BORDER, getController());
		output.setLayout(new MigLayout("fill"));
		output.setLayoutData("hmin pref, wmin pref, grow");

		input.addAllInputValidListener(criteria);
	}



	public File getTSPFile() {
		return _tspFile;
	}



	public void setTSPFile(File pFile) {
		_tspFile = pFile;
	}



	public File getTSPConfigFile() {
		return _tspConfigFile;
	}



	public void setTSPConfigFile(File pFile) {
		_tspConfigFile = pFile;
	}

}
