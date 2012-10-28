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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
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
import de.hwrberlin.it11.tsp.gui.widgets.AntLabel;
import de.hwrberlin.it11.tsp.gui.widgets.AntText;
import de.hwrberlin.it11.tsp.interfaces.AlgorithmListener;
import de.hwrberlin.it11.tsp.interfaces.AllInputValidListener;
import de.hwrberlin.it11.tsp.interfaces.ValidInputListener;
import de.hwrberlin.it11.tsp.model.AntProject;
import de.hwrberlin.it11.tsp.model.Parameter;
import de.hwrberlin.it11.tsp.persistence.Persister;

/**
 * Das StopCriteriaComposite lässt das Abbruchkriterium festlegen und den Algrotihmus starten.
 * 
 * @author Patrick Szostack
 * 
 */
public class StopCriteriaComposite extends ADataBindableComposite implements PropertyChangeListener, AllInputValidListener, ValidInputListener,
		AlgorithmListener {

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

	/** Button zum Auswählen der Lösungsdatei */
	private AntButton _bOptTourFilePath;

	/** Zeigt den Status der ausgewählten Lösungsdatei an */
	private AntLabel _lAccept;

	/** Button zum Starten und Stoppen des Algorithmus */
	private AntButton _bStart;

	/** Status des Start-Buttons - false: ein Klick startet den Algorithmus, true: ein Klick stoppt den Algorithmus */
	private boolean _buttonState;

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
		getController().getProject().getTSPData().addPropertyChangeListener(this);
		getController().addAlgorithmListener(this);

		Composite comp = new Composite(this, SWT.NONE);
		comp.setLayout(new MigLayout("fill, wrap 3, ins 0", "[pref!][]"));
		comp.setLayoutData("hmin pref, wmin pref, growx, pushx");

		_rIterationCount = new AntButton(new Button(comp, SWT.RADIO), getController().getProject());
		_rIterationCount.getButton().setLayoutData("hmin 0, wmin 0");
		_rIterationCount.getButton().setText("Iterationen:");
		_rIterationCount
				.setTooltipText("Diese Option lässt den Suchvorgang stoppen, nachdem eine eingestellte Anzahl an Iterationen durchgeführt wurden.");

		_tIterationCount = new AntText(new Text(comp, SWT.BORDER), getController().getProject());
		_tIterationCount.getText().setLayoutData("hmin pref, wmin 50, spanx 2, growx");
		_tIterationCount.setTooltipText("Hier können Sie einstellen, wie oft iteriert werden soll. (X > 0)");
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
		_tMaximumTourLength.setTooltipText("Hier können Sie einstellen, bei welcher Tourlänge abegebrochen werden soll. (X > 0)");
		_tMaximumTourLength.setInputMode(AntText.DOUBLE_ONLY);
		_tMaximumTourLength.setNumberRange(0, Double.POSITIVE_INFINITY, false, true);
		_tMaximumTourLength.addValidInputListener(this);

		_rOptTourFilePath = new AntButton(new Button(comp, SWT.RADIO), getController().getProject());
		_rOptTourFilePath.getButton().setLayoutData("hmin 0, wmin 0");
		_rOptTourFilePath.getButton().setText("Optimale Tour:");
		_rOptTourFilePath.setTooltipText("Diese Option lässt den Suchvorgang stoppen, wenn eine eingestellte Lösung gefunden wurde.");

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
					if (indexList.size() - 1 != getController().getProject().getTSPData().getNodeList().size()) {
						MessageDialog
								.openError(getShell(), "Ungültige optimale Tour",
										"Die Liste der Knoten und die Liste der optimalen Tour weisen eine unterschiedliche Länge auf, somit kann die optimale Tour nicht geladen werden.");
					}
					else {
						getController().getProject().setOptimalTourIndeces(indexList);
					}
				}
				evaluateSolutionAccept();
				evaluateStartEnabled();
			}

		});

		_lAccept = new AntLabel(new Label(comp, SWT.NONE), getController().getProject());
		_lAccept.getLabel().setLayoutData("hmin 0, wmin 0, push");
		_lAccept.getLabel().setImage(Images.CROSS);
		_lAccept.setTooltipText("Zeigt den Status der Lösungsdatei an.");

		SelectionAdapter radioGroupListener = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				Object source = pE.getSource();
				if (source == _rIterationCount.getButton()) {
					_tIterationCount.getText().setEnabled(true);
					_tMaximumTourLength.getText().setEnabled(false);
					_bOptTourFilePath.getButton().setEnabled(false);
					_lAccept.getLabel().setEnabled(false);
					getController().setIterationMode(IterationMode.COUNT);
				}
				if (source == _rMaximumTourLength.getButton()) {
					_tIterationCount.getText().setEnabled(false);
					_tMaximumTourLength.getText().setEnabled(true);
					_bOptTourFilePath.getButton().setEnabled(false);
					_lAccept.getLabel().setEnabled(false);
					getController().setIterationMode(IterationMode.LENGTH);
				}
				if (source == _rOptTourFilePath.getButton()) {
					_tIterationCount.getText().setEnabled(false);
					_tMaximumTourLength.getText().setEnabled(false);
					_bOptTourFilePath.getButton().setEnabled(true);
					_lAccept.getLabel().setEnabled(true);
					getController().setIterationMode(IterationMode.SOLUTION);
				}
				evaluateStartEnabled();
			}
		};

		_bStart = new AntButton(new Button(comp, SWT.PUSH), getController().getProject());
		_bStart.getButton().setText("START");
		_bStart.getButton().setLayoutData("hmin pref, wmin pref, spanx, growx");
		_bStart.setTooltipText("Dieser Button startet und stoppt den Suchvorgang.");
		_bStart.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (!_buttonState) {
					getController().start();
				}
				else {
					getController().stop();
				}
			}
		});

		_rIterationCount.getButton().addSelectionListener(radioGroupListener);
		_rMaximumTourLength.getButton().addSelectionListener(radioGroupListener);
		_rOptTourFilePath.getButton().addSelectionListener(radioGroupListener);
		_rIterationCount.getButton().setSelection(true);
		_tMaximumTourLength.getText().setEnabled(false);
		_lAccept.getLabel().setEnabled(false);
		_bOptTourFilePath.getButton().setEnabled(false);
		_bStart.getButton().setEnabled(false);

		_allInputValid = true;

		resetBinding();
	}



	@Override
	protected void bindValues(DataBindingContext pDBC, Realm pRealm) {
		Parameter parameter = getController().getProject().getParameter();

		// Zahl der Iterationen binden
		pDBC.bindValue(SWTObservables.observeText(_tIterationCount.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_ITERATIONCOUNT), new StringToIntegerUpdateStrategy(),
				new NumberToStringUpdateStrategy());
		// Schwellenwert der Tourlänge binden
		pDBC.bindValue(SWTObservables.observeText(_tMaximumTourLength.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_MAXIMUMTOURLENGTH),
				new StringToDoubleUpdateStrategy(), new NumberToStringUpdateStrategy());
	}



	@Override
	public void propertyChange(PropertyChangeEvent pEvt) {
		if (pEvt != null && pEvt.getPropertyName() != null) {
			String propertyName = pEvt.getPropertyName();

			// Auf folgende Events das Databinding erneuern:
			if (PropertyChangeTypes.PROJECT_PARAMETER.equals(propertyName)) { // Parameter des Projektes wurden neu gesetzt
				resetBinding();
			}

			if (PropertyChangeTypes.PROJECT_TSPDATA.equals(propertyName)) {
				getController().getProject().getTSPData().addPropertyChangeListener(this);
			}

			// Auf folgende Events die Validität der Lösungsdatei überprüfen:
			if (PropertyChangeTypes.PROJECT_TSPDATA.equals(propertyName) // TSP Daten des Projektes wurden neu gesetzt
					|| PropertyChangeTypes.TSPDATA_NODELIST.equals(propertyName) // Knotenliste wurde neu gesetzt
					|| PropertyChangeTypes.TSPDATA_NODELIST_ADD.equals(propertyName) // Ein Knoten wurde hinzugefügt
					|| PropertyChangeTypes.TSPDATA_NODELIST_REMOVE.equals(propertyName)) { // Ein Knoten wurde entfernt
				evaluateSolutionAccept();
				evaluateStartEnabled();
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
		AntProject project = getController().getProject();
		enable = enable && project.getTSPData().getNodeList().size() > 1;
		if (_rOptTourFilePath.getButton().getSelection()) {
			enable = enable
					&& !(project.getOptimalTourIndeces() == null || (project.getTSPData().getNodeList().size() != project.getOptimalTourIndeces()
							.size() - 1));
		}
		_bStart.getButton().setEnabled(enable);
	}



	/**
	 * Setzte das Bild des Lösungsdateiakzeptanz-Labels entsprechend der Validität der Lösungsdatei. Die Lösungsdatei ist genau dann valide, wenn sie
	 * die gleiche Länge wie die Liste der Knoten aufweist.
	 */
	private void evaluateSolutionAccept() {
		AntProject project = getController().getProject();
		if (project.getOptimalTourIndeces() == null || (project.getOptimalTourIndeces().size() != project.getTSPData().getNodeList().size())) {
			_lAccept.getLabel().setImage(Images.CROSS);
		}
		else {
			_lAccept.getLabel().setImage(Images.TICK);
		}
	}



	@Override
	public void algorithmStarted() {
		_buttonState = true;
		_bStart.getButton().setText("STOP");
		_rIterationCount.getButton().setEnabled(false);
		_rMaximumTourLength.getButton().setEnabled(false);
		_rOptTourFilePath.getButton().setEnabled(false);
		if (_rIterationCount.getButton().getSelection()) {
			_tIterationCount.getText().setEnabled(false);
		}
		if (_rMaximumTourLength.getButton().getSelection()) {
			_tMaximumTourLength.getText().setEnabled(false);
		}
		if (_rOptTourFilePath.getButton().getSelection()) {
			_bOptTourFilePath.getButton().setEnabled(false);
		}
	}



	@Override
	public void algorithmStopped() {
		_buttonState = false;
		// Dieses Event kommt unter Umständen nicht aus dem UI-Thread, deswegen müssen Operationen am Widget mit Display.syncExec() ausgeführt werden
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				_bStart.getButton().setText("START");
				_rIterationCount.getButton().setEnabled(true);
				_rMaximumTourLength.getButton().setEnabled(true);
				_rOptTourFilePath.getButton().setEnabled(true);
				if (_rIterationCount.getButton().getSelection()) {
					_tIterationCount.getText().setEnabled(true);
				}
				if (_rMaximumTourLength.getButton().getSelection()) {
					_tMaximumTourLength.getText().setEnabled(true);
				}
				if (_rOptTourFilePath.getButton().getSelection()) {
					_bOptTourFilePath.getButton().setEnabled(true);
				}
			}
		});
	}



	@Override
	public void iterationModeChanged(IterationMode pMode) {
		// NO-OP
	}

}
