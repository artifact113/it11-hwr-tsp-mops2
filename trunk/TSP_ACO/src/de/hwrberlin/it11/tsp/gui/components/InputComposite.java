/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import net.miginfocom.swt.MigLayout;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.constant.IterationMode;
import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.controller.AntController;
import de.hwrberlin.it11.tsp.gui.widgets.AntText;
import de.hwrberlin.it11.tsp.interfaces.AlgorithmListener;
import de.hwrberlin.it11.tsp.interfaces.AllInputValidListener;
import de.hwrberlin.it11.tsp.interfaces.AllInputValidValidator;
import de.hwrberlin.it11.tsp.model.Parameter;

/**
 * Das InputComposite regelt die Darstellung der Parameter.
 * 
 * @author Patrick Szostack
 * 
 */
public class InputComposite extends ADataBindableComposite implements PropertyChangeListener, AlgorithmListener {

	/** Textfeld zum Verändern der Anzahl der Ameisen */
	private AntText _tAntCount;

	/** Textfeld zum Verändern des Pheromonenparameters */
	private AntText _tPheromonParameter;

	/** Textfeld zum Verändern des Parameters der lokalen Information */
	private AntText _tLocalInformation;

	/** Textfeld zum Verändern des Verdunstungsparameters */
	private AntText _tEvaporationParameter;

	/** Textfeld zum Verändern des initialen Pheromonenwertes */
	private AntText _tInitialPheromonParameter;

	/** Textfeld zum Verändern des Pheromonupdateparameters */
	private AntText _tPheromonUpdateParameter;

	private List<AllInputValidListener> _allInputValidListenerList;



	/**
	 * Erzeugt ein neues InputComposite.
	 * 
	 * @param pParent
	 *            das Eltern-Composite
	 * @param pStyle
	 *            der Style des Composite
	 * @param pController
	 *            der Controller dieses DrawComposite
	 */
	public InputComposite(Composite pParent, int pStyle, AntController pController) {
		super(pParent, pStyle, pController);

		getController().getProject().addPropertyChangeListener(this);
		getController().addAlgorithmListener(this);

		Composite comp = new Composite(this, SWT.NONE);
		comp.setLayout(new MigLayout("fill, wrap 2, ins 0", "[pref!][]"));
		comp.setLayoutData("hmin pref, wmin pref, growx, pushx");

		Label lAntCount = new Label(comp, SWT.NONE);
		lAntCount.setLayoutData("hmin 0, wmin 0");
		lAntCount.setText("Anzahl der Ameisen:");

		_tAntCount = new AntText(new Text(comp, SWT.BORDER), getController().getProject());
		_tAntCount.getText().setLayoutData("hmin pref, wmin 50, growx");
		_tAntCount.setTooltipText("Hier können Sie einstellen, wie viele Ameisen pro Iterationen den Weg suchen sollen. (X > 0)");
		_tAntCount.setInputMode(AntText.INTEGER_ONLY);
		_tAntCount.setNumberRange(1, Double.POSITIVE_INFINITY, true, true);

		Label lPheromonParameter = new Label(comp, SWT.NONE);
		lPheromonParameter.setLayoutData("hmin 0, wmin 0");
		lPheromonParameter.setText("Pheromonparameter:");

		_tPheromonParameter = new AntText(new Text(comp, SWT.BORDER), getController().getProject());
		_tPheromonParameter.getText().setLayoutData("hmin pref, wmin 50, growx");
		_tPheromonParameter
				.setTooltipText("Hier können Sie einstellen, wie wichtig das Pheromon auf einer Kante bei der Auswahl des Weges ist. (0 < X <= 5)");
		_tPheromonParameter.setInputMode(AntText.DOUBLE_ONLY);
		_tPheromonParameter.setNumberRange(0, 5, false, true);

		Label lLocalInformation = new Label(comp, SWT.NONE);
		lLocalInformation.setLayoutData("hmin 0, wmin 0");
		lLocalInformation.setText("Lokale Information:");

		_tLocalInformation = new AntText(new Text(comp, SWT.BORDER), getController().getProject());
		_tLocalInformation.getText().setLayoutData("hmin pref, wmin 50, growx");
		_tLocalInformation.setTooltipText("Hier können Sie einstellen, wie wichtig kürzere Wege bei der Auswahl des Weges sind. (0 < X <= 5)");
		_tLocalInformation.setInputMode(AntText.DOUBLE_ONLY);
		_tLocalInformation.setNumberRange(0, 5, false, true);

		Label lEvaporationParameter = new Label(comp, SWT.NONE);
		lEvaporationParameter.setLayoutData("hmin 0, wmin 0");
		lEvaporationParameter.setText("Verdunstungsparameter:");

		_tEvaporationParameter = new AntText(new Text(comp, SWT.BORDER), getController().getProject());
		_tEvaporationParameter.getText().setLayoutData("hmin pref, wmin 50, growx");
		_tEvaporationParameter.setTooltipText("Hier können Sie einstellen, wie viele Pheromone nach einer Iteration verdunsten sollen. (0 < X <= 1)");
		_tEvaporationParameter.setInputMode(AntText.DOUBLE_ONLY);
		_tEvaporationParameter.setNumberRange(0, 1, false, true);

		Label lInitialPheromonParameter = new Label(comp, SWT.NONE);
		lInitialPheromonParameter.setLayoutData("hmin 0, wmin 0");
		lInitialPheromonParameter.setText("Initialer Pheromonwert:");

		_tInitialPheromonParameter = new AntText(new Text(comp, SWT.BORDER), getController().getProject());
		_tInitialPheromonParameter.getText().setLayoutData("hmin pref, wmin 50, growx");
		_tInitialPheromonParameter
				.setTooltipText("Hier können Sie einstellen, wie viele Pheromone auf den Kanten beim Start des Suchvorganges liegen sollen. (X > 0)");
		_tInitialPheromonParameter.setInputMode(AntText.DOUBLE_ONLY);
		_tInitialPheromonParameter.setNumberRange(0, Double.POSITIVE_INFINITY, false, true);

		Label lPheromonUpdateParameter = new Label(comp, SWT.NONE);
		lPheromonUpdateParameter.setLayoutData("hmin 0, wmin 0");
		lPheromonUpdateParameter.setText("Pheromonupdateparameter:");

		_tPheromonUpdateParameter = new AntText(new Text(comp, SWT.BORDER), getController().getProject());
		_tPheromonUpdateParameter.getText().setLayoutData("hmin pref, wmin 50, growx");
		_tPheromonUpdateParameter
				.setTooltipText("Hier können Sie einstellen, wie viele Pheromone eine Ameise auf eine Kante legt, wenn sie darüber gelaufen ist. (X > 0)");
		_tPheromonUpdateParameter.setInputMode(AntText.DOUBLE_ONLY);
		_tPheromonUpdateParameter.setNumberRange(0, Double.POSITIVE_INFINITY, false, true);

		_allInputValidListenerList = new ArrayList<AllInputValidListener>();

		new AllInputValidValidator(_allInputValidListenerList, _tAntCount, _tEvaporationParameter, _tInitialPheromonParameter, _tLocalInformation,
				_tPheromonParameter, _tPheromonUpdateParameter);

		resetBinding();
	}



