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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

import de.hwrberlin.it11.tsp.constant.IterationMode;
import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.controller.AntController;
import de.hwrberlin.it11.tsp.interfaces.AlgorithmListener;
import de.hwrberlin.it11.tsp.model.Result;

/**
 * Das OutputComposite stellt die errechneten Touren und den Fortschritt dar.
 * 
 * @author Patrick Szostack
 * 
 */
public class OutputComposite extends ADataBindableComposite implements PropertyChangeListener, AlgorithmListener {

	/** Label zum Anzeigen der Besten Tour der Iteration */
	private Label _lBestTourIteration;

	/** Label zur Anzeige der Besten Tour global */
	private Label _lBestTourGlobal;

	/** Label zur Anzeige der durchschnittlichen Tour der Iteration */
	private Label _lAverageTourIteration;

	/** Label zur Anzeige der durchschnittlichen Tour global */
	private Label _lAverageTourGlobal;

	/** Label zur Anzeige der verstrichenen Zeit */
	private Label _lElapsedTime;

	/** ProgressBar zur Anzeige des Fortschritts */
	private ProgressBar _progress;

	/** Gibt an, ob die ProgressBar einen unbekannten Zustand anzeigen soll */
	private boolean _undeterminedProgress;



	/**
	 * Erstellt ein neues OutpurComposite.
	 * 
	 * @param pParent
	 *            das Eltern-Composite
	 * @param pStyle
	 *            der Style des Composite
	 * @param pController
	 *            der Controller dieses DrawComposite
	 */
	public OutputComposite(Composite pParent, int pStyle, AntController pController) {
		super(pParent, pStyle, pController);

		getController().getProject().addPropertyChangeListener(this);
		getController().getProject().getParameter().addPropertyChangeListener(this);
		getController().addAlgorithmListener(this);

		Composite comp = new Composite(this, SWT.NONE);
		comp.setLayout(new MigLayout("fill, wrap, ins 0"));
		comp.setLayoutData("hmin pref, wmin pref, growx");

		Group bestTour = new Group(comp, SWT.NONE);
		bestTour.setLayout(new MigLayout("fill, wrap 2", "[pref!][]"));
		bestTour.setLayoutData("hmin pref, wmin pref, growx");
		bestTour.setText("Beste Tour");

		Label bestTourIteration = new Label(bestTour, SWT.NONE);
		bestTourIteration.setLayoutData("hmin 0, wmin 0");
		bestTourIteration.setText("Iteration:");

		_lBestTourIteration = new Label(bestTour, SWT.NONE);
		_lBestTourIteration.setLayoutData("hmin 0, wmin 0, growx");

		Label bestTourGlobal = new Label(bestTour, SWT.NONE);
		bestTourGlobal.setLayoutData("hmin 0, wmin 0");
		bestTourGlobal.setText("Global:");

		_lBestTourGlobal = new Label(bestTour, SWT.NONE);
		_lBestTourGlobal.setLayoutData("hmin 0, wmin 0, growx");

		Group averageTour = new Group(comp, SWT.NONE);
		averageTour.setLayout(new MigLayout("fill, wrap 2", "[pref!][]"));
		averageTour.setLayoutData("hmin pref, wmin pref, growx");
		averageTour.setText("Durchschnittliche Tour");

		Label averageTourIteration = new Label(averageTour, SWT.NONE);
		averageTourIteration.setLayoutData("hmin 0, wmin 0");
		averageTourIteration.setText("Iteration:");

		_lAverageTourIteration = new Label(averageTour, SWT.NONE);
		_lAverageTourIteration.setLayoutData("hmin 0, wmin 0, growx");

		Label averageTourGlobal = new Label(averageTour, SWT.NONE);
		averageTourGlobal.setLayoutData("hmin 0, wmin 0");
		averageTourGlobal.setText("Global:");

		_lAverageTourGlobal = new Label(averageTour, SWT.NONE);
		_lAverageTourGlobal.setLayoutData("hmin 0, wmin 0, growx");

		Composite passedTimeComp = new Composite(comp, SWT.NONE);
		passedTimeComp.setLayout(new MigLayout("fill, wrap 2, ins 0 10 0 0", "[pref!][]"));
		passedTimeComp.setLayoutData("hmin pref, wmin pref, growx");

		Label elapsedTime = new Label(passedTimeComp, SWT.NONE);
		elapsedTime.setLayoutData("hmin 0, wmin 0");
		elapsedTime.setText("Verstrichene Zeit:");

		_lElapsedTime = new Label(passedTimeComp, SWT.NONE);
		_lElapsedTime.setLayoutData("hmin 0, wmin 0, growx");

		_progress = new ProgressBar(comp, SWT.HORIZONTAL | SWT.SMOOTH);
		_progress.setLayoutData("hmin 0, wmin 0, growx");
		_progress.setMaximum(getController().getProject().getParameter().getIterationCount());

		resetBinding();
	}



