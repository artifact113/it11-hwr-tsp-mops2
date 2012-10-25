/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

import net.miginfocom.swt.MigLayout;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.constant.FileDialogFilter;
import de.hwrberlin.it11.tsp.constant.Images;
import de.hwrberlin.it11.tsp.constant.IterationMode;
import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.controller.AntController;
import de.hwrberlin.it11.tsp.factories.FileDialogFactory;
import de.hwrberlin.it11.tsp.gui.updatestrategy.NumberToStringUpdateStrategy;
import de.hwrberlin.it11.tsp.gui.updatestrategy.StringToDoubleUpdateStrategy;
import de.hwrberlin.it11.tsp.gui.updatestrategy.StringToIntegerUpdateStrategy;
import de.hwrberlin.it11.tsp.gui.widgets.AntButton;
import de.hwrberlin.it11.tsp.gui.widgets.AntText;
import de.hwrberlin.it11.tsp.interfaces.AllInputValidListener;
import de.hwrberlin.it11.tsp.interfaces.ValidInputListener;
import de.hwrberlin.it11.tsp.model.Parameter;
import de.hwrberlin.it11.tsp.persistence.Persister;

/**
 * Das StopCriteriaComposite lässt das Abbruchkriterium festlegen und den Algrotihmus starten.
 * 
 * @author Patrick Szostack
 * 
 */
public class StopCriteriaComposite extends ADataBindableComposite implements PropertyChangeListener, AllInputValidListener, ValidInputListener {

	/** Button, um Iteration als Abbruchbedingung auszuwählen */
	private AntButton _rIterationCount;

	/** Textfeld, um die Zahl der Iterationen einzustellen */
	private AntText _tIterationCount;

	/** Button um Schwellenwert der Tourlänge als Abbruchbedingung auszuwählen */
	private AntButton _rMaximumTourLength;

	/** Textfeld um den Schwellenwert einzustellen */
	private AntText _tMaximumTourLength;

	/** Button, um eine Lösungsdatei als Abbruchbedingung auszuwählen */
	private AntButton _rOptTourFilePath;

	/** Textfeld zum Anzeigen des Dateipfades der Lösungsdatei */
	private AntText _tOptTourFilePath;

	/** Button zum Auswählen der Lösungsdatei */
	private AntButton _bOptTourFilePath;

	/** Button zum Starten und Stoppen des Algorithmus */
	private AntButton _bStart;

	/** Speichert den Wert des AllInputValidListener */
	private boolean _allInputValid;



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

		_rIterationCount = new AntButton(new Button(comp, SWT.RADIO), getController().getProject());
		_rIterationCount.getButton().setLayoutData("hmin 0, wmin 0");
		_rIterationCount.getButton().setText("Iterationen:");
		_rIterationCount
				.setTooltipText("Diese Option lässt den Suchvorgang stoppen, nachdem eine eingestellte Anzahl an Iterationen durchgeführt wurden.");

		_tIterationCount = new AntText(new Text(comp, SWT.BORDER), getController().getProject());
		_tIterationCount.getText().setLayoutData("hmin pref, wmin 50, spanx 2, growx");
		_tIterationCount.setTooltipText("Hier können Sie einstellen, wie oft iteriert werden soll.");
		_tIterationCount.setInputMode(AntText.INTEGER_ONLY);
		_tIterationCount.setNumberRange(1, Double.POSITIVE_INFINITY, false, true);
		_tIterationCount.addValidInputListener(this);

		_rMaximumTourLength = new AntButton(new Button(comp, SWT.RADIO), getController().getProject());
		_rMaximumTourLength.getButton().setLayoutData("hmin 0, wmin 0");
		_rMaximumTourLength.getButton().setText("Tourlänge:");
		_rMaximumTourLength
				.setTooltipText("Diese Option lässt den Suchvorgang stoppen, nachdem eine Tour gefunden wurde, die kürzer oder gleich lang als eine eingestellte Länge ist.");

		_tMaximumTourLength = new AntText(new Text(comp, SWT.BORDER), getController().getProject());
		_tMaximumTourLength.getText().setLayoutData("hmin pref, wmin 50, spanx 2, growx");
		_tMaximumTourLength.setTooltipText("Hier können Sie einstellen, bei welcher Tourlänge abegebrochen werden soll.");
		_tMaximumTourLength.setInputMode(AntText.DOUBLE_ONLY);
		_tMaximumTourLength.setNumberRange(0, Double.POSITIVE_INFINITY, false, true);
		_tMaximumTourLength.addValidInputListener(this);

		_rOptTourFilePath = new AntButton(new Button(comp, SWT.RADIO), getController().getProject());
		_rOptTourFilePath.getButton().setLayoutData("hmin 0, wmin 0");
		_rOptTourFilePath.getButton().setText("Optimale Tour:");
		_rOptTourFilePath.setTooltipText("Diese Option lässt den Suchvorgang stoppen, wenn eine eingestellte Lösung gefunden wurde.");

		_tOptTourFilePath = new AntText(new Text(comp, SWT.BORDER | SWT.READ_ONLY), getController().getProject());
		_tOptTourFilePath.getText().setLayoutData("hmin pref, wmin 50, growx, pushx");
		_tOptTourFilePath.setTooltipText("Hier steht der Dateipfad zu der Lösungsdatei.");

