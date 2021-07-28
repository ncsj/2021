import  java.awt.*;
import  java.awt.event.*;
import	java.util.ArrayList;

/*****************
  クラス:MapPanel
  本クラスは、パネルを生成し地図のみを描画するメソッドです。

  @Author Kyoko Fukuda
  @since 1.0
*****************/
public class MapPanel extends Panel{
	public static final int offset = 250;
	
	Drawer  drawer = new Drawer(offset);

	String file = "yamanakako.csv";
	String file2 = "camp.csv";
	Color [] colors = {Color.orange,Color.green,Color.blue,Color.gray};

	String [] files = {"kokudo.csv","kendo.csv"};
    ArrayList<FileData> array   = new ArrayList<FileData>();
	ArrayList<FileData> array2   = new ArrayList<FileData>();
	ArrayList<FileData> array3   = new ArrayList<FileData>();
    int mag = 200;

    double latmin   = 3524.1047;
    double lonmin   = 13850.3526;
    double latmax   = 3525.98972;
    double lonmax   = 13854.48552;
    Polygon polygon = new Polygon();

	Polygon [] polygons = null;

	/*********
	  パネルに描画するためのポリゴンを準備する。
	  山中湖のデータが入ったCSVファイルを読み込み、ポリゴンに追加する。
	*********/
	public MapPanel(){
		System.out.println("MapPanel was created in MAP_PANELclass");
		polygons = new Polygon [files.length];

		load();
		for(FileData data : array){
			double r    = data.lon;
			double t   = (r-lonmin)*mag+offset;
            double n    = data.lat;
			double m   = (latmax-n)*mag+offset;

            int lat = (int)t;
			int lon = (int)m;
            polygon.addPoint(lat,lon);
		}
	}

	/************
	  本クラスのペイントメソッド。
	  上記のメソッドで追加されたポリゴン(山中湖)を描画する。
	  ポリゴンがnullであった場合、フレームの大きさを取得し
	  同じサイズの矩形を描く。
	************/
	public void paint(Graphics g){
		System.out.println("yamanakako was painted");
		if(polygon!=null){
			g.setColor(Color.blue);
			g.fillPolygon(polygon);
		 }
		else{
            Rectangle rect = this.getBounds();
            g.setColor(Color.pink);
            g.fillRect(0,0,rect.width,rect.height);
        }

		paint2(g);
		paint3(g);

		drawer.paint(g);	//走行記録を描くDrawerクラス内のペイントメソッドを呼び出す。
    }

	/************
	  FileManagerのloadメソッドを利用し、指定のCSVファイルを取り出すメソッド。
	  1行ごとに取り出したCSVファイルの中身を","で区切り分割し、ArrayListに追加する。
	************/
    void load(){
        FileManager manager = new FileManager(file);
        String[] lines  = manager.load();
        for(String line :  lines){
            String[] cols   = line.split(",");
            FileData data   = new FileData(cols);
            array.add(data);
        }
    }

	/***********
	  店名,x座標,y座標の入ったCSVファイルを扱うload2メソッド。
	***********/
	void load2(){
        FileManager manager = new FileManager(file2);
        String[] lines  = manager.load();

        for(String line : lines){
            String[] cols   = line.split(",");
            FileData data   = new FileData(cols);
            array2.add(data);
        }
    }

	/*************
	  地図にポイントを描画するペイント2メソッド。
	*************/
	public void paint2(Graphics g){
		int w   = 15;
		int h   = 15;

		Color color = Color.orange;

		if(file2.equals("wine.csv")){
            color   = Color.magenta;
        }
        else if(file2.equals("camp.csv")){
            color   = Color.green;
        }

        load2();
        for(FileData data : array2){
            double r    = data.lon;
            double t   = (r-lonmin)*mag+offset;
            double n    = data.lat;
            double m   = (latmax-n)*mag+offset;

            int lat = (int)t;
            int lon = (int)m;

            g.setColor(Color.black);
            g.fillOval(lat-3,lon-3,w+6,h+6);

            g.setColor(color);
            g.fillOval(lat,lon,w,h);
        }
	}

	void load3(String file){
        FileManager manager = new FileManager(file);
        if(file != null){
			String[] lines  = manager.load();
			if(lines != null){
				for(String line :  lines){
					String[] cols   = line.split(",");
					FileData data   = new FileData(cols);
					array3.add(data);
				}
			}
		}
    }

	/**************
	  道路の複数のファイルが格納された配列からひとつを取り出し、１つずつ読み込むload31メソッド。
	**************/
	void load31(){
		polygons = new Polygon [files.length];
		for(int i=0;i<files.length;i++){
			load3(files[i]);
		}

		int i = 0;
		for(String f : files){
			System.out.println(f);
            Polygon polygon = new Polygon();
            for(FileData data : array3){
				double r    = data.lon;
                double t   = (r-lonmin)*mag+offset;
                double n    = data.lat;
                double m   = (latmax-n)*mag+offset;

                int lat = (int)t;
                int lon = (int)m;
                polygon.addPoint(lat,lon);
            }
            polygons[i] = polygon;
			i++;
        }
	}
	
	/**************
	  複数の周辺道路を描くペイントメソッド。
	**************/
	public void paint3(Graphics g){
		load31();
		for(int i=0;i<polygons.length;i++){
            g.setColor(colors[i]);
			try{
				if(polygons[i].xpoints != null && polygons[i].ypoints != null){
					g.fillPolygon(polygons[i]);
				}
			}
			catch(Exception e){
			}
        }
	}
}
