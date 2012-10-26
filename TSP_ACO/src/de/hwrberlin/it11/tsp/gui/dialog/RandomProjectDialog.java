/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui.dialog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.miginfocom.swt.MigLayout;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.hwrberlin.it11.tsp.constant.Utility;
import de.hwrberlin.it11.tsp.gui.widgets.AntButton;
import de.hwrberlin.it11.tsp.gui.widgets.AntText;
import de.hwrberlin.it11.tsp.model.AntProject;
import de.hwrberlin.it11.tsp.model.Node;
import de.hwrberlin.it11.tsp.model.TSPData;

/**
 * Dialog zum generieren eines zufälligen Projektes.
 * 
 * @author Patrick Szostack
 * 
 */
public class RandomProjectDialog extends AAntDialog {

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
	public RandomProjectDialog(Shell pParent, AntProject pProject) {
		super(pParent, pProject);
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

		final AntText tNodeCount = new AntText(new Text(shell, SWT.BORDER), getProject());
		tNodeCount.getText().setText("0");
		tNodeCount.getText().setLayoutData("hmin pref, wmin 50, growx");
		tNodeCount.setTooltipText("Tragen Sie hier die Anzahl der Knoten ein. Für einen zufälligen Wert von 1 bis 100 tragen Sie 0 ein.");
		tNodeCount.setInputMode(AntText.INTEGER_ONLY);
		tNodeCount.setNumberRange(0, Double.POSITIVE_INFINITY, true, true);

		Label lMaxXCoordinate = new Label(shell, SWT.NONE);
		lMaxXCoordinate.setText("Maximale X Koordinate:");
		lMaxXCoordinate.setLayoutData("hmin pref, wmin pref");

		final AntText tMaxXCoordinate = new AntText(new Text(shell, SWT.BORDER), getProject());
		tMaxXCoordinate.getText().setText("0");
		tMaxXCoordinate.getText().setLayoutData("hmin pref, wmin 50, growx");
		tMaxXCoordinate
				.setTooltipText("Tragen Sie hier den maximalen Wert der X Koordinate ein, den ein Knoten haben kann. Für einen zufälligen Wert von 1 bis 5000 tragen Sie 0 ein.");
		tMaxXCoordinate.setInputMode(AntText.DOUBLE_ONLY);
		tMaxXCoordinate.setNumberRange(0, Double.POSITIVE_INFINITY, true, true);

		Label lMaxYCoordinate = new Label(shell, SWT.NONE);
		lMaxYCoordinate.setText("Maximale Y Koordinate:");
		lMaxYCoordinate.setLayoutData("hmin pref, wmin pref");

		final AntText tMaxYCoordinate = new AntText(new Text(shell, SWT.BORDER), getProject());
		tMaxYCoordinate.getText().setText("0");
		tMaxYCoordinate.getText().setLayoutData("hmin pref, wmin 50, growx");
		tMaxYCoordinate
				.setTooltipText("Tragen Sie hier den maximalen Wert der Y Koordinate ein, den ein Knoten haben kann. Für einen zufälligen Wert von 1 bis 5000 tragen Sie 0 ein.");
		tMaxYCoordinate.setInputMode(AntText.DOUBLE_ONLY);
		tMaxYCoordinate.setNumberRange(0, Double.POSITIVE_INFINITY, true, true);

		Composite buttonComp = new Composite(shell, SWT.NONE);
		buttonComp.setLayout(new MigLayout("wrap 2, ins 0", "[50%][50%]"));
		buttonComp.setLayoutData("hmin 0, wmin 0, growx, spanx");

		AntButton confirm = new AntButton(new Button(buttonComp, SWT.PUSH), getProject());
		confirm.getButton().setText("Erstellen");
		confirm.getButton().setLayoutData("hmin pref, wmin pref, grow");
		confirm.setTooltipText("Erstellt ein neues Projekt und schließt den Dialog.");
		confirm.getButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (tNodeCount.isValidInput() && tMaxXCoordinate.isValidInput() && tMaxYCoordinate.isValidInput()) {
					List<Node> nodeList = new ArrayList<Node>();
					int nodeCount;
					try {
						nodeCount = Utility.FORMAT.parse(tNodeCount.getText().getText()).intValue();
						double maxX = Utility.FORMAT.parse(tMaxXCoordinate.getText().getText()).doubleValue();
						double maxY = Utility.FORMAT.parse(tMaxYCoordinate.getText().getText()).doubleValue();
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
							Node node = new Node(Math.random() * maxX, Math.random() * maxY);
							nodeList.add(node);
						}

						_result = new TSPData();
						_result.setName("Zufallsprojekt");
						_result.setComment("Zufallsgeneriertes Projekt");
						_result.setEdgeWeightType("EUC_2D");
						_result.setNodeList(nodeList);
						_result.setType("TSP");
					}
					catch (ParseException e) {
						MessageDialog.openError(shell, "Fehler beim umwandeln der Werte",
								"Beim Umwandeln der Werte von Text in eine Zahl ist ein Fehler aufgetreten.");
					}

					shell.dispose();
				}
			}
		});

		AntButton cancel = new AntButton(new Button(buttonComp, SWT.PUSH), getProject());
		cancel.getButton().setText("Abbrechen");
		cancel.getButton().setLayoutData("hmin pref, wmin pref, grow");
		cancel.setTooltipText("Schleißt den Dialog ohne ein Projekt zu erstellen.");
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
		return _result;
	}

}
