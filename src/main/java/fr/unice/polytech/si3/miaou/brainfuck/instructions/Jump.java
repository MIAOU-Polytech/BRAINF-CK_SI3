package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;

/**
 * Jump instruction: jump to the instruction right after the associated BACK if the pointed
 * memory cell is equal to zero.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 * @see Machine
 * @see fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Memory
 */
public class Jump extends ConditionalJump {
	/**
	 * Constructs the Jump instruction.
	 */
	public Jump() {
		super("JUMP", '[', 0xFFFF7F00);
	}


	/**
	 * Action performed by the instruction: conditional jump.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		super.accept(machine);
		if (!machine.isJumping() && machine.readMemory() == Byte.MIN_VALUE) {
			machine.setJumping(true);
		} else {
			machine.setReversed(false);
			machine.setJumping(false);
		}
	}

}
