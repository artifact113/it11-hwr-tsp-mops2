/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import net.miginfocom.swt.MigLayout;

import org.eclipse.core.runtime.Assert;
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

import de.hwrberlin.it11.tsp.model.TSPData;

/**
 * Dieser Dialog bietet die Möglichkeit die TSP Daten zu bearbeiten.
 * <p>
 * (NOTIZ: Momentan nur Name und Kommentar, der Rest ist festgesetzt)
 * 
 * @author Patrick Szostack
 * 
 */
public class TSPDataDialog extends Dialog {

	/** Die zu bearbeitende TSPData */
	private TSPData _tspData;



	/**
	 * Erstellt einen neuen TSPDataDialog.
	 * 
	 * @param pParent
	 *            die Parent-Shell des Dialoges
	 * @param pProject
	 *            das AntProject des zu erstellenden Dialoges
	 */
	public TSPDataDialog(Shell pParent, TSPData pTSPData) {
		super(pParent);
		Assert.isNotNull(pTSPData, "Der Parameter pTSPData darf nicht null sein.");
		_tspData = pTSPData;
	}



	/**
	 * Öffnet diesen Dialog und bingt ihn in den Vordergrund. Wird der "Speichern"-Button gedrückt werden die eingegebenen Daten in die übergebenen
	 * Daten abgespeichert.
	 */
	public void open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText("Zufallsprojekt");
		shell.setLayout(new MigLayout("fill, wrap 2", "[pref!][300!]"));
		shell.setLayoutData("hmin pref, wmin pref");

		Label lName = new Label(shell, SWT.NONE);
		lName.setText("Name:");
		lName.setLayoutData("hmin pref, wmin pref");

		final Text tName = new Text(shell, SWT.BORDER);
		tName.setText(_tspData.getName());
		tName.setLayoutData("hmin pref, wmin 50, growx");

		Label lComment = new Label(shell, SWT.NONE);
		lComment.setText("Kommentar:");
		lComment.setLayoutData("hmin pref, wmin pref");

		final Text tComment = new Text(shell, SWT.BORDER);
		tComment.setText(_tspData.getComment());
		tComment.setLayoutData("hmin pref, wmin 50, growx");

		Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setLayout(new MigLayout("wrap 2, ins 0", "[50%][50%]"));
		buttonComp.setLayoutData("hmin 0, wmin 0, growx, spanx");

		Button confirm = new Button(buttonComp, SWT.PUSH);
		confirm.setText("Speichern");
		confirm.setLayoutData("hmin pref, wmin pref, grow");
		confirm.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				_tspData.setName(tName.getText());
				_tspData.setComment(tName.getText());

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
	}

}
