import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


/**********
  $B%/%i%9!'(BDrawer2
  $B$3$N%/%i%9$OJ#?t$N(BCSV$B%U%!%$%k$r%m!<%I$7!"0l$D$N2hLL$KIA2h$9$k$?$a$K:n@.$5$l$?$b$N$G$9!#(B

  @Author	Ayana Tanaka
  **********/


class Drawer2 extends Frame implements Closable{
	String [] files	= {"yamanakako.csv","kokudo.csv","kendo.csv","kawaguchikoLATLON.csv","saikoLATLON.csv"};
	ArrayList<FileData> array	= new ArrayList<FileData>();
	int mag	= 80;   // $BG\N((B


	double latmin	= 3523.0736;
	double lonmin	= 13839.08076;
	double latmax	= 3531.75272;
	double lonmax	= 13859.58924;

	Polygon [] polygons	= null;


/*****
  $BK\%/%i%9$N%3%s%9%H%i%/%?!<(B
  $B2hLL$N=PNO$H%]%j%4%s$NIA<L0LCV$r@_Dj$9$k!#(B
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
  $B%m!<%I$5$l$?(BCSV$B%U%!%$%k$N0^EY7PEY$N:G>.CM:GBgCM$r5a$a$k%a%=%C%I(B
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
  $B%]%j%4%s$N?'$r@_Dj$9$k%a%=%C%I(B
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
  CSV$B%U%!%$%k$r%m!<%I$9$k$?$a$N%a%=%C%I(B
  $B!V(B,$B!W$G%G!<%?$r6h@Z$jG[Ns$KDI2C$9$k!#(B
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

