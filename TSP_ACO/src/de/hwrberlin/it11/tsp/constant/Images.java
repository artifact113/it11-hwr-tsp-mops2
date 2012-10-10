/**
 * Copyright (c) 2012 mops² Productions
 */
package de.hwrberlin.it11.tsp.constant;

import org.eclipse.swt.graphics.Image;

/**
 * Diese Klasse enthält die verwendeten Bilder als Konstanten.
 * 
 * @author Patrick Szostack
 * 
 */
public class Images {

	/** Das Titelbild des Programms */
	public static final Image ANTS_ON_FIRE = getImageFromResource("/img/armeise.png");

	public static final Image COWBOY = getScaledImageFromResource("/img/Ameise_Cowboy.png", 250, 300);

	public static final Image JEA = getImageFromResource("/img/jea.png");

	public static final Image AMEISE = getScaledImageFromResource("/img/Ameise.png", 300, 300);

	/** Ordner */
	public static final Image FOLDER = getImageFromResource("/img/folder.png");

	/** Rotes Kreuz */
	public static final Image CLOSE = getImageFromResource("/img/cross.png");



	/**
	 * Erzeugt ein Image aus dem angegebenen Dateipfad.
	 * 
	 * @param pPath
	 *            Dateipfad des Image
	 * @return das erzeugte Image
	 */
	private static Image getImageFromResource(String pPath) {
		return new Image(null, Images.class.getResourceAsStream(pPath));
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
