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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.controller.AntController;
import de.hwrberlin.it11.tsp.gui.listener.VerifyDoubleListener;
import de.hwrberlin.it11.tsp.gui.listener.VerifyIntegerListener;
import de.hwrberlin.it11.tsp.model.Parameter;

/**
 * Das InputComposite regelt die Darstellung der Parameter.
 * 
 * @author Patrick Szostack
 * 
 */
public class InputComposite extends ADataBindable implements PropertyChangeListener {

	/** Textfeld zum Verändern der Anzahl der Ameisen */
	private Text _tAntCount;

	/** Textfeld zum Verändern des Pheromonenparameters */
	private Text _tPheromonParameter;

	/** Textfeld zum Verändern des Parameters der lokalen Information */
	private Text _tLocalInformation;

	/** Textfeld zum Verändern des Verdunstungsparameters */
	private Text _tEvaporationParameter;

	/** Textfeld zum Verändern des initialen Pheromonenwertes */
	private Text _tInitialPheromonParameter;

	/** Textfeld zum Verändern des Pheromonupdateparameters */
	private Text _tPheromonUpdateParameter;



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

		Composite comp = new Composite(this, SWT.NONE);
		comp.setLayout(new MigLayout("fill, wrap 2", "[pref!][]"));
		comp.setLayoutData("hmin pref, wmin pref, growx, pushx");

		Label lAntCount = new Label(comp, SWT.NONE);
		lAntCount.setLayoutData("hmin 0, wmin 0");
		lAntCount.setText("Anzahl der Ameisen:");

		_tAntCount = new Text(comp, SWT.BORDER);
		_tAntCount.setLayoutData("hmin pref, wmin 50, growx");
		_tAntCount.addVerifyListener(VerifyIntegerListener.getInstance());

		Label lPheromonParameter = new Label(comp, SWT.NONE);
		lPheromonParameter.setLayoutData("hmin 0, wmin 0");
		lPheromonParameter.setText("Pheromonparameter:");

		_tPheromonParameter = new Text(comp, SWT.BORDER);
		_tPheromonParameter.setLayoutData("hmin pref, wmin 50, growx");
		_tPheromonParameter.addVerifyListener(VerifyDoubleListener.getInstance());

		Label lLocalInformation = new Label(comp, SWT.NONE);
		lLocalInformation.setLayoutData("hmin 0, wmin 0");
		lLocalInformation.setText("Lokale Information:");

		_tLocalInformation = new Text(comp, SWT.BORDER);
		_tLocalInformation.setLayoutData("hmin pref, wmin 50, growx");
		_tLocalInformation.addVerifyListener(VerifyDoubleListener.getInstance());

		Label lEvaporationParameter = new Label(comp, SWT.NONE);
		lEvaporationParameter.setLayoutData("hmin 0, wmin 0");
		lEvaporationParameter.setText("Verdunstungsparameter:");

		_tEvaporationParameter = new Text(comp, SWT.BORDER);
		_tEvaporationParameter.setLayoutData("hmin pref, wmin 50, growx");
		_tEvaporationParameter.addVerifyListener(VerifyDoubleListener.getInstance());

		Label lInitialPheromonParameter = new Label(comp, SWT.NONE);
		lInitialPheromonParameter.setLayoutData("hmin 0, wmin 0");
		lInitialPheromonParameter.setText("Initialer Pheromonwert:");

		_tInitialPheromonParameter = new Text(comp, SWT.BORDER);
		_tInitialPheromonParameter.setLayoutData("hmin pref, wmin 50, growx");
		_tInitialPheromonParameter.addVerifyListener(VerifyDoubleListener.getInstance());

		Label lPheromonUpdateParameter = new Label(comp, SWT.NONE);
		lPheromonUpdateParameter.setLayoutData("hmin 0, wmin 0");
		lPheromonUpdateParameter.setText("Pheromonupdateparameter:");

		_tPheromonUpdateParameter = new Text(comp, SWT.BORDER);
		_tPheromonUpdateParameter.setLayoutData("hmin pref, wmin 50, growx");
		_tPheromonUpdateParameter.addVerifyListener(VerifyDoubleListener.getInstance());

		resetBinding();
	}



	@Override
	protected void bindValues(DataBindingContext pDBC, Realm pRealm) {
		Parameter parameter = getController().getProject().getParameter();

		// Anzahl der Ameisen
		pDBC.bindValue(SWTObservables.observeText(_tAntCount, SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_ANTCOUNT));
		// Pheromonparameter
		pDBC.bindValue(SWTObservables.observeText(_tPheromonParameter, SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_PHEROMONPARAMETER));
		// Lokale Information
		pDBC.bindValue(SWTObservables.observeText(_tLocalInformation, SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_LOCALINFORMATION));
		// Verdunstungsparameter
		pDBC.bindValue(SWTObservables.observeText(_tEvaporationParameter, SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_EVAPORATIONPARAMETER));
		// Initialer Pheromonwert
		pDBC.bindValue(SWTObservables.observeText(_tInitialPheromonParameter, SWT.Modify),
				BeansObservables.observeValue(pRealm, parameter, PropertyChangeTypes.PARAMETER_INITIALPHEROMONPARAMETER));
		// Pheromonupdateparameter
		pDBC.bindValue(SWTObservables.observeText(_tPheromonUpdateParameter, SWT.Modify),
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

}
