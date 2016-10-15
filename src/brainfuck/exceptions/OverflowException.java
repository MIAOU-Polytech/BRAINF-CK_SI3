package brainfuck.exceptions;

/**
 * Exception thrown when trying to increment or decrement beyond memory cell value limit.
 *
 * @author Pierre-Emmanuel Novac
 */
public class OverflowException extends BrainfuckException {
	/**
	 * Constructs an OverflowException.
	 */
	public OverflowException() {
		super();
	}

	/**
	 * Constructs an OverflowException by calling BrainfuckException constructor with specified message.
	 *
	 * @param message	exception message.
	 */
	public OverflowException(String message) {
		super(message);
	}

	/**
	 * Returns the error code 1 for notifying an underflow or overflow condition.
	 * The application will exit with this error code.
	 *
	 * @return 1.
	 */
	@Override
	public int getErrorCode() {
		return 1;
	}
}
