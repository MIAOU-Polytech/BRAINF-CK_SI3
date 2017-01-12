package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.Procedure;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

/**
 * Defines the behaviour of the languages.
 *
 * @author Guillaume Casagrande
 */
abstract class Language {
	/**
	 * Lists the equivalent code of each instruction.
	 */
	private Map<Class<? extends Instruction>, String> instructionsTranslation;

	/**
	 * Reprensents the extension of the file according to the language.
	 */
	private String extension;

	/**
	 * The name of the language.
	 */
	private String name;

	/**
	 * Constructs a Language object and create the map of instructions.
	 */
	Language(String name, String extension) {
		instructionsTranslation = new HashMap<>();
		this.name = name;
		this.extension = extension;
	}

	/**
	 * Picks an instruction and gives its equivalent in another language.
	 */
	abstract String translateInstruction(Instruction instr);

	/**
	 * Writes the front of the file.
	 */
	abstract String buildHeader();

	abstract String buildProcedureDeclaration(String procname);

	/**
	 * Writes the footer of the file.
	 */
	abstract String buildFooter();

	/**
	 * Creates the io files.
	 */
	abstract String io(String in, String out);

	void addTranslation(Class<? extends Instruction> instr, String code) {
		instructionsTranslation.put(instr, code);
	}

	String getTranslation(Class<? extends Instruction> instr) {
		return instructionsTranslation.get(instr);
	}

	/**
	 * Returns the extension of the file.
	 */
	String getExtension() {
		return extension;
	}

	/**
	 * Returns the name of the language.
	 */
	String getName() {
		return name;
	}
}
