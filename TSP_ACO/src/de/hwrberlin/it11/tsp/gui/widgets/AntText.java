/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.widgets;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.constant.Colors;
import de.hwrberlin.it11.tsp.constant.Utility;
import de.hwrberlin.it11.tsp.interfaces.ValidInputListener;
import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * Die Text-Implementierung des AAntControl. Das AntText stellt extra Funktionaltäten für die Spezifizierung des Inputmodus und Beschränkung des
 * Zahlenbereiches bereit.
 * 
 * @author Patrick Szostack
 * 
 */
public class AntText extends AAntControl {

	/** Inputmodus für ganze Zahlen */
	public static final int INTEGER_ONLY = 1;

	/** Inputmodus für Dezimalzahlen */
	public static final int DOUBLE_ONLY = 2;

	/** Der Inputmodus dieser Instanz */
	private int _inputMode;

	/** Die untere Zahlenbereichsgrenze dieser Instanz */
	private double _lowerBorder;

	/** Die obere Zahlenbereichsgrenze dieser Instanz */
	private double _upperBorder;

	/** Indikator, ob der Zahlenwert auch den Wert der unteren Grenze annehmen darf */
	private boolean _includeLowerBorder;

	/** Indikator, ob der Zahlenwert auch den Wert der oberen Grenze annehmen darf */
	private boolean _includeUpperBorder;

	/** Indikator für einen validen Inputtext */
	private boolean _validInput;

	private List<ValidInputListener> _validInputListenerList;



	/**
	 * Erzeugt ein neues AntText. Die Zahlenbereichsgrenzen werden jeweils auf Double.NEGATIVE_INFINITY und Double.POSITIVE_INFINITY initialisiert.
	 * Die "include border" booleans werden beide auf true initialisiert. Inputmodus wird nicht weiter spezifiert, somit ist jeder eingegebene Text
	 * zulässig. Das Text wird mit validem Input initialisiert.
	 * 
	 * @param pText
	 *            das zu wrappende Text
	 * @param pProject
	 *            das benutzte AntProject
	 */
	public AntText(Text pText, AntProject pProject) {
		super(pText, pProject);
		_lowerBorder = Double.NEGATIVE_INFINITY;
		_upperBorder = Double.POSITIVE_INFINITY;
		_includeLowerBorder = true;
		_includeUpperBorder = true;
		_validInput = true;
		_validInputListenerList = new ArrayList<ValidInputListener>();
	}



	/**
	 * Gibt das gewrappte Text zurück.
	 * 
	 * @return das gewrappte Text
	 */
	public Text getText() {
		return (Text) getControl();
	}



	/**
	 * Gibt zurück, ob das Text einen validen Inpput hat.
	 * 
	 * @return true, wenn der Text valide ist, andernfalls false
	 */
	public boolean isValidInput() {
		return _validInput;
	}



	/**
	 * Setzt den Inputmodus auf den angegebenen InputModus. Momentan zulässige Modi sind AntText.DOUBLE_ONLY und AntText.INTEGER_ONLY
	 * 
	 * @param pMode
	 *            den zu setzenden Inputmodus
	 */
	public void setInputMode(int pMode) {
		if (!(pMode == DOUBLE_ONLY || pMode == INTEGER_ONLY)) {
			throw new IllegalArgumentException("Erlaubte Inputtypen sind INTEGER_ONLY oder DOUBLE_ONLY.");
		}
		_inputMode = pMode;
		getText().addModifyListener(new NumberRangeListener());
	}



	/**
	 * Spezifiziert einen validen Zahlenbereich. Für eine offene untere Grenze muss Double.NEGATIVE_INFINITY und für eine offene obere Grenze
	 * Double.POSITIVE_INFINITY angegeben werden.
	 * 
	 * @param pFrom
	 *            die untere Grenze des Zahlenbereiches
	 * @param pTo
	 *            die obere Grenze des Zahlenbereiches
	 * @param pIncludeFrom
	 *            ob der Wert der unteren Grenze selbst als validen Wert angesehen werden soll
	 * @param pIncludeTo
	 *            ob der Wert der oberen Grenze selbst als validen Wert angesehen werden soll
	 */
	public void setNumberRange(double pFrom, double pTo, boolean pIncludeFrom, boolean pIncludeTo) {
		_lowerBorder = pFrom;
		_upperBorder = pTo;
		_includeLowerBorder = pIncludeFrom;
		_includeUpperBorder = pIncludeTo;
	}



