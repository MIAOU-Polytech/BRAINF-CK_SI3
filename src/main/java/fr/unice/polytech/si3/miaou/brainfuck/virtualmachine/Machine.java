package fr.unice.polytech.si3.miaou.brainfuck.virtualmachine;

import java.util.*;
import java.util.stream.Stream;
import java.lang.Character;

import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.*;
import fr.unice.polytech.si3.miaou.brainfuck.io.WriteTextFile;
import fr.unice.polytech.si3.miaou.brainfuck.io.Io;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.EndOfInputException;

/**
 * Actual virtual machine which processes the instructions and interracts with the memory.
 *
 * @author Pierre-Emmanuel Novac
 * @author Nassim Bounouas
 * @author Julien Lemaire
 * @see fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction
 * @see Memory
 */
public class Machine {
	/**
	 * Virtual machine's data memory.
	 */
	private Memory memory;

	/**
	 * Jumptable used to associate ConditionalJump instructions
	 */
	private JumpTable jumptable;

	/**
	 * Input and output gateway.
	 */
	private Io ioAccess;

	/**
	 * Stack of return addresses.
	 */
	private Stack<Integer> addressesStack;

	/**
	 * Current location in memory.
	 */
	private int location;

	/**
	 * Current location in Instructions' memory.
	 */
	private int instrPointer;

	/**
	 * Constructs a new virtual machine, initialize its Memory.
	 *
	 * @param entryPoint	The initial position of instruction pointer.
	 * @param jumptable     The jumptable containing conditional jumps positions.
	 */
	public Machine(int entryPoint, JumpTable jumptable) {
		this.memory = new Memory();
		this.jumptable = jumptable;
		this.addressesStack = new Stack<>();
		this.location = 0;
		this.instrPointer = entryPoint;
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
	 * Execute an instruction.
	 * Instruction is not executed if we are currently jumping.
	 *
	 * @param instr	Instruction to execute.
	 */
	public void executeOp(Instruction instr) {
		instr.accept(this);
		instrPointer++;
	}

	/**
	 * Return the index of the conditional Jump associated to current {@link Machine#instrPointer}.
	 */
	public void jump() {
		this.setInstrPointer(jumptable.getJump(instrPointer));
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

	/**
	 * Change the instruction pointer location.
	 *
	 * @param i new {@link Machine#instrPointer}
	 */
	public void setInstrPointer(int i) {
		instrPointer = i;
	}

	/**
	 * Returns the current instruction pointer location.
	 *
	 * @return {@link Machine#instrPointer}
	 */
	public int getInstrPointer() {
		return instrPointer;
	}

	/**
	 * Saves next instrPointer as a return address.
	 * Called by Procedure to store a return address for the next Return instruction.
	 */
	public void saveReturnAddress() {
		addressesStack.push(instrPointer);
	}

	/**
	 * Change the instruction pointer location for the first element of the pointer stack, which is popped.
	 */
	public void goToLastReturnAddress() {
		instrPointer = addressesStack.pop();
	}

	/**
 	 * Return the next input value read in the file
	 *
	 * @return The next inputted character
	 * @throws EndOfInputException	if the input didn't have enough character to read.
	 */
	public int getInput(){
		int c = ioAccess.read();
		return c;
	}

	public void output(int c){
		this.ioAccess.write(c);
	}

	/**
	 * Set the IO gateway
	 *
	 * @param ac	Io gateway to use for this Machine.
	 */
	public void setIo(Io ac) {
		this.ioAccess = ac;
	}

	/**
	 * Dumps the memory content to a String.
	 *
	 * @return Memory's content as a String.
	 */
	public String dumpMemory() {
		return memory.toString();
	}
}