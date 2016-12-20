package fr.unice.polytech.si3.miaou.brainfuck.exceptions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BrainfuckExceptionTest {
	private class DummyBrainfuckException extends BrainfuckException {
		DummyBrainfuckException() {
			super();
		}

		DummyBrainfuckException(String str) {
			super(str);
		}

		@Override
		public int getErrorCode() {
			return 0;
		}
	}

	BrainfuckException bfexception;

	@Before
	public void setUp() {
		bfexception = new DummyBrainfuckException();
	}

	@Test
	public void getErrorCodeTest() {
		assertEquals(0, bfexception.getErrorCode());
	}

	@Test
	public void getMessageTest() {
		bfexception = new DummyBrainfuckException("test");
		assertEquals("test", bfexception.getMessage());
	}
}
