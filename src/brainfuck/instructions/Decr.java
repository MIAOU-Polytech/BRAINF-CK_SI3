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
		super("DECR", '-', new int[] {0x4B, 0x00, 0x82});
	}

	/**
	 * Action performed by the instruction: decrement the current memory cell by one.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		byte value = machine.readMemory();
		if (value <= Byte.MIN_VALUE) {
		        System.err.println("Error: below minimum value");
	       		System.exit(1);
		}		
		value--;
		machine.writeMemory(value);
	}
}
