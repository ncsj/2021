import java.awt.Polygon;
import  java.io.FileInputStream;
import  java.io.InputStreamReader;
import  java.io.BufferedReader;
import  java.io.FileOutputStream;
import  java.io.EOFException;
import  java.io.IOException;
import  java.io.File;
import  java.io.FileNotFoundException;
import  java.util.ArrayList;

/*
   $B%/%i%9(BHikingRoad
   $B$3$N%/%i%9$O(BCSV$B%U%!%$%k$rFI$_9~$`$?$a$K:n@.$7$?$b$N(B
  @author Miyano Tanaka
  @since  1.0

 */


public
class HikingRoad{

	ArrayList <Polygon> array1 = new ArrayList <Polygon> ();
	ArrayList <Polygon> array2 = new ArrayList <Polygon> ();

	/*$B$3$N%/%i%9$N%3%s%9%H%i%/%?!<(B*/
	public HikingRoad(){
		
	}
	/*yamanakako$B$N%G%#%l%/%H%j$rFI$_9~$`%a%=%C%I(B*/
	void loadyamanakako(){
        System.out.println("loadyamanakako");
        loadDIR("yamanakako",array1);

    }

	/*HikingData$B$N%G%#%l%/%H%j$rFI$_9~$`%a%=%C%I(B*/
    void loadHikingData(){
        System.out.println("loadHikingData");
        loadDIR("HikingData",array2);
    }


	/*$B%G%#%l%/%H%j$H$=$NCf$N(BCSV$B%U%!%$%k$rFI$_9~$`%a%=%C%I(B
	 */
	void loadDIR(String path,ArrayList <Polygon> array){
        File dir = new File(path);

        if(dir.isDirectory()){
            String [] files = dir.list();
            for(String file : files){
                System.out.println(file);

                load(path + "/" + file,array);
            }
        }
    }


	/*$B%U%!%$%k$NCf$r0l9T$:$DFI$_9~$_!"(Blines$B$rJV$9%a%=%C%I(B
      FileInputStream$B$r;H$$FI$_9~$`(B
     */
	public String [] load(String file,ArrayList <Polygon> array){
		String [] lines = null;
		Polygon polygon  = new Polygon(); 

		try{

			FileInputStream fin = new FileInputStream(file);
			InputStreamReader is = new InputStreamReader(fin);
			BufferedReader reader = new BufferedReader(is);

			ArrayList <String> array10 = new ArrayList <String> ();

			while(true){
				try{
					String line = reader.readLine();
					if(line == null){
						break;
					}
					array10.add(line); //ArrayList$B$KDI2C$9$k(B
					
				}
				catch(EOFException e){
					break;
				}
			}

			lines = new String [array10.size()];

			for(int i=0;i<lines.length;i++){ //$B0l9T$:$DFI$_9~$`(B
				lines[i] = array10.get(i);
			}

			array.add(polygon);

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
		System.out.println("load1"+lines);
		
		return lines;
		
	}

}
