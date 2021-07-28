import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.List;
import java.awt.Label;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Polygon;
import java.awt.event.WindowListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.io.File;

/*
   $B%/%i%9(BMyFrame
   $B2hLL>e$K;3Cf8P$H%O%$%-%s%0%3!<%9$rI=<($9$k$?$a$N%/%i%9(B
  @author Miyano Tanaka
  @since  1.0

 */

public class MyFrame extends Frame implements Closable{
	ArrayList <HikingData> list = new ArrayList <HikingData> ();
	ArrayList <Polygon> array1 = new ArrayList <Polygon> ();
    ArrayList <Polygon> array2 = new ArrayList <Polygon> ();

	Polygon polygon = new Polygon();

	HikingData data = new HikingData();
	HikingRoad hroad = new HikingRoad();

	Button btn1 = new Button("Information");
    Button btn2 = new Button("Culture's Picture");
    Button btn3 = new Button("Calc Kcal");

	int n = 1;

	/*$B$3$N%/%i%9$N%3%s%9%H%i%/%?!<$O(B
     $B%&%#%s%I%&!J(BFrame$B!K$rI=<($9$k!#(B
     $B$3$N%"%W%j%1!<%7%g%s$N%a%$%s%&%#%s%I%&(B*/
	public MyFrame(){
		setBounds(0,0,1300,1000);
        setLayout(null);

		initGUI();

		/* $B%&%#%s%I%&%"%@%W%?$N%$%s%9%?%s%9$r@8@.$7!"%j%9%J!<$H$7$FEPO?!#(B
           $B2hLL$rI=<($9$k%a%=%C%I(B
         */
		addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent e){
				addPoint();
				hroad.loadyamanakako();
				hroad.loadHikingData();		
            }
        });

		addWindowListener(new Closer(this));
	}

	/*$BCO?^$N=L<\$r@_Dj$9$k%a%=%C%I(B*/
	public void setScale(int n){
		this.n = n;
		repaint();
	}

	/*GUI$B$r@_Dj$9$k%a%=%C%I(B
		$BB>$N%/%i%9$N%&%#%s%I%&$r@8@.$9$k%\%?%s$N@_Dj(B
	 */
	void initGUI(){

		/*$B>\:Y>pJs$rI=<($9$k%\%?%s(B*/
		add(btn1);
        btn1.setBounds(100,600,150,20);
        btn1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
				MainInfo maininfo = new MainInfo();
            }
        });

		/*$BJ83X$N?9$N2hA|$rI=<($9$k%\%?%s(B*/
		add(btn2);
        btn2.setBounds(100,640,150,20);
        btn2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
               Culture culture = new Culture();
            }
        });

		/*$B%+%m%j!<7W;;$r$9$k%\%?%s(B*/
		add(btn3);
        btn3.setBounds(100,680,150,20);
        btn3.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
				CalcKcal calckcal = new CalcKcal();
            }
        });
	}

	/*HikingData$B$+$i<h$j=P$7$?%G!<%?$r(Blist$B$KDI2C$9$k%a%=%C%I(B*/
	public void add(HikingData data){
        list.add(data);
    }
	
	/*$B%U%!%$%k$+$iFI$_9~$s$@%G!<%?$rE,@Z$J7A$K$7!"(B
	  polygon$B$H(Barray1$B$KDI2C$9$k%a%=%C%I(B*/
	void addPoint(){
		System.out.println("----- CHECK START -----");
		double r = 0;
		double t = 0;
		String [] lines = hroad.load("yamanakako/yamanakakoright.csv",array1);
        for(String line :  lines){
            String [] cols = line.split(",");  // ,$B$GJ,3d$9$k(B
            HikingData data = new HikingData(cols);
            add(data);

			/*$B:GBgCM!":G>.CM$NDj5A(B*/
			HikingData max1 = max1();$B!!(B
			HikingData min2 = min2();

			double d = data.x;
			double o = data.y;

			r = max1.x - d; //$B:GBgCM$+$i%G!<%?$NCM$r0z$/!JJd?t!K(B
			t = o - min2.y; //$B%G!<%?$NCM$+$i:G>.CM$r0z$/(B

			System.out.println("R: " + r);
			System.out.println("T: " + t);

			/*$B2hLL>e$NE,@Z$J0LCV$KI=<($5$l$k$h$&$KD4@0(B*/
			r = r * 150 + 300;
			t = t * 150 + 300;

			/*double$B$+$i(Bint$B$X%-%c%9%H$9$k(B*/
			int x = (int)r;
			int y = (int)t;

			System.out.println("MAX X: " + max1.x);
			System.out.println("MIN Y: " + min2.y);
			System.out.println("X: " + x);
			System.out.println("Y: " + y);

			polygon.addPoint(y,x);
			
			array1.add(polygon);

		}
		System.out.println("----- CHECK END -----");
    } 

	/*polygon$B$r@_Dj$9$k%a%=%C%I(B
		@param Graphics g  
	 */
	public void paint(Graphics g){
		/**
		System.out.println("----- CHECK START2 -----");	

		double r = 0;
        double t = 0;
        String [] lines = hroad.load("HikingData/HikingCourse1.csv",array2);
        int count = 0;**/
		try{
			/**for(String line :  lines){
				String [] cols = line.split(",");
				HikingData data = new HikingData(cols);
				add(data);

				HikingData max1 = max1();
				HikingData min2 = min2();

				double d = data.x;
				double o = data.y;

				r = max1.x - d;
				t = o - min2.y;

				System.out.println("R: " + r);
				System.out.println("T: " + t);

				int offset = 300;
				int scale = 150 * n;

				r = r * scale + offset;
				t = t * scale + offset;

				int x = (int)r;
				int y = (int)t;

				System.out.println("MAX X: " + max1.x);
				System.out.println("MIN Y: " + min2.y);
				System.out.println("X: " + x);

				array2.add(polygon);**/
				
				g.setColor(Color.green);
				for(Polygon pgon : array1){
					g.fillPolygon(pgon);
				}

				g.setColor(Color.blue);
				for(Polygon pgon : array2){
					g.fillPolygon(pgon);
				}

				//g.setColor(Color.blue);
				//g.fillPolygon(polygon);
		
				//g.fillOval(y-3,x-3,3,3);
				
			//	count++;
		//	}
		}
		catch(Exception e){
			//System.out.println("count : " + count);
			System.out.println(e.toString());
		}
		System.out.println("----- CHECK END2 -----");
	}

	/* $B%&%#%s%I%&$rJD$8$k%a%=%C%I(B*/
	@Override
	public void close(){
		setVisible(false);
		dispose();
	}

	/*$B0^EY$N:GBgCM$r5a$a$k%a%=%C%I(B*/
	public HikingData max1(){
        HikingData data = null;

        for(HikingData d : list){
            if(data == null){
                data = d;
            }
            else{
                if(d.x > data.x){
                    data = d;
                }
            }
        }
        return data;
    }

	/*$B7PEY$N:GBgCM$r5a$a$k%a%=%C%I(B*/
	public HikingData max2(){
        HikingData data = null;

        for(HikingData d : list){
            if(data == null){
                data = d;
            }
            else{
                if(d.y > data.y){
                    data = d;
                }
            }
        }

        return data;
    }

	/*$B0^EY$N:G>.CM$r5a$a$k%a%=%C%I(B*/
	public HikingData min1(){
        HikingData data = null;
		try{
			for(HikingData d : list){
				if(data == null){
					data = d;
					System.out.println("list:"+list.size());
				}

				else{
					if(d.x < data.x){
						data = d;
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("min1 ERROR");
			System.out.println(e.toString());

		}	
       
        return data;
    }
	
	/*$B7PEY$N:G>.CM$r5a$a$k%a%=%C%I(B*/
	public HikingData min2(){
        HikingData data = null;

        for(HikingData d : list){
            if(data == null){
                data = d;
            }
            else{
                if(d.y < data.y){
                    data = d;
                }
            }
        }
		

        return data;
    }

	/* $B%a%$%s%a%=%C%I(B
     $BK\%/%i%9$r%$%s%9%?%s%92=$7!"5/F0$9$k$?$a$N%a%=%C%I!#(B
     @param args $B%3%^%s%I%i%$%s$+$i$N%Q%i%a!<%?$r<u$1<h$kJ8;zNs$NG[Ns(B
     */
	public static void main(String args[]){
		MyFrame mf = new MyFrame();
		int scale = Integer.valueOf(args[0]).intValue();
		mf.setScale(scale);
		mf.setVisible(true);
	}
}
