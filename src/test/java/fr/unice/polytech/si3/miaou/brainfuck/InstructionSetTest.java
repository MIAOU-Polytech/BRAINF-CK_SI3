package fr.unice.polytech.si3.miaou.brainfuck;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Incr;


public class InstructionSetTest {
	InstructionSet is;
	Incr incr;

	@Before
	public void setUp() {
		is = new InstructionSet();
		incr = new Incr();
	}

	@Test
	public void getOpStringTest() {
		assertEquals(Incr.class, is.getOp(incr.getName()).getClass());
	}

	@Test
	public void getOpEmptyStringTest() {
		assertNull(is.getOp(""));
	}

	@Test
	public void getOpCharTest() {
		assertEquals(Incr.class, is.getOp(incr.getSymbol()).getClass());
	}

	@Test
	public void getOpNullCharTest() {
		assertNull(is.getOp('\0'));
	}

	@Test
	public void getOpIntTest() {
		assertEquals(Incr.class, is.getOp(incr.getColor()).getClass());
	}

	@Test
	public void getOpNullIntTest() {
		assertNull(is.getOp(0));
	}

	@Test
	public void addProcTest() {
		Procedure proc = new Procedure("test", 0);
		is.addProc(proc);
		assertEquals(proc, is.getProc("test"));
	}

	@Test
	public void getProcEmptyTest() {
		assertNull(is.getProc(""));
	}

	@Test
	public void addProcDuplicateTest() {
		Procedure proc = new Procedure("test", 0);
		Procedure proc2 = new Procedure("test", 0);
		is.addProc(proc);
		is.addProc(proc2);
		assertEquals(proc, is.getProc("test"));
		assertFalse(proc2 == is.getProc("test"));
	}

	@Test
	public void getProceduresTest() {
		Procedure proc = new Procedure("test", 0);
		assertTrue(is.getProcedures().isEmpty());
		is.addProc(proc);
		assertEquals(1, is.getProcedures().size());
		assertTrue(is.getProcedures().contains(proc));
	}
}
