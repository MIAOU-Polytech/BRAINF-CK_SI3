package brainfuck.exceptions;

/**
 * Exception thrown when trying to go beyond memory bounds.
 *
 * @author Pierre-Emmanuel Novac
 */
public class OutOfMemoryException extends IndexOutOfBoundsException {
	/**
	 * Constructs an OutOfMemoryException.
	 *
	 * @param i	accessed memory cell number.
	 * @param size	total memory size.
	 */
	public OutOfMemoryException(int i, int size) {
		super();
	}
}
