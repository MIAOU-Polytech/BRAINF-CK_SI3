package fr.unice.polytech.si3.miaou.brainfuck.io;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.io.FileOutputStream;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.OutputFileNotFoundException;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InputOutputException;

/**
 * Opens a file and provide a Stream to read the data from.
 *
 * @author Nassim Bounouas
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html">Files</a>
 */
public class WriteTextFile {
	/**
	 * File's path as a Path object for encapsulation.
	 */
	private File file;

	private FileOutputStream fos;

	/**
	 * Constructs a file reader with the given file path and check if the file exists.
	 *
	 * @throws IOException	if the file can't be created
	 * @param filename	File's path.
	 */
	public WriteTextFile(String filename) throws IOException {
		this.file = new File(filename);
		renewFileOutputStream();
	}

	private void renewFileOutputStream() {
		try {
			this.fos = new FileOutputStream(this.file, true);
		} catch(FileNotFoundException e) {
			throw new OutputFileNotFoundException(e);
		}
	}

	/**
	 * Write a single line to the underlying file.
	 * A newline ('\n') character is automatically appended.
	 *
	 * @param str	the single line to write to the file.
	 */
	public void write(String str) {
		try {
			if (!fos.getFD().valid())
				renewFileOutputStream();
			fos.write(str.getBytes());
			fos.write('\n');
			fos.close();
		} catch(IOException e) {
			throw new InputOutputException(e);
		}
	}

}
