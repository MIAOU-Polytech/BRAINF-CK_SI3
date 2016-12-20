package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArgumentsExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new ArgumentsException("test");
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(5, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		assertEquals("test", bfexception.getMessage());
	}
}
