import  java.io.*;
import  java.util.ArrayList;

public
class SushiLoad{
	String file = null;
	public SushiLoad(String file){
		this.file = file;
	}

	public String [] load(){
		String [] lines = null;
		try{

		FileInputStream fin = new FileInputStream(file);
		InputStreamReader is = new InputStreamReader(fin);
		BufferedReader reader = new BufferedReader(is);

		ArrayList <String> array = new ArrayList <String> ();
		while(true){
			try{
				String line = reader.readLine();
				if(line == null){
					break;
				}

				array.add(line);
			}
			catch(EOFException e){
				break;
			}
		}

		lines = new String [array.size()];

		for(int i=0;i<lines.length;i++){
			lines[i] = array.get(i);
		}

		reader.close();
		is.close();
		}
		catch(FileNotFoundException e){
			System.out.println(e.toString());
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
		
		return lines;
	}

	public static void main(String args[]){
		SushiLoad sloader = new SushiLoad(args[0]);

		String [] lines = sloader.load();
		for(String line : lines){
			System.out.println(line);
		}
	}
}
