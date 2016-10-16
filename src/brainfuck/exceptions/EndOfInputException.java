package brainfuck.exceptions;

/**
 * Exception thrown when trying to read more character from the input than available.
 *
 * @author Pierre-Emmanuel Novac
 */
public class EndOfInputException extends BrainfuckException {
	/**
	 * Constructs an EndOfInputException by calling BrainfuckException constructor with specified message.
	 */
	public EndOfInputException() {
		super("End of input");
	}

	/**
	 * Returns the error code 7 notifying of a try to read more character from the input than available.
	 * The application will exit with this error code.
	 *
	 * @return 7.
	 */
	@Override
	public int getErrorCode() {
		return 7;
	}
}
