package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.BracketCounter;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.Metrics;

public class JumpTest {
	ConditionalJump jump;
	Machine machine;
	boolean notMatching = false;

	@Before
	public void setUp() {
		jump = new Jump();
		machine = new Machine();
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

	@Test
	public void acceptDoNothingTest() {
		machine.writeMemory((byte) 1);
		jump.accept(machine);
		assertFalse(machine.isJumping());
		assertFalse(machine.isReversed());
	}

	@Test
	public void acceptTest() {
		jump.accept(machine);
		assertTrue(machine.isJumping());
		assertFalse(machine.isReversed());
		assertTrue(0 != Metrics.DATA_READ.value());
	}

	@Test
	public void acceptThenResetTest() {
		machine.setReversed(true);
		jump.accept(machine);
		jump.accept(machine);
		assertFalse(machine.isJumping());
		assertFalse(machine.isReversed());
	}

	@Test
	public void acceptResetTest() {
		machine.writeMemory((byte) 1);
		machine.setReversed(true);
		machine.setJumping(true);
		jump.accept(machine);
		assertFalse(machine.isJumping());
		assertFalse(machine.isReversed());
	}

	@Test
	public void incrBracketCounterTest() {
		BracketCounter bc = new BracketCounter() {
			@Override public void onMismatch() {
				JumpTest.this.notMatching = true;
			}
		};
		assertFalse(notMatching);
		jump.incr(bc);
		bc.checkMatching();
		assertTrue(notMatching);
	}
}
