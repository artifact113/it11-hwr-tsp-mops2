/**
 * Copyright (c) 2012 mops≤ Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.hwrberlin.it11.tsp.constant.Images;
import de.hwrberlin.it11.tsp.gui.widgets.AntButton;
import de.hwrberlin.it11.tsp.gui.widgets.AntLabel;
import de.hwrberlin.it11.tsp.model.AntProject;

/**
 * 
 * 
 * @author Patrick Szostack
 * 
 */
public class AboutDialog extends AAntDialog {

	public AboutDialog(Shell pParent, AntProject pProject) {
		super(pParent, pProject);
	}



	public void open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText("‹ber");
		shell.setLayout(new MigLayout("fill, wrap"));
		shell.setLayoutData("hmin pref, wmin pref");

		FontData data = Display.getDefault().getSystemFont().getFontData()[0];
		data.setHeight(20);
		final Font font20Height = new Font(null, data);
		data.setStyle(SWT.BOLD);
		final Font font20HeightBold = new Font(null, data);

		AntLabel picture = new AntLabel(new Label(shell, SWT.NONE), getProject());
		picture.getLabel().setImage(Images.MOPSS);
		picture.getLabel().setLayoutData("hmin pref, wmin pref, growx");
		picture.setTooltipText("Das Firmenlogo von MOPS≤ Productions.");

		Composite teamComposite = new Composite(shell, SWT.NONE);
		teamComposite.setLayout(new MigLayout("wrap, ins 0"));
		teamComposite.setLayoutData("hmin pref, wmin pref, alignx center");

		Label team = new Label(teamComposite, SWT.NONE);
		team.setText("Team:");
		team.setFont(font20HeightBold);
		team.setLayoutData("hmin pref, wmin pref, alignx center");

		Composite matthisComposite = new Composite(teamComposite, SWT.NONE);
		matthisComposite.setLayout(new MigLayout("ins 0 20 0 0"));
		matthisComposite.setLayoutData("hmin pref, wmin pref, growx");

		Label matthisBegin = new Label(matthisComposite, SWT.NONE);
		matthisBegin.setText("M");
		matthisBegin.setFont(font20HeightBold);
		matthisBegin.setLayoutData("hmin pref, wmin pref");

		Label matthis = new Label(matthisComposite, SWT.NONE);
		matthis.setText("atthis Feld");
		matthis.setFont(font20Height);
		matthis.setLayoutData("hmin pref, wmin pref, growx");

		Composite oliverComposite = new Composite(teamComposite, SWT.NONE);
		oliverComposite.setLayout(new MigLayout("ins 0 20 0 0"));
		oliverComposite.setLayoutData("hmin pref, wmin pref, growx");

		Label oliverBegin = new Label(oliverComposite, SWT.NONE);
		oliverBegin.setText("O");
		oliverBegin.setFont(font20HeightBold);
		oliverBegin.setLayoutData("hmin pref, wmin pref");

		Label oliver = new Label(oliverComposite, SWT.NONE);
		oliver.setText("liver Vogel");
		oliver.setFont(font20Height);
		oliver.setLayoutData("hmin pref, wmin pref, growx");

		Composite patrickComposite = new Composite(teamComposite, SWT.NONE);
		patrickComposite.setLayout(new MigLayout("ins 0 20 0 0"));
		patrickComposite.setLayoutData("hmin pref, wmin pref, growx");

		Label patrickBegin = new Label(patrickComposite, SWT.NONE);
		patrickBegin.setText("P");
		patrickBegin.setFont(font20HeightBold);
		patrickBegin.setLayoutData("hmin pref, wmin pref");

		Label patrick = new Label(patrickComposite, SWT.NONE);
		patrick.setText("atrick Szostack");
		patrick.setFont(font20Height);
		patrick.setLayoutData("hmin pref, wmin pref, growx");

		Composite stevenComposite = new Composite(teamComposite, SWT.NONE);
		stevenComposite.setLayout(new MigLayout("ins 0 20 0 0"));
		stevenComposite.setLayoutData("hmin pref, wmin pref, growx");

		Label stevenBegin = new Label(stevenComposite, SWT.NONE);
		stevenBegin.setText("S");
		stevenBegin.setFont(font20HeightBold);
		stevenBegin.setLayoutData("hmin pref, wmin pref");

		Label steven = new Label(stevenComposite, SWT.NONE);
		steven.setText("teven Zimmermann");
		steven.setFont(font20Height);
		steven.setLayoutData("hmin pref, wmin pref, growx");

		Composite simonComposite = new Composite(teamComposite, SWT.NONE);
		simonComposite.setLayout(new MigLayout("ins 0 20 0 0"));
		simonComposite.setLayoutData("hmin pref, wmin pref, growx");

		Label simonBegin = new Label(simonComposite, SWT.NONE);
		simonBegin.setText("S");
		simonBegin.setFont(font20HeightBold);
		simonBegin.setLayoutData("hmin pref, wmin pref");

		Label simon = new Label(simonComposite, SWT.NONE);
		simon.setText("imon Rodeike");
		simon.setFont(font20Height);
		simon.setLayoutData("hmin pref, wmin pref, growx");

		AntButton confirm = new AntButton(new Button(shell, SWT.PUSH), getProject());
		confirm.getButton().setText("Genug gesehen");
		confirm.getButton().setLayoutData("hmin pref, wmin pref, grow");
		confirm.setTooltipText("Schlieﬂt den Dialog.");
		confirm.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				shell.dispose();
			}
		});

		shell.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent pE) {
				font20Height.dispose();
				font20HeightBold.dispose();
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
