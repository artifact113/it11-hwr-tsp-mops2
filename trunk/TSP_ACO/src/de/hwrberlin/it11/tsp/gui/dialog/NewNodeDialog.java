/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import java.text.ParseException;

import net.miginfocom.swt.MigLayout;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.constant.Utility;
import de.hwrberlin.it11.tsp.gui.widgets.AntButton;
import de.hwrberlin.it11.tsp.gui.widgets.AntText;
import de.hwrberlin.it11.tsp.model.AntProject;
import de.hwrberlin.it11.tsp.model.Node;

/**
 * Dialog zum erstellen einer neuen Node.
 * 
 * @author Patrick Szostack
 * 
 */
public class NewNodeDialog extends AAntDialog {

	/** Rückgabewert dieses Dialogs */
	private Node _result;

	/** Initiale X Koordinate */
	private double _xCoordinate;

	/** Initiale Y Koordinate */
	private double _yCoordinate;



	/**
	 * Erstellt einen neuen NewNodeDialog.
	 * 
	 * @param pParent
	 *            die Parent-Shell des Dialoges
	 * @param pXCoordinate
	 *            die initiale X Koordinate, die dieser Dialog anzeigen soll
	 * @param pYCoordinate
	 *            die initiale Y Koordinate, die dieser Dialog anzeigen soll
	 * @param pProject
	 *            das AntProject des zu erstellenden Dialoges
	 */
	public NewNodeDialog(Shell pParent, AntProject pProject, double pXCoordinate, double pYCoordinate) {
		super(pParent, pProject);
		_xCoordinate = pXCoordinate;
		_yCoordinate = pYCoordinate;
	}



	/**
	 * Öffnet diesen Dialog und bingt ihn in den Vordergrund.
	 * 
	 * @return eine neu erstellte Node, falls der Erstellen-Button geklickt wurde, andernfalls null
	 */
	public Node open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText("Neue Node");
		shell.setLayout(new MigLayout("fill, wrap 2", "[pref!][100!]"));
		shell.setLayoutData("hmin pref, wmin pref");

		Label lXCoordinate = new Label(shell, SWT.NONE);
		lXCoordinate.setText("X Koordinate:");
		lXCoordinate.setLayoutData("hmin pref, wmin pref");

		final AntText tXCoordinate = new AntText(new Text(shell, SWT.BORDER), getProject());
		tXCoordinate.getText().setText(Utility.FORMAT.format(_xCoordinate));
		tXCoordinate.getText().setLayoutData("hmin pref, wmin 50, growx");
		tXCoordinate.setTooltipText("Die X Koordinate für den neuen Knoten.");
		tXCoordinate.setInputMode(AntText.DOUBLE_ONLY);
		tXCoordinate.setNumberRange(0, Double.POSITIVE_INFINITY, false, true);

		Label lYCoordinate = new Label(shell, SWT.NONE);
		lYCoordinate.setText("Y Koordinate:");
		lYCoordinate.setLayoutData("hmin pref, wmin pref");

		final AntText tYCoordinate = new AntText(new Text(shell, SWT.BORDER), getProject());
		tYCoordinate.getText().setText(Utility.FORMAT.format(_yCoordinate));
		tYCoordinate.getText().setLayoutData("hmin pref, wmin 50, growx");
		tYCoordinate.setTooltipText("Die Y Koordinate für den neuen Knoten.");
		tYCoordinate.setInputMode(AntText.DOUBLE_ONLY);
		tYCoordinate.setNumberRange(0, Double.POSITIVE_INFINITY, false, true);

		Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setLayout(new MigLayout("wrap 2, ins 0", "[50%][50%]"));
		buttonComp.setLayoutData("hmin 0, wmin 0, growx, spanx");

		AntButton confirm = new AntButton(new Button(buttonComp, SWT.PUSH), getProject());
		confirm.getButton().setText("Erstellen");
		confirm.getButton().setLayoutData("hmin pref, wmin pref, grow");
		confirm.setTooltipText("Erstellt einen neuen Knoten mit den angegebenen Koordinaten und schließt den Dialog.");
		confirm.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (tXCoordinate.isValidInput() && tYCoordinate.isValidInput()) {
					try {
						_result = new Node(Utility.FORMAT.parse(tXCoordinate.getText().getText()).doubleValue(), Utility.FORMAT.parse(
								tYCoordinate.getText().getText()).doubleValue());
					}
					catch (ParseException e) {
						MessageDialog.openError(shell, "Fehler beim umwandeln der Werte",
								"Beim Umwandeln der Werte von Text in eine Zahl ist ein Fehler aufgetreten.");
					}
					shell.dispose();
				}
				else {
					MessageDialog
							.openError(shell, "Ungültiger Wert",
									"Eines der Textfelder hat einen ungültigen Eingabewert (rot unterlegt). Um fortzufahren geben Sie bitte einen gültigen Wert ein.");
				}
			}
		});

		AntButton cancel = new AntButton(new Button(buttonComp, SWT.PUSH), getProject());
		cancel.getButton().setText("Abbrechen");
		cancel.getButton().setLayoutData("hmin pref, wmin pref, grow");
		cancel.setTooltipText("Schließt den Dialog ohne Erstellung eines Knotens.");
		cancel.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				shell.dispose();
			}
		});

		shell.pack();
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return _result;
	}

}
