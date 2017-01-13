// Should be tested using C compiler instead, this test class is complete shit

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

public class CLanguageTest {
	CLanguage cl;

	@Before
	public void setUp() {
		cl = new CLanguage();
	}

	@Test
	public void getNameTest() {
		assertEquals("c", cl.getName());
	}

	@Test
	public void getExtensionTest() {
		assertEquals("c", cl.getExtension());
	}

	@Test
	public void translateInstructionTest() {
		assertEquals("    }", cl.translateInstruction(new Back()));
		assertEquals("    (*memory)--;", cl.translateInstruction(new Decr()));
		assertEquals("    (*memory) = fgetc(finput);", cl.translateInstruction(new In()));
		assertEquals("    (*memory)++;", cl.translateInstruction(new Incr()));
		assertEquals("    while (*memory) {", cl.translateInstruction(new Jump()));
		assertEquals("    memory--;", cl.translateInstruction(new Left()));
		assertEquals("    fputc(*memory, foutput);", cl.translateInstruction(new Out()));
		assertEquals("    memory++;", cl.translateInstruction(new Right()));
		assertEquals("    return;\n}", cl.translateInstruction(new Return()));
	}

	@Test
	public void procedureTranslateTest() {
		assertEquals("    test();", cl.translateInstruction(new ProcedureCall(new Procedure("test", 0))));
	}

	@Test
	public void buildProcDeclTest() {
		assertEquals("    void test() {", cl.buildProcedureDeclaration("test"));
	}

	@Test
	public void headerTest() {
		assertEquals("#include <stdio.h>\n"
		+ "#include <stdlib.h>\n"
		+ "#include <string.h>\n\n"

		+ "#define SIZE_MEMORY 30000\n\n"
		+ "\n"
		+ "static char p[SIZE_MEMORY] = {0};\n"
		+ "static char *memory = p;\n"
		+ "\n"
		+ "int main() {\n", cl.buildHeader());
	}

	@Test
	public void ioTest() {
		assertEquals("    FILE *finput = stdin;\n    FILE *foutput = fopen(\"test.txt\", \"w\");\n", cl.io("System.in", "test.txt"));
		assertEquals("    FILE *finput = fopen(\"test.txt\", \"r\");\n    FILE *foutput = stdout;\n", cl.io("test.txt", "System.out"));
	}

	@Test
	public void footerTest() {
		assertEquals("\n"
		+ "    for (int i = 0; i < SIZE_MEMORY; i++) {\n"
		+ "        if (p[i]) { fprintf(foutput, \"\\nC%d: %d\", i, p[i]); }\n    }\n"
		+ "    fprintf(foutput, \"\\n\");\n"
		+ "    return 0;\n}", cl.buildFooter());

	}
}
