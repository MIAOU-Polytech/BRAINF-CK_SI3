package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InvalidInstructionException;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.SyntaxFunctionException;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Procedure;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parsing Procedure declarations .
 * Implements the function interface Function in order to be used directly in the Stream of line's map().
 *
 * @author Julien Lemaire
 */
class FunctionsParser implements Function<String, Stream<String>> {
	/**
	 * The InstructionSet we're adding procedures to.
	 */
	private InstructionSet iset;

	/**
	 * True if currently defining a function.
	 */
	private boolean defining;

	/**
	 * Name of the currently parsed function.
	 */
	private String name;

	/**
	 * Parameter of a procedure.
	 */
	private Optional<Integer> parameter;

	/**
	 * Instructions counter.
	 */
	private int counter;

	/**
	 * Location of the previous procedure.
	 */
	private int prevCounter;

	/**
	 * Main constructor of FunctionsParser.
	 *
	 * @param iset the InstructionSet the parsed Procedures has to be added to.
	 */
	public FunctionsParser(InstructionSet iset) {
		this.iset = iset;
		defining = false;
		name = "";
		counter = 0;
	}

	/**
	 * Parses the given line and parses every function declaration.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html">Function</a>'s apply() method.
	 *
	 * @param line the line to parse coming from the calling Stream function.
	 * @return function parsing result.
	 */
	@Override
	public Stream<String> apply(String line) {
		if (line.startsWith("FUNC")) {
			String[] split = line.split(" ");
			if (defining || split.length < 2) {
				throw new SyntaxFunctionException("Trying to declare a new function before the end of another one.");
			} else {
				name = line.split(" ")[1];
				if (split.length > 2) {

				}
				defining = true;
				return Stream.empty();
			}
		} else if (line.startsWith("RET")) {
			if (!defining) {
				throw new SyntaxFunctionException("Trying to return without declaring a function.");
			} else {
				defining = false;
				iset.addProc(new Procedure(name, prevCounter));
				counter++;
				prevCounter = counter;
				return Stream.of(line);
			}
		} else {
			if (defining) {
				InstructionTextCounter count = new InstructionTextCounter(iset);
				count.accept(line);
				counter += count.getCounter();
			}
			return Stream.of(line);
		}
	}

	/**
	 * Gets the current value of the counter.
	 */
	public int getCounter() {
		return counter;
	}
}
