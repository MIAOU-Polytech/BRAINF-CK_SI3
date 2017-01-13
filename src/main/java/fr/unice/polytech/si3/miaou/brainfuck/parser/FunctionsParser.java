package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.Procedure;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.SyntaxFunctionException;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.ProcedureCall;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Return;

import java.util.function.Function;
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
	 * Instructions counter.
	 */
	private int counter;

	/**
	 * Location of the previous procedure.
	 */
	private int prevCounter;

	private class InstructionCounter extends InstructionParser {
		private int counter;

		@Override
		public void addInstruction(Instruction instr) {
			counter++;
		}

		public int getCounter() {
			return counter;
		}
	}

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
				InstructionCounter instrCounter = new InstructionCounter();
				InstructionTextParser count = new InstructionTextParser(instrCounter, iset);
				count.accept(line);
				counter += instrCounter.getCounter();
			}
			return Stream.of(line);
		}
	}

	/**
	 * Gets the current value of the counter.
	 *
	 * @return current value of counter.
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Parsing a procedure call and returns the corresponding ProcedureCall object.
	 *
	 * @param split a split line containing the procedure call.
	 * @return ProcedureCall object corresponding to the current procedure call.
	 */
	public ProcedureCall parseCall(String[] split) {
		if (split.length < 1)
			return null;
		else {
			Procedure proc = iset.getProc(split[0]);
			if (proc == null)
				return null;

			if (split.length > 1)
				return new ProcedureCall(proc, Integer.parseInt(split[1]));
			else
				return new ProcedureCall(proc);
		}
	}

	/**
	 * Parsing a return instruction and returns a new Return object with parsed parameter.
	 *
	 * @param split	a split line containing the return instruction.
	 * @return Return object with parser parameter.
	 */
	public Return parseReturn(String[] split) {
		if (split.length == 2) { // length == 1 means instruction should have been recognized earlier
			Instruction instr = iset.getOp(split[0]);
			if (instr instanceof Return)
				return new Return(Integer.parseInt(split[1]));
		}
		return null;
	}
}
