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


public class IoTest {
	Io io;

	ByteArrayOutputStream outStream;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() {
		outStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outStream));
	}

	@Test
	public void writeSystemOutTest() throws FileNotFoundException {
		io = new Io(null, null);
		assertTrue(outStream.toString().isEmpty());
		io.write('a');
		io.write('z');
		assertEquals("az", outStream.toString());
	}

	@Test
	public void writeFileOutTest() throws IOException, FileNotFoundException {
		String filename = testFolder.newFile().getPath();
		io = new Io(null, filename);
		assertTrue((new String(Files.readAllBytes(Paths.get(filename)))).isEmpty());
		io.write('a');
		io.write('z');
		assertEquals("az", new String(Files.readAllBytes(Paths.get(filename))));
	}

	@Test(expected=InputFileNotFoundException.class)
	public void inputFileNotFoundTest() throws IOException, FileNotFoundException {
		new Io("", null);
	}

	@Test
	public void readFileInTest() throws IOException, FileNotFoundException {
		String filename = testFolder.newFile().getPath();
		io = new Io(filename, null);
		assertEquals(-1, io.read()); // empty input file
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
			writer.write("az");
		}
		assertEquals('a', io.read());
		assertEquals('z', io.read());
	}
}
