package brainfuck;

/**
 * Parser for the arguments passed through the call of the bfck executable.
 * It also stores the different parsed arguments into fields relative to their function.
 * e.g : if a filename is passed through bfck command, it is stored as a filename in this class.
 * @author Julien Lemaire
 */
public class ArgParser {
	//fields
	private String filename = "";
	private String in;
	private String out;
	private Mode mode;

	//methods
	/**
	 * Main constructor of the <code>ArgParser</code> class.
	 * It takes an array of String, respresenting the arguments, and parse it to store them.
	 * @param args Array of String containing all the arguments passed through the executable.
	 */
	public ArgParser(String[] args) throws SyntaxException {
		mode = Mode.FILEREAD; //reading a file by default
		in = "stdin";
		out = "stdout";
		//parsing files
		for (int i = 0 ; i < args.length ; i++) {
			if (args[i].equals("-p")) {
				if (i+1 < args.length && !(args[i+1].startsWith("-"))) {
					this.filename = args[i+1];
					i++;
				} else {
					throw new SyntaxException("No file for -p option.");
				}
			} else if (args[i].equals("-i")) {
				if (i+1 < args.length && !(args[i+1].startsWith("-"))) {
					this.in = args[i+1];
					i++;
				} else {
					throw new SyntaxException("No file for -i option.");
				}
			} else if (args[i].equals("-o")) {
				if (i+1 < args.length && !(args[i+1].startsWith("-"))) {
					this.out = args[i+1];
					i++;
				} else {
					throw new SyntaxException("No file for -o option.");
				}
			} else if (args[i].equals("-o")) {
				
			} else if (args[i].equals("--rewrite")) {
				this.mode = Mode.REWRITE;
			} else if (args[i].equals("--translate")) {
				this.mode = Mode.TRANSLATE;
			} else if (args[i].equals("--check")) {
				this.mode = Mode.CHECK;
			} else {
				throw new SyntaxException(args[i]+" is not a recognized option or argument.");
			}
		}
	}

	/**
	 * Getter for the filename, if any.
	 * @return The filename if one was passed, else an empty string.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Getter for the name of the input file (or stdin if no -i has been specified). 
	 * @return The name of the input file if one was passed, else "stdin".
	 */
	public String getInput() {
		return in;
	}
	
	/**
	 * Getter for the name of the output file (or stdout if no -o has been specified). 
	 * @return The name of the output file if one was passed, else "stdout".
	 */
	public String getOutput() {
		return out;
	}
	
	/**
	 * Getter for the mode of execution. 
	 * @return The program's mode of execution (by default "READFILE").
	 */
	public Mode getMode() {
		return mode;
	}
}

enum Mode {
	FILEREAD, IMAGEREAD, REWRITE, TRANSLATE, CHECK
};

/**
 * An exception thrown when arguments passed through the bfck executable are not correct.
 * @author Julien Lemaire
 */ 
class SyntaxException extends RuntimeException {
	/**
	 * The main constructor of the <code>SyntaxException</code> class.
	 * @param message The message to display as an error.
	 */
	public SyntaxException(String message) {
		super(message);
	}
}
