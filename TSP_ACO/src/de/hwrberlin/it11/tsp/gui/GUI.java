/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import net.miginfocom.swt.MigLayout;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Adapter;
import org.eclipse.swt.custom.CTabFolderEvent;
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
import de.hwrberlin.it11.tsp.constant.PropertyChangeTypes;
import de.hwrberlin.it11.tsp.factories.FileDialogFactory;
import de.hwrberlin.it11.tsp.gui.components.TabContent;
import de.hwrberlin.it11.tsp.gui.dialog.AboutDialog;
import de.hwrberlin.it11.tsp.gui.dialog.HelpDialog;
import de.hwrberlin.it11.tsp.gui.dialog.PreferencesDialog;
import de.hwrberlin.it11.tsp.gui.dialog.RandomProjectDialog;
import de.hwrberlin.it11.tsp.gui.dialog.TSPDataDialog;
import de.hwrberlin.it11.tsp.model.Parameter;
import de.hwrberlin.it11.tsp.model.TSPData;
import de.hwrberlin.it11.tsp.persistence.Persister;

/**
 * Das Hauptfenster des User Interfaces.
 */
public class GUI implements PropertyChangeListener {

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
		shell.setImage(Images.COWBOY);
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
				_currentTabContent.getController().getProject().getTSPData().removePropertyChangeListener(GUI.this);
				_currentTabContent = (TabContent) control;
				_currentTabContent.getController().getProject().getTSPData().addPropertyChangeListener(GUI.this);
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
		_tabFolder.addCTabFolder2Listener(new CTabFolder2Adapter() {

			@Override
			public void close(CTabFolderEvent pEvent) {
				if (_tabFolder.getItemCount() == 1) {
					newTab();
				}
				Control control = ((CTabItem) pEvent.item).getControl();
				if (!(control instanceof TabContent)) {
					throw new IllegalArgumentException("Das Control des CTabItems muss ein TabContent sein.");
				}
				((TabContent) control).getController().getProject().getTSPData().removePropertyChangeListener(GUI.this);
			}

		});

		newTab();

		shell.pack();
		shell.open();
		shell.setMinimumSize(shell.getSize());
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
		fileMenuItemNewTab.setImage(Images.TAB_ADD);

		new MenuItem(fileMenuItemSubMenu, SWT.SEPARATOR); // Separator

		MenuItem fileMenuItemOpenTSPFile = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemOpenTSPFile.setText("TSP Datei öffnen");
		fileMenuItemOpenTSPFile.setImage(Images.CHART_LINE);

		MenuItem fileMenuItemSaveTSPFile = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemSaveTSPFile.setText("TSP Datei speichern");
		fileMenuItemSaveTSPFile.setImage(Images.DISK);

		MenuItem fileMenuItemSaveTSPFileAs = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemSaveTSPFileAs.setText("TSP Datei speichern unter...");
		fileMenuItemSaveTSPFileAs.setImage(Images.DISK);

		new MenuItem(fileMenuItemSubMenu, SWT.SEPARATOR); // Separator

		MenuItem fileMenuItemOpenConfigFile = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemOpenConfigFile.setText("Konfigurationsdatei öffnen");
		fileMenuItemOpenConfigFile.setImage(Images.TABLE);

		MenuItem fileMenuItemSaveConfigFile = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemSaveConfigFile.setText("Konfigurationsdatei speichern");
		fileMenuItemSaveConfigFile.setImage(Images.DISK);

		MenuItem fileMenuItemSaveConfigFileAs = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemSaveConfigFileAs.setText("Konfigurationsdatei speichern unter...");
		fileMenuItemSaveConfigFileAs.setImage(Images.DISK);

		new MenuItem(fileMenuItemSubMenu, SWT.SEPARATOR); // Separator

		MenuItem fileMenuItemClose = new MenuItem(fileMenuItemSubMenu, SWT.NONE);
		fileMenuItemClose.setText("Beenden");
		fileMenuItemClose.setImage(Images.CROSS);

		fileMenuItem.setMenu(fileMenuItemSubMenu);

