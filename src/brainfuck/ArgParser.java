package brainfuck;

import brainfuck.exceptions.SyntaxException;

/**
 * Parser for the arguments passed through the call of the bfck executable.
 * It also stores the different parsed arguments into fields relative to their function.
 * e.g : if a filename is passed through bfck command, it is stored as a filename in this class.
 *
 * @author Julien Lemaire
 */
public class ArgParser {
	private String filename = "";
	private String in;
	private String out;
	private Mode mode;

	/**
	 * Main constructor of the <code>ArgParser</code> class.
	 * It takes an array of String, respresenting the arguments, and parse it to store them.
 	 *
	 * @param args Array of String containing all the arguments passed through the executable.
	 */
	public ArgParser(String[] args) throws SyntaxException {
		mode = Mode.FILEREAD; //reading a file by default
		//parsing files
		for (int i = 0 ; i < args.length ; i++) {
			switch (args[i]) {
				case "-p":
					if (i+1 < args.length && !(args[i+1].startsWith("-"))) {
						this.filename = args[i+1];
						if (this.filename.endsWith(".bmp")) {
							mode = Mode.IMAGEREAD;
						}
						i++;
					} else {
						throw new SyntaxException("No file for -p option.");
					}
					break;
				case "-i":
					if (i+1 < args.length && !(args[i+1].startsWith("-"))) {
						this.in = args[i+1];
						i++;
					} else {
						throw new SyntaxException("No file for -i option.");
					}
					break;
				case "-o":
					if (i+1 < args.length && !(args[i+1].startsWith("-"))) {
						this.out = args[i+1];
						i++;
					} else {
						throw new SyntaxException("No file for -o option.");
					}
					break;
				case "--rewrite":
					this.mode = Mode.REWRITE;
					break;
				case "--translate":
					this.mode = Mode.TRANSLATE;
					break;
				case "--check":
					this.mode = Mode.CHECK;
					break;
				default:
					throw new SyntaxException(args[i]+" is not a recognized option or argument.");
			}
		}
	}

	/**
	 * Getter for the filename, if any.
	 *
	 * @return The filename if one was passed, else an empty string.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Getter for the name of the input file.
	 *
	 * @return The name of the input file if one was passed, else null.
	 */
	public String getInput() {
		return in;
	}

	/**
	 * Getter for the name of the output file.
	 *
	 * @return The name of the output file if one was passed, else null.
	 */
	public String getOutput() {
		return out;
	}

	/**
	 * Getter for the mode of execution.
	 *
	 * @return The program's mode of execution (by default "READFILE").
	 */
	public Mode getMode() {
		return mode;
	}
}

/**
 * The mode of execution of the current instance of the program.
 *
 * @author Julien Lemaire
 */
enum Mode {
	FILEREAD, IMAGEREAD, REWRITE, TRANSLATE, CHECK
};
