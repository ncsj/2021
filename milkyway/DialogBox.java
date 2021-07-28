import  java.awt.Frame;
import  java.awt.Dialog;
import  java.awt.TextField;
import  java.awt.Button;
import  java.awt.Label;

import  java.awt.event.ActionListener;
import  java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**********************
  クラス:DialogBox
  ダイアログボックスを表示し、テキストを取得するためのクラス
  @Author Haruka Tokito
  *********************/

public class DialogBox extends Dialog{
	TextField     field = new TextField();
	Button        btn1  = new Button("ADD");
	Button        btn2  = new Button("DEL");
	boolean flag = false;


/**
  このクラスのコンストラクターで、ダイアログボックスを表示させる
  */
	public DialogBox(Frame parent){
		super(parent,"INPUT NAME",true);
		setLayout(null);

		// ラベルを実装
		{
			Label label = new Label("INPUT THE NAME OF POINT & COORDINATES");
			add(label);
			label.setBounds(40,60,400,20);
		}
		{
			Label label = new Label("ex.Kaze,123,456");
			add(label);
			label.setBounds(40,75,200,20);
		}
		
		{
			Label label = new Label("NAME : ");
			add(label);
			label.setBounds(40,100,50,20);
		}

		add(field);
		field.setBounds(100,100,150,20);

		// ADDボタンの実装。追加後、ウィンドウは閉じる。
		add(btn1);
		btn1.setBounds(150,130,80,20);
		btn1.addActionListener((ActionEvent e)->{ flag = true; close();});

		// DELボタンの実装。ダイアログボックスを閉じる。
		add(btn2);
		btn2.setBounds(230,130,80,20);
		btn2.addActionListener((ActionEvent e)->{ close();});

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				close();
			}
		});
	}

/**
  PosDialogに入力されたStringを取得するメソッド
  */
	public String getName(){
		String s = field.getText();
		return s;
	}

/**
  flagがtrueだった場合、flagを返すためのメソッド
  */
	public boolean isOK(){
		return flag;
	}

/**
  ウィンドウ(Frame)を閉じるためのメソッド
  */
	void close(){
		setVisible(false);
		dispose();
	}
}
