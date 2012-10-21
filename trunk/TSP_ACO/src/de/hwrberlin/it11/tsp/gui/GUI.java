/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui;

import java.io.File;
import java.util.List;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import de.hwrberlin.it11.tsp.constant.Colors;
import de.hwrberlin.it11.tsp.constant.FileDialogFilter;
import de.hwrberlin.it11.tsp.constant.Images;
import de.hwrberlin.it11.tsp.factories.FileDialogFactory;
import de.hwrberlin.it11.tsp.gui.components.TabContent;
import de.hwrberlin.it11.tsp.gui.dialog.PreferencesDialog;
import de.hwrberlin.it11.tsp.gui.dialog.RandomProjectDialog;
import de.hwrberlin.it11.tsp.model.Node;
import de.hwrberlin.it11.tsp.model.Parameter;
import de.hwrberlin.it11.tsp.model.TSPData;
import de.hwrberlin.it11.tsp.persistence.Persister;

/**
 * Das Hauptfenster des User Interfaces.
 * 
 * @author Patrick Szostack
 * 
 */
public class GUI {

	/** Der CTabFolder, der die CTabItems mit den verschiedenen Projekten hält */
	private CTabFolder _tabFolder;

	/** Der TabConten des momentan ausgewählten CTabItems */
	private TabContent _currentTabContent;



