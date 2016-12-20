package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputOutputExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new InputOutputException("test");
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(8, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		assertEquals("test", bfexception.getMessage());
	}

	@Test
	public void getMessageFromIOExceptionTest() {
		bfexception = new InputOutputException(new IOException("test"));
		assertEquals("test", bfexception.getMessage());
	}

	@Test
	public void getMessageNullFromIOExceptionTest() {
		bfexception = new InputOutputException(new IOException());
		assertNull(bfexception.getMessage());
	}
}