		// Bearbeiten MenuItem
		MenuItem editMenuItem = new MenuItem(menuBar, SWT.CASCADE);
		editMenuItem.setText("Bearbeiten");

		Menu editMenuItemSubMenu = new Menu(editMenuItem);

		MenuItem editMenuItemCreateRandomProject = new MenuItem(editMenuItemSubMenu, SWT.NONE);
		editMenuItemCreateRandomProject.setText("Zufallsprojekt erstellen");
		editMenuItemCreateRandomProject.setImage(Images.CHART_LINE_EDIT);

		MenuItem editMenuItemCreateRandomParameter = new MenuItem(editMenuItemSubMenu, SWT.NONE);
		editMenuItemCreateRandomParameter.setText("Zufallsparameter erstellen");
		editMenuItemCreateRandomParameter.setImage(Images.TABLE_EDIT);

		new MenuItem(editMenuItemSubMenu, SWT.SEPARATOR); // Separator

		MenuItem editMenuItemEditTSPData = new MenuItem(editMenuItemSubMenu, SWT.NONE);
		editMenuItemEditTSPData.setText("TSP Daten bearbeiten");
		editMenuItemEditTSPData.setImage(Images.PAGE_WHITE_EDIT);

		new MenuItem(editMenuItemSubMenu, SWT.SEPARATOR); // Separator

		MenuItem editMenuItemEditPreferences = new MenuItem(editMenuItemSubMenu, SWT.NONE);
		editMenuItemEditPreferences.setText("Eigenschaften");
		editMenuItemEditPreferences.setImage(Images.COG);

		editMenuItem.setMenu(editMenuItemSubMenu);

		// Hilfe MenuItem
		MenuItem helpMenuItem = new MenuItem(menuBar, SWT.CASCADE);
		helpMenuItem.setText("Hilfe");

		Menu helpMenuItemSubMenu = new Menu(helpMenuItem);

		MenuItem helpMenuItemHelp = new MenuItem(helpMenuItemSubMenu, SWT.NONE);
		helpMenuItemHelp.setText("Hilfe");
		helpMenuItemHelp.setImage(Images.HELP);

