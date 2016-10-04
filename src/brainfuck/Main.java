package brainfuck;

import java.util.Arrays;
import brainfuck.virtualmachine.Machine;

public class Main {
	public static void main(String[] args) throws SyntaxException {
		ArgParser parser = new ArgParser(args); 

		Machine machine = new Machine();

		machine.executeOp("INCR");
		System.out.println(machine.dumpMemory());

		machine.executeOp('-');
		System.out.println(machine.dumpMemory());

		for (int i = 0; i < 255; i++) {
			machine.executeOp("INCR");
		}
		System.out.println(machine.dumpMemory());
	}
}
