/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.listener;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

/**
 * Ein VerifyListener, der nur Zahlen, den Dezimaltrenner und Backspace zulässt.
 * 
 * @author Patrick Szostack
 * 
 */
public class VerifyDoubleListener implements VerifyListener {

	/** Die einzige Instanz dieser Klasse */
	private static VerifyDoubleListener _instance;



	/** private Konstruktor, um die Instanziierung von außerhalb zu verhindern */
	private VerifyDoubleListener() {
	}



	/**
	 * Ein VerifyDoubleListener referenziert nichts und Arbeitet allein mit einem VerifyEvent, daher wird nur eine Instanz benötigt.
	 * 
	 * @return die einzige Instanz des VerifyDoubleListener
	 */
	public static VerifyDoubleListener getInstance() {
		if (_instance == null) {
			_instance = new VerifyDoubleListener();
		}
		return _instance;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.VerifyListener#verifyText(org.eclipse.swt.events.VerifyEvent)
	 */
	@Override
	public void verifyText(VerifyEvent pE) {
		// Operation nur zulassen, wenn Zahlen, der Dezimaltrenner oder Backspace eingegeben werden
		pE.doit = pE.text.matches("[\\d,]+") || pE.character == '\b';
	}

}
