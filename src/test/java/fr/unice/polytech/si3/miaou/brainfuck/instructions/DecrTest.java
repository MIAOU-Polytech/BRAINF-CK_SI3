package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.OverflowException;

public class DecrTest {
	Instruction decr;
	Machine machine;


	@Before
	public void setUp() {
		decr = new Decr();
		machine = new Machine();
	}

	@Test
	public void nameTest() {
		assertEquals("DECR", decr.getName());
	}

	@Test
	public void symbolTest() {
		assertEquals('-', decr.getSymbol());
	}

	@Test
	public void colorTest() {
		assertEquals(0xFF4B0082, decr.getColor());
	}

	@Test
	public void acceptTest() {
		machine.writeMemory((byte) (Byte.MIN_VALUE + 1));
		decr.accept(machine);
		assertEquals(Byte.MIN_VALUE, machine.readMemory());
		assertTrue(0 != Metrics.DATA_WRITE.value());
	}

	@Test(expected=OverflowException.class)
	public void acceptOverflowTest() {
		decr.accept(machine);
	}
}
