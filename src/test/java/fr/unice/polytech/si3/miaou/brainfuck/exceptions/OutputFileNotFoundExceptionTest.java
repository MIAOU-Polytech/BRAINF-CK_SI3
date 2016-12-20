package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OutputFileNotFoundExceptionTest {
	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new OutputFileNotFoundException(new FileNotFoundException("test"));
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(9, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		assertEquals("test", bfexception.getMessage());
	}

	@Test
	public void getMessageNullTest() {
		bfexception = new OutputFileNotFoundException(new FileNotFoundException());
		assertNull(bfexception.getMessage());
	}
}
