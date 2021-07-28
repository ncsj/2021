import java.awt.Frame;
import java.awt.List;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Color;
import java.io.File;


/**
	クラスCulture
	このクラスは山中湖文学の森の写真を表示するために作成したもの
	@author Chiho Nishiwaki
	@since 1.0
*/
class Culture extends Frame implements Closable{
	List list = new List();
	Image [] images = null;


	/**
	  このクラスのコンストラクター
	  ウィンドウ(Frame)を表示する。
	*/
	public Culture(){
		setBounds(100,100,800,600);
		setLayout(null);

		add(list);
		list.setBounds(80,510,200,80);
		list.addItemListener((ItemEvent e)->{ setImage();});
		
		addWindowListener(new WindowAdapter(){
			public void windowOpened(WindowEvent e){
				String [] files = getFiles();
				loadImages(files);
			}
		});
		addWindowListener(new Closer(this));
		setVisible(true);
	}

	/**
		imageがnullであれば背景に白い矩形を表示し、
		そうでなければimageをウィンドウ上に表示するメソッド。
		@param:Graphics g
	*/
	public void paint(Graphics g){
		if(this.image == null){
			g.setColor(Color.white);
			g.fillRect(80,20,640,480);
		}
		else{
				g.drawImage(image,80,20,this);			
		}
	}

	/**
	  リスト内にある写真を選択し、
	  選択された写真をウィンドウ上に表示するメソッド。
	*/
	Image image = null;
	void setImage(){
		int index = list.getSelectedIndex();
		this.image = this.images[index];

		repaint();
	}

	/**
		読み込む写真が入っているディレクトリを読み込み、
		ArrayListに追加するメソッド。
	*/
	String [] getFiles(){
		String [] files = null;
		File dir = new File("bungaku");
		if(dir.isDirectory()){
			files = dir.list();
			System.out.println("load");

		}
		for(String s: files){
			list.add(s);
		}
		return files;
	}

	/**
	  ToolkitやMediaTrackerを使用して、写真を読み込むメソッド。
	  @param:Strinf [] files
	*/
	void loadImages(String [] files){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		MediaTracker mt = new MediaTracker(this);
		
		this.image = toolkit.getImage("bungaku/bungaku1.jpg");
		mt.addImage(this.image,100);
		System.out.println("load");

		this.images = new Image[files.length];
		for(int i=0;i<images.length;i++){
			this.images[i] = toolkit.getImage("bungaku/" + files[i]);
			mt.addImage(images[i],i);

			try{
				mt.waitForID(i);
			}
			catch(Exception e){
			}
		}
	}

	/**
	  ウィンドウを閉じるメソッド。
	*/
	@Override
		public void close(){
			setVisible(false);
			dispose();
		}

	/**
	  メインメソッド
	  本クラスをインスタンス化し、起動するためのメソッド。
	*/
	public static void main(String args[]){
		new Culture();
	}
}
