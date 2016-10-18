package brainfuck.instructions;

import brainfuck.Instruction;
import brainfuck.virtualmachine.Machine;
import brainfuck.virtualmachine.Memory;
import brainfuck.exceptions.OverflowException;

/**
 * Read the value present in the input as an ASCII character.
 *
 * @author Nassim Bounouas
 * @see Instruction
 * @see Machine
 * @see brainfuck.virtualmachine.Memory
 */
public class In extends Instruction {
	/**
	 * Constructs the In instruction.
	 */
	public In() {
		super("IN", ',', 0xFF00FF00);
	}

	/**
	 * Action performed by the instruction: print out the contents of the current memory cell as ASCII.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 * @throws OverflowException	if the value of the character read is outside of the allowed range [0-255].
	 */
	@Override
	public void accept(Machine machine) {
		int c = machine.read();
		if(c < 0 || c > 255) {
			throw new OverflowException();
		}
		byte cB = (byte) c;
		cB -= Memory.OFFSET;
		machine.writeMemory(cB);
	}
}
