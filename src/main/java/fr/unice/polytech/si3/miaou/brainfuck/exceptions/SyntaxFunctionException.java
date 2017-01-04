package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

/**
 * An exception thrown when Function definition structure is not used correctly.
 *
 * @author Julien Lemaire
 */
public class SyntaxFunctionException extends BrainfuckException {
	/**
	 * The main constructor of the <code>SyntaxFunctionException</code> class.
	 * @param message The message to display as an error.
	 */
	public SyntaxFunctionException(String message) {
		super(message);
	}

	/**
	 * Returns the error code 11 for notifying an error in syntax when defining a Function.
	 * The application will exit with this error code.
	 *
	 * @return 11.
	 */
	@Override
	public int getErrorCode() {
		return 11;
	}
}
