package brainfuck.exceptions;

/**
 * Exception thrown when trying to parse an invalid instruction.
 *
 * @author Pierre-Emmanuel Novac
 */
public class InvalidInstructionException extends BrainfuckException {
	/**
	 * Constructs an InvalidInstructionException by calling BrainfuckException constructor with specified message.
	 *
	 * @param message	detail message.
	 */
	private InvalidInstructionException(String message) {
		super("Invalid instruction: " + message);
	}

	/**
	 * Constructs an InvalidInstructionException with the given invalid instruction character.
	 *
	 * @param c	instruction's character.
	 */
	public InvalidInstructionException(char c) {
		this(Character.toString(c));
	}

	/**
	 * Constructs an InvalidInstructionException with the given invalid instruction color code..
	 *
	 * @param n	instruction's color code.
	 */
	public InvalidInstructionException(int n) {
		this(Integer.toHexString(n));
	}

	/**
	 * Returns the error code 6 notifying of an invalid instruction in the brainfuck program.
	 * The application will exit with this error code.
	 *
	 * @return 6.
	 */
	@Override
	public int getErrorCode() {
		return 6;
	}
}
