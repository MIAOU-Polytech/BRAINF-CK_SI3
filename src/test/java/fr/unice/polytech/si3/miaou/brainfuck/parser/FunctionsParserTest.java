package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.ProcedureCall;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Return;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.SyntaxFunctionException;

public class FunctionsParserTest {
	private FunctionsParser parser;
	private InstructionSet instructionSet;

	@Before
	public void defineContext() {
		instructionSet = new InstructionSet();
		parser = new FunctionsParser(instructionSet);

	}

	@Test
	public void testExists() {
		parser.apply("FUNC Miaou");
		parser.apply("++");
		parser.apply("RET");
		parser.apply("FUNC Nyan");
		parser.apply("-");
		parser.apply("RET");
		assertNotNull(instructionSet.getProc("Miaou"));
		assertNotNull(instructionSet.getProc("Nyan"));
	}

	@Test
	public void testCounter() {
		parser.apply("FUNC Miaou");
		parser.apply("++");
		parser.apply("RET");
		parser.apply("FUNC Nyan");
		parser.apply("-");
		parser.apply("RET");
		assertEquals(5, parser.getCounter());
	}

	@Test
	public void testParseCall() {
		parser.apply("FUNC Miaou");
		parser.apply("++");
		parser.apply("RET");
		parser.apply("FUNC Nyan");
		parser.apply("-");
		parser.apply("RET");
		List<Instruction> instr = new ArrayList<>();
		instr.add(parser.parseCall(new String[]{"Miaou"}));
		instr.add(parser.parseCall(new String[]{"Nyan"}));
		assertEquals(2, instr.size());
		assertEquals(ProcedureCall.class, instr.get(0).getClass());
		assertEquals(ProcedureCall.class, instr.get(1).getClass());
	}

	@Test
	public void testParseCallParameter() {
		parser.apply("FUNC Miaou");
		parser.apply("++");
		parser.apply("RET");
		parser.apply("FUNC Nyan");
		parser.apply("-");
		parser.apply("RET");
		List<Instruction> instr = new ArrayList<>();
		instr.add(parser.parseCall(new String[]{"Miaou", "13"}));
		assertEquals(ProcedureCall.class, instr.get(0).getClass());
	}

	@Test
	public void declareTest() {
		parser.apply("FUNC MIAOU");
		parser.apply("RET");
		assertEquals("MIAOU", instructionSet.getProc("MIAOU").getName());
		assertEquals(1, parser.getCounter());
	}

	@Test
	public void declareShortSyntaxTest() {
		parser.apply("FUNC MIAOU");
		parser.apply("+++");
		parser.apply("+++");
		parser.apply("RET");
		parser.apply("FUNC NYAN");
		parser.apply("MIAOU");
		parser.apply("RET");
		parser.apply("NYAN");

		assertEquals(9, parser.getCounter());

		assertEquals("MIAOU", instructionSet.getProc("MIAOU").getName());

		Machine machine = new Machine(10, new JumpTable());
		machine.executeOp(new ProcedureCall(instructionSet.getProc("NYAN")));
		assertEquals(7, machine.getInstrPointer());
		machine.executeOp(new ProcedureCall(instructionSet.getProc("MIAOU")));
		assertEquals(0, machine.getInstrPointer());
	}

	@Test(expected=SyntaxFunctionException.class)
	public void retSyntaxErrorTest() {
		parser.apply("RET");
	}

	@Test(expected=SyntaxFunctionException.class)
	public void doubledeclareFuncErrorTest() {
		parser.apply("FUNC MIAOU");
		parser.apply("FUNC NYAN");
	}

	@Test(expected=SyntaxFunctionException.class)
	public void noNameTest() {
		parser.apply("FUNC");
	}

	@Test
	public void parseReturnTest() {
		String line = "RET 38";
		Return ret = parser.parseReturn(line.split(" "));

		Machine machine = new Machine(0, new JumpTable());
		machine.saveReturnAddress();
		machine.setLocation(38);
		machine.writeMemory((byte) 2);
		ret.accept(machine);
		assertEquals(2, machine.readMemory());
	}

	@Test
	public void parseCallNullTest() {
		assertNull(parser.parseCall(new String[] {}));
	}

	@Test
	public void parseReturnNullTest() {
		assertNull(parser.parseReturn("".split(" ")));
		assertNull(parser.parseReturn("a".split(" ")));
		assertNull(parser.parseReturn("a a a".split(" ")));
		assertNull(parser.parseReturn("INCR 39".split(" ")));
	}
}
