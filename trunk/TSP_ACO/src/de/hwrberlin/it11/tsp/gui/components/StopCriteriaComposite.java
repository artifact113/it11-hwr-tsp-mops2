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
import de.hwrberlin.it11.tsp.gui.listener.VerifyDoubleListener;
import de.hwrberlin.it11.tsp.gui.widgets.AAntControl;
import de.hwrberlin.it11.tsp.model.Parameter;
import de.hwrberlin.it11.tsp.persistence.Persister;

/**
 * Das StopCriteriaComposite lässt das Abbruchkriterium festlegen und den Algrotihmus starten.
 * 
 * @author Patrick Szostack
 * 
 */
public class StopCriteriaComposite extends ADataBindableComposite implements PropertyChangeListener {

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

		new AAntControl(_rIterationCount, getController().getProject(),
				"Diese Option lässt den Suchvorgang stoppen, nachdem eine eingestellte Anzahl an Iterationen durchgeführt wurden.");

		_tIterationCount = new Text(comp, SWT.BORDER);
		_tIterationCount.setLayoutData("hmin pref, wmin 50, spanx 2, growx");
		// _tIterationCount.addVerifyListener(VerifyIntegerListener.getInstance());

		new AAntControl(_tIterationCount, getController().getProject(), "Hier können Sie einstellen, wie oft iteriert werden soll.");

		_rMaximumTourLength = new Button(comp, SWT.RADIO);
		_rMaximumTourLength.setLayoutData("hmin 0, wmin 0");
		_rMaximumTourLength.setText("Tourlänge:");

		new AAntControl(_rMaximumTourLength, getController().getProject(),
				"Diese Option lässt den Suchvorgang stoppen, nachdem eine Tour gefunden wurde, die kürzer oder gleich lang als eine eingestellte Länge ist.");

		_tMaximumTourLength = new Text(comp, SWT.BORDER);
		_tMaximumTourLength.setLayoutData("hmin pref, wmin 50, spanx 2, growx");
		_tMaximumTourLength.addVerifyListener(VerifyDoubleListener.getInstance());

		new AAntControl(_tMaximumTourLength, getController().getProject(),
				"Hier können Sie einstellen, bei welcher Tourlänge abegebrochen werden soll.");

		_rOptTourFilePath = new Button(comp, SWT.RADIO);
		_rOptTourFilePath.setLayoutData("hmin 0, wmin 0");
		_rOptTourFilePath.setText("Optimale Tour:");

		new AAntControl(_rOptTourFilePath, getController().getProject(),
				"Diese Option lässt den Suchvorgang stoppen, wenn eine eingestellte Lösung gefunden wurde.");

		_tOptTourFilePath = new Text(comp, SWT.BORDER | SWT.READ_ONLY);
		_tOptTourFilePath.setLayoutData("hmin pref, wmin 50, growx, pushx");

		new AAntControl(_tOptTourFilePath, getController().getProject(), "Hier steht der Dateipfad zu der Lösungsdatei.");

		_bOptTourFilePath = new Button(comp, SWT.PUSH);
		_bOptTourFilePath.setLayoutData("hmin 0, wmin 0");
		_bOptTourFilePath.setImage(Images.FOLDER);
		_bOptTourFilePath.addSelectionListener(new SelectionAdapter() {

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
						_tOptTourFilePath.setText(path);
						getController().getProject().setOptimalTourIndeces(indexList);
					}
				}
			}

		});

		new AAntControl(_bOptTourFilePath, getController().getProject(),
				"Ein Klick auf diesen Button öffnet den Datei-Browser, mit dem Sie eine Lösungsdatei aussuchen können.");

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

		new AAntControl(bStart, getController().getProject(), "Dieser Button startet und stoppt den Suchvorgang.");

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
