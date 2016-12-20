package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.BracketCounter;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

/**
 * Back instruction: go back to the instruction right after the associated JUMP if the pointed
 * memory cell is not equals to zero.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 * @see Machine
 * @see fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Memory
 */
public class Back extends ConditionalJump {
	/**
	 * Constructs the Back instruction.
	 */
	public Back() {
		super("BACK", ']', 0xFFFF0000);
	}


	/**
	 * Action performed by the instruction: conditional loop.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		super.accept(machine);
		if (!machine.isJumping() && machine.readMemory() != Byte.MIN_VALUE) {
			machine.setJumping(true);
			machine.setReversed(true);
		} else {
			machine.setJumping(false);
		}
	}

	/**
	 * Increments the right bracket counter.
	 *
	 * @param bc	BracketCounter whose right bracket count is to be incremented.
	 */
	@Override
	public void incr(BracketCounter bc) {
		bc.incrRight();
	}
}
