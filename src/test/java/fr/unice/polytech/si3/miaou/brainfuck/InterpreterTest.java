package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.*;
import java.io.*;
import java.nio.file.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.Ignore;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import fr.unice.polytech.si3.miaou.brainfuck.metrics.ExecMove;

public class InterpreterTest {
	Logger log;
	Instruction instr;
	Machine machine;
	Interpreter inter;
	List<Instruction> instrl;

	ByteArrayOutputStream outStream;

	String logs = "";


	int counter = 0;

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();


	class DummyLogger extends Logger {
		DummyLogger() throws IOException { super("."); }
		public void logStep(int exec, Instruction instruction, Machine machine) {
			logs += "Logged " + instruction.getName();
		}
	}

	@Before
	public void setUp() throws IOException {
		outStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outStream));

		instr = new Instruction("test", '\0', 0) {
			@Override public void accept(Machine machine) {
				counter++;
			}
		};

		log = new DummyLogger();
		machine = new Machine(0, new JumpTable());
		instrl = new ArrayList<>();
		instrl.add(instr);
		inter = new Interpreter(instrl);
	}

	@Test
	public void loggerTest() throws IOException {
		assertTrue(logs.isEmpty());
		inter.setLogger(log);
		inter.run(machine);
		assertFalse(logs.isEmpty());
		assertTrue(logs.contains("test"));
	}

	@Test
	public void dumpMetricsTest() throws IOException {
		inter.run(machine);
		assertTrue(outStream.toString().contains("EXEC_MOVE"));
	}

	@Test
	public void dumpMetricsDisabledTest() throws IOException {
		inter.disableMetricsReport();
		inter.run(machine);
		assertTrue(outStream.toString().isEmpty());
	}

	@Test
	public void runDummyInstr() throws IOException {
		instrl.add(instr);
		inter.run(machine);
		assertEquals(2, counter);
		assertTrue(machine.getMetric(ExecMove.class).toString().contains(" 2"));
	}
}
