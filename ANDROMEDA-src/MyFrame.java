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
   クラスMyFrame
   画面上に山中湖とハイキングコースを表示するためのクラス
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

	/*このクラスのコンストラクターは
     ウィンドウ（Frame）を表示する。
     このアプリケーションのメインウィンドウ*/
	public MyFrame(){
		setBounds(0,0,1300,1000);
        setLayout(null);

		initGUI();

		/* ウィンドウアダプタのインスタンスを生成し、リスナーとして登録。
           画面を表示するメソッド
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

	/*地図の縮尺を設定するメソッド*/
	public void setScale(int n){
		this.n = n;
		repaint();
	}

	/*GUIを設定するメソッド
		他のクラスのウィンドウを生成するボタンの設定
	 */
	void initGUI(){

		/*詳細情報を表示するボタン*/
		add(btn1);
        btn1.setBounds(100,600,150,20);
        btn1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
				MainInfo maininfo = new MainInfo();
            }
        });

		/*文学の森の画像を表示するボタン*/
		add(btn2);
        btn2.setBounds(100,640,150,20);
        btn2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
               Culture culture = new Culture();
            }
        });

		/*カロリー計算をするボタン*/
		add(btn3);
        btn3.setBounds(100,680,150,20);
        btn3.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
				CalcKcal calckcal = new CalcKcal();
            }
        });
	}

	/*HikingDataから取り出したデータをlistに追加するメソッド*/
	public void add(HikingData data){
        list.add(data);
    }
	
	/*ファイルから読み込んだデータを適切な形にし、
	  polygonとarray1に追加するメソッド*/
	void addPoint(){
		System.out.println("----- CHECK START -----");
		double r = 0;
		double t = 0;
		String [] lines = hroad.load("yamanakako/yamanakakoright.csv",array1);
        for(String line :  lines){
            String [] cols = line.split(",");  // ,で分割する
            HikingData data = new HikingData(cols);
            add(data);

			/*最大値、最小値の定義*/
			HikingData max1 = max1();　
			HikingData min2 = min2();

			double d = data.x;
			double o = data.y;

			r = max1.x - d; //最大値からデータの値を引く（補数）
			t = o - min2.y; //データの値から最小値を引く

			System.out.println("R: " + r);
			System.out.println("T: " + t);

			/*画面上の適切な位置に表示されるように調整*/
			r = r * 150 + 300;
			t = t * 150 + 300;

			/*doubleからintへキャストする*/
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

	/*polygonを設定するメソッド
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

	/* ウィンドウを閉じるメソッド*/
	@Override
	public void close(){
		setVisible(false);
		dispose();
	}

	/*緯度の最大値を求めるメソッド*/
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

	/*経度の最大値を求めるメソッド*/
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

	/*緯度の最小値を求めるメソッド*/
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
	
	/*経度の最小値を求めるメソッド*/
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

	/* メインメソッド
     本クラスをインスタンス化し、起動するためのメソッド。
     @param args コマンドラインからのパラメータを受け取る文字列の配列
     */
	public static void main(String args[]){
		MyFrame mf = new MyFrame();
		int scale = Integer.valueOf(args[0]).intValue();
		mf.setScale(scale);
		mf.setVisible(true);
	}
}
