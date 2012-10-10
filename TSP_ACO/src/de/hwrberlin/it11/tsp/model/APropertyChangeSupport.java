/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Abstrakte Klasse, die als Basisklasse für alle Beans benutzt wird. Bietet alle Grundlagen für den PropertyChangeSupport.
 * 
 * @author Patrick Szostack
 * 
 */
public class APropertyChangeSupport {

	/** Der PropertyChangeSupport, der für diese Instanz genutzt wird */
	private PropertyChangeSupport _propertyChangeSupport;



	public APropertyChangeSupport() {
		_propertyChangeSupport = new PropertyChangeSupport(this);
	}



	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener pListener) {
		_propertyChangeSupport.addPropertyChangeListener(pListener);
	}



	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(String, PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String pPropertyName, PropertyChangeListener pListener) {
		_propertyChangeSupport.addPropertyChangeListener(pPropertyName, pListener);
	}



	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener pListener) {
		_propertyChangeSupport.removePropertyChangeListener(pListener);
	}



	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(String, PropertyChangeListener)
	 */
	public void removePropertyChangeListener(String pPropertyName, PropertyChangeListener pListener) {
		_propertyChangeSupport.removePropertyChangeListener(pPropertyName, pListener);
	}



	/**
	 * Entfernt alle PropertyChangeListener, die an diesem Objet registriertwurden.
	 */
	public void removeAllPropertyChangeListeners() {
		for (PropertyChangeListener listener : _propertyChangeSupport.getPropertyChangeListeners()) {
			_propertyChangeSupport.removePropertyChangeListener(listener);
		}
	}



	/**
	 * @see PropertyChangeSupport#firePropertyChange(String, Object, Object)
	 */
	public void firePropertyChange(String pPropertyName, Object pOldValue, Object pNewValue) {
		_propertyChangeSupport.firePropertyChange(pPropertyName, pOldValue, pNewValue);
	}

}
