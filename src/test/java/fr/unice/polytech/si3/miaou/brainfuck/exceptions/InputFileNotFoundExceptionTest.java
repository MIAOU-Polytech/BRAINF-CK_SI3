package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputFileNotFoundExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new InputFileNotFoundException(new FileNotFoundException("test"));
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(3, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		assertEquals("test", bfexception.getMessage());
	}

	@Test
	public void getMessageNullTest() {
		bfexception = new InputFileNotFoundException(new FileNotFoundException());
		assertNull(bfexception.getMessage());
	}
}
