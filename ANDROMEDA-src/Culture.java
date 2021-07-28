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
	$B%/%i%9(BCulture
	$B$3$N%/%i%9$O;3Cf8PJ83X$N?9$N<L??$rI=<($9$k$?$a$K:n@.$7$?$b$N(B
	@author Chiho Nishiwaki
	@since 1.0
*/
class Culture extends Frame implements Closable{
	List list = new List();
	Image [] images = null;


	/**
	  $B$3$N%/%i%9$N%3%s%9%H%i%/%?!<(B
	  $B%&%#%s%I%&(B(Frame)$B$rI=<($9$k!#(B
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
		image$B$,(Bnull$B$G$"$l$PGX7J$KGr$$6k7A$rI=<($7!"(B
		$B$=$&$G$J$1$l$P(Bimage$B$r%&%#%s%I%&>e$KI=<($9$k%a%=%C%I!#(B
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
	  $B%j%9%HFb$K$"$k<L??$rA*Br$7!"(B
	  $BA*Br$5$l$?<L??$r%&%#%s%I%&>e$KI=<($9$k%a%=%C%I!#(B
	*/
	Image image = null;
	void setImage(){
		int index = list.getSelectedIndex();
		this.image = this.images[index];

		repaint();
	}

	/**
		$BFI$_9~$`<L??$,F~$C$F$$$k%G%#%l%/%H%j$rFI$_9~$_!"(B
		ArrayList$B$KDI2C$9$k%a%=%C%I!#(B
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
	  Toolkit$B$d(BMediaTracker$B$r;HMQ$7$F!"<L??$rFI$_9~$`%a%=%C%I!#(B
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
	  $B%&%#%s%I%&$rJD$8$k%a%=%C%I!#(B
	*/
	@Override
		public void close(){
			setVisible(false);
			dispose();
		}

	/**
	  $B%a%$%s%a%=%C%I(B
	  $BK\%/%i%9$r%$%s%9%?%s%92=$7!"5/F0$9$k$?$a$N%a%=%C%I!#(B
	*/
	public static void main(String args[]){
		new Culture();
	}
}
