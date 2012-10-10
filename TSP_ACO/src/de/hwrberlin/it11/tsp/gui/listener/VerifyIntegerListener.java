/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.listener;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

/**
 * Ein VerifyListener, der nur Zahlen und Backspace zulässt.
 * 
 * @author Patrick Szostack
 * 
 */
public class VerifyIntegerListener implements VerifyListener {

	/** Die einzige Instanz dieser Klasse */
	private static VerifyIntegerListener _instance;



	/** private Konstruktor, um die Instanziierung von außerhalb zu verhindern */
	private VerifyIntegerListener() {
	}



	/**
	 * Ein VerifyIntegerListener referenziert nichts und Arbeitet allein mit einem VerifyEvent, daher wird nur eine Instanz benötigt.
	 * 
	 * @return die einzige Instanz des VerifyIntegerListener
	 */
	public static VerifyIntegerListener getInstance() {
		if (_instance == null) {
			_instance = new VerifyIntegerListener();
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
		// Operation nur zulassen, wenn Zahlen oder Backspace eingegeben werden
		pE.doit = pE.text.matches("\\d+") || pE.character == '\b';
	}

}
