package brainfuck.virtualmachine;

import java.util.stream.Stream;
import java.util.Arrays;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import brainfuck.InstructionSet;
import brainfuck.Instruction;
import brainfuck.WriteTextFile;
import brainfuck.instructions.ConditionalJump;
import brainfuck.BracketCounter;
import brainfuck.exceptions.EndOfInputException;

/**
 * Actual virtual machine which processes the instructions and interracts with the memory.
 *
 * @author Pierre-Emmanuel Novac
 * @author Nassim Bounouas
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
	 * Input flux if specified.
	 */
	private	List<Character> inputs;

	/**
	 * Output flux if specified.
	 */
	private WriteTextFile output;

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
	 * Bracket counter for conditional jumps instruction matching.
	 */
	private BracketCounter bracketCounter;

	/**
	 * Constructs a new virtual machine, initialize its Memory.
	 */
	public Machine() {
		memory = new Memory();
		iset = new InstructionSet();
		bracketCounter = new BracketCounter() { // Anonymous class for defining the onMatch() callback method.
			@Override protected void onMatch() {
				Machine.this.setJumping(false);
				Machine.this.setReversed(false);
				this.reset();
			}
		};
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
		if (!jumping) instr.accept(this); // Jumping may be modified in there
		if (jumping && instr instanceof ConditionalJump) { // Increase the corresponding counter when encountering a conditional jump instruction
			((ConditionalJump) instr).incr(bracketCounter);
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
	 * Store an input flux in the machine.
         *
	 * @param ss	Input flux to load in the machine
	 */
	public void setInputFlux(Stream<String> ss){
		Object[] obArr = ss.toArray();
		String[] arr = Arrays.copyOf(obArr, obArr.length, String[].class);

		inputs = new ArrayList<Character>();

		for(String s : arr)
		{
			for(char c : s.toCharArray())
			{
				inputs.add(new Character(c));
			}
		}
	}

	public void setOutputFlux(WriteTextFile wtf){
		this.output = wtf;
	}

	/**
 	 * Return the next input value read in the file
	 *
	 * @return The next inputted character
	 * @throws EndOfInputException	if the input didn't have enough character to read.
	 */
	public Character getInputFlux(){
		if(this.inputs == null){
			Scanner reader = new Scanner(System.in);
			char c = reader.next().charAt(0);
			return c;
		}else{
			System.out.println(this.inputs.isEmpty());
			for(int i = 0; i < this.inputs.size(); i++)
			{
				System.out.println(this.inputs.get(i));
			}
			if(this.inputs.isEmpty()){
				throw new EndOfInputException();
			}
			return this.inputs.remove(0);
		}
	}

	public void useOutputFlux(String str){
		if(this.output == null){
			System.out.println(str);
		}else{
			this.output.write(str);
		}
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
