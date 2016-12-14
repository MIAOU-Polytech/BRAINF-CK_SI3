package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DecrTest {
	Instruction back;

	@Before
	public void setUp() {
		back = new Decr();
	}

	@Test
	public void nameTest() {
		assertEquals("DECR", back.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('-', back.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFF4B0082, back.getColor());
	}
}
