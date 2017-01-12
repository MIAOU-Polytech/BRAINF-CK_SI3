package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.*;

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
	}

	@Override
	String translateInstruction(Instruction instr) {
		return "    " + getTranslation(instr.getClass());
	}

	@Override
	String buildHeader() {
		return "#include <stdio.h>\n"
		+ "#include <stdlib.h>\n"
		+ "#include <string.h>\n\n"

		+ "#define SIZE_MEMORY 30000\n\n"

		+ "int main() {\n"
		+ "    char *memory = calloc(SIZE_MEMORY, sizeof(char));\n"
		+ "    char *p = memory;\n";
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
		+ "    for (int i = 0; i < SIZE_MEMORY; i++, *p++) {\n"
		+ "        if (*p) { fprintf(foutput, \"\\nC%d: %d\", i, *p); }\n    }\n"
		+ "    fprintf(foutput, \"\\n\");\n"
		+ "    return 0;\n}";
	}
}
