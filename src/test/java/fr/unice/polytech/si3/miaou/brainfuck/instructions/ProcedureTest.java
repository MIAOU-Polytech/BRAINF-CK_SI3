package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.FunctionUsageException;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.SyntaxFunctionException;
import org.junit.Before;
import org.junit.Test;

public class ProcedureTest {
	private Procedure procedure;

	@Before
	public void defineContext() {
		procedure = new Procedure("miaou", 0);
	}
}
