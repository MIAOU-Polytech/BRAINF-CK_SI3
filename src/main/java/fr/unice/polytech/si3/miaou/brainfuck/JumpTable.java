package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.BracketMismatchException;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Back;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Jump;

/**
 * instructions from a List and execute them.
 *
 * @author Nassim Bounouas
 * @see Instruction
 */
public class JumpTable {

	/**
	 * Hashmap containing conditionnal jump instructions indexes
	 */
	private Map<Integer, Integer> conditionnalJumpMap;

	/**
	 * Stack used as a buffer to store a Jump waiting its Back instruction
	 */
	private Deque<Integer> intermediateStack;

	private boolean valid = true;

	/**
	 * Constructs an interpreter using the given List of Instruction.
	 */
	public JumpTable() {
		this.conditionnalJumpMap = new HashMap<>();
		this.intermediateStack = new ArrayDeque<>();
	}

	/**
	 * Performs the Jump/back instructions check
	 */
	public void check() {
		if (!valid || !this.intermediateStack.isEmpty()) {
			throw new BracketMismatchException();
		}
	}

	/**
	 * Add and associate a Jump/Back instruction to its corresponding instruction
	 *
	 * @param i The instruction to add in the Jumptable
	 * @param index The instruction index
	 */
	public void bind(Instruction i, int index) {
		if (i instanceof Jump) {
			this.intermediateStack.push(new Integer(index));
		} else if (i instanceof Back) {
			if (intermediateStack.size() <= 0) {
				this.valid = false;
				return;
			}

			Integer jumpIndex = this.intermediateStack.pop();
			Integer backIndex = index;
			this.conditionnalJumpMap.put(jumpIndex, backIndex);
			this.conditionnalJumpMap.put(backIndex, jumpIndex);
		}
	}

	/**
	 * Return the index of the associated conditionnal Jump.
	 *
	 * @param index The index from where we are jumping
	 * @return The target index
	 */
	public Integer getJump(int index) {
			return this.conditionnalJumpMap.get(new Integer(index));
	}
}
