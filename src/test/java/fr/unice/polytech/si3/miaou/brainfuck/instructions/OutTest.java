package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OutTest {
	Instruction back;

	@Before
	public void setUp() {
		back = new Out();
	}

	@Test
	public void nameTest() {
		assertEquals("OUT", back.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('.', back.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFFFFFF00, back.getColor());
	}
}
