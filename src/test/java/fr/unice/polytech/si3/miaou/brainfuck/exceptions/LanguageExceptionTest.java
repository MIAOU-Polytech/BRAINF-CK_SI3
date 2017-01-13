package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LanguageExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new LanguageException("test");
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(13, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		assertEquals("test", bfexception.getMessage());
	}
}
