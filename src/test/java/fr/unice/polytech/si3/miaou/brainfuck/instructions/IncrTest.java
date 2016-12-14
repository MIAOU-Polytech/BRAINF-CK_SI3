package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IncrTest {
	Instruction back;

	@Before
	public void setUp() {
		back = new Incr();
	}

	@Test
	public void nameTest() {
		assertEquals("INCR", back.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('+', back.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFFFFFFFF, back.getColor());
	}
}
