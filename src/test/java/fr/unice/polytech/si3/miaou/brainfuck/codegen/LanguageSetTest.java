package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.util.*;
import java.io.*;
import java.nio.file.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import org.junit.rules.TemporaryFolder;


import fr.unice.polytech.si3.miaou.brainfuck.exceptions.LanguageException;

public class LanguageSetTest {
	LanguageSet ls;

	@Before
	public void setUp() throws IOException {
		ls = new LanguageSet();
	}

	@Test
	public void getLanguageTest() {
		assertEquals(PythonLanguage.class, ls.getLanguage("python").getClass());
		assertEquals(RubyLanguage.class, ls.getLanguage("ruby").getClass());
		assertEquals(CLanguage.class, ls.getLanguage("c").getClass());
	}

	@Test(expected=LanguageException.class)
	public void getLanguageErrorTest() {
		ls.getLanguage("test");
	}
}
