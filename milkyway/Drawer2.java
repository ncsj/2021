import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


/**********
  クラス：Drawer2
  このクラスは複数のCSVファイルをロードし、一つの画面に描画するために作成されたものです。

  @Author	Ayana Tanaka
  **********/


class Drawer2 extends Frame implements Closable{
	String [] files	= {"yamanakako.csv","kokudo.csv","kendo.csv","kawaguchikoLATLON.csv","saikoLATLON.csv"};
	ArrayList<FileData> array	= new ArrayList<FileData>();
	int mag	= 80;   // 倍率


	double latmin	= 3523.0736;
	double lonmin	= 13839.08076;
	double latmax	= 3531.75272;
	double lonmax	= 13859.58924;

	Polygon [] polygons	= null;


/*****
  本クラスのコンストラクター
  画面の出力とポリゴンの描写位置を設定する。
 *****/
	public Drawer2(){
		setBounds(0,0,1600,2000);
		setLayout(null);

		polygons = new Polygon [files.length];

		addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent e){
				for(int i=0;i<files.length;i++){
					load(files[i]);
					Polygon polygon	= new Polygon();
					for(FileData data : array){
						double r    = data.lon;
						double t   = (r-lonmin)*mag+50;
						double n    = data.lat;
						double m   = (latmax-n)*mag+100;

						int lat = (int)t;
						int lon = (int)m;
						polygon.addPoint(lat,lon);
					}
					polygons[i]	= polygon;
				}
				repaint();
			}
			public void windowClosing(WindowEvent e){
                close();
            }
		});
		setVisible(true);
	}

	public void close(){
		setVisible(false);
		dispose();
	}

/*****
  ロードされたCSVファイルの緯度経度の最小値最大値を求めるメソッド
 *****/
	public double latMin(){
		//load();
		FileData d	= null;
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
    public double lonMin(){
		//load();
		FileData d	= null;
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
	public double latMax(){
        //load();
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
	public double lonMax(){
        //load();
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


/*****
  ポリゴンの色を設定するメソッド
 *****/
	Color [] color = {Color.cyan,Color.orange,Color.green,Color.blue,Color.gray};
	public void paint(Graphics g){
        for(int i=0;i<polygons.length;i++){
            Polygon polygon  = new Polygon();
            g.setColor(color[i]);
			g.fillPolygon(polygons[i]);
		}

		
		//g.setColor(Color.cyan);
		//g.fillPolygon(polygons);
	}

/*****
  CSVファイルをロードするためのメソッド
  「,」でデータを区切り配列に追加する。
 *****/
	void load(String file){
		FileManager manager	= new FileManager(file);
		String[] lines	= manager.load();
		for(String line :  lines){
            String[] cols   = line.split(",");
            FileData data   = new FileData(cols);
            array.add(data);
        }
	}

	public static void main(String args[]){
		Drawer2 drawer	= new Drawer2();
	}
}

