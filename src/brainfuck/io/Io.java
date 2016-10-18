package brainfuck.io;


/**
 * Manage Applications's input & output flows
 * 
 * @author Nassim Bounouas
 */
public class Io{

	private InputStream input;
	private OutputStream output;

	public Io(String in, String out){
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
	}

	public int getInput(){
		return this.input.read();
	}

	public void setOutput(int c){
		this.output.write(c);	
	}

}
