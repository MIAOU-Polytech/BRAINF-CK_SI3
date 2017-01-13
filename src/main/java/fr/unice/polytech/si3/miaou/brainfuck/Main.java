package fr.unice.polytech.si3.miaou.brainfuck;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.nio.file.Files;

import fr.unice.polytech.si3.miaou.brainfuck.parser.InstructionParser;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.LanguageException;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.io.Io;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.BrainfuckException;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.BracketMismatchException;
import fr.unice.polytech.si3.miaou.brainfuck.io.WriteImage;
import fr.unice.polytech.si3.miaou.brainfuck.io.ReadTextFile;
import fr.unice.polytech.si3.miaou.brainfuck.io.ReadImageFile;
import fr.unice.polytech.si3.miaou.brainfuck.codegeneration.CodeGenerator;

/**
 * Entry point for the application.
 *
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public class Main {
	/**
	 * ArgParser with parsed arguments.
	 */
	private ArgParser argp;

	/**
	 * Constructs a Main with the given ArgParser, ie. arguments parsed from command line parameters.
	 *
	 * @param argp	arguments parsed from main's args.
	 */
	Main(ArgParser argp) {
		this.argp = argp;
	}

	/**
	 * Application entry point.
	 *
	 * @param args array of received command-line arguments.
	 * @throws IOException		in case of IO error on file operation.
	 */
	public static void main(String[] args) throws IOException {
		ArgParser ap = null;
		try {
			ap = new ArgParser(args);
			Main app = new Main(ap);
			app.run();
		} catch (BrainfuckException e) {
			System.err.println(e);
			System.exit(e.getErrorCode());
		}
	}

	/**
	 * Runs the requested behaviour depending on the command line arguments.
	 *
	 * @throws IOException	in case of IO error on file operation.
	 */
	private void run() throws LanguageException, IOException {
		InstructionParser ip;

		if (argp.getType() == Type.IMAGE) {
			ip = imageRead(argp.getFilename());
		} else {
			ip = textFileRead(argp.getFilename());
		}

		if (argp.isIn(Mode.CHECK)) { // Don't run program in check mode
			check(ip);
		} else if (argp.isIn(Mode.RUN)) {
			check(ip);
			execute(ip);
		}

		if (argp.isIn(Mode.REWRITE)) {
			Translator tr = new Translator();
			tr.toShortSyntax(ip.get());
		}

		if (argp.isIn(Mode.TRANSLATE)) {
			if (argp.getType() == Type.TEXT) {
				Translator tra = new Translator();
				WriteImage iw = new WriteImage(tra.toColor(textFileRead(argp.getFilename()).get()));
			} else {
				Files.copy(Paths.get(argp.getFilename()), System.out); //Copy the image file to stdout
			}
		}

		if (argp.isIn(Mode.GENERATE)) {
			CodeGenerator cg = new CodeGenerator(argp.getFilename(), argp.getLanguage(), argp.getInput(), argp.getOutput(), textFileRead(argp.getFilename()));
		}
	}

	/**
	 * Returns an InstructionParser which parses the text file with given filename.
	 *
	 * @param filename	file's name of the text file we should parse.
	 * @return the InstructionParser which parsed the given file.
	 * @throws IOException  if an IO error arised when reading the file.
	 */
	private InstructionParser textFileRead(String filename) throws IOException {
		ReadTextFile file = new ReadTextFile(filename);

		return new InstructionParser(file.getData());
	}

	/**
	 * Returns an InstructionParser which parses the image file with given filename.
	 *
	 * @param filename	file's name of the image file we should parse.
	 * @return the InstructionParser which parsed the given file.
	 * @throws IOException	if an IO error arised when reading the file.
	 */
	private InstructionParser imageRead(String filename) throws IOException {
		ReadImageFile file = new ReadImageFile(filename);

		return new InstructionParser(file.getData());
	}

	/**
	 * Starts the check to make sure the program is well-formed.
	 *
	 * @param ip	InstructionParser which previously parsed a file.
	 */
	private void check(InstructionParser ip) {
		ip.getJumpTable().check();
	}

	/**
	 * Starts the Interpreter to execute the instructions.
	 *
	 * @param ip	InstructionParser which previously parsed a file.
	 * @throws FileNotFoundException	if creating/opening output file failed.
	 * @throws IOException			if writing the log failed (when one has to be written).
	 */
	private void execute(InstructionParser ip) throws FileNotFoundException, IOException {
		Machine machine = new Machine(ip.getMainPosition(), ip.getJumpTable());
		machine.setIo(new Io(argp.getInput(),argp.getOutput()));
		Interpreter interpreter = new Interpreter(ip.get());

		if (argp.isIn(Mode.TRACE)) {
			Logger log = new Logger(argp.getFilename());
			interpreter.setLogger(log);
		}

		if (argp.isIn(Mode.NOMETRICS)) {
			interpreter.disableMetricsReport();
		}

		interpreter.run(machine);
	}
}
