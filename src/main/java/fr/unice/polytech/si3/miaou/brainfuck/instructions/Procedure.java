package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.FunctionUsageException;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * Instruction called for each procedure call.
 *
 * @author Julien Lemaire
 */
public class Procedure extends Instruction {
	/**
	 * Position where the Procedure's instructions are placed in the Instructions' memory.
 	 */
	private int position;

	/**
	 * Optional of an Integer corresponding to the parameter if passed.
 	 */
	private Optional<Integer> parameter;

	/**
	 * Main constructor of Procedure.
	 *
	 * @param name name of the Procedure.
	 * @param position position where the Procedure's instructions are placed in the Instructions' memory.
	 */
	public Procedure(String name, int position) {
		super(name, '\0', 0);
		this.position = position;
		this.parameter = Optional.empty();
	}


	/**
	 * Set value of the parameter.
	 *
	 * @param parameter new value of the parameter.
	 */
	public void setParameter(int parameter) {
		this.parameter = Optional.of(parameter);
	}

	/**
	 * Action performed by the instruction: move the instruction pointer to the Procedure's location.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		if (parameter.isPresent()) {
			machine.saveMemoryAddress();
			machine.setLocation(parameter.get());
		}
		machine.saveReturnAddress();
		machine.setInstrPointer(position-1); //-1, because the instruction pointer is incremented right after the execution of the Procedure instruction.
	}
}
