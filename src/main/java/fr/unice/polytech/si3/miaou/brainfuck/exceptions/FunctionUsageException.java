package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

/**
 * An exception thrown when Function call is not used correctly.
 *
 * @author Julien Lemaire
 */
public class FunctionUsageException extends BrainfuckException {
	/**
	 * The main constructor of the <code>FunctionUsageException</code> class.
	 * @param message The message to display as an error.
	 */
	public FunctionUsageException(String message) {
		super(message);
	}

	/**
	 * Returns the error code 12 for notifying an error in syntax when defining a Function.
	 * The application will exit with this error code.
	 *
	 * @return 12.
	 */
	@Override
	public int getErrorCode() {
		return 12;
	}
}
