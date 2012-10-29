/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.gui.components;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.hwrberlin.it11.tsp.controller.AntController;

/**
 * Diese Klasse definiert einen DataBindingContext mit dem Databinding durchgef�hrt werden kann.
 */
public abstract class ADataBindableComposite extends AAntComposite {

	/** Der DataBindingContext, der f�r das Databinding verwendet wird */
	protected DataBindingContext _dbc;

	/** Der Realm, in dem das Databinding abl�uft */
	private Realm _realm;



	/**
	 * Konstruktor. Initialisiert einen Realm und einen DataBindingContext.
	 * 
	 * @param pParent
	 *            das Elternelement
	 * @param pStyle
	 *            der Style, den dieses Composite haben soll
	 * @param pProject
	 *            das Ameisenprojekt
	 */
	public ADataBindableComposite(Composite pParent, int pStyle, AntController pController) {
		super(pParent, pStyle, pController);
		_realm = SWTObservables.getRealm(Display.getDefault());
		_dbc = new DataBindingContext(_realm);
	}



	/**
	 * Vernichtet das bestehende Databinding. Ruft anschlie�end die bindValues()-Methode mit dem verwendeten DataBindingContext und Realm auf.
	 * 
	 * @see DataBindingContext#dispose()
	 * @see ADataBindableComposite#bindValues(DataBindingContext, Realm)
	 */
	protected void resetBinding() {
		_dbc.dispose();
		bindValues(_dbc, _realm);
	}



	/**
	 * Erbende Klassen erhalten hier ihre M�glichkeit ihr Databinding zu erledigen. Als Parameter werden der benutzte DataBindingContext und Realm
	 * �bergeben.
	 * 
	 * @param pDBC
	 *            der benutzte DataBindingContext
	 * @param pRealm
	 *            der Realm, in dem das Databinding abl�uft
	 */
	protected abstract void bindValues(DataBindingContext pDBC, Realm pRealm);

}
