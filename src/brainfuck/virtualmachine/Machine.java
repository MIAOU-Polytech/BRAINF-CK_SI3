package brainfuck.virtualmachine;

import brainfuck.InstructionSet;
import brainfuck.Instruction;

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
	 * Input flux if specified
	 */
	private ByteBuffer inputFlux;

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
	 * @return false if the instruction is invalid true otherwise.
	 */
	public boolean executeOp(String name) {
		Instruction instr = iset.getOp(name);
		if (instr == null) return false;
		instr.accept(this);
		return true;
	}

	/**
	 * Fetch an instruction by its symbol and execute it.
	 *
	 * @param symbol	Instruction's symbol
	 * @return false if the instruction is invalid, true otherwise.
	 */
	public boolean executeOp(char symbol) {
		Instruction instr = iset.getOp(symbol);
		if (instr == null) return false;
		instr.accept(this);
		return true;
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
	 */
	public void setLocation(int i) {
		memory.checkBounds(i);
		location = i;
	}

	public void setInputFlux(ByteBuffer bb){
		this.inputFlux = bb;
	}
	
	/**
	 * @return Memory's content.
	 */
	public String dumpMemory() {
		return memory.toString();
	}
}
