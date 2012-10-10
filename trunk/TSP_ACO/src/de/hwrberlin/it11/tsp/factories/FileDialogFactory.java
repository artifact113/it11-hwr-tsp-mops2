/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.factories;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import de.hwrberlin.it11.tsp.constant.FileDialogFilter;

/**
 * Factory zum Zusammenbauen eines FileDialog.
 * 
 * @author Patrick Szostack
 * 
 */
public class FileDialogFactory {

	/** Der erstellte FileDialog */
	private FileDialog _dialog;

	/** Die Parent Shell des zu erstellenden Dialogs */
	private Shell _parent;

	/** Style des zu erstellenden Dialogs */
	private int _style;

	/** Filter des zu erstellenden Dialogs */
	private FileDialogFilter _filter;



	/**
	 * Setzt die Parent Shell des zu erstellenden Dialogs auf die angegebene Shell.
	 * 
	 * @param pParent
	 *            die Parent Shell des zu erstellenden Dialogs
	 * @return diese Instanz der FileDialogFactory, um eine Verkettung von Aufrufen zu ermöglichen
	 */
	public FileDialogFactory setParent(Shell pParent) {
		_parent = pParent;
		return this;
	}



	/**
	 * Setzt den Style des zu erstellenden Dialogs auf den angegebenen Style.
	 * 
	 * @param pStyle
	 *            der Style des zu erstellenden Dialogs (SWT.OPEN oder SWT.SAVE)
	 * @return diese Instanz der FileDialogFactory, um eine Verkettung von Aufrufen zu ermöglichen
	 * @see SWT.OPEN
	 * @see SWT.SAVE
	 */
	public FileDialogFactory setStyle(int pStyle) {
		_style = pStyle;
		return this;
	}



	/**
	 * Setzt den FileDialogFilter des zu erstellenden Dialogs auf den angegebenen FileDialogFilter.
	 * 
	 * @param pFilter
	 *            der FileDialogFilter des zu erstellenden Dialogs
	 * @return diese Instanz der FileDialogFactory, um eine Verkettung von Aufrufen zu ermöglichen
	 */
	public FileDialogFactory setFilter(FileDialogFilter pFilter) {
		_filter = pFilter;
		return this;
	}



	/**
	 * Erstellt den FileDialog auf Basis der zuvor angegebenen Parameter, öffnet ihn und gibt das Ergebnis zurück.
	 * 
	 * @return den Rückgabewert der open()-Methode des FileDialog
	 * @see FileDialog#open()
	 */
	public String open() {
		_dialog = new FileDialog(_parent, _style);

		String filterExtension = "";
		String filterName = "";

		switch (_filter) {
			case TSP:
				filterExtension = "*.tsp";
				filterName = "Traveling Salesman-Dateien (.tsp)";
				break;
			case TSPCONFIG:
				filterExtension = "*.tspconfig";
				filterName = "Traveling Salesman Konfiguationsdateien (.tspconfig)";
				break;
			case OPTTOUR:
				filterExtension = "*.opt.tour";
				filterName = "Traveling Salesman Optimale Tour Dateien (.opt.tour)";
				break;
		}

		_dialog.setFilterExtensions(new String[] { filterExtension });
		_dialog.setFilterNames(new String[] { filterName });

		return _dialog.open();
	}

}
