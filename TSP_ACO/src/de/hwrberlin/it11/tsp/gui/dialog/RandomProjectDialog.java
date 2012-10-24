/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import java.util.ArrayList;
import java.util.List;

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
import de.hwrberlin.it11.tsp.model.TSPData;

/**
 * Dialog zum generieren eines zufälligen Projektes.
 * 
 * @author Patrick Szostack
 * 
 */
public class RandomProjectDialog extends Dialog {

	/** Rückgabewert dieses Dialoges */
	private TSPData _result;



	/**
	 * Erstellt einen neuen RandomProjectDialog.
	 * 
	 * @param pParent
	 *            die Parent-Shell des Dialoges
	 * @param pProject
	 *            das AntProject des zu erstellenden Dialoges
	 */
	public RandomProjectDialog(Shell pParent) {
		super(pParent);
	}



	/**
	 * Öffnet diesen Dialog und bingt ihn in den Vordergrund.
	 * 
	 * @return eine TSPData Instanz mit einer Liste bstehend aus zufällig erzeugten Nodes, oder null falls abgebrochen wurde
	 */
	public TSPData open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText("Zufallsprojekt");
		shell.setLayout(new MigLayout("fill, wrap 2", "[pref!][100!]"));
		shell.setLayoutData("hmin pref, wmin pref");

		Label lNodeCount = new Label(shell, SWT.NONE);
		lNodeCount.setText("Anzahl der Städte:");
		lNodeCount.setLayoutData("hmin pref, wmin pref");

		final Text tNodeCount = new Text(shell, SWT.BORDER);
		tNodeCount.setText("0");
		tNodeCount.setToolTipText("Tragen Sie hier die Anzahl der Städte ein. Für einen zufälligen Wert von 1 bis 100 tragen Sie 0 ein.");
		tNodeCount.setLayoutData("hmin pref, wmin 50, growx");
		tNodeCount.addVerifyListener(VerifyIntegerListener.getInstance());

		Label lMaxXCoordinate = new Label(shell, SWT.NONE);
		lMaxXCoordinate.setText("Maximale X Koordinate:");
		lMaxXCoordinate.setLayoutData("hmin pref, wmin pref");

		final Text tMaxXCoordinate = new Text(shell, SWT.BORDER);
		tMaxXCoordinate.setText("0");
		tMaxXCoordinate
				.setToolTipText("Tragen Sie hier den maximalen Wert der X Koordinate ein, den eine Stadt haben kann. Für einen zufälligen Wert von 1 bis 5000 tragen Sie 0 ein.");
		tMaxXCoordinate.setLayoutData("hmin pref, wmin 50, growx");
		tMaxXCoordinate.addVerifyListener(VerifyIntegerListener.getInstance());

		Label lMaxYCoordinate = new Label(shell, SWT.NONE);
		lMaxYCoordinate.setText("Maximale Y Koordinate:");
		lMaxYCoordinate.setLayoutData("hmin pref, wmin pref");

		final Text tMaxYCoordinate = new Text(shell, SWT.BORDER);
		tMaxYCoordinate.setText("0");
		tMaxYCoordinate
				.setToolTipText("Tragen Sie hier den maximalen Wert der Y Koordinate ein, den eine Stadt haben kann. Für einen zufälligen Wert von 1 bis 5000 tragen Sie 0 ein.");
		tMaxYCoordinate.setLayoutData("hmin pref, wmin 50, growx");
		tMaxYCoordinate.addVerifyListener(VerifyIntegerListener.getInstance());

		Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setLayout(new MigLayout("wrap 2, ins 0", "[50%][50%]"));
		buttonComp.setLayoutData("hmin 0, wmin 0, growx, spanx");

		Button confirm = new Button(buttonComp, SWT.PUSH);
		confirm.setText("Erstellen");
		confirm.setLayoutData("hmin pref, wmin pref, grow");
		confirm.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				List<Node> nodeList = new ArrayList<Node>();
				int nodeCount = Integer.valueOf(tNodeCount.getText());
				int maxX = Integer.valueOf(tMaxXCoordinate.getText());
				int maxY = Integer.valueOf(tMaxYCoordinate.getText());
				if (nodeCount == 0) {
					nodeCount = (int) (Math.random() * 99) + 1;
				}
				if (maxX == 0) {
					maxX = (int) (Math.random() * 4999) + 1;
				}
				if (maxY == 0) {
					maxY = (int) (Math.random() * 4999) + 1;
				}
				for (int i = 0; i < nodeCount; i++) {
					Node node = new Node((int) (Math.random() * maxX), (int) (Math.random() * maxY));
					nodeList.add(node);
				}

				_result = new TSPData();
				_result.setName("Zufallsprojekt");
				_result.setComment("Zufallsgeneriertes Projekt");
				_result.setEdgeWeightType("EUC_2D");
				_result.setNodeList(nodeList);
				_result.setType("TSP");

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
