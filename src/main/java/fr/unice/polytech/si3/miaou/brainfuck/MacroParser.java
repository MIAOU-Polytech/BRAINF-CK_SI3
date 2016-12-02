package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.SyntaxMacroException;

/**
 * Parsing <code>Macro</code>s (while creating them and using them).
 *
 * @author Pierre-Emmanuel Novac
 * @author Julien Lemaire
 */
public class MacroParser {
	/**
	 * Different states of the <code>Macro</code> parsing process.
	 *
	 * @author Pierre-Emmanuel Novac
	 */
	private enum State {
		/**
		 * Not defining a <code>Macro</code>.
		 */
		NO_MACRO,
		/**
		 * Next line defines the name of the <code>Macro</code>.
		 */
		MACRO_NAME,
		/**
		 * Next lines define the different arguments of the <code>Macro</code>.
		 */
		MACRO_ARGS,
		/**
		 * Next lines define the content of the <code>Macro</code>.
		 */
		MACRO_BODY
	}

	/**
	 * Defining the content of a <code>Macro</code>.
	 *
	 * @author Pierre-Emmanuel Novac
	 */
	private class Macro {
		/**
		 * Contains <code>Macro</code>'s content.
		 */
		private List<String> body;

		/**
		 * Main constructor of class <code>Macro</code>.
		 */
		Macro() {
			body = new ArrayList<>();
		}

		/**
		 * Adds content to the <code>Macro</code>'s body.
		 *
		 * @param line Content to add to <code>Macro</code>'s body.
		 */
		void addToBody(String line) {
			body.add(line);
		}

		void addToBody(Stream<String> lines) {
			lines.forEach(this::addToBody);
		}

		/**
		 * Returns the <code>Macro</code>'s body as a stream.
		 *
		 * @return the <code>Macro</code>'s body as a stream.
		 */
		Stream<String> getBody() {
			return body.stream();
		}
	}

	/**
	 * Map containing the different <code>Macro</code>s linked to their names.
	 */
	private Map<String, Macro> macros;
	/**
	 * Original content of the program, with <code>Macro</code> syntax.
	 */
	private Stream<String> prog;
	/**
	 * Current state of the finite state machine.
	 */
	private State state = State.NO_MACRO;

	/**
	 * Stores the name of the <code>Macro</code> being defined.
	 */
	private String macroName;
	/**
	 * Content of the <code>Macro</code> being defined.
	 */
	private Macro macro;

	/**
	 * Main constructor of <code>MacroParser</code>.
	 *
	 * @param stream stream of lines containing instructions symbols and keywords to parse.
	 */
	public MacroParser(Stream<String> stream) {
		this.macros = new HashMap<>();
		this.prog = stream;
	}

	/**
	 * Parses the stream given in constructor and returns it without <code>Macro</code>s definitions and usage.
	 *
	 * @return The stream of lines given in constructor without <code>Macro</code>s definitions and usage.
	 */
	public Stream<String> parse() {
		return prog.flatMap(line -> {
			State prev_state = state;

			// MACRO_ARGS not implemented for now
			if ("DEFINE".equals(line)) {
				if (state == State.NO_MACRO) {
					state = State.MACRO_NAME;
					macro = new Macro();
					return Stream.empty();
				} else {
					throw new SyntaxMacroException("Defining a Macro while not finishing defining a previous one.");
				}
			} else if ("AS".equals(line)) {
				if (state == State.MACRO_NAME) {
					state = State.MACRO_BODY;
					return Stream.empty();
				} else {
					throw new SyntaxMacroException("AS without a previous DEFINE.");
				}
			} else if ("END".equals(line)) {
				if (state == State.MACRO_BODY) {
					state = State.NO_MACRO;
					macros.put(macroName, macro);
					return Stream.empty();
				} else {
					throw new SyntaxMacroException("END without a previous AS.");
				}
			}

			if (state == State.MACRO_NAME) macroName = line;
			if (state == State.MACRO_BODY) {
				if (macros.containsKey(line)) // Recursive macro
					macro.addToBody(macros.get(line).getBody());
				else
					macro.addToBody(line);
			}

			if (state == State.NO_MACRO) {
				if (macros.containsKey(line)) { // We've got a macro right there, try to replace it. Naive check so you can be evil and declare a "+" macro.
					return macros.get(line).getBody();
				} else {
					return Stream.of(line);
				}
			}
			return Stream.empty();
		});
	}
}
