import	java.awt.Frame;
import	java.awt.Label;
import	java.awt.Button;
import	java.awt.TextField;
import	java.awt.event.WindowListener;
import	java.awt.event.WindowAdapter;
import	java.awt.event.WindowEvent;
import  java.awt.event.ActionListener;
import  java.awt.event.ActionEvent;
import	java.awt.event.KeyAdapter;
import	java.awt.event.KeyEvent;
import	java.awt.Color;
import	java.util.ArrayList;

/**
	このクラスはカロリー計算を行うクラス

	@author Takuya Nakaigawa
	@since	1.0
*/

class CalcKcal extends Frame implements Closable{
	ArrayList <CalcValue> value = new ArrayList <CalcValue> ();

	TextField field1 = new TextField();
	TextField field2 = new TextField();
	TextField field3 = new TextField();

	Button btn1 = new Button("計算");
	Button btn2 = new Button("クリア");


	CalcEngine engine = null;

	public CalcKcal(){
		setBounds(0,0,600,400);
		setLayout(null);

		initGUI();

		addWindowListener(new WindowAdapter(){
		public void	windowOpened(WindowEvent e){
			}
		public void windowClosing(WindowEvent e){
				close();
			}	
		});

		setVisible(true);
	}

	/**
	  ラベル、ボタン、テキストフィールド
	  大きさ、文字列、ボタンの機能を設定しているメソッド
	  */
	void initGUI(){
		{
			Label label1 = new Label("時間");
			add(label1);
			label1.setBounds(130,150,40,20);
			Label label2 = new Label("分");
			add(label2);
			label2.setBounds(350,150,40,20);

			Label label3 = new Label("体重");
			add(label3);
			label3.setBounds(130,200,40,20);
			Label label4 = new Label("kg");
			add(label4);
			label4.setBounds(350,200,40,20);

			Label label5 = new Label("消費カロリー");
			add(label5);
			label5.setBounds(130,250,120,20);
			Label label6 = new Label("kcal");
			add(label6);
			label6.setBounds(350,250,40,20);
		}
		{
			add(field1);
			field1.setBounds(250,150,90,20);
			field1.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					int code = e.getKeyCode();
					switch(code){
						case KeyEvent.VK_ENTER:
							field2.requestFocus();
							break;
					}
				}
			});
			
			add(field2);
			field2.setBounds(250,200,90,20);
			field2.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					int code = e.getKeyCode();
					switch(code){
						case KeyEvent.VK_ENTER:
							calc();
							break;
					}
				}
			});

			add(field3);
			field3.setBounds(250,250,90,20);
			
		}
		{
			add(btn1);
			btn1.setBounds(50,320,80,30);
			btn1.addActionListener((ActionEvent e)->{ calc(); });

			add(btn2);
			btn2.setBounds(130,320,80,30);
			btn2.addActionListener((ActionEvent e)->{ clear(); });
		}
		
	}


	/**
	  カロリー計算をしているメソッド
	  結果に応じてコメントをラベルに出力
	  */
	Label label7 = new Label();
	 void calc(){
		add(label7);
		label7.setBounds(240,320,140,30);
		label7.setBackground(Color.white);

		try{
			double n = 0.155;
			String s1 = field1.getText();
			String s2 = field2.getText();

			int x = Integer.valueOf(s1).intValue();
			int y = Integer.valueOf(s2).intValue();

			double xx = (double)x;
			double yy = (double)y;

			double z = x * y * n;
			
			//double z = engine.calc(xx,yy);
			//String s = String.format("%d",cv.value);

			String s3 = String.valueOf(z).toString();
			field3.setText(s3);

			if(z < 1000){
				label7.setText("頑張りましょう!");
			}
			else if(z < 3000){
				label7.setText("ハンバーガー10個分");
			}
			else if(z < 5000){
				label7.setText("ラーメン10杯分");
			}
			else{
				label7.setText("素晴らしい!");
			}

		}
		catch(Exception e){
			System.out.println("Error not calc");
		}
	}

	/**
	  クリア機能を持つメソッド
	  フィールド、ラベルの表示を消去
	  フィールド１にフォーカスを戻す
	  */
	void clear(){
		field1.setText("");
		field2.setText("");
		field3.setText("");
		label7.setText("");

		field1.requestFocus();
	}

	/**
	  ウィンドを閉じる機能
	  */
	@Override
	public void close(){
		setVisible(false);
		dispose();
	}
	
	public static void main(String args[]){
		new CalcKcal();
	}
}
