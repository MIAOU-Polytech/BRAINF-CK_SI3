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

	public Return() {
		super("RET", 'R', 0);
		returnValue = Optional.empty();
	}

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

	public void setReturnValue(int returnValue) {
		this.returnValue = Optional.of(returnValue);
	}
}
