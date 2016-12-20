package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

/**
 * Left instruction: move to the previous memory cell.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 * @see Machine
 * @see fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Memory
 */
public class Left extends MoveCursor {
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
		super.accept(machine);
		int location = machine.getLocation();
		location--;
		machine.setLocation(location);
	}
}
