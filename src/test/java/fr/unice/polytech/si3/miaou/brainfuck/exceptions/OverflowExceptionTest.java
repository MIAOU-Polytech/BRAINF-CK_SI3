package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OverflowExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new OverflowException();
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(1, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		bfexception = new OverflowException("test");
		assertEquals("test", bfexception.getMessage());
	}
}