	/**
	 * Legt die UI-Komponenten aus
	 */
	public void layout() {
		Display display = new Display();

		Shell shell = new Shell(display);
		shell.setText("Ants on Fire");
		shell.setImage(Images.JEA);
		shell.setLayout(new MigLayout("fill, ins 0 5 5 5"));
		shell.setLayoutData("hmin pref, wmin pref, hmax pref, wmax pref");

		createMenuBar(shell);

		_tabFolder = new CTabFolder(shell, SWT.CLOSE | SWT.TOP);
		_tabFolder
				.setSelectionBackground(new Color[] { Colors.TITLE_INACTIVE_BACKGROUND_GRADIENT, Colors.TITLE_BACKGROUND }, new int[] { 100 }, true);
		_tabFolder.setSimple(false);
		_tabFolder.setLayoutData("hmin 0, wmin 0, grow");
		_tabFolder.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				Control control = _tabFolder.getSelection().getControl();
				if (!(control instanceof TabContent)) {
					throw new IllegalArgumentException("Das Control des CTabItems muss ein TabContent sein.");
				}
				_currentTabContent = (TabContent) control;
			}
		});
		_tabFolder.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent pE) {
				// Wenn der CTabFolder im simple-Modus Fokus bekommt, wird der Tex des momentan ausgewählten Tabs unterstrichen. Um das zu verhinden
				// setzen wir den Fokus einfach auf was anderes, wenn der CTabFolder Fokus bekommen würde.
				_tabFolder.getSelection().getControl().setFocus();
			}
		});

		newTab();

		shell.pack();
		shell.open();
		shell.setMinimumSize(shell.getSize());
		// shell.setMaximized(true);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}



	/**
	 * Erzeugt ein Menü auf der angegebenen Shell
	 * 
	 * @param pParent
	 *            die Shell, auf der das Menü erzeugt wird
	 */
	private void createMenuBar(final Shell pParent) {
		// MenuBar
		Menu menuBar = new Menu(pParent, SWT.BAR);

		// Datei MenuItem
		MenuItem fileMenuItem = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuItem.setText("Datei");

		Menu fileMenuItemSubMenu = new Menu(fileMenuItem);

		MenuItem fileMenuItemNewTab = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemNewTab.setText("Neuer Tab");

		new MenuItem(fileMenuItemSubMenu, SWT.SEPARATOR); // Separator

		MenuItem fileMenuItemOpenTSPFile = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemOpenTSPFile.setText("TSP Datei öffnen");

		MenuItem fileMenuItemSaveTSPFile = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemSaveTSPFile.setText("TSP Datei speichern");
		fileMenuItemSaveTSPFile.setEnabled(false);

		MenuItem fileMenuItemSaveTSPFileAs = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemSaveTSPFileAs.setText("TSP Datei speichern unter...");
		fileMenuItemSaveTSPFileAs.setEnabled(false);

		new MenuItem(fileMenuItemSubMenu, SWT.SEPARATOR); // Separator

		MenuItem fileMenuItemOpenConfigFile = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemOpenConfigFile.setText("Konfigurationsdatei öffnen");

		MenuItem fileMenuItemSaveConfigFile = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemSaveConfigFile.setText("Konfigurationsdatei speichern");

		MenuItem fileMenuItemSaveConfigFileAs = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemSaveConfigFileAs.setText("Konfigurationsdatei speichern unter...");

		new MenuItem(fileMenuItemSubMenu, SWT.SEPARATOR); // Separator

		MenuItem fileMenuItemClose = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemClose.setText("Beenden");
		fileMenuItemClose.setImage(Images.CLOSE);

		fileMenuItem.setMenu(fileMenuItemSubMenu);

		// Bearbeiten MenuItem
		MenuItem editMenuItem = new MenuItem(menuBar, SWT.CASCADE);
		editMenuItem.setText("Bearbeiten");

		Menu editMenuItemSubMenu = new Menu(editMenuItem);

		MenuItem editMenuItemCreateRandomProject = new MenuItem(editMenuItemSubMenu, SWT.NONE);
		editMenuItemCreateRandomProject.setText("Zufallsprojekt erstellen");

		MenuItem editMenuItemCreateRandomParameter = new MenuItem(editMenuItemSubMenu, SWT.NONE);
		editMenuItemCreateRandomParameter.setText("Zufallsparameter erstellen");

		new MenuItem(editMenuItemSubMenu, SWT.SEPARATOR); // Separator

		MenuItem editMenuItemEditPreferences = new MenuItem(editMenuItemSubMenu, SWT.NONE);
		editMenuItemEditPreferences.setText("Eigenschaften");

		editMenuItem.setMenu(editMenuItemSubMenu);

		// Hilfe MenuItem
		MenuItem helpMenuItem = new MenuItem(menuBar, SWT.CASCADE);
		helpMenuItem.setText("Hilfe");

		Menu helpMenuItemSubMenu = new Menu(helpMenuItem);

		MenuItem helpMenuItemHelp = new MenuItem(helpMenuItemSubMenu, SWT.NONE);
		helpMenuItemHelp.setText("Hilfe");

		MenuItem helpMenuItemAbout = new MenuItem(helpMenuItemSubMenu, SWT.NONE);
		helpMenuItemAbout.setText("Über");

		helpMenuItem.setMenu(helpMenuItemSubMenu);

		pParent.setMenuBar(menuBar);

		// Event Handling

		fileMenuItemNewTab.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				newTab();
			}
		});

		fileMenuItemOpenTSPFile.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.OPEN).setFilter(FileDialogFilter.TSP).open();
				if (path != null) {
					_currentTabContent.setTSPFile(new File(path));
					TSPData data = Persister.loadTSPFile(_currentTabContent.getTSPFile());
					_tabFolder.getSelection().setText(data.getName());
					_currentTabContent.getController().getProject().setData(data);
				}
			}
		});

		fileMenuItemSaveTSPFile.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (_currentTabContent.getTSPFile() == null) {
					String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.SAVE).setFilter(FileDialogFilter.TSP).open();
					if (path != null) {
						_currentTabContent.setTSPFile(new File(path));
						Persister.saveTSPFile(_currentTabContent.getTSPFile(), _currentTabContent.getController().getProject().getData());
					}
				}
				else {
					Persister.saveTSPFile(_currentTabContent.getTSPFile(), _currentTabContent.getController().getProject().getData());
				}
			}
		});

		fileMenuItemSaveTSPFileAs.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.SAVE).setFilter(FileDialogFilter.TSP).open();
				if (path != null) {
					_currentTabContent.setTSPFile(new File(path));
					Persister.saveTSPFile(_currentTabContent.getTSPFile(), _currentTabContent.getController().getProject().getData());
				}
			}
		});

		fileMenuItemOpenConfigFile.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.OPEN).setFilter(FileDialogFilter.TSPCONFIG).open();
				if (path != null) {
					_currentTabContent.setTSPConfigFile(new File(path));
					_currentTabContent.getController().getProject().setParameter(Persister.loadParameterFile(_currentTabContent.getTSPConfigFile()));
				}
			}
		});

		fileMenuItemSaveConfigFile.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (_currentTabContent.getTSPConfigFile() == null) {
					String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.SAVE).setFilter(FileDialogFilter.TSPCONFIG).open();
					if (path != null) {
						_currentTabContent.setTSPConfigFile(new File(path));
						Persister.saveParameterFile(_currentTabContent.getTSPConfigFile(), _currentTabContent.getController().getProject()
								.getParameter());
					}
				}
				else {
					Persister.saveParameterFile(_currentTabContent.getTSPConfigFile(), _currentTabContent.getController().getProject().getParameter());
				}
			}
		});

		fileMenuItemSaveConfigFileAs.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.SAVE).setFilter(FileDialogFilter.TSPCONFIG).open();
				if (path != null) {
					_currentTabContent.setTSPConfigFile(new File(path));
					Persister.saveParameterFile(_currentTabContent.getTSPConfigFile(), _currentTabContent.getController().getProject().getParameter());
				}
			}
		});

		fileMenuItemClose.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				pParent.dispose();
			}
		});

		editMenuItemCreateRandomProject.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				RandomProjectDialog randomProjectDialog = new RandomProjectDialog(pParent, _currentTabContent.getController().getProject());
				List<Node> nodeList = randomProjectDialog.open();
				if (nodeList != null) {
					_currentTabContent.getController().getProject().setNodeList(nodeList);
				}
			}
		});

		editMenuItemCreateRandomParameter.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				int antCount = (int) (Math.random() * 99) + 1;
				int iterationCount = (int) (Math.random() * 9999) + 1;
				double pheromonParameter = 10 - Math.random() * 10;
				double localInformation = 10 - Math.random() * 10;
				double evaporationParameter = 1 - Math.random();
				double initialPheromonParameter = 10 - Math.random() * 10;
				double pheromonUpdateParameter = 10 - Math.random() * 10;

				Parameter param = _currentTabContent.getController().getProject().getParameter();
				param.setAntCount(antCount);
				param.setIterationCount(iterationCount);
				param.setPheromonParameter(pheromonParameter);
				param.setLocalInformation(localInformation);
				param.setEvaporationParameter(evaporationParameter);
				param.setInitialPheromonParameter(initialPheromonParameter);
				param.setPheromonUpdateParameter(pheromonUpdateParameter);
			}
		});

		editMenuItemEditPreferences.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				new PreferencesDialog(pParent).open();
			}
		});
	}



	/**
	 * Erzeugt einen neuen TabContent, fügt ihn zu einem neuen CTabItem hinzu und fügt das CTabItems dem CTabFolder hinzu. Danach wird der Fokus auf
	 * das neu kreierte Item gesetzt.
	 */
	private void newTab() {
		TabContent content = new TabContent(_tabFolder, SWT.NONE);
		content.setLayout(new MigLayout("fill"));
		content.setLayoutData("hmin 0, wmin 0");
		content.createComponent();

		CTabItem tabItem = new CTabItem(_tabFolder, SWT.NONE);
		tabItem.setText("Neuer Tab");
		tabItem.setControl(content);

		_tabFolder.setSelection(tabItem);
		_currentTabContent = content;
	}

}
