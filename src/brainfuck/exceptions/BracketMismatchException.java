package brainfuck.exceptions;

/**
 * Exception thrown when the Checker finds a mismatch between the Jump and Back instruction counters.
 *
 * @author Pierre-Emmanuel Novac
 */
public class BracketMismatchException extends BrainfuckException {
	/**
	 * Constructs a BracketMismatchException.
	 */
	public BracketMismatchException() {
		super("Left and right bracket count not matching");
	}

	/**
	 * Returns the error code 4 notifying of a mismatch between Jump and Back in the brainfuck program.
	 * The application will exit with this error code.
	 *
	 * @return 4.
	 */
	@Override
	public int getErrorCode() {
		return 4;
	}
}
