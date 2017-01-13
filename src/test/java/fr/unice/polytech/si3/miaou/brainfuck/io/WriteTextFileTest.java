package fr.unice.polytech.si3.miaou.brainfuck.io;

import java.util.*;
import java.io.*;
import java.nio.file.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.OutputFileNotFoundException;


public class WriteTextFileTest {
	WriteTextFile wtf;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() {
	}

	@Test
	public void writeTextFileEmptyTest() throws IOException {
		String filename = testFolder.newFile().getPath();
		wtf = new WriteTextFile(filename);
		assertTrue((new String(Files.readAllBytes(Paths.get(filename)))).isEmpty());
	}

	@Test
	public void writeTextFileTest() throws IOException {
		String filename = testFolder.newFile().getPath();
		wtf = new WriteTextFile(filename);
		wtf.write("miaou");
		wtf.write("");
		wtf.write("nyan");
		assertEquals("miaou\n\nnyan\n", (new String(Files.readAllBytes(Paths.get(filename)))));
	}

	@Test
	public void writeTextFileAppendTest() throws IOException {
		String filename = testFolder.newFile().getPath();
		wtf = new WriteTextFile(filename);
		wtf.write("miaou");
		wtf = new WriteTextFile(filename);
		wtf.write("nyan");
		assertEquals("miaou\nnyan\n", (new String(Files.readAllBytes(Paths.get(filename)))));
	}

	@Test
	public void writeTextFileClearTest() throws IOException {
		String filename = testFolder.newFile().getPath();
		wtf = new WriteTextFile(filename);
		wtf.write("miaou");
		wtf = new WriteTextFile(filename);
		wtf.clear();
		wtf.write("nyan");
		assertEquals("nyan\n", (new String(Files.readAllBytes(Paths.get(filename)))));
	}

	@Test(expected=OutputFileNotFoundException.class)
	public void outputDirNotFoundTest() throws IOException {
		String filename = testFolder.getRoot().getPath() + "/test/test";
		wtf = new WriteTextFile(filename);
	}

/*	@Test(expected=FileNotFoundException.class)
	public void readTextFileNotFoundTest() throws IOException {
		new WriteTextFile("nonexistentfile");
	}*/
}
