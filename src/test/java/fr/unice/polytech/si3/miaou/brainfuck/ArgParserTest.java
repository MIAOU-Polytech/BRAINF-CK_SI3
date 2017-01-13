package fr.unice.polytech.si3.miaou.brainfuck;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.ArgumentsException;

public class ArgParserTest {
	ArgParser ap;

	@Test(expected=ArgumentsException.class)
	public void pNoFilenameTest() {
		ap = new ArgParser(new String[] {"-p"});
	}

	@Test(expected=ArgumentsException.class)
	public void iNoFilenameTest() {
		ap = new ArgParser(new String[] {"-i"});
	}

	@Test(expected=ArgumentsException.class)
	public void oNoFilenameTest() {
		ap = new ArgParser(new String[] {"-o"});
	}

	@Test(expected=ArgumentsException.class)
	public void invalidArgTest() {
		ap = new ArgParser(new String[] {"--test"});
	}

	@Test(expected=ArgumentsException.class)
	public void invalidSyntaxFileTest() {
		ap = new ArgParser(new String[] {"-p", "-i"});
	}

	@Test(expected=ArgumentsException.class)
	public void getNameNoArgsTest() {
		ap = new ArgParser(new String[] {});
		ap.getFilename();
	}

	@Test(expected=ArgumentsException.class)
	public void getNameEmptyTest() {
		ap = new ArgParser(new String[] {"-p", ""});
		ap.getFilename();
	}

	@Test
	public void pTest() {
		ap = new ArgParser(new String[] {"-p", "test"});
		assertEquals("test", ap.getFilename());
		assertEquals(Type.TEXT, ap.getType());
		assertTrue(ap.isIn(Mode.RUN));
	}

	@Test
	public void nometricsTest() {
		ap = new ArgParser(new String[] {"--nometrics"});
		assertTrue(ap.isIn(Mode.NOMETRICS));
	}

	@Test
	public void pImgTest() {
		ap = new ArgParser(new String[] {"-p", "test.bmp"});
		assertEquals(Type.IMAGE, ap.getType());
		ap = new ArgParser(new String[] {"-p", "test.BMP"});
		assertEquals(Type.IMAGE, ap.getType());
	}

	@Test
	public void iTest() {
		ap = new ArgParser(new String[] {"-i", "test"});
		assertEquals("test", ap.getInput());
	}

	@Test
	public void oTest() {
		ap = new ArgParser(new String[] {"-o", "test"});
		assertEquals("test", ap.getOutput());
	}

	@Test
	public void generateName() {
		ap = new ArgParser(new String[] {"--generate", "test"});
		assertEquals("test", ap.getLanguage());
	}

	@Test
	public void notTracingTest() {
		ap = new ArgParser(new String[] {});
		assertFalse(ap.isIn(Mode.TRACE));
	}

	@Test
	public void tracingTest() {
		ap = new ArgParser(new String[] {"--trace"});
		assertTrue(ap.isIn(Mode.TRACE));
		assertTrue(ap.isIn(Mode.RUN));
	}

	@Test
	public void rewriteCheckTest() {
		ap = new ArgParser(new String[] {"--rewrite", "--check"});
		assertTrue(ap.isIn(Mode.REWRITE));
		assertTrue(ap.isIn(Mode.CHECK));
		assertFalse(ap.isIn(Mode.RUN));
        }

	@Test
	public void translateTraceTest() {
		ap = new ArgParser(new String[] {"--translate", "--trace"});
		assertTrue(ap.isIn(Mode.TRANSLATE));
		assertTrue(ap.isIn(Mode.TRACE));
		assertFalse(ap.isIn(Mode.RUN));

	}
}
