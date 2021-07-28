import  java.awt.Frame;
import  java.awt.List;
import	java.awt.Panel;
import  java.awt.Graphics;
import  java.awt.Color;
import  java.awt.event.WindowListener;
import  java.awt.event.WindowAdapter;
import  java.awt.event.WindowEvent;
import	java.awt.event.ItemListener;
import	java.awt.event.ItemEvent;
import	java.util.ArrayList;
import	java.lang.reflect.Constructor;

/**********
  クラス名:UserWindow
  本クラスは、一般利用者向けの画面を設定するクラスです。

  @Author Kyoko Fukuda
  @since 1.0
**********/

class UserWindow extends Frame implements Closable{
	String file	= null;
	//String pname	= "UserPanel";

	/******************
	  SelectWindowから起動される、ユーザー向けのウィンドウ
	  地図を描くMapPanelおよびほうとう屋、キャンプ場、レストランの3つのポイントを描くUserPanelを
	  起動するための中間地点となるウィンドウ
	  @param	file
				Panel起動時に開くファイルを、SelectWindowからパラメーターで指定する
	******************/
	public UserWindow(String file){
		this.file	= file;

		// MapPanel panel	= create(pname);
		MapPanel panel	= new MapPanel();
		if(panel!=null){
			// this.panel	= panel;
			add("Center",panel);
		
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					close();
				}
			});
			setBounds(0,0,1200,800);
			// setLayout(null);
			setVisible(true);
		}

		else{
			System.out.println("Panel is nothing: "+pname);
		}
	}

	/************
	  マップパネルを生成するメソッド。
	  パラメーターとして渡された特定のパネルの名前がある場合、それを生成する。
	  @return MapPanel
	************/
	public MapPanel create(String pname){
        MapPanel panel = null;
        try{
            Class c = Class.forName(pname);
            Constructor [] cons = c.getConstructors();

            Object [] args = null;
            panel = (MapPanel)cons[0].newInstance(args);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return panel;
    }

	public void close(){
		setVisible(false);
		dispose();
	}
	/***********
	  本クラスのメインメソッド
	  本クラスをインスタンス化し、起動するメソッドとなる
	  @param	args
				コマンドラインからパラメーターを受け取る文字列の配列
    ***********/
	public static void main(String args[]){
		UserWindow user	= new UserWindow(args[0]);
	}
}
