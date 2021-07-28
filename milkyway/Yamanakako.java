import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
  クラス：山中湖を表示させるためのクラス
  */

class Yamanakako extends Frame{
	String file	= "yamanakako.csv";
	ArrayList<FileData> array	= new ArrayList<FileData>();
	int mag	= 200;
	double latmin	= 3524.1047;
	double lonmin	= 13850.3526;
	double latmax	= 3525.98972;
	double lonmax	= 13854.48552;
	Polygon polygon	= new Polygon();

	public Yamanakako(){
		setBounds(0,0,800,1200);
		setLayout(null);
		addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent e){
                load();
				 for(FileData data : array){
				    double r    = data.lon;
					double t   = (r-lonmin)*mag+20;
				    double n    = data.lat;
					double m   = (latmax-n)*mag+150;

					int lat = (int)t;
				    int lon = (int)m;
					polygon.addPoint(lat,lon);
				}
				repaint();
            }
			public void windowClosing(WindowEvent e){
				close();
			}
		});

		setVisible(true);
	}

	public double latMin(){
		load();
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
		load();
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
	public double lonMax(){
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

	
	public void paint(Graphics g){
        g.setColor(Color.blue);
		g.fillPolygon(polygon);
	}

	void load(){
		FileManager manager	= new FileManager(file);
		String[] lines	= manager.load();
		for(String line :  lines){
            String[] cols   = line.split(",");
            FileData data   = new FileData(cols);
            array.add(data);
        }
	}

	public void close(){
		setVisible(false);
		dispose();
	}

	public static void main(String args[]){
		Yamanakako ym = new Yamanakako();
	}
}
