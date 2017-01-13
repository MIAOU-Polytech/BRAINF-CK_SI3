package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.util.HashMap;
import java.util.stream.Collectors;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.LanguageException;

/**
 * Stores all the object representing the languages available to translate a Brainfuck program.
 *
 * @author Guillaume Casagrande
 */
class LanguageSet {
	/**
	 * Lists all Language objects (instantiated from their respective classes).
	 */
	private static final Language[] languages = {new CLanguage(), new PythonLanguage(), new RubyLanguage()};

	/**
	 * Maps between the language's name and the language object for easy fetching.
	 */
	private HashMap<String, Language> names;

	/**
	 * Constructs the language set and populates the HashMap.
	 */
	LanguageSet() {
		names = new HashMap<>();
		for (Language language: languages) {
			names.put(language.getName(), language);
		}
	}

	/**
	 * Gets the language's object by its name.
	 *
	 * @param name	Language's name.
	 * @return	Corresponding Language object.
	 */
	Language getLanguage(String name) {
		if (!names.containsKey(name)) {
			throw new LanguageException("Incorrect name of language. Try one of: " + names.keySet().stream().collect(Collectors.joining(", ")));
		}
		return names.get(name);
	}
}
