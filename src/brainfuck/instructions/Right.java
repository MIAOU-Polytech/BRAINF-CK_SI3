package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;

/**
 * Right instruction: move to the next memory cell.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 * @see Machine
 * @see brainfuck.virtualmachine.Memory
 */
public class Right extends Instruction {
	/**
	 * Constructs the Right instruction.
	 */
	public Right() {
		super("RIGHT", '>', 0xFF0000FF);
	}

	/**
	 * Action performed by the instruction: move to the next memory cell.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		int location = machine.getLocation();
		location++;
		machine.setLocation(location);
	}
}
