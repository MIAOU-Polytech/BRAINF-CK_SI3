package fr.unice.polytech.si3.miaou.brainfuck.io;

import java.util.*;
import java.io.*;
import java.nio.file.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InputFileNotFoundException;


public class ReadTextFileTest {
	private static final int SIZE_SQUARE = 3;
	ReadTextFile rif;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() {
	}

	@Test
	public void readTextFileEmptyTest() throws IOException {
		File file = testFolder.newFile();
		rif = new ReadTextFile(file.getPath());
		assertEquals(0, rif.getData().toArray().length);
	}

	@Test
	public void readTextFileTest() throws IOException {
		String filename = testFolder.newFile().getPath();

		Path filepath = Paths.get(filename);
		try (BufferedWriter writer = Files.newBufferedWriter(filepath)) {
			writer.write("miaou\n\nnyan");
		}

		rif = new ReadTextFile(filename);
		assertArrayEquals(new String[] {"miaou", "", "nyan"}, rif.getData().toArray(String[]::new));
	}

	@Test(expected=FileNotFoundException.class)
	public void readTextFileNotFoundTest() throws IOException {
		new ReadTextFile("nonexistentfile");
	}
}
