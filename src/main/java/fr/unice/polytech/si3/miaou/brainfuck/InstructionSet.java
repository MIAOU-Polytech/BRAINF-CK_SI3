package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.HashMap;
import java.util.Collection;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.*;

/**
 * Container for all the instructions supported by the Brainfuck language.
 * For efficiency, an internal HashMap is used to map between each representations (keyword, symbol, color) and the corresponding Instruction.
 *
 * @author Pierre-Emmanuel Novac
 * @see Instruction
 */
public class InstructionSet {
	/**
	 * Lists all Instruction objects (instantiated from their respective classes).
	 */
	private static final Instruction[] instructions = {new Incr(), new Decr(), new Right(), new Left(), new In(), new Out(), new Jump(), new Back(), new Return()};

	/**
	 * Maps between the instruction's keyword and the instruction object for easy fetching.
	 */
	private HashMap<String, Instruction> names;

	/**
	 * Maps between the instruction's symbol and the instruction object for easy fetching.
	 */
	private HashMap<Character, Instruction> symbols;

	/**
	 * Maps between the instruction's color and the instruction object for easy fetching.
	 */
	private HashMap<Integer, Instruction> colors;

	/**
	 * Maps between a procedure's name and its affiliated instruction.
	 */
	private HashMap<String, Procedure> procedures;

	/**
	 * Constructs the instruction set and populates the HashMaps.
	 */
	public InstructionSet() {
		names = new HashMap<>();
		symbols = new HashMap<>();
		colors = new HashMap<>();
		procedures = new HashMap<>();
		for (Instruction instruction: instructions) {
			if (!instruction.getName().isEmpty()) {
				names.put(instruction.getName(), instruction);
			}
			if (instruction.getSymbol() != '\0') {
				symbols.put(instruction.getSymbol(), instruction);
			}
			if (instruction.getColor() != 0) {
				colors.put(instruction.getColor(), instruction);
			}
		}
	}

	/**
	 * Adds a Procedure to procedures map, if not added yet.
	 *
	 * @param procedure the procedure to add.
	 */
	public void addProc(Procedure procedure) {
		if (!procedures.containsKey(procedure.getName())) {
			procedures.put(procedure.getName(), procedure);
		}
	}

	/**
	 * Gets the procedure's object by its keyword.
	 *
	 * @param name Procedure's name.
	 * @return	Corresponding Procedure object.
	 */
	public Procedure getProc(String name) {
		return procedures.get(name);
	}

	public Collection<Procedure> getProcedures() {
		return procedures.values();
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

	/**
	 * Gets the instruction's object by its color.
	 *
	 * @param color	Instruction's symbol.
	 * @return 	Corresponding Instruction object.
	 */
	public Instruction getOp(int color) {
		return colors.get(color);
	}
}
