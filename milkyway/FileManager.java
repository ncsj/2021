import  java.io.*;
import  java.awt.*;
import  java.util.ArrayList;

/********************
  $B%/%i%9!'(BFileManager
  $B%U%!%$%k$NFI$_9~$_!"J]B8$r9T$&%/%i%9$G$9!#(B

  @Author Haruka Tokito
  *******************/

public class FileManager{
	String file = null;

	// String$B$r(BArrayList$B$K$9$k(B
	ArrayList <String> array = new ArrayList <String> ();

	/**
	  $BK\%/%i%9$N%3%s%s%9%H%i%/%?!<(B
	  $B%U%!%$%k$NDj5A$r9T$J$C$F$$$k(B
	  @param String file
	  */
	public FileManager(String file){
		this.file = file;
	}


	/**
	  $B%U%!%$%k$rFI$_9~$`$?$a$N%a%=%C%I(B
	  */
	public String [] load(){
		String [] lines = null;
		try{
			FileInputStream fin = new FileInputStream(file);	//$B;XDj$7$?%U%!%$%k$rFI$_9~$`(B
			InputStreamReader is = new InputStreamReader(fin);	//$BFI$_9~$s$@%U%!%$%k$NCf?H$r<h$j=P$9(B 
			BufferedReader reader = new BufferedReader(is);		//$B<h$j=P$7$?%U%!%$%k$r0l9T$:$DFI$_9~$`(B

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
				lines[i] = array.get(i);		// $B>pJs$r0l$D$:$D<h$j=P$9(B
			}
			reader.close();
			is.close();
			fin.close();
		}
		catch(FileNotFoundException e){
			System.out.println(e.toString());
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
		return lines;
	}



	/**
	  ArrayList$B$K(BString$B$rDI2C$9$k$?$a$N%a%=%C%I(B
	  @param String name
	  */

	public void addData(String name){
		if(name != null){
			array.add(name);
		}
	}


	
	/**
	  ArrayList$B$NMWAG$r:o=|$9$k%a%=%C%I(B
	  @param int index
	  */
	public void removeData(int index){
		if(array.get(index) != null){
				array.remove(index);
				System.out.println("ok");
			}	
	}



	/**
      $BJ]B8$r$9$k$?$a$K(BFileOutputStream,PrintStream$B$r;H$C$F=q$-=P$9(B

	  @param String file
	  */
	public void save(String file){
		if(file != null && array != null){
			try{
				FileOutputStream fout = new FileOutputStream(file);
				PrintStream ps = new PrintStream(fout);

				for(int i=0;i<array.size();i++){
					ps.print(array.get(i));		// $B=q$-9~$_(B
					ps.print("\n");				//$B!!2~9T(B
				}
				ps.close();
				fout.close();
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
	}


	public static void main(String args[]){
		FileManager manager = new FileManager(args[0]);

		String [] lines = manager.load();
		for(String line : lines){
			System.out.println(line);
		}
	}
}
