package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;

/**
 * Left instruction: move to the previous memory cell.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 * @see Machine
 * @see brainfuck.virtualmachine.Memory
 */
public class Left extends Instruction {
	/**
	 * Constructs the Left instruction.
	 */
	public Left() {
		super("LEFT", '<', 0xFF9400D3);
	}


	/**
	 * Action performed by the instruction: move to the previous memory cell.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		int location = machine.getLocation();
		location--;
		machine.setLocation(location);
	}
}
