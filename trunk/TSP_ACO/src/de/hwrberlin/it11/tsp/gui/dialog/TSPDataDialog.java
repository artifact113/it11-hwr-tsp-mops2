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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.constant.Images;
import de.hwrberlin.it11.tsp.gui.widgets.AntButton;
import de.hwrberlin.it11.tsp.gui.widgets.AntText;
import de.hwrberlin.it11.tsp.model.AntProject;
import de.hwrberlin.it11.tsp.model.TSPData;

/**
 * Dieser Dialog bietet die Möglichkeit die TSP Daten zu bearbeiten.
 * <p>
 * (NOTIZ: Momentan nur Name und Kommentar, der Rest ist festgesetzt)
 */
public class TSPDataDialog extends AAntDialog {

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
	public TSPDataDialog(Shell pParent, TSPData pTSPData, AntProject pProject) {
		super(pParent, pProject);
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
		shell.setImage(Images.PAGE_WHITE_EDIT);
		shell.setLayout(new MigLayout("fill, wrap 2", "[pref!][300!]"));
		shell.setLayoutData("hmin pref, wmin pref");

		Label lName = new Label(shell, SWT.NONE);
		lName.setText("Name:");
		lName.setLayoutData("hmin pref, wmin pref");

		final AntText tName = new AntText(new Text(shell, SWT.BORDER), getProject());
		tName.getText().setText(_tspData.getName());
		tName.getText().setLayoutData("hmin pref, wmin 50, growx");
		tName.setTooltipText("Name des TSP Projektes.");

		Label lComment = new Label(shell, SWT.NONE);
		lComment.setText("Kommentar:");
		lComment.setLayoutData("hmin pref, wmin pref");

		final AntText tComment = new AntText(new Text(shell, SWT.BORDER), getProject());
		tComment.getText().setText(_tspData.getComment());
		tComment.getText().setLayoutData("hmin pref, wmin 50, growx");
		tComment.setTooltipText("Kommentar des TSP Projektes.");

		Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setLayout(new MigLayout("wrap 2, ins 0", "[50%][50%]"));
		buttonComp.setLayoutData("hmin 0, wmin 0, growx, spanx");

		AntButton confirm = new AntButton(new Button(buttonComp, SWT.PUSH), getProject());
		confirm.getButton().setText("Speichern");
		confirm.getButton().setLayoutData("hmin pref, wmin pref, grow");
		confirm.setTooltipText("Speichert die Daten und schließt den Dialog.");
		confirm.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				_tspData.setName(tName.getText().getText());
				_tspData.setComment(tName.getText().getText());

				shell.dispose();
			}
		});

		AntButton cancel = new AntButton(new Button(buttonComp, SWT.PUSH), getProject());
		cancel.getButton().setText("Abbrechen");
		cancel.getButton().setLayoutData("hmin pref, wmin pref, grow");
		cancel.setTooltipText("Schließt den Dialog ohne eine Bearbeitung der Daten vorzunehmen.");
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
	}

}
