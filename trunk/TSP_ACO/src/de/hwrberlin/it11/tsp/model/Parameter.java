/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.model;

import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;

/**
 * Die Parameter-Klasse dient zur Speicherung aller eingegebenen Parameter.
 * 
 * @author Patrick Szostack
 * 
 */
public class Parameter extends APropertyChangeSupport {

	/** Anzahl der Ameisen */
	private int _antCount;

	/** Anzahl der Iterationen */
	private int _iterationCount;

	/** Pheromonparameter */
	private double _pheromonParameter;

	/** Heuristischer Parameter für die lokale Information */
	private double _localInformation;

	/** Verdunstungsfaktor */
	private double _evaporationParameter;

	/** Initiale Pheromonwerte */
	private double _initialPheromonParameter;

	/** Heuristischer Parameter für Pheromon-Update */
	private double _pheromonUpdateParameter;

	/** Die maximale Länge einer Tour, damit sie als Lösung akzeptiert wird */
	private double _maximumTourLength;

	/** Der Zoom-Faktor, der die Städtedarstellung vergrößert/verkleinert, in Prozent */
	private double _zoomFactor;



	/**
	 * Erstellt eine neue Parameter-Instanz mit default-Werten.
	 */
	public Parameter() {
		_antCount = 50;
		_iterationCount = 5000;
		_pheromonParameter = 1;
		_localInformation = 2;
		_evaporationParameter = 0.2;
		_initialPheromonParameter = 0.5;
		_pheromonUpdateParameter = 0.5;
		_zoomFactor = 0.5;
		_maximumTourLength = 1;
	}



	/**
	 * @return the antCount
	 */
	public int getAntCount() {
		return _antCount;
	}



	/**
	 * @param pAntCount
	 *            the antCount to set
	 */
	public void setAntCount(int pAntCount) {
		if (pAntCount > 0) {
			firePropertyChange(PropertyChangeTypes.PARAMETER_ANTCOUNT, _antCount, _antCount = pAntCount);
		}
	}



	/**
	 * @return the iterationCount
	 */
	public int getIterationCount() {
		return _iterationCount;
	}



	/**
	 * @param pIterationCount
	 *            the iterationCount to set
	 */
	public void setIterationCount(int pIterationCount) {
		if (pIterationCount > 0) {
			firePropertyChange(PropertyChangeTypes.PARAMETER_ITERATIONCOUNT, _iterationCount, _iterationCount = pIterationCount);
		}
	}



	/**
	 * @return the pheromonParameter
	 */
	public double getPheromonParameter() {
		return _pheromonParameter;
	}



	/**
	 * @param pPheromonParameter
	 *            the pheromonParameter to set
	 */
	public void setPheromonParameter(double pPheromonParameter) {
		if (pPheromonParameter > 0 && pPheromonParameter <= 5) {
			firePropertyChange(PropertyChangeTypes.PARAMETER_PHEROMONPARAMETER, _pheromonParameter, _pheromonParameter = pPheromonParameter);
		}
	}



	/**
	 * @return the localInformation
	 */
	public double getLocalInformation() {
		return _localInformation;
	}



	/**
	 * @param pLocalInformation
	 *            the localInformation to set
	 */
	public void setLocalInformation(double pLocalInformation) {
		if (pLocalInformation > 0 && pLocalInformation <= 5) {
			firePropertyChange(PropertyChangeTypes.PARAMETER_LOCALINFORMATION, _localInformation, _localInformation = pLocalInformation);
		}
	}



	/**
	 * @return the evaporationParameter
	 */
	public double getEvaporationParameter() {
		return _evaporationParameter;
	}



	/**
	 * @param pEvaporationParameter
	 *            the evaporationParameter to set
	 */
	public void setEvaporationParameter(double pEvaporationParameter) {
		if (pEvaporationParameter > 0 && pEvaporationParameter <= 1) {
			firePropertyChange(PropertyChangeTypes.PARAMETER_EVAPORATIONPARAMETER, _evaporationParameter,
					_evaporationParameter = pEvaporationParameter);
		}
	}



	/**
	 * @return the initialPheromonParameter
	 */
	public double getInitialPheromonParameter() {
		return _initialPheromonParameter;
	}



	/**
	 * @param pInitialPheromonParameter
	 *            the initialPheromonParameter to set
	 */
	public void setInitialPheromonParameter(double pInitialPheromonParameter) {
		if (pInitialPheromonParameter > 0) {
			firePropertyChange(PropertyChangeTypes.PARAMETER_INITIALPHEROMONPARAMETER, _initialPheromonParameter,
					_initialPheromonParameter = pInitialPheromonParameter);
		}
	}



	/**
	 * @return the pheromonUpdateParameter
	 */
	public double getPheromonUpdateParameter() {
		return _pheromonUpdateParameter;
	}



	/**
	 * @param pPheromonUpdateParameter
	 *            the pheromonUpdateParameter to set
	 */
	public void setPheromonUpdateParameter(double pPheromonUpdateParameter) {
		if (pPheromonUpdateParameter > 0) {
			firePropertyChange(PropertyChangeTypes.PARAMETER_PHEROMONUPDATEPARAMETER, _pheromonUpdateParameter,
					_pheromonUpdateParameter = pPheromonUpdateParameter);
		}
	}



	/**
	 * @return the maximumTourLength
	 */
	public double getMaximumTourLength() {
		return _maximumTourLength;
	}



	/**
	 * @param pMaximumTourLength
	 *            the maximumTourLength to set
	 */
	public void setMaximumTourLength(double pMaximumTourLength) {
		if (pMaximumTourLength > 0) {
			firePropertyChange(PropertyChangeTypes.PARAMETER_MAXIMUMTOURLENGTH, _maximumTourLength, _maximumTourLength = pMaximumTourLength);
		}
	}



	/**
	 * @return the zoomFactor
	 */
	public double getZoomFactor() {
		return _zoomFactor;
	}



	/**
	 * @param pZoomFactor
	 *            the zoomFactor to set
	 */
	public void setZoomFactor(double pZoomFactor) {
		if (pZoomFactor >= 0.05 && pZoomFactor <= 3) {
			firePropertyChange(PropertyChangeTypes.PARAMETER_ZOOMFACTOR, _zoomFactor, _zoomFactor = pZoomFactor);
		}
	}

}
