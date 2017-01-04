package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class FunctionsParserTest {
	private FunctionsParser parser;
	private Stream<String> lines;
	private InstructionSet instructionSet;

	@Before
	public void defineContext() {
		instructionSet = new InstructionSet();
		parser = new FunctionsParser(instructionSet);
	}

	@Ignore
	@Test
	public void testExists() {
		List<String> linesBuilder = new LinkedList<>();
		linesBuilder.add("FUNC Miaou");
		linesBuilder.add("++--");
		linesBuilder.add("RET");
		lines = linesBuilder.stream();
		lines.map(parser);
		assertNotNull(instructionSet.getProc("Miaou"));
	}
}
