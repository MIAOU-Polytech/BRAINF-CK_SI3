package fr.unice.polytech.si3.miaou.brainfuck.io;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Build a bmp image from a list of instructions'color.
 *
 * @author Guillaume Casagrande
 * @author Julien Lemaire
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferedImage.html">BufferedImage</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html">Graphics</a>
 * @see fr.unice.polytech.si3.miaou.brainfuck.Translator
 */
public class WriteImage {
	/**
	 * Image represented by a buffer.
	 */
	private BufferedImage image;

	/**
	 * Graphic which regroups all the squares.
	 */
	private Graphics graph;

	/**
	 * Instructions to represent in the image.
	 */
	private List<Integer> colors;

	/**
	 * Number of cells by column (or by line).
	 */
	private int nbCol;

	/**
	 * Width and height of a square (number of pixels).
	 */
	private static final int SIZE_SQUARE = 3;

	/**
	 * Constructs an image and draws it.
	 * nbCol figures out how many cells that a column (or a line) has to contain.
	 * Then, a BufferedImage is created with the good dimensions.
	 * The graphic is used to represent easily forms like squares in the image.
	 *
	 * @param colors  list of the color of each pixel
	 * @throws IOException	if an IO error arised when writing the image.
	 */
	public WriteImage(List<Integer> colors) throws IOException {
		this.colors = colors;
		//The image has nbCol² cells. Furthermore, there should be more cells than colors.size().
		//So, one column contains the value rounded off to the superiors of the square root of the number of instructions.
		nbCol = (int)Math.ceil(Math.sqrt(colors.size()));

		if (nbCol <= 0) // Workaround in case of empty list (BufferedImage doesn't accept null width or height)
			nbCol = 1; // Generate one black SIZE_SQUARE*SIZE_SQUARE tile

		image = new BufferedImage(SIZE_SQUARE*nbCol, SIZE_SQUARE*nbCol, BufferedImage. TYPE_INT_RGB);
		graph = image.createGraphics();
		draw();
		writeBmp();
	}

	/**
	 * Add each square in the graphic.
	 */
	private void draw() {
		for (int i = 0; i < colors.size(); i++) {
			graph.setColor(new Color(colors.get(i)));
			//Add a rectangle to the graphic. The first two arguments are the coordinates.
			//The others are the size of the rectangle.
			graph.fillRect(SIZE_SQUARE*(i%nbCol), SIZE_SQUARE*(i/nbCol), SIZE_SQUARE, SIZE_SQUARE);
		}
	}

	/**
	 * Create the file from the BufferedImage.
	 *
	 * @throws IOException	if an IO error arised when writing the image.
	 */
	private void writeBmp() throws IOException {
		ImageIO.write(image, "bmp", System.out);
	}
}
