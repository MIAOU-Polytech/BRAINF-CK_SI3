package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;

public class LeftTest {
	Instruction left;
	Machine machine;

	@Before
	public void setUp() {
		left = new Left();
		machine = new Machine();
	}

	@Test
	public void nameTest() {
		assertEquals("LEFT", left.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('<', left.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFF9400D3, left.getColor());
	}

	@Test
	public void acceptTest() {
		machine.setLocation(2);
		left.accept(machine);
		assertEquals(1, machine.getLocation());
		assertTrue(0 != Metrics.DATA_MOVE.value());
	}
}
