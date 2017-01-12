package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.util.Collection;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.*;
import fr.unice.polytech.si3.miaou.brainfuck.Procedure;

/**
 * Translates a brainfuck program in C.
 *
 * @author Guillaume Casagrande
 */
class CLanguage extends Language {
	public static final String NAME = "c";
	public static final String EXTENSION = "c";

	/**
	 * Constructs a CLanguage object and fills the map of instructions.
	 */
	CLanguage() {
		super(NAME, EXTENSION);

		addTranslation(Back.class, "}");
		addTranslation(Decr.class, "(*memory)--;");
		addTranslation(In.class, "(*memory) = fgetc(finput);");
		addTranslation(Incr.class, "(*memory)++;");
		addTranslation(Jump.class, "while (*memory) {");
		addTranslation(Left.class, "memory--;");
		addTranslation(Out.class, "fputc(*memory, foutput);");
		addTranslation(Right.class, "memory++;");
		addTranslation(Return.class, "return;\n}");
		addTranslation(ProcedureCall.class, "();");
	}

	@Override
	String buildProcedureDeclaration(String procname) {
		return "    void " + procname + "() {";
	}

	@Override
	String translateInstruction(Instruction instr) {
		String s = "    ";
		if (instr instanceof ProcedureCall)
			s += ((ProcedureCall) instr).getProcedureName();
		s += getTranslation(instr.getClass());
		return s;
	}

	@Override
	String buildHeader() {
		return "#include <stdio.h>\n"
		+ "#include <stdlib.h>\n"
		+ "#include <string.h>\n\n"

		+ "#define SIZE_MEMORY 30000\n\n"
		+ "\n"
		+ "static char p[SIZE_MEMORY] = {0};\n"
		+ "static char *memory = p;\n"
		+ "\n"
		+ "int main() {\n";
	}

	@Override
	String io(String in, String out) {
		String s = ""
		+ "    FILE *finput;\n"
		+ "    FILE *foutput;\n";

		if ("System.in".equals(in)) {
			s += "    finput = stdin;\n";
		} else {
			s += "    finput = fopen(\"" + in + "\", \"r\");\n";
		} if ("System.out".equals(out)) {
			s += "    foutput = stdout;\n";
		} else {
			s += "    foutput = fopen(\"" + out + "\", \"w\");\n";
		}
		return s;
	}

	@Override
	String buildFooter() {
		return "\n"
		+ "    for (int i = 0; i < SIZE_MEMORY; i++) {\n"
		+ "        if (p[i]) { fprintf(foutput, \"\\nC%d: %d\", i, p[i]); }\n    }\n"
		+ "    fprintf(foutput, \"\\n\");\n"
		+ "    return 0;\n}";
	}
}
