import	java.awt.*;
import	java.util.ArrayList;
import	java.io.*;

/**********
  クラス：LatLonCalc
  このクラスはCSVファイル内の緯度と経度の最大最小値を求めるために作成されたものです。

  @Author	Ayana Tanaka
  **********/

public class LatLonCalc{
	// String [] files = {"yamanakako.csv","kokudo.csv","kendo.csv"};
	String file = "yamanakako.csv";
    ArrayList<FileData> array   = new ArrayList<FileData>();

	double latmin	= latMin();
	double lonmin	= lonMin();
	double latmax	= latMax();
	double lonmax	= lonMax();

/*******
  このクラスのコンストラクター 
  計算の結果を出力する。
 *******/
	public LatLonCalc(){
		System.out.println("latmin : " + latmin);
		System.out.println("lonmin : " + lonmin);
		System.out.println("latmax : " + latmax);
		System.out.println("lonmax : " + lonmax);
	}
	
/*******
  緯度の最小値を求めるメソッド
 *******/
	public double latMin(){
        load();
        FileData d  = null;
		for(FileData data : array){
			if(d==null){
		        d   = data;	
			}
	       else{
				if(data.lat < d.lat){
					d.lat   = data.lat;
				}
			}
		}
        return d.lat;
    }

/*******
  経度の最小値を求めるメソッド
 *******/
    public double lonMin(){
        load();
        FileData d  = null;
        for(FileData data : array){
            if(d==null){
                d   = data;
            }
            else{
                if(data.lon < d.lon){
                    d.lon   = data.lon;
                }
            }
        }
        return d.lon;
	}

/*******
  緯度の最大値を求めるメソッド
 *******/
	public double latMax(){
          load();
          FileData d  = null;
          for(FileData data : array){
                if(d==null){
                    d   = data;
                }
                else{
                    if(data.lat > d.lat){
                        d.lat   = data.lat;
                    }
                }
            }
        return d.lat;
     }

/*******
  経度の最大値を求めるメソッド
 *******/
    public double lonMax(){
        load();
        FileData d  = null;
        for(FileData data : array){
            if(d==null){
                d   = data;
            }
            else{
                if(data.lon > d.lon){
                    d.lon   = data.lon;
                }
            }
        }
        return d.lon;
    }

/********
  Filemanagerをインスタンス化し、ファイルを読み込むメソッド。
  line.splitを用いて「,」でファイル内の情報を区切るようにしている。	
 *******/
	void load(){
        FileManager manager = new FileManager(file);
        String[] lines  = manager.load();
        for(String line :  lines){
            String[] cols   = line.split(",");
            FileData data   = new FileData(cols);
            array.add(data);
        }
    }

	public static void main(String args[]){
		LatLonCalc latloncalc = new LatLonCalc();
	}
}
