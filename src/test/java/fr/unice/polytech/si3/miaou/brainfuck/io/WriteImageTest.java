package fr.unice.polytech.si3.miaou.brainfuck.io;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.Ignore;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;


public class WriteImageTest {
	private static final int SIZE_SQUARE = 3;
	WriteImage wi;
	ByteArrayOutputStream outStream;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() {
		outStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outStream));
	}

	@Test
	public void writeImageEmptyTest() throws IOException {
		wi = new WriteImage(new ArrayList<>());
		wi.write();

		byte[] out = outStream.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(out);

		BufferedImage img = ImageIO.read(bais);
		assertEquals(SIZE_SQUARE, img.getWidth());
		assertEquals(SIZE_SQUARE, img.getHeight());
		assertEquals(0xFF000000, img.getRGB(0,0));

	}

	@Test
	public void writeImageTest() throws IOException {
		Integer[] colors = { 0xFFFFFFFF, 0xFF000000, 0xFF0000FF, 0xFF00FF00, 0xFFFF0000 };

		wi = new WriteImage(Arrays.asList(colors));
		wi.write();

		byte[] out = outStream.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(out);

		BufferedImage img = ImageIO.read(bais);
		assertEquals(3*SIZE_SQUARE, img.getWidth());
		assertEquals(3*SIZE_SQUARE, img.getHeight());

		assertEquals((int)colors[0], img.getRGB(0*SIZE_SQUARE, 0*SIZE_SQUARE));
		assertEquals((int)colors[1], img.getRGB(1*SIZE_SQUARE, 0*SIZE_SQUARE));
		assertEquals((int)colors[2], img.getRGB(2*SIZE_SQUARE, 0*SIZE_SQUARE));
		assertEquals((int)colors[3], img.getRGB(0*SIZE_SQUARE, 1*SIZE_SQUARE));
		assertEquals((int)colors[4], img.getRGB(1*SIZE_SQUARE, 1*SIZE_SQUARE));
		assertEquals(0xFF000000, img.getRGB(2*SIZE_SQUARE, 1*SIZE_SQUARE));
		assertEquals(0xFF000000, img.getRGB(0*SIZE_SQUARE, 2*SIZE_SQUARE));
		assertEquals(0xFF000000, img.getRGB(1*SIZE_SQUARE, 2*SIZE_SQUARE));
		assertEquals(0xFF000000, img.getRGB(2*SIZE_SQUARE, 2*SIZE_SQUARE));
	}
}
