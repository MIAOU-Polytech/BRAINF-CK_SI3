package brainfuck.io;

import java.io.*;
import java.util.Scanner;
import brainfuck.exceptions.ReadInputException;

/**
 * Manage Applications's input and output flows
 * 
 * @author Nassim Bounouas
 */
public class Io{

	/**
	 * InputStream used as input flow
	 */
	private InputStream input = System.in;
	
	/**
	 * OutputStream used as output flow
	 */
	private OutputStream output = System.out;

	/**
	 * Constructs an Io Object used to access in read or write
	 */
	public Io(String in, String out) {
		try{	
			if(in != null){
				this.input = new FileInputStream(in);
			}
		
			if(out != null){
				this.output = new FileOutputStream(out);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	/**
	 * Get the next input
	 */	
	public int read() {
		try{
			return this.input.read();	
		}catch(IOException e){
			throw new ReadInputException("ReadInput error");
		}
	}

	/**
	 * Output an int
	 */
	public void write(int c) {
		try{
			this.output.write(c);
		}catch(IOException e){
			throw new ReadInputException("write error");
		}
	}
}
