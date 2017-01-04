package fr.unice.polytech.si3.miaou.brainfuck.virtualmachine;

import java.util.stream.Stream;
import java.util.Arrays;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.io.WriteTextFile;
import fr.unice.polytech.si3.miaou.brainfuck.io.Io;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.ConditionalJump;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Jump;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Back;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.EndOfInputException;

/**
 * Actual virtual machine which processes the instructions and interracts with the memory.
 *
 * @author Pierre-Emmanuel Novac
 * @author Nassim Bounouas
 * @see fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction
 * @see Memory
 */
public class Machine {
	/**
	 * Virtual machine's data memory.
	 */
	private Memory memory;

	/**
	 * Input and output gateway.
	 */
	private Io ioAccess;

	/**
	 * Current location in memory.
	 */
	private int location = 0;

	/**
	 * True if we are currently jumping back or to another ConditionalJump instruction.
	 */
	private boolean jumping = false;

	/**
	 * True if the Interpreter needs to browse the instructions in reverse order.
	 */
	private boolean reversed = false;

	/**
	 * Constructs a new virtual machine, initialize its Memory.
	 */
	public Machine() {
		memory = new Memory();
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
		if (instr instanceof Jump && readMemory() == Byte.MIN_VALUE) {
			this.jumping = true;
		} else if (instr instanceof Back && readMemory() != Byte.MIN_VALUE) {
			this.jumping = true;
		} else {
			this.jumping = false ;
			instr.accept(this);
		}

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
	 * Enables or disables the jumping mode.
	 *
	 * @param jumping	sets or unsets the jumping mode.
	 */
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	/**
	 * Tells wether we are currently jumping or not.
	 *
	 * @return true if we're jumping.
	 */
	public boolean isJumping() {
		return jumping;
	}

	/**
	 * Enables or disables reverse browsing of instruction.
	 *
	 * @param r	true to make the Interpreter browse instructions in reverse order.
	 */
	public void setReversed(boolean r) {
		this.reversed = r;
	}

	/**
	 * Tells wether the parser is in reverse order mode or not.
	 *
	 * @return true if we're browsing instructions in reverse order.
	 */
	public boolean isReversed() {
		return reversed;
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
