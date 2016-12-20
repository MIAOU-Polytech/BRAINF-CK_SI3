package fr.unice.polytech.si3.miaou.brainfuck.io;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InputFileNotFoundException;


public class ReadImageFileTest {
	private static final int SIZE_SQUARE = 3;
	ReadImageFile rif;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() {
	}

	@Test
	public void readImageFileEmptyTest() throws IOException {
		File file = testFolder.newFile();
		rif = new ReadImageFile(file.getPath());
		assertEquals(0, rif.getData().toArray().length);
	}

	@Test
	public void readImageFileTest() throws IOException {
		File file = testFolder.newFile();
		BufferedImage image = new BufferedImage(2*SIZE_SQUARE, 2*SIZE_SQUARE, BufferedImage.TYPE_INT_RGB);
		image.setRGB(0*SIZE_SQUARE, 0*SIZE_SQUARE, 0xFF000000);
		image.setRGB(1*SIZE_SQUARE, 0*SIZE_SQUARE, 0xFFFF0000);
		image.setRGB(0*SIZE_SQUARE, 1*SIZE_SQUARE, 0xFF00FF00);
		image.setRGB(1*SIZE_SQUARE, 1*SIZE_SQUARE, 0xFF0000FF);
		ImageIO.write(image, "bmp", file);

		rif = new ReadImageFile(file.getPath());

		assertArrayEquals(new int[] {0xFF000000, 0xFFFF0000, 0xFF00FF00, 0xFF0000FF}, rif.getData().toArray());
	}

	@Test(expected=FileNotFoundException.class)
	public void readImageFileNotFoundTest() throws IOException {
		new ReadImageFile("");
	}
}
