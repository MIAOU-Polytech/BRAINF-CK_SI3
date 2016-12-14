package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BackTest {
	Instruction back;

	@Before
	public void setUp() {
		back = new Back();
	}

	@Test
	public void nameTest() {
		assertEquals("BACK", back.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals(']', back.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFFFF0000, back.getColor());
	}
}
