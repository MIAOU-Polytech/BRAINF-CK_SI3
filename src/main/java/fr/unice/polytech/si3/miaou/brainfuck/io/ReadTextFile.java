package fr.unice.polytech.si3.miaou.brainfuck.io;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Opens a file and provide a Stream to read the data from.
 *
 * @author Pierre-Emmanuel Novac
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html">Stream</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html">Files</a>
 */
public class ReadTextFile {
	/**
	 * File's path as a Path object for encapsulation.
	 */
	private Path path;

	/**
	 * Constructs a file reader with the given file path and check if the file exists.
	 *
	 * @param filename	File's path.
	 * @throws FileNotFoundException	if the given file does not exist.
	 */
	public ReadTextFile(String filename) throws FileNotFoundException {
		this.path = Paths.get(filename);
		if (!Files.exists(path))
			throw new FileNotFoundException();
	}

	/**
	 * Opens the file from the path given in the constructor and returns a Stream of its lines.
	 *
	 * @return Stream of file's lines.
	 * @throws IOException	if the file could not be open.
	 */
	public Stream<String> getData() throws IOException {
		return Files.lines(path);
	}
}
