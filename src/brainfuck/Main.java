package brainfuck;

import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

public class Main {
	public static void main(String[] args) throws OverflowException {
		ArgParser parser = new ArgParser(args);

		/*Machine machine = new Machine();
		machine.executeOp("INCR");
		System.out.println(machine.dumpMemory());
		machine.executeOp('-');
		System.out.println(machine.dumpMemory());*/
	}
}
