package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.*;

/**
 * Translates a brainfuck program in Ruby.
 *
 * @author Guillaume Casagrande
 */
class RubyLanguage extends Language {
	public static final String NAME = "ruby";
	public static final String EXTENSION = "rb";

	/**
	 * Constructs a RubyLanguage object and fills the map of instructions.
	 */
	RubyLanguage() {
		super(NAME, EXTENSION);

		addTranslation(Back.class, "end");
		addTranslation(Decr.class, "$memory[$i] -= 1");
		addTranslation(In.class, "$memory[$i] = finput.getbyte()");
		addTranslation(Incr.class, "$memory[$i] += 1");
		addTranslation(Jump.class, "while $memory[$i] != 0");
		addTranslation(Left.class, "$i -= 1");
		addTranslation(Out.class, "foutput.write($memory[$i].chr)");
		addTranslation(Right.class, "$i += 1");
		addTranslation(Return.class, "end");
		addTranslation(ProcedureCall.class, "()");
	}

	@Override
	String buildProcedureDeclaration(String procname) {
		return "def " + procname;
	}

	@Override
	String translateInstruction(Instruction instr) {
		String s = "";

		if (instr instanceof ProcedureCall)
			s += ((ProcedureCall) instr).getProcedureName();

		s += getTranslation(instr.getClass());
		return s;
	}

	@Override
	String buildHeader() {
		return "#!/usr/bin/env ruby\n\n"
		+ "$memory = Array.new(30000, 0)\n"
		+ "$i = 0\n";
	}

	@Override
	String io(String in, String out) {
		String s = "";
		if ("System.in".equals(in)) {
			s += "finput = $stdin\n";
		} else {
			s += "finput = File.open(\"" + in + "\", 'rb')\n";
		}
		if ("System.out".equals(out)) {
			s += "foutput = $stdout\n";
		} else {
			s += "foutput = File.open(\"" + out + "\", 'wb')\n";
		}
		return s;
	}

	@Override
	String buildFooter() {
		return "\nfor i in (0...30000)\n"
		+ "    if $memory[i] != 0 then\n"
		+ "        string = \"\\nC\"+i.to_s+\": \"+$memory[i].ord.to_s\n"
		+ "        foutput.write(string)\n"
		+ "    end\n"
		+ "end\nfoutput.write(\"\\n\")";
	}
}
