package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InputOutputException;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.EndOfInputException;
import static fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Memory.OFFSET;

/**
 * Reads and store a byte in the current memory cell.
 *
 * @author Nassim Bounouas
 * @see Instruction
 * @see Machine
 * @see fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Memory
 */
public class In extends WriteMemory {
	/**
	 * Constructs the In instruction.
	 */
	public In() {
		super("IN", ',', 0xFF00FF00);
	}

	/**
	 * Action performed by the instruction: reads a byte from the input and store it in the current memory cell.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 * @throws InputOutputException	if reading the value failed.
	 * @throws EndOfInputException	if there is nothing more to read.
	 */
	@Override
	public void accept(Machine machine) {
		super.accept(machine);
		int c = machine.getInput();
		if (c == -1) {
			throw new EndOfInputException();
		}
		c -= OFFSET;
		machine.writeMemory((byte) c);
	}
}
