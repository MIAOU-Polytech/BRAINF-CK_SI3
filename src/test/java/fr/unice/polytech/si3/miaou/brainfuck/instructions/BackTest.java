package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.BracketCounter;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;

public class BackTest {
	ConditionalJump back;
	Machine machine;
	boolean notMatching = false;

	@Before
	public void setUp() {
		back = new Back();
		machine = new Machine();
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

	@Test
	public void acceptDoNothingTest() {
		back.accept(machine);
		assertFalse(machine.isJumping());
		assertFalse(machine.isReversed());
	}

	@Test
	public void acceptTest() {
		machine.writeMemory((byte) 1);
		back.accept(machine);
		assertTrue(machine.isJumping());
		assertTrue(machine.isReversed());
		assertTrue(0 != Metrics.DATA_READ.value());
	}

	@Test
	public void acceptThenResetTest() {
		machine.writeMemory((byte) 1);
		back.accept(machine);
		back.accept(machine);
		assertFalse(machine.isJumping());
		assertTrue(machine.isReversed());
	}

	@Test
	public void acceptResetTest() {
		machine.setReversed(true);
		machine.setJumping(true);
		back.accept(machine);
		assertFalse(machine.isJumping());
		assertTrue(machine.isReversed());
	}

	@Test
	public void incrBracketCounterTest() {
		BracketCounter bc = new BracketCounter() {
			@Override public void onMismatch() {
				BackTest.this.notMatching = true;
			}
		};
		assertFalse(notMatching);
		back.incr(bc);
		assertTrue(notMatching);
	}
}
