package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.io.Io;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.*;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;

public class OutTest {
	Instruction out;
	Machine machine;
	ByteArrayOutputStream outStream;

	@Before
	public void setUp() throws FileNotFoundException {
		out = new Out();
		machine = new Machine();
		outStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outStream));
		machine.setIo(new Io(null, null));
	}

	@Test
	public void nameTest() {
		assertEquals("OUT", out.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('.', out.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFFFFFF00, out.getColor());
	}

	@Test
	public void acceptTest() throws IOException {
		assertTrue(outStream.toString().isEmpty());
		out.accept(machine);
		assertEquals("\0", outStream.toString());
		out.accept(machine);
		assertEquals("\0\0", outStream.toString());
		assertTrue(0 != Metrics.DATA_READ.value());
	}
}
