package brainfuck;

import java.util.List;
import brainfuck.instructions.ConditionalJump;
import brainfuck.exceptions.BracketMismatchException;

/**
 * Checks whether the program is well formed or not.
 * A well formed program has got a matching Back instruction for every Jump instruction.
 *
 * @author Pierre-Emmanuel Novac
 */
public class Checker {
	/**
	 * List of instructions which will be checked.
	 */
	private List<Instruction> instructions;

	/**
	 * Bracket counter for counting Jump and Back instructions (respectively called right bracket and left bracket).
	 */
	private BracketCounter bracketCounter;

	/**
	 * Constructs a new Checker using the given list of instructions.
	 * Gives an onMismatch() callback to the bracket counter which exits with error code 4.
	 *
	 * @param instructions	List of Instruction on which the check should be performed.
	 */
	public Checker(List<Instruction> instructions) {
		this.instructions = instructions;
		bracketCounter = new BracketCounter() {
			@Override protected void onMismatch() {
				throw new BracketMismatchException();
			}
		};
	}

	/**
	 * Performs the check.
	 */
	public void check() {
		instructions.forEach(i -> {
			if (i instanceof ConditionalJump) { // Checks if the Instruction is either a Jump or a Back, both of which implements ConditionalJump
				((ConditionalJump) i).incr(bracketCounter); // Increment either the right of left bracket counter
			}
		});
		bracketCounter.checkMatching(); // Finally check if all the Jump instructions has got a Back instruction.
	}
}
