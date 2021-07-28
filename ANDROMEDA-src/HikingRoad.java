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
   クラスHikingRoad
   このクラスはCSVファイルを読み込むために作成したもの
  @author Miyano Tanaka
  @since  1.0

 */


public
class HikingRoad{

	ArrayList <Polygon> array1 = new ArrayList <Polygon> ();
	ArrayList <Polygon> array2 = new ArrayList <Polygon> ();

	/*このクラスのコンストラクター*/
	public HikingRoad(){
		
	}
	/*yamanakakoのディレクトリを読み込むメソッド*/
	void loadyamanakako(){
        System.out.println("loadyamanakako");
        loadDIR("yamanakako",array1);

    }

	/*HikingDataのディレクトリを読み込むメソッド*/
    void loadHikingData(){
        System.out.println("loadHikingData");
        loadDIR("HikingData",array2);
    }


	/*ディレクトリとその中のCSVファイルを読み込むメソッド
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


	/*ファイルの中を一行ずつ読み込み、linesを返すメソッド
      FileInputStreamを使い読み込む
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
					array10.add(line); //ArrayListに追加する
					
				}
				catch(EOFException e){
					break;
				}
			}

			lines = new String [array10.size()];

			for(int i=0;i<lines.length;i++){ //一行ずつ読み込む
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
