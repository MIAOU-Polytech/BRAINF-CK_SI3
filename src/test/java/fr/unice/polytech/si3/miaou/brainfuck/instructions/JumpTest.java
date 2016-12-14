package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JumpTest {
	Instruction jump;

	@Before
	public void setUp() {
		jump = new Jump();
	}

	@Test
	public void nameTest() {
		assertEquals("JUMP", jump.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('[', jump.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFFFF7F00, jump.getColor());
	}
}
