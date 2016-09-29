package brainfuck;

public class ArgParser {
	//fields
	String filename = "";

	//methods
	public ArgParser(String[] args) throws SyntaxException {
		for (int i = 0 ; i < args.length ; i++) {
			if (args[i].equals("-p")) {
				if (i+1 < args.length && !(args[i+1].startsWith("-"))) {  
					this.filename = args[i+1];
					i++;
				} else {
					throw new SyntaxException("No file for -p option");
				}
			}
		}		
	}

	public String getFilename() {
		return filename;
	}
}

class SyntaxException extends RuntimeException {
	public SyntaxException(String message) {
		super(message);
	}
}
