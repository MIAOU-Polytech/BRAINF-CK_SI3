package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

import java.util.Optional;

/**
 * Return instruction
 *
 * @author Julien Lemaire
 */
public class Return extends Instruction {
	private Optional<Integer> returnValue;

	/**
	 * Constructs the Return instruction.
	 */
	public Return() {
		super("RET", 'R', 0);
		returnValue = Optional.empty();
	}

	public Return(int val) {
		this();
		returnValue = Optional.of(val);
	}

	/**
	 * Action performed by the instruction: returns to the location after the previous procedure call.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		Optional<Byte> toReturn = Optional.empty();
		if (returnValue.isPresent()) {
			machine.setLocation(returnValue.get());
			toReturn = Optional.of(machine.readMemory());
		}
		machine.goBackMemory();
		toReturn.ifPresent(machine::writeMemory);
		machine.goToLastReturnAddress();
	}
}