		_bOptTourFilePath = new AntButton(new Button(comp, SWT.PUSH), getController().getProject());
		_bOptTourFilePath.getButton().setLayoutData("hmin 0, wmin 0");
		_bOptTourFilePath.getButton().setImage(Images.FOLDER);
		_bOptTourFilePath.setTooltipText("Ein Klick auf diesen Button öffnet den Datei-Browser, mit dem Sie eine Lösungsdatei aussuchen können.");
		_bOptTourFilePath.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				String path = new FileDialogFactory().setParent(getShell()).setFilter(FileDialogFilter.OPTTOUR).setStyle(SWT.OPEN).open();
				if (path != null) {
					List<Integer> indexList = Persister.loadOptTourFile(new File(path));
					if (indexList.size() != getController().getProject().getTSPData().getNodeList().size()) {
						MessageDialog
								.openError(getShell(), "Ungültige optimale Tour",
										"Die Liste der Knoten und die Liste der optimalen Tour weisen eine unterschiedliche Länge auf, somit kann die optimale Tour nicht geladen werden.");
					}
					else {
						_tOptTourFilePath.getText().setText(path);
						getController().getProject().setOptimalTourIndeces(indexList);
					}
				}
				evaluateStartEnabled();
			}

		});

		SelectionAdapter radioGroupListener = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				Object source = pE.getSource();
				if (source == _rIterationCount.getButton()) {
					_tIterationCount.getText().setEnabled(true);
					_tMaximumTourLength.getText().setEnabled(false);
					_tOptTourFilePath.getText().setEnabled(false);
					_bOptTourFilePath.getButton().setEnabled(false);
					getController().setIterationMode(IterationMode.COUNT);
				}
				if (source == _rMaximumTourLength.getButton()) {
					_tIterationCount.getText().setEnabled(false);
					_tMaximumTourLength.getText().setEnabled(true);
					_tOptTourFilePath.getText().setEnabled(false);
					_bOptTourFilePath.getButton().setEnabled(false);
					getController().setIterationMode(IterationMode.LENGTH);
				}
				if (source == _rOptTourFilePath.getButton()) {
					_tIterationCount.getText().setEnabled(false);
					_tMaximumTourLength.getText().setEnabled(false);
					_tOptTourFilePath.getText().setEnabled(true);
					_bOptTourFilePath.getButton().setEnabled(true);
					getController().setIterationMode(IterationMode.SOLUTION);
				}
				evaluateStartEnabled();
			}
		};

		_rIterationCount.getButton().addSelectionListener(radioGroupListener);
		_rMaximumTourLength.getButton().addSelectionListener(radioGroupListener);
		_rOptTourFilePath.getButton().addSelectionListener(radioGroupListener);
		_rIterationCount.getButton().setSelection(true);
		_tMaximumTourLength.getText().setEnabled(false);
		_tOptTourFilePath.getText().setEnabled(false);
		_bOptTourFilePath.getButton().setEnabled(false);

		_bStart = new AntButton(new Button(comp, SWT.PUSH), getController().getProject());
		_bStart.getButton().setText("START");
		_bStart.getButton().setLayoutData("hmin pref, wmin pref, spanx, growx");
		_bStart.setTooltipText("Dieser Button startet und stoppt den Suchvorgang.");
		_bStart.getButton().addSelectionListener(new SelectionAdapter() {

			boolean _isRunning;



			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (!_isRunning) {
					_bStart.getButton().setText("STOP");
					getController().start();
					_isRunning = true;
				}
				else {
					_bStart.getButton().setText("START");
					getController().stop();
					_isRunning = false;
				}
			}
		});

		_allInputValid = true;

		resetBinding();
	}



	@Override
	protected void bindValues(DataBindingContext pDBC, Realm pRealm) {
		Parameter parameter = getController().getProject().getParameter();

		// Zahl der Iterationen binden
		pDBC.bindValue(SWTObservables.observeText(_tIterationCount.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_ITERATIONCOUNT),
				StringToIntegerUpdateStrategy.getInstance(), NumberToStringUpdateStrategy.getInstance());
		// Schwellenwert der Tourlänge binden
		pDBC.bindValue(SWTObservables.observeText(_tMaximumTourLength.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_MAXIMUMTOURLENGTH),
				StringToDoubleUpdateStrategy.getInstance(), NumberToStringUpdateStrategy.getInstance());
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



	@Override
	public void isValid(boolean pValid) {
		_allInputValid = pValid;
		evaluateStartEnabled();
	}



	@Override
	public void validStatusChanged(boolean pValid) {
		evaluateStartEnabled();
	}



	/**
	 * Enabled und disabled den Start-Button basierend auf den Validierungsstati der in dieser Instanz definierten Textfelder und des Ergebnisses des
	 * AllInputValodListener.
	 */
	private void evaluateStartEnabled() {
		boolean enable = _allInputValid && _tIterationCount.isValidInput() && _tMaximumTourLength.isValidInput();
		if (_rOptTourFilePath.getButton().getSelection()) {
			if (getController().getProject().getOptimalTourIndeces() == null
					|| getController().getProject().getTSPData().getNodeList().size() != getController().getProject().getOptimalTourIndeces().size()) {
				enable = false;
			}
		}
		_bStart.getButton().setEnabled(enable);
	}

}
