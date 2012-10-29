/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.model;

import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;

/**
 * Die Edge stellt eine Kante in dem Netz der zu besuchenden Orte dar.
 */
public class Edge extends APropertyChangeSupport {

	/** Der Pheromonwert, der auf dieser Edge liegt */
	private double _pheromone;



	// private static final double PHEROMONE_MINIMUM = Math.pow(10, -10);

	/**
	 * Erzeugt eine Edge mit dem angegebenen Pheromonwert.
	 * 
	 * @param pPheromone
	 *            der initiale Pheromonwert der Edge
	 */
	public Edge(double pPheromone) {
		_pheromone = pPheromone;
	}



	/**
	 * Gibt den Pheromonwert dieser Edge zurück.
	 * 
	 * @return den Pheromonwert dieser Edge
	 */
	public double getPheromone() {
		return _pheromone;
	}



	/**
	 * Setzt den Pheromonwert dieser Edge auf den angegebenen Pheromonwert und löst ein PropertyChangeEvent aus.
	 * <p>
	 * Da es passieren kann, dass wenig benutzte Edges bei hohen Verdunstungsparametern schnell einen sehr geringen Pheromonwert erreichen und dieser
	 * von der JVM eventuell nicht von 0 unterschieden werden kann, wird ein Pheromonwertminimum von 1.0E-10 festgelegt.
	 * 
	 * @param pPheromone
	 *            der neue Pheromonwert dieser Edge
	 */
	public void setPheromone(double pPheromone) {
		firePropertyChange(PropertyChangeTypes.EDGE_PHEROMONE, _pheromone, _pheromone = pPheromone);
		// firePropertyChange(PropertyChangeTypes.EDGE_PHEROMONE, _pheromone, pPheromone < PHEROMONE_MINIMUM ? (_pheromone = PHEROMONE_MINIMUM)
		// : (_pheromone = pPheromone));
	}

}
