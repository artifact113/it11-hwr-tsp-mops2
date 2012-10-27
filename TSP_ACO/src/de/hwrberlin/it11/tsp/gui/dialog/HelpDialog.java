/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * 
 * @author Patrick Szostack
 * 
 */
public class HelpDialog extends Dialog {

	public HelpDialog(Shell pParent) {
		super(pParent);
	}



	public void open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE);
		shell.setText("Über");
		shell.setLayout(new MigLayout("fill, wrap"));
		shell.setLayoutData("hmin pref, wmin pref");

		// Browser browser = new Browser(shell, SWT.NONE);
		// browser.setLayoutData("hmin pref, wmin pref, hmax 100%-60, wmax 100%-30, grow");
		// try {
		// browser.setUrl(new File(getClass().getResource("").toURI()).getAbsolutePath());
		// }
		// catch (URISyntaxException e) {
		// e.printStackTrace();
		// }

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
