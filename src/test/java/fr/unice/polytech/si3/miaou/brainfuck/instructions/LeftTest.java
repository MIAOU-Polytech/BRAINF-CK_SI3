package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LeftTest {
	Instruction back;

	@Before
	public void setUp() {
		back = new Left();
	}

	@Test
	public void nameTest() {
		assertEquals("LEFT", back.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('<', back.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFF9400D3, back.getColor());
	}
}
