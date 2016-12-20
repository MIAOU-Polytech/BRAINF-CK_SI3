package fr.unice.polytech.si3.miaou.brainfuck.virtualmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.OutOfMemoryException;

public class MemoryTest {
	private static final int DEFAULT_SIZE = 30000;
	Memory memory;

	@Before
	public void setUp() {
		memory = new Memory();
	}

	@Test
	public void notOutOfBoundsDefaultSizeTest() throws OutOfMemoryException {
		memory.checkBounds(DEFAULT_SIZE-1);
	}

	@Test(expected=OutOfMemoryException.class)
	public void outOfBoundsDefaultSizeTest() {
		memory.checkBounds(DEFAULT_SIZE);
	}

	@Test
	public void notOutOfBoundsCustomSizeTest() throws OutOfMemoryException {
		memory = new Memory(1);
		memory.checkBounds(0);
	}

	@Test(expected=OutOfMemoryException.class)
	public void outOfBoundsCustomSizeTest() {
		memory = new Memory(1);
		memory.checkBounds(1);
	}

	@Test(expected=OutOfMemoryException.class)
	public void outOfBoundsNegativeTest() {
		memory.checkBounds(-1);
	}

	@Test
	public void getTest() {
		assertEquals(Byte.MIN_VALUE, memory.get(0));
	}

	@Test
	public void setTest() {
		memory.set(1, (byte) 1);
		assertEquals(1, memory.get(1));
		assertEquals(Byte.MIN_VALUE, memory.get(0));
	}

	@Test
	public void toStringEmptyTest() {
		assertTrue(memory.toString().isEmpty());
	}

	@Test
	public void toStringTest() {
		memory.set(1, (byte) -126);
		memory.set(3, (byte) 1);
		assertEquals("C1: 2\nC3: 129\n", memory.toString());
	}
}
