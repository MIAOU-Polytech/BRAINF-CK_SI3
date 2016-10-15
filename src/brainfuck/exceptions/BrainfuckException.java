package brainfuck.exceptions;

/**
 * Superclass for all our application's exceptions.
 * Extends RuntimeException so that all our exceptions are unchecked.
 *
 * @author Pierre-Emmanuel Novac
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/RuntimeException.html">RuntimeException</a>
 */
public abstract class BrainfuckException extends RuntimeException {
	/**
	 * Constructs a BrainfuckException by calling RuntimeException constructor.
	 */
	public BrainfuckException() {
		super();
	}

	/**
	 * Constructs a BrainfuckException by calling RuntimeException constructor with specified message.
	 *
	 * @param message	exception message.
	 */
	public BrainfuckException(String message) {
		super(message);
	}

	/**
	 * Returns the error code corresponding to the error which happened.
	 * The application will exit with this error code.
	 *
	 * @return error code to exit with.
	 */
	abstract public int getErrorCode();
}
