/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.miginfocom.swt.MigLayout;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.constant.Images;
import de.hwrberlin.it11.tsp.constant.IterationMode;
import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.controller.AntController;
import de.hwrberlin.it11.tsp.gui.listener.VerifyDoubleListener;
import de.hwrberlin.it11.tsp.model.Parameter;

/**
 * Das StopCriteriaComposite lässt das Abbruchkriterium festlegen und den Algrotihmus starten.
 * 
 * @author Patrick Szostack
 * 
 */
public class StopCriteriaComposite extends ADataBindable implements PropertyChangeListener {

	/** Button, um Iteration als Abbruchbedingung auszuwählen */
	private Button _rIterationCount;

	/** Textfeld, um die Zahl der Iterationen einzustellen */
	private Text _tIterationCount;

	/** Button um Schwellenwert der Tourlänge als Abbruchbedingung auszuwählen */
	private Button _rMaximumTourLength;

	/** Textfeld um den Schwellenwert einzustellen */
	private Text _tMaximumTourLength;

	/** Button, um eine Lösungsdatei als Abbruchbedingung auszuwählen */
	private Button _rOptTourFilePath;

	/** Textfeld zum Anzeigen des Dateipfades der Lösungsdatei */
	private Text _tOptTourFilePath;

	/** Button zum Auswählen der Lösungsdatei */
	private Button _bOptTourFilePath;



	/**
	 * Erstellt ein neues StopCriteriaComposite.
	 * 
	 * @param pParent
	 *            das Eltern-Composite
	 * @param pStyle
	 *            der Style des Composite
	 * @param pController
	 *            der Controller dieses DrawComposite
	 */
	public StopCriteriaComposite(Composite pParent, int pStyle, AntController pController) {
		super(pParent, pStyle, pController);
		getController().getProject().addPropertyChangeListener(this);

		Composite comp = new Composite(this, SWT.NONE);
		comp.setLayout(new MigLayout("fill, wrap 3", "[pref!][]"));
		comp.setLayoutData("hmin pref, wmin pref, growx, pushx");

		_rIterationCount = new Button(comp, SWT.RADIO);
		_rIterationCount.setLayoutData("hmin 0, wmin 0");
		_rIterationCount.setText("Iterationen:");

		_tIterationCount = new Text(comp, SWT.BORDER);
		_tIterationCount.setLayoutData("hmin pref, wmin 50, spanx 2, growx");
		// _tIterationCount.addVerifyListener(VerifyIntegerListener.getInstance());

		_rMaximumTourLength = new Button(comp, SWT.RADIO);
		_rMaximumTourLength.setLayoutData("hmin 0, wmin 0");
		_rMaximumTourLength.setText("Tourlänge:");

		_tMaximumTourLength = new Text(comp, SWT.BORDER);
		_tMaximumTourLength.setLayoutData("hmin pref, wmin 50, spanx 2, growx");
		_tMaximumTourLength.addVerifyListener(VerifyDoubleListener.getInstance());

		_rOptTourFilePath = new Button(comp, SWT.RADIO);
		_rOptTourFilePath.setLayoutData("hmin 0, wmin 0");
		_rOptTourFilePath.setText("Optimale Tour:");

		_tOptTourFilePath = new Text(comp, SWT.BORDER);
		_tOptTourFilePath.setLayoutData("hmin pref, wmin 50, growx, pushx");

		_bOptTourFilePath = new Button(comp, SWT.PUSH);
		_bOptTourFilePath.setLayoutData("hmin 0, wmin 0");
		_bOptTourFilePath.setImage(Images.FOLDER);

		SelectionAdapter radioGroupListener = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				Object source = pE.getSource();
				if (source == _rIterationCount) {
					_tIterationCount.setEnabled(true);
					_tMaximumTourLength.setEnabled(false);
					_tOptTourFilePath.setEnabled(false);
					_bOptTourFilePath.setEnabled(false);
					getController().setIterationMode(IterationMode.COUNT);
				}
				if (source == _rMaximumTourLength) {
					_tIterationCount.setEnabled(false);
					_tMaximumTourLength.setEnabled(true);
					_tOptTourFilePath.setEnabled(false);
					_bOptTourFilePath.setEnabled(false);
					getController().setIterationMode(IterationMode.LENGTH);
				}
				if (source == _rOptTourFilePath) {
					_tIterationCount.setEnabled(false);
					_tMaximumTourLength.setEnabled(false);
					_tOptTourFilePath.setEnabled(true);
					_bOptTourFilePath.setEnabled(true);
					getController().setIterationMode(IterationMode.SOLUTION);
				}
			}
		};

		_rIterationCount.addSelectionListener(radioGroupListener);
		_rMaximumTourLength.addSelectionListener(radioGroupListener);
		_rOptTourFilePath.addSelectionListener(radioGroupListener);
		_rIterationCount.setSelection(true);
		_tMaximumTourLength.setEnabled(false);
		_tOptTourFilePath.setEnabled(false);
		_bOptTourFilePath.setEnabled(false);

		final Button bStart = new Button(comp, SWT.PUSH);
		bStart.setText("START");
		bStart.setLayoutData("hmin pref, wmin pref, spanx, growx");
		bStart.addSelectionListener(new SelectionAdapter() {

			boolean _isRunning;



			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (!_isRunning) {
					bStart.setText("STOP");
					getController().start();
					_isRunning = true;
				}
				else {
					bStart.setText("START");
					getController().stop();
					_isRunning = false;
				}
			}
		});

		resetBinding();
	}



	@Override
	protected void bindValues(DataBindingContext pDBC, Realm pRealm) {
		Parameter parameter = getController().getProject().getParameter();

		// Zahl der Iterationen binden
		pDBC.bindValue(SWTObservables.observeText(_tIterationCount, SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_ITERATIONCOUNT));
		// Schwellenwert der Tourlänge binden
		pDBC.bindValue(SWTObservables.observeText(_tMaximumTourLength, SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_MAXIMUMTOURLENGTH));
	}



	@Override
	public void propertyChange(PropertyChangeEvent pEvt) {
		if (pEvt != null && pEvt.getPropertyName() != null) {
			String propertyName = pEvt.getPropertyName();

			// Auf folgende Events das Databinding erneuern:
			if (PropertyChangeTypes.PROJECT_PARAMETER.equals(propertyName)) { // Parameter des Projektes wurden neu gesetzt
				resetBinding();
			}
		}
	}

}
