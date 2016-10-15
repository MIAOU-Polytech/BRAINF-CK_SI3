package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.OverflowException;

/**
 * Decr instruction: decrements the current memory cell by one.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 * @see Machine
 * @see brainfuck.virtualmachine.Memory
 */
public class Decr extends Instruction {
	/**
	 * Constructs the Decr instruction.
	 */
	public Decr() {
		super("DECR", '-', 0xFF4B0082);
	}

	/**
	 * Action performed by the instruction: decrement the current memory cell by one.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 * @throws OverflowException	if the current cell value is at it's bottom limit.
	 */
	@Override
	public void accept(Machine machine) throws OverflowException {
		byte value = machine.readMemory();
		if (value <= Byte.MIN_VALUE) throw new OverflowException();
		value--;
		machine.writeMemory(value);
	}
}
