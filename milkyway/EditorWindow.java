import  java.awt.Frame;
import  java.awt.Color;
import  java.awt.Panel;
import  java.awt.List;
import  java.awt.Button;
import  java.awt.TextField;
import  java.awt.Dialog;
import  java.awt.*;

import  java.io.File;
import  java.util.ArrayList;

import  java.awt.event.ActionListener;
import  java.awt.event.ActionEvent;
import  java.awt.event.WindowListener;
import  java.awt.event.WindowAdapter;
import  java.awt.event.WindowEvent;
import  java.awt.event.KeyListener;
import  java.awt.event.KeyAdapter;
import  java.awt.event.KeyEvent;
import  java.awt.event.MouseListener;
import  java.awt.event.MouseAdapter;
import  java.awt.event.MouseEvent;

/********************
  クラス：EditorWindow
  このクラスは管理者用のウィンドウを実現するクラスです。

  @Author Haruka Tokito
 ********************/

public class EditorWindow extends Frame{
	List list = new List();
	String file = null;
	FileManager manager = new FileManager(file);


	// 山中湖、国道、県道、走行軌跡を呼び出す
	Yamanakako ym = new Yamanakako();
	Kokudou    kd = new Kokudou();
	Kendo      kn = new Kendo();
	Bike       bi = new Bike();


	/**
	  このクラスのコンストラクター
	  ウィンドウを表示する
	  このアプリケーションの編集者用のウィンドウ
	  @param String file
	  */
	public EditorWindow(String file){
		this.file	= file;
		setBounds(0,0,1200,900);			// ウィンドウのサイズを設定
		setLayout(null);					// ドット単位で表示できるようにする

		add(list);							// リストを実装
		list.setBounds(900,80,200,300);



		// ボタンを実装し、ラムダ式でdel()を呼び出す
		{
			Button btn = new Button("DEL");
			add(btn);
			btn.setBounds(950,450,60,30);
			btn.addActionListener((ActionEvent e)->{del();});
		}

		// ウィンドウアダプタのインスタンスを生成し、リスナーとして登録
		
		addWindowListener(new WindowAdapter(){
				public void windowOpened(WindowEvent e){
					load(file);
				}
				public void windowClosing(WindowEvent e){
					save(file);	
					close();
				}
		});

		// クリックすると、x座標、y座標を取得する
		addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					int x = e.getX();
					int y = e.getY();

					System.out.println(x + ":" + y);
					addPoint(x,y);
				}
		});
		setVisible(true);
	}

	/**
	  山中湖、国道、県道、走行軌跡を描かせるためのメソッド

	  @param Graphics g
	  */
	public void paint(Graphics g){
		ym.paint(g);
		kd.paint(g);
		kn.paint(g);
		bi.paint(g);
	}
	

	/**
	  PosDialogを呼び出し、店名、座標を登録するメソッド
	  */
	void addPoint(int x,int y){
		DialogBox dlb = new DialogBox(this);
		dlb.setBounds(x,y,350,200);
		dlb.setVisible(true);

		if(dlb.isOK()){
			String name = dlb.getName();	// ダイアログボックスに入力されたStringを取得
			addList(name);					// リストに表示
		}
	}


	/**
	  リストに登録した店名を表示、ファイルに書込みを行うメソッド
	  @param String name
	*/	  
	public void addList(String name){
		list.add(name);						 // リストに店名、座標を追加
		manager.addData(name);				 // ファイルに書込み
	}


	/**
	　あらかじめ登録されているものを読み込むメソッド
	  @param String filename
	 */
	
	public void load(String filename){
		try{
			FileManager manager = new FileManager(filename);
			String [] lines = manager.load();
				for(String line : lines){
					addList(line);		// 読み込んだものをリストに表示
				}
			}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}

    
	/**
	 選択されている項目を取得するためのメソッド
	*/
	void getList(){
		String line = list.getSelectedItem();
	}



	/**
	  登録したものを削除するメソッド
	  */
	public void del(){
		int index = list.getSelectedIndex();		// 選択されている項目のindexを取得
		if(index > -1){
			list.remove(index);						// リストから削除
			try{
				manager.removeData(index);			// ファイルから削除
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
		}
		else{
			System.out.println("error");
		}
	}



	/**
	  登録したものを保存するメソッド
	  @param String filename
	  */
	public void save(String filename){
		try{
			manager.save(filename);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}


	/**
	  ウィンドウを閉じるメソッド
	  */
	public void close(){
		setVisible(false);
		dispose();
	}

	/**
	  メインメソッド
	  本クラスをインスタンス化し、起動するためのメソッド
	  */
	public static void main(String args[]){
		new EditorWindow(args[0]); 
	}
}
