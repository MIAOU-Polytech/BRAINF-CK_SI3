package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.Procedure;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

import java.util.Optional;

/**
 * Instruction corresponding to the call of a Procedure.
 *
 * @author Julien Lemaire
 */
public class ProcedureCall extends Instruction {
	/**
	 * Called procedure.
	 */
	private Procedure proc;

	/**
	 * Integer parameter.
	 */
	private Optional<Integer> parameter;

	/**
	 * Main constructor of a ProcedureCall.
	 *
	 * @param proc called procedure.
	 */
	public ProcedureCall(Procedure proc) {
		super(proc.getName(), '\0', 0);
		this.proc = proc;
		parameter = Optional.empty();
	}

	/**
	 * Constructor of a ProcedureCall with an integer parameter.
	 *
	 * @param proc called procedure.
	 * @param parameter integer parameter.
	 */
	public ProcedureCall(Procedure proc, int parameter) {
		this(proc);
		this.parameter = Optional.of(parameter);
	}

	/**
	 * Action performed by the instruction: goes to procedure location.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine
	 */
	@Override
	public void accept(Machine machine) {
		if (parameter.isPresent()) {
			machine.saveMemoryAddress();
			machine.setLocation(parameter.get());
		}
		machine.saveReturnAddress();
		machine.setInstrPointer(proc.getPosition()-1); //-1, because the instruction pointer is incremented right after the execution of the Procedure instruction.
	}

	public String getProcedureName() {
		return proc.getName();
	}
}
