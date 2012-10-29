/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.interfaces;

import java.util.List;

import de.hwrberlin.it11.tsp.gui.widgets.AntText;

/**
 * Diese Klasse überwacht eine Menge an AntText auf Änderung ihres Validierungszustandes. Es werden alle spezifizierten Listener benachrichtigt, wenn
 * sich der Gesamtvalidierungszustand ändert.
 */
public class AllInputValidValidator implements ValidInputListener {

	/** Die überwachten AntText */
	private AntText[] _antTexts;

	/** Die Liste an zu benachrichtigen Listener */
	private List<AllInputValidListener> _listenerList;



	/**
	 * Erzeugt einen neuen AllInputValidValidator.
	 * <p>
	 * Alle Elemente der übergebenen AllInputValidListener-Liste werden folgenderweise benachrichtigt:
	 * <p>
	 * true wenn ALLE überwachten AntText true mit ihrer isValidInput()-Methode zurückgeben
	 * <p>
	 * false wenn MINDESTENS EIN überwachtes AntText false mit seiner isValidInput()-Methode zurückgibt
	 * 
	 * @param pListenerList
	 *            die Liste der zu benachrichtigen AllInputValidListener
	 * @param pAntTexts
	 *            die Menge an zu überwachenden AntText
	 */
	public AllInputValidValidator(List<AllInputValidListener> pListenerList, AntText... pAntTexts) {
		_antTexts = pAntTexts;
		_listenerList = pListenerList;

		for (AntText antText : pAntTexts) {
			antText.addValidInputListener(this);
		}
	}



	@Override
	public void validStatusChanged(boolean pValid) {
		for (AntText antText : _antTexts) {
			if (!antText.isValidInput()) {
				for (AllInputValidListener listener : _listenerList) {
					listener.isValid(false);
				}
				return;
			}
		}
		for (AllInputValidListener listener : _listenerList) {
			listener.isValid(true);
		}
	}

}
