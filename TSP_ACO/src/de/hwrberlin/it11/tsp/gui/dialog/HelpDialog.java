/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import java.io.File;
import java.net.URISyntaxException;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.hwrberlin.it11.tsp.constant.Images;

/**
 * Dieser Dialog zeigt die Hilfe-HTML-Datei an.
 */
public class HelpDialog extends Dialog {

	/**
	 * Erstellt einen neuen HelpDialog.
	 * 
	 * @param pParent
	 *            die Parent-Shell
	 * @param pProject
	 *            das AntProject des zu erstellenden Dialoges
	 */
	public HelpDialog(Shell pParent) {
		super(pParent);
	}



	/**
	 * Öffnet diesen Dialog und bingt ihn in den Vordergrund.
	 */
	public void open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE | SWT.MAX);
		shell.setText("Hilfe");
		shell.setImage(Images.HELP);
		shell.setLayout(new MigLayout("fill, wrap"));
		shell.setLayoutData("hmin 400, wmin 500");

		Browser browser = new Browser(shell, SWT.NONE);
		browser.setLayoutData("hmin 400, wmin 800, hmax 100%-60, wmax 100%-30, grow");
		try {
			browser.setUrl(new File(getClass().getResource("/html/help.html").toURI()).getAbsolutePath());
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}

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
