import  java.awt.*;
import  java.awt.event.*;
import	java.util.ArrayList;

/*****************
  $B%/%i%9(B:MapPanel
  $BK\%/%i%9$O!"%Q%M%k$r@8@.$7CO?^$N$_$rIA2h$9$k%a%=%C%I$G$9!#(B

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
	  $B%Q%M%k$KIA2h$9$k$?$a$N%]%j%4%s$r=`Hw$9$k!#(B
	  $B;3Cf8P$N%G!<%?$,F~$C$?(BCSV$B%U%!%$%k$rFI$_9~$_!"%]%j%4%s$KDI2C$9$k!#(B
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
	  $BK\%/%i%9$N%Z%$%s%H%a%=%C%I!#(B
	  $B>e5-$N%a%=%C%I$GDI2C$5$l$?%]%j%4%s(B($B;3Cf8P(B)$B$rIA2h$9$k!#(B
	  $B%]%j%4%s$,(Bnull$B$G$"$C$?>l9g!"%U%l!<%`$NBg$-$5$r<hF@$7(B
	  $BF1$8%5%$%:$N6k7A$rIA$/!#(B
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

		drawer.paint(g);	//$BAv9T5-O?$rIA$/(BDrawer$B%/%i%9Fb$N%Z%$%s%H%a%=%C%I$r8F$S=P$9!#(B
    }

	/************
	  FileManager$B$N(Bload$B%a%=%C%I$rMxMQ$7!";XDj$N(BCSV$B%U%!%$%k$r<h$j=P$9%a%=%C%I!#(B
	  1$B9T$4$H$K<h$j=P$7$?(BCSV$B%U%!%$%k$NCf?H$r(B","$B$G6h@Z$jJ,3d$7!"(BArrayList$B$KDI2C$9$k!#(B
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
	  $BE9L>(B,x$B:BI8(B,y$B:BI8$NF~$C$?(BCSV$B%U%!%$%k$r07$&(Bload2$B%a%=%C%I!#(B
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
	  $BCO?^$K%]%$%s%H$rIA2h$9$k%Z%$%s%H(B2$B%a%=%C%I!#(B
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
	  $BF;O)$NJ#?t$N%U%!%$%k$,3JG<$5$l$?G[Ns$+$i$R$H$D$r<h$j=P$7!"#1$D$:$DFI$_9~$`(Bload31$B%a%=%C%I!#(B
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
	  $BJ#?t$N<~JUF;O)$rIA$/%Z%$%s%H%a%=%C%I!#(B
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
