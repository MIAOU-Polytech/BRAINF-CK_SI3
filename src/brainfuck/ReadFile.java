package brainfuck;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


import brainfuck.virtualmachine.Machine;

/**
 * Opens a file and provide a memory mapped buffer to read from it.
 * Avoid copying the file in RAM.
 *
 * @author MIAOU
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/MappedByteBuffer.html">MappedByteBuffer</a>
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/io/RandomAccessFile.html">RandomAccessFile</a>
 */
public class ReadFile {
	/**
	 * Buffer for the memory mapped file.
	 */
	private MappedByteBuffer mbb;

	/**
	 * Constructs a ReadFile, consisting of a MappedByteBuffer coming from a RandomAccessFile opened in read-only mode.
	 * FileChannel is an intermediate object for getting the MappedByteBuffer.
	 *
	 * @param filename	file's path.
	 * @throws FileNotFoundException	if the given file does not exist.
	 * @throws IOException	if the file could not be open.
	 */
	public ReadFile(String filename) throws FileNotFoundException, IOException {
		FileChannel fileChannel = new RandomAccessFile(filename, "r").getChannel();
		mbb = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
	}

	/**
	 * Returns the MappedByteBuffer corresponding to the file given in the constructor.
	 *
	 * @return Stream of file's lines.
	 */
	public ByteBuffer getFile() {
		return mbb;
	}
}
