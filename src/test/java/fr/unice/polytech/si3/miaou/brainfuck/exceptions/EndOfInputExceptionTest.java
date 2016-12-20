package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EndOfInputExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new EndOfInputException();
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(7, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		assertEquals("End of input", bfexception.getMessage());
	}
}
