/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.constant;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.graphics.Image;

/**
 * Diese Klasse enthält die verwendeten Bilder als Konstanten.
 * 
 * @author Patrick Szostack
 * 
 */
public class Images {

	/** Größe des momentan benutzten Bildschirms */
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	/** Titelbild des Programms */
	public static final Image COWBOY = getScaledImageFromResource("/img/Ameise_Cowboy.png", (int) (SCREEN_SIZE.height / 5.5 / 6 * 5),
			(int) (SCREEN_SIZE.height / 5.5));

	/** MOPS² Firmenlogo */
	public static final Image MOPSS = getScaledImageFromResource("/img/Mops.png", (int) (SCREEN_SIZE.height / 3.6 * 1.35),
			(int) (SCREEN_SIZE.height / 3.6));

	/** Hinzufügen */
	public static final Image ADD = getImageFromResource("/img/add.png");

	/** Colorswatch */
	public static final Image COLOR_SWATCH = getImageFromResource("/img/color_swatch.png");

	/** Ordner */
	public static final Image FOLDER = getImageFromResource("/img/folder.png");

	/** Rotes Kreuz */
	public static final Image CROSS = getImageFromResource("/img/cross.png");

	/** Grüner Haken */
	public static final Image TICK = getImageFromResource("/img/tick.png");

	/** Graph bearbeiten */
	public static final Image CHART_LINE_EDIT = getImageFromResource("/img/chart_line_edit.png");

	/** Graph */
	public static final Image CHART_LINE = getImageFromResource("/img/chart_line.png");

	/** Zahnrad */
	public static final Image COG = getImageFromResource("/img/cog.png");

	/** Diskette */
	public static final Image DISK = getImageFromResource("/img/disk.png");

	/** Hilfe */
	public static final Image HELP = getImageFromResource("/img/help.png");

	/** Information */
	public static final Image INFORMATION = getImageFromResource("/img/information.png");

	/** Weiße Seite bearbeiten */
	public static final Image PAGE_WHITE_EDIT = getImageFromResource("/img/page_white_edit.png");

	/** Tab hinzufügen */
	public static final Image TAB_ADD = getImageFromResource("/img/tab_add.png");

	/** Tabelle bearbeiten */
	public static final Image TABLE_EDIT = getImageFromResource("/img/table_edit.png");

	/** Tabelle */
	public static final Image TABLE = getImageFromResource("/img/table.png");



	/**
	 * Erzeugt ein Image aus dem angegebenen Dateipfad.
	 * 
	 * @param pPath
	 *            Dateipfad des Image
	 * @return das erzeugte Image
	 */
	private static Image getImageFromResource(String pPath) {
		InputStream stream = Image.class.getResourceAsStream(pPath);
		Image image = new Image(null, stream);
		try {
			stream.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}



	/**
	 * Erzeugt ein Image aus dem angegebenen Dateipfad und skaliert es auf die angegebene Breite und Höhe.
	 * 
	 * @param pPath
	 *            der Dateipfad des Image
	 * @param pWidth
	 *            die gwünschte Breite des Image
	 * @param pHeight
	 *            die gewünschte Höhe des Image
	 * @return das skalierte Image
	 */
	private static Image getScaledImageFromResource(String pPath, int pWidth, int pHeight) {
		Image source = getImageFromResource(pPath);
		return new Image(null, source.getImageData().scaledTo(pWidth, pHeight));
	}

}
