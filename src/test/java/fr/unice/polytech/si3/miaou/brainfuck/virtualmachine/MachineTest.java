package fr.unice.polytech.si3.miaou.brainfuck.virtualmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;

public class MachineTest {
	Machine machine;
	Instruction instr;
	boolean executed = false;

	@Before
	public void setUp() {
		machine = new Machine(0, new JumpTable());
		instr = new Instruction("", 'c', 0) {
			@Override public void accept(Machine machine) {
				MachineTest.this.executed = true;
			}
		};
	}

	@Test
	public void writeMemoryTest() {
		machine.writeMemory((byte) 1);
		assertEquals(1, machine.readMemory());
	}

	@Test
	public void readMemoryTest() {
		assertEquals(Byte.MIN_VALUE, machine.readMemory());
	}

	@Test
	public void executeOpTest() {
		assertFalse(executed);
		machine.executeOp(instr);
		assertTrue(executed);
	}

	@Test
	public void dumpMemoryTest() {
		assertTrue(machine.dumpMemory().isEmpty());
		machine.writeMemory((byte) -127);
		assertEquals("C0: 1\n", machine.dumpMemory());
	}

	@Test
	public void goBackMemoryTest() {
		machine.setLocation(10);
		machine.saveMemoryAddress();
		machine.setLocation(2);
		machine.goBackMemory();
		assertEquals(10, machine.getLocation());
	}

	@Test
	public void goBackMemoryEmptyStackTest() {
		machine.setLocation(2);
		machine.goBackMemory();
		assertEquals(2, machine.getLocation());
	}
}
