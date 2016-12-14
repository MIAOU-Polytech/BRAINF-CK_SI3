package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RightTest {
	Instruction back;

	@Before
	public void setUp() {
		back = new Right();
	}

	@Test
	public void nameTest() {
		assertEquals("RIGHT", back.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('>', back.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFF0000FF, back.getColor());
	}
}
