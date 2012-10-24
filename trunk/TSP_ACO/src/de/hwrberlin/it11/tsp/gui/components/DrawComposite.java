/**
 * Copyright (c) 2012 mops� Productions
 */
package de.hwrberlin.it11.tsp.gui.components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.List;

import net.miginfocom.swt.MigLayout;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;

import de.hwrberlin.it11.tsp.constant.Colors;
import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.controller.AntController;
import de.hwrberlin.it11.tsp.gui.dialog.NewNodeDialog;
import de.hwrberlin.it11.tsp.gui.updatestrategy.ZoomFactorModelToTargetUpdateStrategy;
import de.hwrberlin.it11.tsp.gui.updatestrategy.ZoomFactorTargetToModelUpdateStrategy;
import de.hwrberlin.it11.tsp.gui.widgets.AAntControl;
import de.hwrberlin.it11.tsp.model.Node;
import de.hwrberlin.it11.tsp.model.Preferences;
import de.hwrberlin.it11.tsp.model.Result;

/**
 * In diesem Composite wird der Fortschritt des Ameisenprojektes gemalt.
 * 
 * @author Patrick Szostack
 * 
 */
public class DrawComposite extends ADataBindableComposite implements PropertyChangeListener {

	/**
	 * Die Breite des Randes, die zwischen dem Rand des Canvas und der Malfl�che gelassen wird. So wird verhindert, dass der Punkt der Nodes, die sich
	 * zum Beispiel an Koordinate 0 befinden, halb abgeschnitten wird.
	 */
	private static final int BORDER_WIDTH = 25;

	/** Das benutzte DecimalFormat um Dezimalzahlen in einen String zu konvertieren */
	private static final DecimalFormat FORMAT = new DecimalFormat("#.000");

	/** Die momentan ausgew�hlte Node */
	private Node _selectedNode;

	/** Die Fl�che, auf der der ganze Spa� gemalt wird */
	private Canvas _canvas;

	/** Die Scale, mit der sich der Zoomfaktor einstellen l�sst */
	private Scale _zoomFactor;

	/** Gibt an, ob die Maus gedr�ckt ist, w�hrend sie sich bewegt */
	private boolean _drag;

	/** Das ScrolledComposite scrollt den Canvas */
	private ScrolledComposite _scrolledComposite;

	/** Hier werden verschiedene Statusmeldungen angezeigt */
	private Label _statusLabel;



