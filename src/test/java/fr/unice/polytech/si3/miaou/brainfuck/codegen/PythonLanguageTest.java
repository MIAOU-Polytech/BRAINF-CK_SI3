// Should be tested using Python interpreter instead, this test class is complete shit

package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.*;
import fr.unice.polytech.si3.miaou.brainfuck.Procedure;

public class PythonLanguageTest {
	PythonLanguage pl;

	@Before
	public void setUp() {
		pl = new PythonLanguage();
	}

	@Test
	public void getNameTest() {
		assertEquals("python", pl.getName());
	}

	@Test
	public void getExtensionTest() {
		assertEquals("py", pl.getExtension());
	}

	@Test
	public void translateInstructionTest() {
		assertEquals("memory[i] -= 1", pl.translateInstruction(new Decr()));
		assertEquals("memory[i] = ord(finput.read(1))", pl.translateInstruction(new In()));
		assertEquals("memory[i] += 1", pl.translateInstruction(new Incr()));
		assertEquals("i -= 1", pl.translateInstruction(new Left()));
		assertEquals("foutput.write(chr(memory[i]))", pl.translateInstruction(new Out()));
		assertEquals("i += 1", pl.translateInstruction(new Right()));
		assertEquals("while not memory[i] == 0:", pl.translateInstruction(new Jump()));
		assertEquals("    ", pl.translateInstruction(new Back()));
		assertEquals("", pl.translateInstruction(new Return()));
	}

	@Test
	public void testIndent() {
		pl.buildProcedureDeclaration("test");
		assertTrue(pl.translateInstruction(new Incr()).startsWith("    "));

		pl.translateInstruction(new Jump());
		assertTrue(pl.translateInstruction(new Incr()).startsWith("        "));
		pl.translateInstruction(new Back());
		assertFalse(pl.translateInstruction(new Incr()).startsWith("        "));


		pl.translateInstruction(new Return());
		assertFalse(pl.translateInstruction(new Incr()).startsWith("    "));
	}

	@Test
	public void procedureTranplateTest() {
		assertEquals("test()", pl.translateInstruction(new ProcedureCall(new Procedure("test", 0))));
	}

	@Test
	public void buildProcDeclTest() {
		assertEquals("def test():", pl.buildProcedureDeclaration("test"));
	}

	@Test
	public void headerTest() {
		assertEquals("#!/usr/bin/env python\n"
		+ "#coding: latin-1\n\n"
		+ "import os\n"
		+ "import sys\n\n"
		+ "size_memory = 30000\n"
		+ "memory = [0] * size_memory\n"
		+ "i = 0\n", pl.buildHeader());
	}

	@Test
	public void ioTest() {
		assertEquals("finput = sys.stdin\nfoutput = open(\"test.txt\", \"wb\")\n", pl.io("System.in", "test.txt"));
		assertEquals("finput = open(\"test.txt\", \"rb\")\nfoutput = sys.stdout\n", pl.io("test.txt", "System.out"));
	}

	@Test
	public void footerTest() {
		assertEquals("\nfor j in range(0, size_memory):\n"
		+ "    if memory[j] != 0:\n"
		+ "        string = \"\\nC\"+str(j)+\": \"+str(memory[j])\n"
		+ "        foutput.write(string)\n"
		+ "foutput.write(\"\\n\")", pl.buildFooter());
	}
}
