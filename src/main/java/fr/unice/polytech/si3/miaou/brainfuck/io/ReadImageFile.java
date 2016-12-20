package fr.unice.polytech.si3.miaou.brainfuck.io;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.stream.IntStream;

/**
 * Reads an image and extract color code for each 3x3 square.
 * The parsed image MUST be in .bmp format.
 *
 * @author Julien Lemaire
 */
public class ReadImageFile {
	/**
	 * Stream of colors being built.
	 */
	private IntStream.Builder colors;

	/**
	 * Width and height of a square (number of pixels).
	 */
	private static final int SIZE_SQUARE = 3;

	/**
	 * Main constructor of the <code>ReadImage</code> class.
	 *
	 * @param filename	the filename of the image to decode.
	 * @throws IOException			if the given filename doesn't exist, or if reading failed.
	 * @throws FileNotFoundException	if the given file does not exist.
	 */
	public ReadImageFile(String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists())
			throw new FileNotFoundException();

		BufferedImage img = ImageIO.read(file);
		colors = IntStream.builder();

		if (img == null) // Could not decode file (empty?)
			return;

		int height = img.getHeight();
		int width = img.getWidth();

		for (int y = 0 ; y < height ; y += SIZE_SQUARE) {
			for (int x = 0 ; x < width ; x += SIZE_SQUARE) {
				colors.add(img.getRGB(x, y));
			}
		}
	}

	/**
	 * Returns the colors of the decoded image, as an <code>IntStream</code> containing color codes as ints in the ARGB format.
	 *
	 * @return an <code>IntStream</code> containing the integer value of each color in ARGB.
	 */
	public IntStream getData() {
		return colors.build();
	}
}
