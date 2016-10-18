package brainfuck.exceptions;

/**
 * Exception thrown when trying to increment or decrement beyond memory cell value limit.
 *
 * @author Nassim Bounouas
 */
public class ReadInputException extends BrainfuckException {
	/**
	 * Constructs an ReadInputException.
	 */
	public ReadInputException() {
		super();
	}

	/**
	 * Constructs an ReadInputException by calling BrainfuckException constructor with specified message.
	 *
	 * @param message	exception message.
	 */
	public ReadInputException(String message) {
		super(message);
	}

	/**
	 * Returns the error code.
	 * The application will exit with this error code.
	 *
	 * @return 8.
	 */
	@Override
	public int getErrorCode() {
		return 8;
	}
}
