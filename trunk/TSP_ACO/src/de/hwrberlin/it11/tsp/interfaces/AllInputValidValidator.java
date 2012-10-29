/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.interfaces;

import java.util.List;

import de.hwrberlin.it11.tsp.gui.widgets.AntText;

/**
 * Diese Klasse �berwacht eine Menge an AntText auf �nderung ihres Validierungszustandes. Es werden alle spezifizierten Listener benachrichtigt, wenn
 * sich der Gesamtvalidierungszustand �ndert.
 */
public class AllInputValidValidator implements ValidInputListener {

	/** Die �berwachten AntText */
	private AntText[] _antTexts;

	/** Die Liste an zu benachrichtigen Listener */
	private List<AllInputValidListener> _listenerList;



	/**
	 * Erzeugt einen neuen AllInputValidValidator.
	 * <p>
	 * Alle Elemente der �bergebenen AllInputValidListener-Liste werden folgenderweise benachrichtigt:
	 * <p>
	 * true wenn ALLE �berwachten AntText true mit ihrer isValidInput()-Methode zur�ckgeben
	 * <p>
	 * false wenn MINDESTENS EIN �berwachtes AntText false mit seiner isValidInput()-Methode zur�ckgibt
	 * 
	 * @param pListenerList
	 *            die Liste der zu benachrichtigen AllInputValidListener
	 * @param pAntTexts
	 *            die Menge an zu �berwachenden AntText
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
