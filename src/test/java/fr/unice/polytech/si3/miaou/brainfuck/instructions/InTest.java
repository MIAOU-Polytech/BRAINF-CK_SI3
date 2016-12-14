package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InTest {
	Instruction back;

	@Before
	public void setUp() {
		back = new In();
	}

	@Test
	public void nameTest() {
		assertEquals("IN", back.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals(',', back.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFF00FF00, back.getColor());
	}
}
