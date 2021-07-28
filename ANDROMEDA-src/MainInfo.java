import	java.awt.Frame;
import	java.awt.Label;
import	java.awt.TextField;
import	java.awt.List;
import	java.awt.Button;
import	java.awt.event.WindowAdapter;
import	java.awt.event.WindowEvent;
import	java.awt.event.WindowListener;
import	java.awt.event.ActionListener;
import	java.awt.event.ActionEvent;

/**
  このクラスは詳細情報表示フォーム
  フレームの継承やGUIの機能を持つ

  @author	Takuya Nakaigwa
  @since	1.0

**/
public class MainInfo extends Frame implements Closable{
	TextField field   = new TextField();
	List list = new List();
	
	InfoManager manager = new InfoManager();
	String filename = "Hiking.data";

	public MainInfo(){
		setBounds(0,0,800,600);
		setLayout(null);

		setGUI();

		addWindowListener(new WindowAdapter(){
			public void windowOpened(WindowEvent e){
				load();
			}
			public void windowClosing(WindowEvent e){
				close();
			}
		});

		setVisible(true);
	}

	/**
	  InfoDataに保存した地図データを読み込むメソッド
	  */
	void load(){
		InfoData [] datas = manager.load(filename);
		if(datas != null){
			for(InfoData data : datas){
				String time = data.time;
				list.add(time);
			}
		}
	}

	/**
	  GUIの機能を設定するメソッド
	  ラベルやリストの大きさと文字列を追加する
	  */
	void setGUI(){
		{
			Label label = new Label("大平山ハイキングコースの情報");
			add(label);
			label.setBounds(105,30,190,100);

			add(list);
			list.setBounds(105,140,550,400);
		}
	}

	/**
	  Closableを実装してウィンドウを閉じる機能を持つメソッド
	  InfoManagerの保存機能を実行する
	  */
	@Override
	public void close(){
		manager.save(filename);

		setVisible(false);
		dispose();
	}

	public static void main(String args[]){
		new MainInfo();
	}
}
