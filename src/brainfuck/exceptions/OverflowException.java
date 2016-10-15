package brainfuck.virtualmachine;

/**
 * Exception thrown when trying to increment or decrement beyond memory cell value limit.
 *
 * @author Pierre-Emmanuel Novac
 */
public class OverflowException extends RuntimeException {
	/**
	 * Constructs an OverflowException.
	 */
	public OverflowException() {
		super();
	}
}
