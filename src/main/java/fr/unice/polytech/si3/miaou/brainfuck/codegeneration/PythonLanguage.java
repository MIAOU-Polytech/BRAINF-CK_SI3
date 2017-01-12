package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.util.Collection;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.*;
import fr.unice.polytech.si3.miaou.brainfuck.Procedure;

/**
 * Translates a brainfuck program in Python.
 *
 * @author Guillaume Casagrande
 */
class PythonLanguage extends Language {
	public static final String NAME = "python";
	public static final String EXTENSION = "py";

	/**
	 * Counts the number of tabulations to add before the writing of an instruction.
	 */
	private int spaces;

	/**
	 * Constructs a PythonLanguage object and fills the map of instructions.
	 */
	PythonLanguage() {
		super(NAME, EXTENSION);
		spaces = 0;

		addTranslation(Back.class, "");
		addTranslation(Decr.class, "memory[i] -= 1");
		addTranslation(In.class, "memory[i] = ord(finput.read(1))");
		addTranslation(Incr.class, "memory[i] += 1");
		addTranslation(Jump.class, "while not memory[i] == 0:");
		addTranslation(Left.class, "i -= 1");
		addTranslation(Out.class, "foutput.write(chr(memory[i]))");
		addTranslation(Right.class, "i += 1");
		addTranslation(Return.class, "");
		addTranslation(ProcedureCall.class, "()");
	}

	@Override
	String buildProcedureDeclaration(String procname) {
		spaces++;
		return "def " + procname + "():";
	}

	@Override
	String translateInstruction(Instruction instr) {
		String s = "";
		for (int i = spaces; i > 0; i--) {
			s += "    ";
		}
		if (Jump.class == instr.getClass()) { spaces++; }
		else if (Back.class == instr.getClass() || instr instanceof Return) { spaces--; }

		if (instr instanceof ProcedureCall)
			s += ((ProcedureCall) instr).getProcedureName();

		s += getTranslation(instr.getClass());
		return s;
	}

	@Override
	String buildHeader() {
		return "#!/usr/bin/env python\n"
		+ "#coding: latin-1\n\n"
		+ "import os\n"
		+ "import sys\n\n"
		+ "size_memory = 30000\n"
		+ "memory = [0] * size_memory\n"
		+ "i = 0\n";
	}

	@Override
	String io(String in, String out) {
		String s = "";
		if ("System.in".equals(in)) {
			s += "finput = sys.stdin\n";
		} else {
			s += "finput = open(\"" + in +"\", \"rb\")\n";
		} if ("System.out".equals(out)) {
			s += "foutput = sys.stdout\n";
		} else {
			s += "foutput = open(\"" + out + "\", \"wb\")\n";
		}
		return s;
	}

	@Override
	String buildFooter() {
		return "\nfor j in range(0, size_memory):\n"
		+ "    if memory[j] != 0:\n"
		+ "        string = \"\\nC\"+str(j)+\": \"+str(memory[j])\n"
		+ "        foutput.write(string)\n"
		+ "foutput.write(\"\\n\")";
	}
}