	/**
	 * Fügt den angegebenen ValidInputListener der Liste an ValidInputListener hinzu.
	 * 
	 * @param pListener
	 *            der hinzuzufügende ValidInputListener
	 */
	public void addValidInputListener(ValidInputListener pListener) {
		_validInputListenerList.add(pListener);
	}



	/**
	 * Entfernt den angegebenen ValidInputListener aus der Liste an ValidInputListener.
	 * 
	 * @param pListener
	 *            der zu entfernende ValidInputListener
	 */
	public void removeValidInputListener(ValidInputListener pListener) {
		_validInputListenerList.remove(pListener);
	}



	/**
	 * Diese Klasse führt die Überprüfung auf validen Zahleninput aus. Wenn es valide ist, wird die Hintergrundfarbe des Text auf Weiß und die
	 * valid-Flag auf true gesetzt. Wenn nicht, wird die Hintergrundfarbe des Text auf Rot und die valid-Flag auf false gesetzt.
	 * 
	 * @author Patrick Szostack
	 * 
	 */
	private class NumberRangeListener implements ModifyListener {

		@Override
		public void modifyText(ModifyEvent pE) {
			Text text = getText();
			if (_inputMode == INTEGER_ONLY) {
				// Überprüfen, ob sich der text in einen Integer parsen lässt.
				try {
					Integer.parseInt(text.getText());
				}
				catch (NumberFormatException pEx) {
					setValid(false);
					return;
				}
				// Der Text an sich ist valide, jetzt müssen wir überprüfen, ob die Zahl sich auch im Zahlenbereich befindet
				if (!checkNumberRange(Integer.parseInt(text.getText()))) {
					setValid(false);
				}
				else {
					setValid(true);
				}
			}
			else if (_inputMode == DOUBLE_ONLY) {
				// Überprüfen, ob der Text im Textfeld das folgende Muster hat:
				// Ein oder beliebig viele numerische Zeichen, gefolgt von keiner oder einer Kombination aus einem Dezimalseparator und einem oder
				// beliebig vielen numerischen Zeichen
				if (!text.getText().matches("\\d+(" + Utility.getDecimalSeparator() + "{1}\\d+)?")) {
					setValid(false);
					return;
				}
				// Der Text an sich ist valide, jetzt müssen wir überprüfen, ob die Zahl sich auch im Zahlenbereich befindet
				try {
					if (!checkNumberRange(Utility.FORMAT.parse(text.getText()).doubleValue())) {
						setValid(false);
					}
					else {
						setValid(true);
					}
				}
				catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}



		/**
		 * Überprüft, ob sich die angegebene Zahl im zuvor spezifizierten Zahlenbereich befindet.
		 * 
		 * @param pNumber
		 *            die zu überprüfende Zahl
		 * @return true, wenn sie sich im Zahlenbereich befindet, andernfalls false
		 */
		private boolean checkNumberRange(double pNumber) {
			if (_includeLowerBorder) {
				if (_lowerBorder > pNumber) {
					return false;
				}
			}
			else {
				if (_lowerBorder >= pNumber) {
					return false;
				}
			}
			if (_includeUpperBorder) {
				if (_upperBorder < pNumber) {
					return false;
				}
			}
			else {
				if (_upperBorder <= pNumber) {
					return false;
				}
			}
			return true;
		}



		/**
		 * Kleine Utitlity-Methode zum Setzen des Validierungsstatus'. Setzt die valid-Flag dieser Instanz entsprechend dem Parameter und passt die
		 * Hintergrundfarbe des Text antsprechend an. Alle registrierten ValidInputListener werden benachrichtigt.
		 * 
		 * @param pValid
		 *            ob der Input valide ist oder nicht
		 */
		private void setValid(final boolean pValid) {
			_validInput = pValid;
			if (pValid) {
				getText().setBackground(Colors.WHITE);
			}
			else {
				getText().setBackground(Colors.RED);
			}
			for (ValidInputListener listener : _validInputListenerList) {
				listener.validStatusChanged(pValid);
			}
		}

	}

}
