package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import java.io.*;
import java.nio.file.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.io.Io;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.*;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;

public class InTest {
	Instruction back;
	Machine machine;
	File file;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	@Before
	public void setUp() throws FileNotFoundException, IOException {
		file = testFolder.newFile();
		back = new In();
		machine = new Machine();
		machine.setIo(new Io(file.getPath(), null));
	}

	@Test
	public void nameTest() {
		assertEquals("IN", back.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals(',', back.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFF00FF00, back.getColor());
	}

	@Test(expected=EndOfInputException.class)
	public void acceptNoInputTest() {
		back.accept(machine);
	}

	@Test
	public void acceptTest() throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
			writer.write("az");
		}
		back.accept(machine);
		assertEquals(-31, machine.readMemory());
		back.accept(machine);
		assertEquals(-6, machine.readMemory());
		assertTrue(0 != Metrics.DATA_WRITE.value());
	}
}
