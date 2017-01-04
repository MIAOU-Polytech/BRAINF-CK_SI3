package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

/**
 * Return instruction
 *
 * @author Julien Lemaire
 */
public class Return extends Instruction {
	public Return() {
		super("RET", 'R', 0);
	}

	@Override
	public void accept(Machine machine) {
		machine.goToLastReturnAddress();
	}
}
