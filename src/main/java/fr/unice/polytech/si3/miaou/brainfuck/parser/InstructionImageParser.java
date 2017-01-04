package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InvalidInstructionException;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

import java.util.List;
import java.util.function.IntConsumer;

/**
 * Parsing instructions from text.
 * Inherits of java IntConsumer's interface.
 *
 * @author Julien Lemaire
 */
class InstructionImageParser implements IntConsumer {
	private List<Instruction> instructions;
	private JumpTable jumptable;
	private InstructionSet iset;

	public InstructionImageParser(List<Instruction> instructions, JumpTable jumptable, InstructionSet iset) {
		this.instructions = instructions;
		this.jumptable = jumptable;
		this.iset = iset;
	}

	@Override
	public void accept(int colour) {
		if (colour != 0xFF000000) { // Skip black
			Instruction instr = iset.getOp(colour);
			if (instr != null) {
				this.addAndBindInstructions(instr);
			} else {
				throw new InvalidInstructionException(colour);
			}
		}
	}

	/**
	 * Add an instruction to the list of instructions and bind it in the jumptable if it's an JUMP or BACK instruction.
	 *
	 * @param instr Instruction to add and bind
	 */
	private void addAndBindInstructions(Instruction instr) {
		instructions.add(instr);
		jumptable.bind(instr, this.instructions.size());
	}
}
