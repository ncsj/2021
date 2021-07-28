import java.awt.Frame;
import java.awt.Button;
import java.awt.Label;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/***********
  クラス名:SelectionScreen
  本クラスは、アプリケーション起動時の初期画面になる。
  選択するボタンによって、引き値として渡すファイルを区別している。
  
  @Author Kyoko Fukuda
  @since 1.0
***********/
class SelectionScreen extends Frame{
	Button btn1	= new Button("ご飯食べたい！");
	Button btn2	= new Button("ワイン飲みたい！");
	Button btn3	= new Button("山中湖に泊まりたい！");
	Label label	= new Label();

	String file1	= "test1.csv";		//使用するファイルは3種類
	String file2	= "test2.csv";
	String file3	= "test3.csv";

	MenuBar	mbar	= new MenuBar();
	Menu	editMenu	= new Menu("ForEditor");

	public SelectionScreen(){
		setBounds(0,0,800,600);
		setLayout(null);
		
		add(label);
		label.setBounds(150,120,450,40);
		label.setText("山中湖で何したい？");
		label.setFont(new Font("SansSerif",Font.BOLD,40));

		setMenuBar();

		add(btn1);
		btn1.setBounds(150,250,450,40);
		btn1.addActionListener((ActionEvent e)->{ new UserWindow(file1); });
		//ボタンを押すと、新規ウィンドウが表示される。
		//ウィンドウを生成する際、必要となるファイルをここで指定する。
		
		add(btn2);
		btn2.setBounds(150,350,450,40);
		btn2.addActionListener((ActionEvent e)->{ new UserWindow(file2); });
		
		add(btn3);
		btn3.setBounds(150,450,450,40);
		btn3.addActionListener((ActionEvent e)->{ new UserWindow(file3); });

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				close();
			}
		});

		setVisible(true);
	}

	/**********
	  クラス名:EditMenu
	  SelectWindowに作成したメニューバーの、動きを規定するインナークラス。
	  項目を選択することでEditorWindowが開かれる。
	  その際、編集する対象のファイルも渡される。
	**********/
	class EditMenu implements ActionListener{
		public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();

            switch(cmd){
                case "Houtou":
                    new EditorWindow(file1);
                    break;
                case "Sake":
                    new EditorWindow(file2);
                    break;
                case "Camp":
					new EditorWindow(file3);
                    break;
                default:
                    break;
            }
        }
	}

	/*******
	  メニューバーを設定する。
	*******/
	void setMenuBar(){
		{
            String [] items = {"Houtou","Wine","Camp"};

            EditMenu editor	= new EditMenu();
            for(String item : items){
                MenuItem mi = new MenuItem(item);
                mi.addActionListener(editor);
                editMenu.add(mi);
            }
        }
        mbar.add(editMenu);
        setMenuBar(mbar);
    }

	/********
	  ウィンドウを閉じることで呼び出される。GUIを終了させる。
	********/
	void close(){
		setVisible(false);
		dispose();
	}
	public static void main(String args[]){
		SelectionScreen select	= new SelectionScreen();
	}
}
