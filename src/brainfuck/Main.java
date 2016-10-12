package brainfuck;

import java.io.IOException;
import brainfuck.virtualmachine.Machine;

public class Main {
	public static void main(String[] args) throws SyntaxException, IOException {
		ArgParser argp = new ArgParser(args);
		switch(argp.getMode()) {
			case FILEREAD:
				execute(fileRead(argp.getFilename()));
				break;
			case IMAGEREAD:
				execute(imageRead(argp.getFilename()));
				break;
			case REWRITE:
				Translator tr = new Translator();
				tr.toShortSyntax(fileRead(argp.getFilename()).get());
				break;
			case TRANSLATE:
				Translator tr = new Translator();
				ImageWriter iw = new ImageWriter(tr.toColor(fileRead(argp.getFilename()).get()));
				break;
			case CHECK:
				System.out.println("Miaou");
				break;

		}

	}

	public static void execute(InstructionParser ip) {
		Machine machine = new Machine();
		Interpreter interpreter = new Interpreter(ip.get());
		interpreter.run(machine);
	}
}
