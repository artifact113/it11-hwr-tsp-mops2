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

import de.hwrberlin.it11.tsp.model.Preferences;

/**
 * Dieser Dialog dient zum Anpassen der Eigenschaften.
 * 
 * @author Patrick Szostack
 * 
 */
public class PreferencesDialog extends Dialog {

	/**
	 * Erstellt einen neuen PreferencesDialog mit der angegebenen Parent-Shell.
	 * 
	 * @param pParent
	 *            die Parent-Shell
	 */
	public PreferencesDialog(Shell pParent) {
		super(pParent);
	}



	/**
	 * Öffnet diesen Dialog und bingt ihn in den Vordergrund.
	 */
	public void open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText("Eigenschaften");
		shell.setLayout(new MigLayout("fill, wrap 2"));
		shell.setLayoutData("hmin pref, wmin pref");

		Preferences preferences = Preferences.getInstance();

		Label lAntialias = new Label(shell, SWT.NONE);
		lAntialias.setText("Antialiasing:");
		lAntialias.setLayoutData("hmin pref, wmin pref");

		Button bAntialias = new Button(shell, SWT.CHECK);
		bAntialias.setSelection(preferences.isAntialias());
		bAntialias.setLayoutData("hmin pref, wmin pref");

		Label lNodeColor = new Label(shell, SWT.NONE);
		lNodeColor.setText("Farbe der Knoten:");
		lNodeColor.setLayoutData("hmin pref, wmin pref");

		Composite cNodeColor = new Composite(shell, SWT.BORDER);
		cNodeColor.setBackground(preferences.getNodeColor());
		cNodeColor.setLayoutData("height 20!, wmin 50");

		Label lCurrentNodeColor = new Label(shell, SWT.NONE);
		lCurrentNodeColor.setText("Farbe der ausgewählten Knoten:");
		lCurrentNodeColor.setLayoutData("hmin pref, wmin pref");

		Composite cCurrentNodeColor = new Composite(shell, SWT.BORDER);
		cCurrentNodeColor.setBackground(preferences.getCurrentNodeColor());
		cCurrentNodeColor.setLayoutData("height 20!, wmin 50");

		Label lBestTourGlobalColor = new Label(shell, SWT.NONE);
		lBestTourGlobalColor.setText("Farbe der besten globalen Tour:");
		lBestTourGlobalColor.setLayoutData("hmin pref, wmin pref");

		Composite cBestTourGlobalColor = new Composite(shell, SWT.BORDER);
		cBestTourGlobalColor.setBackground(preferences.getBestTourGlobalColor());
		cBestTourGlobalColor.setLayoutData("height 20!, wmin 50");

		Label lBestTourIterationColor = new Label(shell, SWT.NONE);
		lBestTourIterationColor.setText("Farbe der besten globalen Tour:");
		lBestTourIterationColor.setLayoutData("hmin pref, wmin pref");

		Composite cBestTourIterationColor = new Composite(shell, SWT.BORDER);
		cBestTourIterationColor.setBackground(preferences.getBestTourIterationColor());
		cBestTourIterationColor.setLayoutData("height 20!, wmin 50");

		Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setLayout(new MigLayout("wrap 2, ins 0", "[50%][50%]"));
		buttonComp.setLayoutData("hmin 0, wmin 0, growx, spanx");

		Button confirm = new Button(buttonComp, SWT.PUSH);
		confirm.setText("Speichern");
		confirm.setLayoutData("hmin pref, wmin pref, grow");
		confirm.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
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