		MenuItem helpMenuItemAbout = new MenuItem(helpMenuItemSubMenu, SWT.NONE);
		helpMenuItemAbout.setText("Über");
		helpMenuItemAbout.setImage(Images.INFORMATION);

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
				if (!_currentTabContent.getController().isRunning()) {
					String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.OPEN).setFilter(FileDialogFilter.TSP).open();
					if (path != null) {
						try {
							File file = new File(path);
							TSPData data = Persister.loadTSPFile(file);
							_currentTabContent.setTSPFile(file);
							_tabFolder.getSelection().setText(data.getName());
							_currentTabContent.getController().getProject().setTSPData(data);
						}
						catch (IllegalArgumentException pEx) {
							MessageDialog.openError(pParent, "Ungültige Datei", pEx.getMessage());
						}
					}
				}
				else {
					MessageDialog.openError(pParent, "TSP Datei öffnen", "Es kann keine TSP Datei geöffnet werden, wenn der Algorithmus läuft.");
				}
			}
		});

		fileMenuItemSaveTSPFile.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				File file = _currentTabContent.getTSPFile();
				if (file == null) {
					String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.SAVE).setFilter(FileDialogFilter.TSP).open();
					if (path != null) {
						file = new File(path);
						_currentTabContent.setTSPFile(file);
					}
				}
				if (file != null) {
					Persister.saveTSPFile(file, _currentTabContent.getController().getProject().getTSPData());
				}
			}
		});

		fileMenuItemSaveTSPFileAs.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.SAVE).setFilter(FileDialogFilter.TSP).open();
				if (path != null) {
					File file = new File(path);
					_currentTabContent.setTSPFile(file);
					Persister.saveTSPFile(file, _currentTabContent.getController().getProject().getTSPData());
				}
			}
		});

		fileMenuItemOpenConfigFile.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (!_currentTabContent.getController().isRunning()) {
					String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.OPEN).setFilter(FileDialogFilter.TSPCONFIG).open();
					if (path != null) {
						try {
							File file = new File(path);
							Parameter parameter = Persister.loadParameterFile(file);
							_currentTabContent.setTSPConfigFile(file);
							_currentTabContent.getController().getProject().setParameter(parameter);
						}
						catch (IllegalArgumentException pEx) {
							MessageDialog.openError(pParent, "Ungültige Datei", pEx.getMessage());
						}
					}
				}
				else {
					MessageDialog.openError(pParent, "Konfigurationsdatei öffnen",
							"Es kann keine Konfigurationsdatei geöffnet werden, wenn der Algorithmus läuft.");
				}
			}
		});

		fileMenuItemSaveConfigFile.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				File file = _currentTabContent.getTSPConfigFile();
				if (file == null) {
					String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.SAVE).setFilter(FileDialogFilter.TSPCONFIG).open();
					if (path != null) {
						file = new File(path);
						_currentTabContent.setTSPConfigFile(file);
					}
				}
				if (file != null) {
					Persister.saveParameterFile(file, _currentTabContent.getController().getProject().getParameter());
				}
			}
		});

		fileMenuItemSaveConfigFileAs.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				String path = new FileDialogFactory().setParent(pParent).setStyle(SWT.SAVE).setFilter(FileDialogFilter.TSPCONFIG).open();
				if (path != null) {
					File file = new File(path);
					_currentTabContent.setTSPConfigFile(file);
					Persister.saveParameterFile(file, _currentTabContent.getController().getProject().getParameter());
				}
			}
		});

		fileMenuItemClose.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				pParent.close();
			}
		});

		editMenuItemCreateRandomProject.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (!_currentTabContent.getController().isRunning()) {
					RandomProjectDialog randomProjectDialog = new RandomProjectDialog(pParent, _currentTabContent.getController().getProject());
					TSPData data = randomProjectDialog.open();
					if (data != null) {
						_currentTabContent.getController().getProject().setTSPData(data);
					}
				}
				else {
					MessageDialog.openError(pParent, "Zufallsprojekt erstellen",
							"Es kann kein Zufallsprojekt erstellt werden, wenn der Algorithmus läuft.");
				}
			}
		});

		editMenuItemCreateRandomParameter.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (!_currentTabContent.getController().isRunning()) {
					int antCount = (int) (Math.random() * 99) + 1;
					int iterationCount = (int) (Math.random() * 9999) + 1;
					double pheromonParameter = 5 - Math.random() * 5;
					double localInformation = 5 - Math.random() * 5;
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
				else {
					MessageDialog.openError(pParent, "Zufallsparameter erstellen",
							"Es können keine Zufallsparameter erstellt werden, wenn der Algorithmus läuft.");
				}
			}
		});

		editMenuItemEditTSPData.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				new TSPDataDialog(pParent, _currentTabContent.getController().getProject().getTSPData(), _currentTabContent.getController()
						.getProject()).open();
			}
		});

		editMenuItemEditPreferences.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				new PreferencesDialog(pParent, _currentTabContent.getController().getProject()).open();
			}
		});

		helpMenuItemHelp.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				new HelpDialog(pParent).open();
			}
		});

		helpMenuItemAbout.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent pE) {
				new AboutDialog(pParent, _currentTabContent.getController().getProject()).open();
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

		CTabItem tabItem = new CTabItem(_tabFolder, SWT.NONE);
		tabItem.setText(content.getController().getProject().getTSPData().getName());
		tabItem.setControl(content);

		_tabFolder.setSelection(tabItem);
		if (_currentTabContent != null) {
			_currentTabContent.getController().getProject().getTSPData().removePropertyChangeListener(this);
		}
		_currentTabContent = content;
		_currentTabContent.getController().getProject().getTSPData().addPropertyChangeListener(this);
	}



	@Override
	public void propertyChange(PropertyChangeEvent pEvt) {
		if (pEvt != null) {
			String propertyName = pEvt.getPropertyName();

			if (PropertyChangeTypes.TSPDATA_NAME.equals(propertyName)) {
				_tabFolder.getSelection().setText((String) pEvt.getNewValue());
			}
		}
	}

}
