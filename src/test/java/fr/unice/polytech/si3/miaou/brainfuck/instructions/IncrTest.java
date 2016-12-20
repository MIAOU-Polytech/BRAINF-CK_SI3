package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.OverflowException;

public class IncrTest {
	Instruction incr;
	Machine machine;

	@Before
	public void setUp() {
		incr = new Incr();
		machine = new Machine();
	}

	@Test
	public void nameTest() {
		assertEquals("INCR", incr.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('+', incr.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFFFFFFFF, incr.getColor());
	}

	@Test
	public void acceptTest() {
		incr.accept(machine);
		assertEquals(Byte.MIN_VALUE + 1, machine.readMemory());
		assertTrue(0 != Metrics.DATA_WRITE.value());
	}

	@Test(expected=OverflowException.class)
	public void acceptOverflowTest() {
		machine.writeMemory(Byte.MAX_VALUE);
		incr.accept(machine);
	}
}
