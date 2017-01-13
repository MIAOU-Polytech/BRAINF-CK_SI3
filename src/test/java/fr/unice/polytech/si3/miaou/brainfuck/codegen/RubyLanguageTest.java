// Should be tested using Ruby interpreter instead, this test class is complete shit

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

public class RubyLanguageTest {
	RubyLanguage rl;

	@Before
	public void setUp() {
		rl = new RubyLanguage();
	}

	@Test
	public void getNameTest() {
		assertEquals("ruby", rl.getName());
	}

	@Test
	public void getExtensionTest() {
		assertEquals("rb", rl.getExtension());
	}

	@Test
	public void translateInstructionTest() {
		assertEquals("end", rl.translateInstruction(new Back()));
		assertEquals("$memory[$i] -= 1", rl.translateInstruction(new Decr()));
		assertEquals("$memory[$i] = finput.getbyte()", rl.translateInstruction(new In()));
		assertEquals("$memory[$i] += 1", rl.translateInstruction(new Incr()));
		assertEquals("while $memory[$i] != 0", rl.translateInstruction(new Jump()));
		assertEquals("$i -= 1", rl.translateInstruction(new Left()));
		assertEquals("foutput.write($memory[$i].chr)", rl.translateInstruction(new Out()));
		assertEquals("$i += 1", rl.translateInstruction(new Right()));
		assertEquals("end", rl.translateInstruction(new Return()));
	}

	@Test
	public void procedureTranslateTest() {
		assertEquals("test()", rl.translateInstruction(new ProcedureCall(new Procedure("test", 0))));
	}

	@Test
	public void buildProcDeclTest() {
		assertEquals("def test", rl.buildProcedureDeclaration("test"));
	}

	@Test
	public void headerTest() {
		assertEquals("#!/usr/bin/env ruby\n\n$memory = Array.new(30000, 0)\n$i = 0\n", rl.buildHeader());
	}

	@Test
	public void ioTest() {
		assertEquals("finput = $stdin\nfoutput = File.open(\"test.txt\", 'wb')\n", rl.io("System.in", "test.txt"));
		assertEquals("finput = File.open(\"test.txt\", 'rb')\nfoutput = $stdout\n", rl.io("test.txt", "System.out"));
	}

	@Test
	public void footerTest() {
		assertEquals("\nfor i in (0...30000)\n"
		+ "    if $memory[i] != 0 then\n"
		+ "        string = \"\\nC\"+i.to_s+\": \"+$memory[i].ord.to_s\n"
		+ "        foutput.write(string)\n"
		+ "    end\n"
		+ "end\nfoutput.write(\"\\n\")", rl.buildFooter());

	}
}
