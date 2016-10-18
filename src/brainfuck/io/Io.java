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

	private InputStream input;
	private OutputStream output;

	public Io(String in, String out) {
		try{	
			if(in != null){
				this.input = new FileInputStream(in);
			}else{
				this.input = System.in;
			}
		
			if(out != null){
				this.output = new FileOutputStream(out);
			}else{
				this.output = System.out;
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public int getInput() {
		try{
			return this.input.read();	
		}catch(IOException e){
			throw new ReadInputException("ReadInput error");
		}
	}

	public void setOutput(int c) {
		try{
			this.output.write(c);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
