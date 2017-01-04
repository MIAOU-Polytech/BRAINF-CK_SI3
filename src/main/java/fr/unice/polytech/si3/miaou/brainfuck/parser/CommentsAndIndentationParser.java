package fr.unice.polytech.si3.miaou.brainfuck.parser;

import java.util.function.Function;

/**
 * Parsing comments & indentation to delete them.
 * Implements the function interface Function in order to be used directly in the Stream of line's map().
 *
 * @author Julien Lemaire
 */
class CommentsAndIndentationParser implements Function<String, String> {
	/**
	 * Parses the given line and deletes every comment and indentation it contains.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html">Function</a>'s apply() method.
	 *
	 * @param line the line to parse coming from the calling Stream function.
	 * @return comment and indentation parsing result.
	 */
	@Override
	public String apply(String line) {
		int p = line.indexOf("#");
		if (p > 0) line = line.substring(0, p); // Remove comments from the line
		return line.trim(); // Remove leading and trailing whitespaces from the line
	}
}