	@Override
	protected void bindValues(DataBindingContext pDBC, Realm pRealm) {
		Result result = getController().getProject().getResult();

		// Beste Tour global binden
		pDBC.bindValue(SWTObservables.observeText(_lBestTourGlobal),
				BeansObservables.observeValue(pRealm, result, PropertyChangeTypes.RESULT_BESTTOURLENGTHGLOBAL));
		// Beste Tour Iteration binden
		pDBC.bindValue(SWTObservables.observeText(_lBestTourIteration),
				BeansObservables.observeValue(pRealm, result, PropertyChangeTypes.RESULT_BESTTOURLENGTHITERATION));
		// Durchschnittliche Tour global binden
		pDBC.bindValue(SWTObservables.observeText(_lAverageTourGlobal),
				BeansObservables.observeValue(pRealm, result, PropertyChangeTypes.RESULT_AVERAGETOURLENGTHGLOBAL));
		// Durchschnittliche Tour Iteration binden
		pDBC.bindValue(SWTObservables.observeText(_lAverageTourIteration),
				BeansObservables.observeValue(pRealm, result, PropertyChangeTypes.RESULT_AVERAGETOURLENGTHITERATION));
		// Verstrichene Zeit binden
		pDBC.bindValue(SWTObservables.observeText(_lElapsedTime),
				BeansObservables.observeValue(pRealm, result, PropertyChangeTypes.RESULT_ELAPSEDTIME));
	}



	@Override
	public void propertyChange(final PropertyChangeEvent pEvt) {
		if (pEvt != null) {
			final String propertyName = pEvt.getPropertyName();

			// Alle Events, die nicht aus dem UI-Thread kommen, müssen mit Display.syncExec() abgearbeitet werden
			Display.getDefault().syncExec(new Runnable() {

				@Override
				public void run() {
					// Auf folgende Events den Maximalwert der ProgressBar aktualisieren:
					if (PropertyChangeTypes.PARAMETER_ITERATIONCOUNT.equals(propertyName)) { // Anzahl der Iterationen hat sich geändert
						_progress.setMaximum((Integer) pEvt.getNewValue());
					}

					// Auf folgende Events den angezeigten Wert der ProgressBar aktualisieren:
					if (PropertyChangeTypes.PROJECT_ITERATIONFINISHED.equals(propertyName)) { // Eine Iteration ist vorbei
						if (!_undeterminedProgress) {
							_progress.setSelection((Integer) pEvt.getNewValue());
						}
						else {
							_progress.setSelection(_progress.getMaximum() / 2);
						}
					}
				}
			});

			// Auf folgende Events das Databinding erneuern:
			if (PropertyChangeTypes.PROJECT_PARAMETER.equals(propertyName)) { // Parameter des Projektes wurden neu gesetzt
				getController().getProject().getParameter().addPropertyChangeListener(this);
			}
		}
	}



	@Override
	public void algorithmStarted() {
		// NO-OP
	}



	@Override
	public void algorithmStopped() {
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				_progress.setSelection(0);
			}
		});
	}



	@Override
	public void iterationModeChanged(IterationMode pMode) {
		if (pMode == IterationMode.COUNT) {
			_undeterminedProgress = false;
		}
		else {
			_undeterminedProgress = true;
		}
	}
}
