/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.components;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.hwrberlin.it11.tsp.controller.AntController;

/**
 * Diese Klasse definiert einen DataBindingContext mit dem Databinding durchgeführt werden kann.
 * 
 * @author Patrick Szostack
 * 
 */
public abstract class ADataBindable extends AAntComposite {

	/** Der DataBindingContext, der für das Databinding verwendet wird */
	protected DataBindingContext _dbc;

	/** Der Realm, in dem das Databinding abläuft */
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
	public ADataBindable(Composite pParent, int pStyle, AntController pController) {
		super(pParent, pStyle, pController);
		_realm = SWTObservables.getRealm(Display.getDefault());
		_dbc = new DataBindingContext(_realm);
	}



	/**
	 * Vernichtet das bestehende Databinding. Ruft anschließend die bindValues()-Methode mit dem verwendeten DataBindingContext und Realm auf.
	 * 
	 * @see DataBindingContext#dispose()
	 * @see ADataBindable#bindValues(DataBindingContext, Realm)
	 */
	protected void resetBinding() {
		_dbc.dispose();
		bindValues(_dbc, _realm);
	}



	/**
	 * Erbende Klassen erhalten hier ihre Möglichkeit ihr Databinding zu erledigen. Als Parameter werden der benutzte DataBindingContext und Realm
	 * übergeben.
	 * 
	 * @param pDBC
	 *            der benutzte DataBindingContext
	 * @param pRealm
	 *            der Realm, in dem das Databinding abläuft
	 */
	protected abstract void bindValues(DataBindingContext pDBC, Realm pRealm);

}
