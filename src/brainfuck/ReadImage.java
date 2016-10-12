package brainfuck;

import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

import brainfuck.virtualmachine.Machine;

/**
 * Reading an image and extract color informations for each 3x3 square to be converted into matching instructions in the future.
 * The parsed image MUST be in .bmp format.
 * @author MIAOU
 */
public class ReadImage {
	private BufferedImage img;
	private List<Integer> instructions;

	/**
	 * Main constructor of the <code>ReadImage</code> class.
	 * @param url The url of the parsed image.
	 * @throws IOException if the given URL doesn't exist, or if reading failed.
	 */
	public ReadImage(String url) throws IOException {
		img = ImageIO.read(new File(url));
		instructions = new ArrayList<>();

		int height = img.getHeight();
		int width = img.getWidth();

		for (int y = 0 ; y < height ; y+=3) {
			for (int x = 0 ; x < width ; x+=3) {
				instructions.add(img.getRGB(x, y));
			}
		}
	}

	/**
	 * Returns the colors of the parsed image, as an <code>IntStream</code> containing integers matching the RGB values of each square.
	 * @return an <code>IntStream</code> containing the integer value of each color.
	 */
	public IntStream getData() {
		return instructions.stream().mapToInt(Integer::intValue);
	}
}
