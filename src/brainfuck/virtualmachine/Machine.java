package brainfuck.virtualmachine;

import brainfuck.InstructionSet;

/**
 * Actual virtual machine which processes the instructions and interracts with the memory.
 *
 * @author Pierre-Emmanuel Novac
 * @see brainfuck.Instruction
 * @see InstructionSet
 * @see Memory
 */
public class Machine {
	/**
	 * Virtual machine's data memory.
	 */
	private Memory memory;

	/**
	 * Instruction set supported on the virtual machine.
	 */
	private InstructionSet iset;

	/**
	 * Current location in memory.
	 */
	private int location = 0;

	/**
	 * Constructs a new virtual machine, initialize its Memory.
	 */
	public Machine() {
		memory = new Memory();
		iset = new InstructionSet();
	}

	/**
	 * Write a value at the current memory location.
	 *
	 * @param value	value to write.
	 */
	public void writeMemory(byte value) {
		memory.set(location, value);
	}

	/**
	 * Read the value at the current memory location.
	 *
	 * @return value read.
	 */
	public byte readMemory() {
		return memory.get(location);
	}

	/**
	 * Fetch an instruction by its keyword and execute it.
	 *
	 * @param name	Instruction's keyword
	 */
	public void executeOp(String name) {
		iset.getOp(name).accept(this);
	}

	/**
	 * Fetch an instruction by its symbol and execute it.
	 *
	 * @param symbol	Instruction's symbol
	 */
	public void executeOp(char symbol) {
		iset.getOp(symbol).accept(this);
	}

	/**
	 * @return current {@link Machine#location} in memory.
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * Change the location in memory.
	 *
	 * @param i	new {@link Machine#location}.
	 * @throws OutOfMemoryException	if the new location is outside the available Memory.
	 */
	public void setLocation(int i) throws OutOfMemoryException {
		memory.checkBounds(i);
		location = i;
	}

	/**
	 * @return Memory's content.
	 */
	public String dumpMemory() {
		return memory.toString();
	}
}
