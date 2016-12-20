package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BracketMismatchExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new BracketMismatchException();
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(4, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		assertEquals("Left and right bracket count not matching", bfexception.getMessage());
	}
}
