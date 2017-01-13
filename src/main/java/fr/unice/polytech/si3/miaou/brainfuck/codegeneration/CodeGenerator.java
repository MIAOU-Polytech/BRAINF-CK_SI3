package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.io.IOException;
import java.util.List;
import java.io.File;
import java.util.Collection;

import fr.unice.polytech.si3.miaou.brainfuck.io.WriteTextFile;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Return;
import fr.unice.polytech.si3.miaou.brainfuck.Procedure;
import fr.unice.polytech.si3.miaou.brainfuck.parser.InstructionParser;

/**
 * Builds a file that is the translation of the brainfuck program in another language.
 *
 * @author Guillaume Casagrande
 */
public class CodeGenerator {
	/**
	 * The file where the program has to be wrote.
	 */
	private WriteTextFile wtf;

	/**
	 * The Language object used to translate the code in another language.
	 */
	private Language lang;

	/**
	 * Input and output of the program.
	 */
	private String input;
	private String output;

	private int entryPoint;

	private InstructionParser ip;

	/**
	 * Main constructor of the <code>CodeGenerator</code> class.
	 *
	 * @param language	the name of the language destination.
	 * @param in		input file to use, null for standard input.
	 * @param out		output file to use, null for standard output.
	 * @param in		InstructionParser from which to get declared procedures.
	 * @throws IOException	if it's impossible to create the log file.
	 */
	public CodeGenerator(String language, String in, String out, InstructionParser ip) throws IOException {
		LanguageSet ls = new LanguageSet();
		lang = ls.getLanguage(language);
		this.ip = ip;

		input = (in == null) ? "System.in" : in;
		output = (out == null) ? "System.out" : out;

		entryPoint = ip.getMainPosition();
	}

	/**
	 * Actually generate and write code to output file.
	 *
	 * @param filename	brainfuck program filename, will output to this filename with the extension for the selected language.
	 */
	public void generate(String filename) throws IOException {
		wtf = new WriteTextFile(filename.substring(0, filename.lastIndexOf('.'))+"."+lang.getExtension());
		wtf.clear(); // Empty file

		front();
		writeProcedures(ip.getProcedures(), ip.get());
		io();
		writeInstructions(ip.get());
		footer();

		setExecutable(filename);
	}

	/**
	 * Writes the equivalent of a call of an instruction in another language.
	 *
	 * @param instructions	list of instructions to generate code for.
	 */
	public void writeInstructions(List<Instruction> instructions) {
		for (int i = entryPoint; i < instructions.size(); i++) {
			wtf.write(lang.translateInstruction(instructions.get(i)));
		}
	}

	/**
	 * Writes the structure of a procedure.
	 *
	 * @param proc  the procedure to be written.
	 * @param instructions  the list of all instructions of the bfck program.
	 */
	private void writeProcedure(Procedure proc, List<Instruction> instructions) {
		wtf.write(lang.buildProcedureDeclaration(proc.getName()));
		for (int i = proc.getPosition(); i < instructions.size(); i++) {
			wtf.write(lang.translateInstruction(instructions.get(i)));
			if (instructions.get(i) instanceof Return)
				break;
		}
	}

	/**
	 * Writes all the procedures of the program.
	 *
	 * @param procs  the list of all the procedures of the program.
	 * @param instructions  the list of all instructions of the bfck program.
	 */
	private void writeProcedures(Collection<Procedure> procs, List<Instruction> instructions) {
		for (Procedure proc : procs)
			writeProcedure(proc, instructions);
	}

	/**
	 * Writes the front of the file.
	 */
	private void front() throws IOException {
		wtf.write(lang.buildHeader());
	}

	/**
	 * Writes the footer of the file.
	 */
	public void footer() throws IOException {
		wtf.write(lang.buildFooter());
	}

	/**
	 * Writes the lines to create the io files.
	 */
	private void io() {
		wtf.write(lang.io(input, output));
	}

	/**
	 * Add executable perm on the generated file.
	 *
	 * @param filename	file path.
	 */
	private void setExecutable(String filename) throws IOException {
		(new File(filename)).setExecutable(true, false);
	}
}
