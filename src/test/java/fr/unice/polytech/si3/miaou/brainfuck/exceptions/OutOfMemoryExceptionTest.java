package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OutOfMemoryExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new OutOfMemoryException(1, 1);
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(2, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		assertEquals("Cell number: 1, Total virtual memory size: 1", bfexception.getMessage());
	}
}
