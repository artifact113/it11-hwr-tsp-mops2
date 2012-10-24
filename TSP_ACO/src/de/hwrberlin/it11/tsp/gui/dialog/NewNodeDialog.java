/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.gui.listener.VerifyIntegerListener;
import de.hwrberlin.it11.tsp.model.Node;

/**
 * Dialog zum erstellen einer neuen Node.
 * 
 * @author Patrick Szostack
 * 
 */
public class NewNodeDialog extends Dialog {

	/** Rückgabewert dieses Dialogs */
	private Node _result;

	/** Initiale X Koordinate */
	private int _xCoordinate;

	/** Initiale Y Koordinate */
	private int _yCoordinate;



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
	public NewNodeDialog(Shell pParent, int pXCoordinate, int pYCoordinate) {
		super(pParent);
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

		final Text tXCoordinate = new Text(shell, SWT.BORDER);
		tXCoordinate.setText(String.valueOf(_xCoordinate));
		tXCoordinate.setLayoutData("hmin pref, wmin 50, growx");
		tXCoordinate.addVerifyListener(VerifyIntegerListener.getInstance());

		Label lYCoordinate = new Label(shell, SWT.NONE);
		lYCoordinate.setText("Y Koordinate:");
		lYCoordinate.setLayoutData("hmin pref, wmin pref");

		final Text tYCoordinate = new Text(shell, SWT.BORDER);
		tYCoordinate.setText(String.valueOf(_yCoordinate));
		tYCoordinate.setLayoutData("hmin pref, wmin 50, growx");
		tYCoordinate.addVerifyListener(VerifyIntegerListener.getInstance());

		Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setLayout(new MigLayout("wrap 2, ins 0", "[50%][50%]"));
		buttonComp.setLayoutData("hmin 0, wmin 0, growx, spanx");

		Button confirm = new Button(buttonComp, SWT.PUSH);
		confirm.setText("Erstellen");
		confirm.setLayoutData("hmin pref, wmin pref, grow");
		confirm.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				_result = new Node(Integer.valueOf(tXCoordinate.getText()), Integer.valueOf(tYCoordinate.getText()));
				shell.dispose();
			}
		});

		Button cancel = new Button(buttonComp, SWT.PUSH);
		cancel.setText("Abbrechen");
		cancel.setLayoutData("hmin pref, wmin pref, grow");
		cancel.addSelectionListener(new SelectionAdapter() {

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
