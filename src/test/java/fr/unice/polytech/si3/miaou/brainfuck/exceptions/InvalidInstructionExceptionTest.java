package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InvalidInstructionExceptionTest {
	BrainfuckException bfexception;
	String bmsg = "Invalid instruction: ";

	@Before
	public void setUp() {
		bfexception = new InvalidInstructionException(0);
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(6, bfexception.getErrorCode());
	}

	@Test
	public void getMessageFromIOExceptionTest() {
		bfexception = new InvalidInstructionException('c');
		assertEquals(bmsg + "c", bfexception.getMessage());
	}

	@Test
	public void getMessageNullFromIOExceptionTest() {
		bfexception = new InvalidInstructionException(65535);
		assertEquals(bmsg + Integer.toHexString(65535), bfexception.getMessage());
	}
}