	/**
	 * Erstellt ein neues DrawComposite.
	 * 
	 * @param pParent
	 *            das Eltern-Composite
	 * @param pStyle
	 *            der Style des Composite
	 * @param pController
	 *            der Controller dieses DrawComposite
	 */
	public DrawComposite(Composite pParent, int pStyle, AntController pController) {
		super(pParent, pStyle, pController);

		getController().getProject().addPropertyChangeListener(this);
		getController().getProject().getParameter().addPropertyChangeListener(this);

		_scrolledComposite = new ScrolledComposite(this, SWT.H_SCROLL | SWT.V_SCROLL);
		_scrolledComposite.setLayoutData("hmin 0, wmin 0, grow, push, growprio 99, shrinkprio 101");
		_scrolledComposite.setExpandHorizontal(true);
		_scrolledComposite.setExpandVertical(true);

		_canvas = new Canvas(_scrolledComposite, SWT.DOUBLE_BUFFERED);
		_canvas.setBackground(Colors.WHITE);

		_zoomFactor = new Scale(this, SWT.VERTICAL);
		_zoomFactor.setLayoutData("hmin 0, wmin 0, growy");
		_zoomFactor.setMinimum(5);
		_zoomFactor.setMaximum(300);

		new AAntControl(_zoomFactor, getController().getProject(),
				"Hier k�nnen Sie den Zoom-Faktor einstellen, mit dem die Knoten und Kanten dargestellt werden.");

		_canvas.addPaintListener(new AntPaintListener());
		_canvas.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDown(MouseEvent pE) {
				if (pE.button == 1) {
					if (_selectedNode != null) {
						_selectedNode.removePropertyChangeListener(DrawComposite.this);
					}
					double zoomFactor = getController().getProject().getParameter().getZoomFactor();
					double x = (pE.x - BORDER_WIDTH) / zoomFactor;
					double y = (pE.y - BORDER_WIDTH) / zoomFactor;
					_selectedNode = getController().getProject().getNodeForCoordinates((int) x, (int) y);
					if (_selectedNode != null) {
						_selectedNode.addPropertyChangeListener(DrawComposite.this);
						_drag = true;
					}
					_canvas.redraw();
				}
			}



			@Override
			public void mouseUp(MouseEvent pE) {
				if (pE.button == 1) {
					_drag = false;
				}
			}



			@Override
			public void mouseDoubleClick(MouseEvent pE) {
				if (pE.button == 1) {
					if (pE.x - BORDER_WIDTH >= 0 && pE.y - BORDER_WIDTH >= 0) {
						double zoomFactor = getController().getProject().getParameter().getZoomFactor();
						NewNodeDialog newNodeDialog = new NewNodeDialog(getShell(), (int) ((pE.x - BORDER_WIDTH) / zoomFactor),
								(int) ((pE.y - BORDER_WIDTH) / zoomFactor));
						Node newNode = newNodeDialog.open();
						if (newNode != null) {
							getController().getProject().addNode(newNode);
						}
					}
				}
			}
		});

		_canvas.addMouseMoveListener(new MouseMoveListener() {

			@Override
			public void mouseMove(MouseEvent pE) {
				double zoomFactor = getController().getProject().getParameter().getZoomFactor();
				if (_drag) {
					_selectedNode.setxCoordinate(pE.x >= BORDER_WIDTH ? (int) ((pE.x - BORDER_WIDTH) / zoomFactor) : 0);
					_selectedNode.setyCoordinate(pE.y >= BORDER_WIDTH ? (int) ((pE.y - BORDER_WIDTH) / zoomFactor) : 0);
				}
				getController().getProject().setStatusText(
						"X: " + FORMAT.format((pE.x - BORDER_WIDTH) / zoomFactor) + ", Y: " + FORMAT.format((pE.y - BORDER_WIDTH) / zoomFactor));
			}
		});

		_canvas.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent pE) {
				if (pE.keyCode == SWT.DEL) {
					if (_selectedNode != null) {
						getController().getProject().removeNode(_selectedNode);
						_selectedNode = null;
					}
				}
			}

		});

		Composite statusComp = new Composite(this, SWT.NONE);
		statusComp.setLayout(new MigLayout("fill, ins 0 5 0 0"));
		statusComp.setLayoutData("newline, hmin pref, wmin pref, spanx, growx");

		_statusLabel = new Label(statusComp, SWT.NONE);
		_statusLabel.setLayoutData("hmin pref, wmin pref, grow");

		_scrolledComposite.setContent(_canvas);

		resetBinding();
	}



	@Override
	protected void bindValues(DataBindingContext pDBC, Realm pRealm) {
		// ZoomFactor-Scale binden
		pDBC.bindValue(SWTObservables.observeSelection(_zoomFactor),
				BeansObservables.observeValue(pRealm, getController().getProject().getParameter(), PropertyChangeTypes.PARAMETER_ZOOMFACTOR),
				ZoomFactorTargetToModelUpdateStrategy.getInstance(), ZoomFactorModelToTargetUpdateStrategy.getInstance());
		// Statustext binden
		pDBC.bindValue(SWTObservables.observeText(_statusLabel),
				BeansObservables.observeValue(pRealm, getController().getProject(), PropertyChangeTypes.PROJECT_STATUSTEXT));
	}



	@Override
	public void propertyChange(PropertyChangeEvent pEvt) {
		if (pEvt != null) {
			String propertyName = pEvt.getPropertyName();

			// Auf folgende Events eine Neuberechnung der gr��ten Koordinaten mit anschlie�endem redraw asuf�hren:
			if (PropertyChangeTypes.PROJECT_NODELIST.equals(propertyName) // Die NodeList des Projektes wurde neu gesetzt
					|| PropertyChangeTypes.NODE_XCOORDINATE.equals(propertyName) // Es wurde die X Koordinate einer Node ver�ndert
					|| PropertyChangeTypes.NODE_YCOORDINATE.equals(propertyName) // Es wurde die Y Koordinate einer Node ver�ndert
					|| PropertyChangeTypes.PROJECT_NODELIST_ADD.equals(propertyName) // Es wurde eine Node zur St�dteliste hinzugef�gt
					|| PropertyChangeTypes.PROJECT_NODELIST_REMOVE.equals(propertyName) // Es wurde eine Node von der St�dteliste entfernt
					|| PropertyChangeTypes.PARAMETER_ZOOMFACTOR.equals(propertyName)) { // Der Zoom-Faktor hat sich ver�ndert
				int maxX = 0;
				int maxY = 0;
				for (Node node : getController().getProject().getNodeList()) {
					if (node.getxCoordinate() > maxX) {
						maxX = node.getxCoordinate();
					}
					if (node.getyCoordinate() > maxY) {
						maxY = node.getyCoordinate();
					}
				}

				double zoomFactor = getController().getProject().getParameter().getZoomFactor();
				int preferredWidth = (int) (maxX * zoomFactor) + BORDER_WIDTH * 2;
				int preferredHeight = (int) (maxY * zoomFactor) + BORDER_WIDTH * 2;

				_scrolledComposite.setMinSize(preferredWidth, preferredHeight);

				_canvas.redraw();
			}

			// Einen redraw auf folgende Events ausf�hren
			if (PropertyChangeTypes.PROJECT_ITERATIONFINISHED.equals(propertyName)) { // Eine Iteration ist vorbei
				// Dieses Event kommt nicht aus dem UI-Thread, deswegen muss der Redraw mit Display.syncExec() ausgef�hrt werden
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						if (!_canvas.isDisposed()) {
							_canvas.redraw();
						}
					}
				});
			}

			// Auf folgende Events das Databinding erneuern:
			if (PropertyChangeTypes.PROJECT_PARAMETER.equals(propertyName)) { // Parameter des Projektes wurden neu gesetzt
				getController().getProject().getParameter().addPropertyChangeListener(this);
				resetBinding();
			}
		}
	}

	/**
	 * Diese Klasse ist der �bersicht halber etwas ausgelagert. Sie �bernimmt das Zeichnen der Nodes und Touren auf den Canvas.
	 * 
	 * @author Patrick Szostack
	 * 
	 */
	private class AntPaintListener implements PaintListener {

		@Override
		public void paintControl(PaintEvent pE) {
			List<Node> nodeList = getController().getProject().getNodeList();
			double zoomFactor = getController().getProject().getParameter().getZoomFactor();

			Result result = getController().getProject().getResult();
			Preferences preferences = Preferences.getInstance();

			if (preferences.isAntialias()) {
				pE.gc.setAntialias(SWT.ON);
			}
			// Beste Tour Iteration
			pE.gc.setForeground(preferences.getBestTourIterationColor());
			for (int i = 0; i < result.getBestTourIteration().size() - 1; i++) {
				Node node = result.getBestTourIteration().get(i);
				Node otherNode = result.getBestTourIteration().get(i + 1);
				pE.gc.drawLine((int) (node.getxCoordinate() * zoomFactor) + BORDER_WIDTH, (int) (node.getyCoordinate() * zoomFactor) + BORDER_WIDTH,
						(int) (otherNode.getxCoordinate() * zoomFactor) + BORDER_WIDTH, (int) (otherNode.getyCoordinate() * zoomFactor)
								+ BORDER_WIDTH);
			}
			// Beste Tour global
			pE.gc.setForeground(preferences.getBestTourGlobalColor());
			for (int i = 0; i < result.getBestTourGlobal().size() - 1; i++) {
				Node node = result.getBestTourGlobal().get(i);
				Node otherNode = result.getBestTourGlobal().get(i + 1);
				pE.gc.drawLine((int) (node.getxCoordinate() * zoomFactor) + BORDER_WIDTH, (int) (node.getyCoordinate() * zoomFactor) + BORDER_WIDTH,
						(int) (otherNode.getxCoordinate() * zoomFactor) + BORDER_WIDTH, (int) (otherNode.getyCoordinate() * zoomFactor)
								+ BORDER_WIDTH);
			}
			// Nodes
			pE.gc.setBackground(preferences.getNodeColor());
			for (Node node : nodeList) {
				if (node == _selectedNode) {
					pE.gc.setBackground(preferences.getCurrentNodeColor());
				}
				pE.gc.fillOval((int) (node.getxCoordinate() * zoomFactor) - 5 + BORDER_WIDTH, (int) (node.getyCoordinate() * zoomFactor) - 5
						+ BORDER_WIDTH, 10, 10);
				if (node == _selectedNode) {
					pE.gc.setBackground(preferences.getNodeColor());
				}
			}
		}

	}
}
