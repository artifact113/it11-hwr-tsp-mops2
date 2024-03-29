/**
 * Copyright (c) 2012 mops� Productions
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
 */
public class TabContent extends AAntComposite {

	/** Die .tsp Datei, die diesem TabContent momentan zugeordnet ist */
	private File _tspFile;

	/** Die .tspconfig Datei, die diesem TabContent momentan zugeordnet ist */
	private File _tspConfigFile;



	/**
	 * Erstellt einen neuen TabContent mit neuem AntController und neuem AntProject.
	 * 
	 * @param pParent
	 *            das Eltern Composite
	 * @param pStyle
	 *            die SWT Stylebits dieses Composite
	 */
	public TabContent(Composite pParent, int pStyle) {
		super(pParent, pStyle, new AntController(new AntProject()));

		Composite comp = new Composite(this, SWT.NONE);
		comp.setLayout(new MigLayout("fill, ins 0", "[][pref!]"));
		comp.setLayoutData("hmin pref, wmin pref, grow, hmax 98%, wmax 99%");

		DrawComposite draw = new DrawComposite(comp, SWT.BORDER, getController());
		draw.setLayout(new MigLayout("ins 0"));
		draw.setLayoutData("hmin 500, wmin 500, grow");

		Composite right = new Composite(comp, SWT.NONE);
		right.setLayout(new MigLayout("wrap, ins 0", "[pref!]"));
		right.setLayoutData("hmin pref, wmin pref, growy");

		Label picture = new Label(right, SWT.NONE);
		picture.setImage(Images.COWBOY);
		picture.setLayoutData("hmin pref, wmin pref, align center");

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



	/**
	 * @return die .tsp Datei, die diesem TabContent momentan zugeordnet ist
	 */
	public File getTSPFile() {
		return _tspFile;
	}



	/**
	 * @param pFile
	 *            Die .tsp Datei, die diesem TabContent zugeordnet werden soll
	 */
	public void setTSPFile(File pFile) {
		_tspFile = pFile;
	}



	/**
	 * @return die .tspconfig Datei, die diesem TabContent momentan zugeordnet ist
	 */
	public File getTSPConfigFile() {
		return _tspConfigFile;
	}



	/**
	 * @param pFile
	 *            Die .tspconfig Datei, die diesem TabContent zugeordnet werden soll
	 */
	public void setTSPConfigFile(File pFile) {
		_tspConfigFile = pFile;
	}

}
