package brainfuck;

import java.util.HashMap;
import brainfuck.instructions.*;

/**
 * Container for all the instructions supported by the Brainfuck language.
 * For efficiency, an internal HashMap is used to map between each representations (keyword, symbol, color) and the corresponding Instruction.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 */
public class InstructionSet {
	public static final int MAX_OP_LENGTH = 5;

	/**
	 * Lists all Instruction objects (instantiated from their respective classes).
	 */
	private final static Instruction[] instructions = {new Incr(), new Decr(), new Right(), new Left()};

	/**
	 * Maps between the instruction's keyword and the instruction object for easy fetching.
	 */
	private HashMap<String, Instruction> names;

	/**
	 * Maps between the instruction's symbol and the instruction object for easy fetching.
	 */
	private HashMap<Character, Instruction> symbols;

	/**
	 * Constructs the instruction set and populates the HashMaps.
	 */
	public InstructionSet() {
		names = new HashMap<>();
		symbols = new HashMap<>();
		for (Instruction instruction: instructions) {
			String name = instruction.getName();
			if (name.length() > MAX_OP_LENGTH) {
				System.err.println("Warning: instruction " + name + " longer than the maximum allowed length");
				System.exit(37);
			}
			names.put(name, instruction);
			symbols.put(instruction.getSymbol(), instruction);
		}
	}

	/**
	 * Gets the instruction's object by its keyword.
	 *
	 * @param name	Instruction's name.
	 * @return	Corresponding Instruction object.
	 */
	public Instruction getOp(String name) {
		return names.get(name);
	}

	/**
	 * Gets the instruction's object by its symbol.
	 *
	 * @param symbol	Instruction's symbol.
	 * @return 	Corresponding Instruction object.
	 */
	public Instruction getOp(char symbol) {
		return symbols.get(symbol);
	}
}
