/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import java.text.ParseException;

import net.miginfocom.swt.MigLayout;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.constant.Utility;
import de.hwrberlin.it11.tsp.gui.widgets.AntButton;
import de.hwrberlin.it11.tsp.gui.widgets.AntComposite;
import de.hwrberlin.it11.tsp.gui.widgets.AntText;
import de.hwrberlin.it11.tsp.model.AntProject;
import de.hwrberlin.it11.tsp.model.Preferences;

/**
 * Dieser Dialog dient zum Anpassen der Eigenschaften.
 * 
 * @author Patrick Szostack
 * 
 */
public class PreferencesDialog extends AAntDialog {

	/**
	 * Erstellt einen neuen PreferencesDialog.
	 * 
	 * @param pParent
	 *            die Parent-Shell
	 * @param pProject
	 *            das AntProject des zu erstellenden Dialoges
	 */
	public PreferencesDialog(Shell pParent, AntProject pProject) {
		super(pParent, pProject);
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

		final Preferences preferences = Preferences.getInstance();

		Label lAntialias = new Label(shell, SWT.NONE);
		lAntialias.setText("Antialiasing:");
		lAntialias.setLayoutData("hmin pref, wmin pref");

		final AntButton bAntialias = new AntButton(new Button(shell, SWT.CHECK), getProject());
		bAntialias.getButton().setSelection(preferences.isAntialias());
		bAntialias.getButton().setLayoutData("hmin pref, wmin pref");
		bAntialias.setTooltipText("Mit dieser Optionen können Sie Antialiasing an- und ausschalten.");

		Label lRedrawInterval = new Label(shell, SWT.NONE);
		lRedrawInterval.setText("Neuzeichnungsintervall:");
		lRedrawInterval.setLayoutData("hmin pref, wmin pref");

		final AntText tRedrawInterval = new AntText(new Text(shell, SWT.BORDER), getProject());
		tRedrawInterval.getText().setText(Utility.FORMAT.format(preferences.getRedrawInterval()));
		tRedrawInterval.getText().setLayoutData("hmin pref, wmin pref, grow");
		tRedrawInterval.setTooltipText("Hier können Sie einstellen, nach wie vielen Iterationen neu gezeichnet werden soll.");
		tRedrawInterval.setInputMode(AntText.INTEGER_ONLY);
		tRedrawInterval.setNumberRange(1, Double.POSITIVE_INFINITY, true, true);

		Label lNodeColor = new Label(shell, SWT.NONE);
		lNodeColor.setText("Farbe der Knoten:");
		lNodeColor.setLayoutData("hmin pref, wmin pref");

		final AntComposite cNodeColor = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		cNodeColor.setBackground(preferences.getNodeColor());
		cNodeColor.getComposite().setLayoutData("height 20!, wmin 50");
		cNodeColor.setTooltipText("Hier können Sie die Farbe einstellen, mit der die Knoten gezeichnet werden.");
		cNodeColor.getComposite().addMouseListener(new ChooseColorListener(cNodeColor));

		Label lCurrentNodeColor = new Label(shell, SWT.NONE);
		lCurrentNodeColor.setText("Farbe der ausgewählten Knoten:");
		lCurrentNodeColor.setLayoutData("hmin pref, wmin pref");

		final AntComposite cCurrentNodeColor = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		cCurrentNodeColor.setBackground(preferences.getCurrentNodeColor());
		cCurrentNodeColor.getComposite().setLayoutData("height 20!, wmin 50");
		cCurrentNodeColor.setTooltipText("Hier können Sie die Farbe einstellen, mit der die ausgewählten Knoten gezeichnet werden.");
		cCurrentNodeColor.getComposite().addMouseListener(new ChooseColorListener(cCurrentNodeColor));

		Label lBestTourGlobalColor = new Label(shell, SWT.NONE);
		lBestTourGlobalColor.setText("Farbe der besten globalen Tour:");
		lBestTourGlobalColor.setLayoutData("hmin pref, wmin pref");

		final AntComposite cBestTourGlobalColor = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		cBestTourGlobalColor.setBackground(preferences.getBestTourGlobalColor());
		cBestTourGlobalColor.getComposite().setLayoutData("height 20!, wmin 50");
		cBestTourGlobalColor.setTooltipText("Hier können Sie die Farbe einstellen, mit der die Linien der globalen besten Tour gezeichnet werden.");
		cBestTourGlobalColor.getComposite().addMouseListener(new ChooseColorListener(cBestTourGlobalColor));

		Label lBestTourIterationColor = new Label(shell, SWT.NONE);
		lBestTourIterationColor.setText("Farbe der besten Tour der Iteration:");
		lBestTourIterationColor.setLayoutData("hmin pref, wmin pref");

		final AntComposite cBestTourIterationColor = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		cBestTourIterationColor.setBackground(preferences.getBestTourIterationColor());
		cBestTourIterationColor.getComposite().setLayoutData("height 20!, wmin 50");
		cBestTourIterationColor
				.setTooltipText("Hier können Sie die Farbe einstellen, mit der die Linien der besten Tour der Iteration gezeichnet werden.");
		cBestTourIterationColor.getComposite().addMouseListener(new ChooseColorListener(cBestTourIterationColor));

		Label lBackgroundColor = new Label(shell, SWT.NONE);
		lBackgroundColor.setText("Farbe des Malflächenhintergrundes:");
		lBackgroundColor.setLayoutData("hmin pref, wmin pref");

		final AntComposite cBackgroundColor = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		cBackgroundColor.setBackground(preferences.getBackgroundColor());
		cBackgroundColor.getComposite().setLayoutData("height 20!, wmin 50");
		cBackgroundColor.setTooltipText("Hier können Sie die Hintergrundfarbe der Malfläche einstellen.");
		cBackgroundColor.getComposite().addMouseListener(new ChooseColorListener(cBackgroundColor));

		Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setLayout(new MigLayout("wrap 2, ins 0", "[50%][50%]"));
		buttonComp.setLayoutData("hmin 0, wmin 0, growx, spanx");

		AntButton confirm = new AntButton(new Button(buttonComp, SWT.PUSH), getProject());
		confirm.getButton().setText("Speichern");
		confirm.getButton().setLayoutData("hmin pref, wmin pref, grow");
		confirm.setTooltipText("Speichert die Eigenschaften und schließt den Dialog.");
		confirm.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (tRedrawInterval.isValidInput()) {
					try {
						preferences.setAntialias(bAntialias.getButton().getSelection());
						preferences.setRedrawInterval(Utility.FORMAT.parse(tRedrawInterval.getText().getText()).intValue());
						preferences.setNodeColor(cNodeColor.getBackground());
						preferences.setCurrentNodeColor(cCurrentNodeColor.getBackground());
						preferences.setBestTourGlobalColor(cBestTourGlobalColor.getBackground());
						preferences.setBestTourIterationColor(cBestTourIterationColor.getBackground());
						preferences.setBackgroundColor(cBackgroundColor.getBackground());
					}
					catch (ParseException e) {
						MessageDialog.openError(shell, "Fehler beim umwandeln der Werte",
								"Beim Umwandeln der Werte von Text in eine Zahl ist ein Fehler aufgetreten.");
					}
					shell.close();
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
		cancel.setTooltipText("Schließt den Dialog und verwirft alle Änderungen.");
		cancel.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				shell.close();
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



	/**
	 * Diese Klasse erbt von MouseAdapter und ist dafür zuständig, bei einem Klick auf ein Compsite den ColorDialog zu öffnen und die neue Farbe als
	 * Hintergrund zu setzen.
	 * 
	 * @author Patrick Szostack
	 * 
	 */
	private class ChooseColorListener extends MouseAdapter {

		/** Das AntComposite dieser Instanz */
		private AntComposite _composite;



		/**
		 * Erzeugt einen neuen ChooseColorListener.
		 * 
		 * @param pComposite
		 *            das AntComposite dieser Instanz
		 */
		public ChooseColorListener(AntComposite pComposite) {
			_composite = pComposite;
		}



		@Override
		public void mouseDown(MouseEvent pE) {
			if (pE.button == 1) {
				Color color = new ColorDialog(getParent(), getProject()).open();
				if (color != null) {
					_composite.setBackground(color);
				}
			}
		}

	}

}
