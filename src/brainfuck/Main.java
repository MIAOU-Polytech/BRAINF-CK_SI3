package brainfuck;

import java.io.IOException;
import brainfuck.virtualmachine.Machine;

public class Main {
	public static void main(String[] args) throws SyntaxException, IOException {
		ArgParser argp = new ArgParser(args);
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

	public static InstructionParser textFileRead(String filename) throws IOException {
		ReadTextFile file = new ReadTextFile(filename);

		return new InstructionParser(file.getData());
	}

	public static InstructionParser imageRead(String filename) throws IOException {
		ReadImage file = new ReadImage(filename);

		return new InstructionParser(file.getData());
	}

	public static void execute(InstructionParser ip) {
		Machine machine = new Machine();
		Interpreter interpreter = new Interpreter(ip.get());
		interpreter.run(machine);
	}
}
