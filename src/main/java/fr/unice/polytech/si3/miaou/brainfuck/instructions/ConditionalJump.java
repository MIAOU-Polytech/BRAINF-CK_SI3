package fr.unice.polytech.si3.miaou.brainfuck.instructions;

/**
 * Describes a conditional jump instruction (Jump or Back).
 */
public abstract class ConditionalJump extends ReadMemory {
	/**
	 * Constructs an conditional instruction with the given name, symbol and color, using Instruction constructor.
	 *
	 * @param name		Instruction's keyword.
	 * @param symbol	Instruction's symbol.
	 * @param color		Instruction's color as an int.
	 */
	public ConditionalJump(String name, char symbol, int color) {
		super(name, symbol, color);
	}

}
