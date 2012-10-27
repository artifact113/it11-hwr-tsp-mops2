/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.hwrberlin.it11.tsp.constant.Colors;
import de.hwrberlin.it11.tsp.gui.widgets.AntButton;
import de.hwrberlin.it11.tsp.gui.widgets.AntComposite;
import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * Dieser Dialog dient zum Auswählen einer Farbe. Als Farben werden dabei alle Basisfarben der Colors Klasse angeboten.
 * 
 * @author Patrick Szostack
 * 
 */
public class ColorDialog extends AAntDialog {

	/** Der Rückgabewert dieses Dialogs */
	private Color _result;



	/**
	 * Erstellt einen neuen ColorDialog.
	 * 
	 * @param pParent
	 *            die Parent-Shell
	 * @param pProject
	 *            das AntProject des zu erstellenden Dialoges
	 */
	public ColorDialog(Shell pParent, AntProject pProject) {
		super(pParent, pProject);
	}



	/**
	 * Öffnet diesen Dialog und bingt ihn in den Vordergrund.
	 * 
	 * @return die ausgewählte Farbe oder null, wenn keine Farbe ausgewählt wurde
	 */
	public Color open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText("Farbe wählen");
		shell.setLayout(new MigLayout("fill, wrap 4", "[50!][50!][50!][50!]", "[20!][20!][20!][20!][]"));
		shell.setLayoutData("hmin pref, wmin pref");

		AntComposite black = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		black.setBackground(Colors.BLACK);
		black.getComposite().addMouseListener(new ChosenColorListener(black));

		AntComposite darkGrey = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		darkGrey.setBackground(Colors.DARK_GREY);
		darkGrey.getComposite().addMouseListener(new ChosenColorListener(darkGrey));

		AntComposite grey = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		grey.setBackground(Colors.GREY);
		grey.getComposite().addMouseListener(new ChosenColorListener(grey));

		AntComposite white = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		white.setBackground(Colors.WHITE);
		white.getComposite().addMouseListener(new ChosenColorListener(white));

		AntComposite darkBlue = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		darkBlue.setBackground(Colors.DARK_BLUE);
		darkBlue.getComposite().addMouseListener(new ChosenColorListener(darkBlue));

		AntComposite blue = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		blue.setBackground(Colors.BLUE);
		blue.getComposite().addMouseListener(new ChosenColorListener(blue));

		AntComposite darkCyan = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		darkCyan.setBackground(Colors.DARK_CYAN);
		darkCyan.getComposite().addMouseListener(new ChosenColorListener(darkCyan));

		AntComposite cyan = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		cyan.setBackground(Colors.CYAN);
		cyan.getComposite().addMouseListener(new ChosenColorListener(cyan));

		AntComposite darkRed = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		darkRed.setBackground(Colors.DARK_RED);
		darkRed.getComposite().addMouseListener(new ChosenColorListener(darkRed));

		AntComposite red = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		red.setBackground(Colors.RED);
		red.getComposite().addMouseListener(new ChosenColorListener(red));

		AntComposite darkMagenta = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		darkMagenta.setBackground(Colors.DARK_MAGENTA);
		darkMagenta.getComposite().addMouseListener(new ChosenColorListener(darkMagenta));

		AntComposite magenta = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		magenta.setBackground(Colors.MAGENTA);
		magenta.getComposite().addMouseListener(new ChosenColorListener(magenta));

		AntComposite darkGreen = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		darkGreen.setBackground(Colors.DARK_GREEN);
		darkGreen.getComposite().addMouseListener(new ChosenColorListener(darkGreen));

		AntComposite green = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		green.setBackground(Colors.GREEN);
		green.getComposite().addMouseListener(new ChosenColorListener(green));

		AntComposite darkYellow = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		darkYellow.setBackground(Colors.DARK_YELLOW);
		darkYellow.getComposite().addMouseListener(new ChosenColorListener(darkYellow));

		AntComposite yellow = new AntComposite(new Composite(shell, SWT.BORDER), getProject());
		yellow.setBackground(Colors.YELLOW);
		yellow.getComposite().addMouseListener(new ChosenColorListener(yellow));

		Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setLayout(new MigLayout("wrap 2, ins 0", "[50%][50%]"));
		buttonComp.setLayoutData("hmin 0, wmin 0, growx, spanx");

		AntButton confirm = new AntButton(new Button(buttonComp, SWT.PUSH), getProject());
		confirm.getButton().setText("Speichern");
		confirm.getButton().setLayoutData("hmin pref, wmin pref, grow");
		confirm.setTooltipText("Wählt die angeklickte Farbe aus und schließt den Dialog.");
		confirm.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				shell.close();
			}
		});

		AntButton cancel = new AntButton(new Button(buttonComp, SWT.PUSH), getProject());
		cancel.getButton().setText("Abbrechen");
		cancel.getButton().setLayoutData("hmin pref, wmin pref, grow");
		cancel.setTooltipText("Schließt den Dialog ohne eine Farbe auszuwählen.");
		cancel.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				_result = null;
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
		return _result;
	}



	/**
	 * Dieser Listener erbt von MouseAdapter und ist dazu da, bei einem Klick auf den Receiver die aktuell ausgewählte Farbe zu setzen.
	 * 
	 * @author Patrick Szostack
	 * 
	 */
	private class ChosenColorListener extends MouseAdapter {

		/** Das AntComposite, das diese Instanz repräsentiert */
		private AntComposite _composite;



		/**
		 * Erstellt einen neuen ChosenColorListener.
		 * 
		 * @param pColor
		 *            die Farbe, die diese Instanz repräsentiert
		 */
		public ChosenColorListener(AntComposite pComposite) {
			_composite = pComposite;
		}



		@Override
		public void mouseDown(MouseEvent pE) {
			if (pE.button == 1) {
				_result = _composite.getBackground();
			}
		}

	}

}
