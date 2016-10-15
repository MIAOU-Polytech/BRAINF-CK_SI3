package brainfuck.virtualmachine;

import brainfuck.exceptions.OutOfMemoryException;

/**
 * Memory container which is basically an interface for an array of bytes.
 *
 * @author Pierre-Emmanuel Novac
 * @see OutOfMemoryException
 */
public class Memory {
	/**
	 * Default memory size, 30,000 bytes.
	 */
	private static final int DEFAULT_SIZE = 30000;

	/**
	 * Offset allowing to obtain values from 0 to 255.
	 */
        public static final int OFFSET = 128;

	/**
	 * Memory block allocated for the virtual machine.
	 */
	private byte[] memory;

	/**
	 * Constructs a new Memory with given size.
	 *
	 * @param size	wanted memory size.
	 */
	public Memory(int size) {
		memory = new byte[size];
		for (int i = 0; i < memory.length; i++) {
			memory[i] = Byte.MIN_VALUE;
		}
	}

	/**
	 * Constructs a new Memory with the default size.
	 */
	public Memory() {
		this(DEFAULT_SIZE);
	}

	/**
	 * Throws an OutOfMemoryException exception if the given index is outside the memory block.
	 *
	 * @param i	index to check.
	 * @throws OutOfMemoryException when an invalid index is supplied.
	 */
	public void checkBounds(int i) throws OutOfMemoryException {
		if (i < 0 || i >= memory.length) throw new OutOfMemoryException(i, memory.length);
	}

	/**
	 * Returns the value in memory at the specified index.
	 *
	 * @param i	index in memory.
	 * @return current value at index i.
	 * @throws OutOfMemoryException	if the index is outside the memory block.
	 */
	public byte get(int i) throws OutOfMemoryException {
		checkBounds(i);
		return memory[i];
	}

	/**
	 * Change the value in memory at the specified index.
	 *
	 * @param i	index in memory.
	 * @param value	new value.
	 * @throws OutOfMemoryException if the index is outside the memory block.
	 */
	public void set(int i, byte value) throws OutOfMemoryException {
		checkBounds(i);
		memory[i] = value;
	}

	/**
	 * Returns the memory content in a String.
	 * Each memory cell whose content is greater than 0 is added to the String on a new line.
	 * Format is: "C&lt;cell_number&gt;: &lt;value&gt;".
	 *
	 * @return memory content as a String.
	 */
	@Override
	public String toString() {
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < memory.length; i++) {
			if (memory[i] != Byte.MIN_VALUE) {
				tmp.append("C" + i + ": " + (memory[i] + OFFSET) + "\n");
			}
		}
		return tmp.toString();
	}
}