	@Override
	protected void bindValues(DataBindingContext pDBC, Realm pRealm) {
		Parameter parameter = getController().getProject().getParameter();

		// Anzahl der Ameisen
		pDBC.bindValue(SWTObservables.observeText(_tAntCount.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_ANTCOUNT));
		// Pheromonparameter
		pDBC.bindValue(SWTObservables.observeText(_tPheromonParameter.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_PHEROMONPARAMETER));
		// Lokale Information
		pDBC.bindValue(SWTObservables.observeText(_tLocalInformation.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_LOCALINFORMATION));
		// Verdunstungsparameter
		pDBC.bindValue(SWTObservables.observeText(_tEvaporationParameter.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_EVAPORATIONPARAMETER));
		// Initialer Pheromonwert
		pDBC.bindValue(SWTObservables.observeText(_tInitialPheromonParameter.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_INITIALPHEROMONPARAMETER));
		// Pheromonupdateparameter
		pDBC.bindValue(SWTObservables.observeText(_tPheromonUpdateParameter.getText(), SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_PHEROMONUPDATEPARAMETER));
	}



	@Override
	public void propertyChange(PropertyChangeEvent pEvt) {
		if (pEvt != null) {
			String propertyName = pEvt.getPropertyName();

			// Auf folgende Events das Databinding erneuern:
			if (PropertyChangeTypes.PROJECT_PARAMETER.equals(propertyName)) { // Parameter des Projektes wurden neu gesetzt
				resetBinding();
			}
		}
	}



	/**
	 * Fügt einen einen AllInputValidListener der Kollektion an Listenern hinzu, der den Validierungszustand aller AntText dieses Composites
	 * überwacht.
	 * 
	 * @param pListener
	 *            der hinzuzufügende AllInputValidListener
	 */
	public void addAllInputValidListener(AllInputValidListener pListener) {
		_allInputValidListenerList.add(pListener);
	}



	/**
	 * Entfernt den angegebenen AllInputValidListener aus der Kollektion der Listener.
	 * 
	 * @param pListener
	 *            der zu entfernende AllInputValidListener
	 */
	public void removeAllInputValidListener(AllInputValidListener pListener) {
		_allInputValidListenerList.remove(pListener);
	}



	@Override
	public void algorithmStarted() {
		_tAntCount.getText().setEnabled(false);
		_tEvaporationParameter.getText().setEnabled(false);
		_tInitialPheromonParameter.getText().setEnabled(false);
		_tLocalInformation.getText().setEnabled(false);
		_tPheromonParameter.getText().setEnabled(false);
		_tPheromonUpdateParameter.getText().setEnabled(false);
	}



	@Override
	public void algorithmStopped() {
		// Dieses Event kommt unter Umständen nicht aus dem UI-Thread, deswegen müssen Operationen am Widget mit Display.syncExec() ausgeführt werden
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				_tAntCount.getText().setEnabled(true);
				_tEvaporationParameter.getText().setEnabled(true);
				_tInitialPheromonParameter.getText().setEnabled(true);
				_tLocalInformation.getText().setEnabled(true);
				_tPheromonParameter.getText().setEnabled(true);
				_tPheromonUpdateParameter.getText().setEnabled(true);
			}
		});
	}



	@Override
	public void iterationModeChanged(IterationMode pMode) {
		// NO-OP
	}

}
