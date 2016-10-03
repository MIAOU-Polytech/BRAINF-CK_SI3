package brainfuck;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import brainfuck.virtualmachine.Machine;
import static brainfuck.InstructionSet.MAX_OP_LENGTH;

/**
 * Reads the instructions from a ByteBuffer and execute them.
 * Works only with ASCII encoded files.
 *
 * @author MIAOU
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html">Stream</a>
 * @see Machine
 */
public class Interpreter {

	/**
	 * ByteBuffer containing the instructions to parse.
	 */
	private ByteBuffer bb;

	/**
	 * Constructs an interpreter using the given ByteBuffer.
	 *
	 * @param bb	ByteBuffer of lines containing instructions.
	 */
	public Interpreter(ByteBuffer bb) {
		this.bb = bb;
	}

	/**
	 * Exits with an error code of 38.
	 * Used when an invalid instruction is encountered.
	 */
	private void exitInvalidInstruction() {
		System.err.println("Invalid Instruction");
		System.exit(38);
	}

	/**
	 * Executes the instructions from the ByteBuffer and print memory content if the program terminated successfully.
	 * Exits with error code 38 upon invalid instruction.
	 * Tries to read the line first as short instructions, if an invalid instruction is found, tries to read it as a long instruction.
	 * Not that efficient but should work in most of the cases.
	 *
	 * @param machine	Virtual machine which executes the instructions.
	 */
	public void run(Machine machine) {
		char[] longInstr = new char[MAX_OP_LENGTH]; // a long instruction has a maximal length
		int i = 0; // number of chars saved in longInstr
		char lastChar = '\n';
		while (bb.hasRemaining()) {
			char c = (char) bb.get();

			if (i > 0 || (c != '\n' && !machine.executeOp(c))) { // if we saved a longInstr or  (currentchar is not a new line  AND the machine can't execute the current character as a short instruction)
				if ((c == '\n' || !bb.hasRemaining()) && i > 0) { // if the current character is the end of the line or (the bytebuffer is  empty AND a long instruction is saved) -> execute the instruction saved
					if(c != '\n'){longInstr[i] = c; i++;}
					String name = new String(longInstr, 0, i);
					if (!machine.executeOp(name)) {
						exitInvalidInstruction();
					}
					i = 0;
				} else if ((i < MAX_OP_LENGTH && i > 0) || lastChar == '\n') { // If a long instruction is currently saved : continue or if the current char can't be execute : start a new save as long instruction.
					longInstr[i] = c;
					i++;
				} else {
						exitInvalidInstruction();
				}
			}

			lastChar = c;
		}
		System.out.print(machine.dumpMemory());
	}

}
