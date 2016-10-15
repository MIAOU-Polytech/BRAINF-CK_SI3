package brainfuck.exceptions;

/**
 * Exception thrown when trying to go beyond memory bounds.
 *
 * @author Pierre-Emmanuel Novac
 */
public class OutOfMemoryException extends BrainfuckException {
	public OutOfMemoryException() {
		super();
	}

	/**
	 * Constructs an OutOfMemoryException.
	 *
	 * @param i	accessed memory cell number.
	 * @param size	total memory size.
	 */
	public OutOfMemoryException(int i, int size) {
		super("Cell number: " + i + ", Total virtual memory size: " + size);
	}

	/**
	 * Returns the error code 2 for notifying an access beyond memory bounds.
	 * The application will exit with this error code.
	 *
	 * @return 2.
	 */
	@Override
	public int getErrorCode() {
		return 2;
	}
}
