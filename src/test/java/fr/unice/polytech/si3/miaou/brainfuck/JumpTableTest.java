package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.*;
import java.io.*;
import java.nio.file.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;


import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Jump;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Back;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.BracketMismatchException;

public class JumpTableTest {
	Instruction dummy;
	JumpTable jt;

	@Before
	public void setUp() throws IOException {
		jt = new JumpTable();
		dummy = new Instruction("", '\0', 0) {
			@Override public void accept(Machine m) {}
		};
	}

	// Check no exceptions thrown
	@Test
	public void checkTest() {
		jt.check();
		assertTrue(true);
	}

	@Test(expected=BracketMismatchException.class)
	public void checkJumpFailTest() {
		jt.bind(new Jump(), 0);
		jt.check();
	}

	@Test
	public void bindTest() {
		jt.bind(new Jump(), 1);
		jt.bind(new Back(), 3);
		assertEquals(1, jt.getJump(3).intValue());
		assertEquals(3, jt.getJump(1).intValue());
		jt.check();
	}

	@Test(expected=BracketMismatchException.class)
	public void checkBackFailTest() {
		jt.bind(new Back(), 0);
		jt.check();
	}

	@Test(expected=BracketMismatchException.class)
	public void dummyInstrBackTest() {
		jt.bind(dummy, 0);
		jt.bind(new Back(), 0);
		jt.check();
	}

	@Test
	public void dummyInstrTest() {
		jt.bind(dummy, 0);
		assertNull(jt.getJump(0));
	}
}
