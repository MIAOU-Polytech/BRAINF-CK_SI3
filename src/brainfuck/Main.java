package brainfuck;

import java.io.IOException;
import brainfuck.virtualmachine.Machine;
import brainfuck.exceptions.BrainfuckException;

/**
 * Entry point for the application.
 *
 * @author Julien Lemaire
 */
public class Main {
	/**
	 * Application entry point.
	 *
	 * @param args array of received command-line arguments.
	 * @throws IOException		in case of IO error on file operation.
	 */
	public static void main(String[] args) throws IOException {
		ArgParser argp = new ArgParser(args);
		Main app = new Main();
		try {
			app.run(argp);
		} catch (BrainfuckException e) {
			System.err.println(e);
			System.exit(e.getErrorCode());
		}
	}

	/**
	 * Runs the requested behaviour depending on the command line arguments.
	 *
	 * @param argp	ArgParser's parsed command line arguments.
	 * @throws IOException	in case of IO error on file operation.
	 */
	private void run(ArgParser argp) throws IOException {
		switch(argp.getMode()) {
			case FILEREAD:
				execute(textFileRead(argp.getFilename()));
				break;
			case IMAGEREAD:
				execute(imageRead(argp.getFilename()));
				break;
			case REWRITE:
				Translator tr = new Translator();
				tr.toShortSyntax(textFileRead(argp.getFilename()).get());
				break;
			case TRANSLATE:
				Translator tra = new Translator();
				ImageWriter iw = new ImageWriter(tra.toColor(textFileRead(argp.getFilename()).get()));
				break;
			case CHECK:
				Checker checker = new Checker(textFileRead(argp.getFilename()).get());
				checker.check();
				break;
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
		ReadImage file = new ReadImage(filename);

		return new InstructionParser(file.getData());
	}

	/**
	 * Starts the Interpreter to execute the instructions.
	 *
	 * @param ip	InstructionParser which previously parsed a file.
	 */
	private void execute(InstructionParser ip) {
		Machine machine = new Machine();
		Interpreter interpreter = new Interpreter(ip.get());
		interpreter.run(machine);
	}
}
