package brainfuck;

import java.io.IOException;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

public class Main {
	public static void main(String[] args) throws IOException {
		ArgParser argp = new ArgParser(args);

		if (argp.getFilename().isEmpty()) System.exit(0); // Do nothing if the -p parameter is missing

		ReadFile file = new ReadFile(argp.getFilename());

		Interpreter interpreter = new Interpreter(file.getFile());

		Machine machine = new Machine();

		interpreter.run(machine);
	}
}
