package fr.unice.polytech.si3.miaou.brainfuck.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.IntStream;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.Procedure;
import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InvalidInstructionException;

/**
 * Parses the instruction from either a stream of String for the two text representations or from a stream of int (color code) for images
 *
 * @author Pierre-Emmanuel Novac
 */
public class InstructionParser {
	/**
	 * Generated list of instructions after parsing.
	 */
	private List<Instruction> instructions;

	/**
	 * Instruction set for recognizing the supported instructions.
	 */
	private InstructionSet iset;

	/**
	 * Jumptable used to bind Jump and Back instructions
	 */
	private JumpTable jumptable;

	/**
	 * Position of main program in instruction memory.
	 */
	private int mainPosition;

	/**
	 * Constructs an InsturctionParser with an empty list of instructions and creates the InstructionSet object.
	 */
	InstructionParser() {
		instructions = new ArrayList<>();
		iset = new InstructionSet();
		jumptable = new JumpTable();
	}

	/**
	 * Constructs an InstructionParser with the given stream of lines and parses it to generate the list of instructions.
	 * Lines can contain either multiple instruction's symbol for short syntax or a single instruction's keyword for long syntax.
	 *
	 * @param stream	stream of lines containing instructions symbols and keywords to parse.
	 * @throws InvalidInstructionException  when an invalid instruction is encountered.
	 */
	public InstructionParser(Stream<String> stream) {
		this();

		FunctionsParser functionsParser = new FunctionsParser(iset);

		stream.filter(l -> !l.startsWith("#")) // Remove lines starting with a comment
		.map(new CommentsAndIndentationParser())
		.flatMap(new MacroParser()) // Expand macros
		.flatMap(functionsParser)
		.forEachOrdered(new InstructionTextParser(this, iset));

		mainPosition = functionsParser.getCounter();
	}

	/**
	 * Constructs an InstructionParser with the given int stream of color codes and parses it to generate the list of instructions.
	 *
	 * @param stream	stream of color codes as integers in the ARVB 32bpp format.
	 * @throws InvalidInstructionException	when an invalid instruction is encountered.
	 */
	public InstructionParser(IntStream stream) {
		this();

		stream.forEachOrdered(new InstructionImageParser(this, iset));
	}

	/**
	 * Add an instruction to the list of instructions and bind it in the jumptable if it's an JUMP or BACK instruction.
	 *
	 * @param instr Instruction to add and bind
	 */
	public void addInstruction(Instruction instr) {
		instructions.add(instr);
		jumptable.bind(instr, this.instructions.size() - 1);
	}

	/**
	 * Returns the list of instructions generated from the data given when constructing this object.
	 * Should be refactored: breaks encapsulation.
	 *
	 * @return the list of parsed instructions.
	 */
	public List<Instruction> get() {
		return instructions;
	}

	/**
	 * Return the jumptable associated to the instructions list
	 *
	 * @return the jumptable
	 */
	public JumpTable getJumpTable() {
		return this.jumptable;
	}

	/**
	 * Returns the position of the main program into the instruction memory.
	 *
	 * @return the position of the main program into the instruction memory.
	 */
	public int getMainPosition() {
		return mainPosition;
	}

	public Collection<Procedure> getProcedures() {
		return iset.getProcedures();
	}
}
