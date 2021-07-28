import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
  クラスCloser
  各ウィンドウを閉じるためのクラス
  @author Chiho Nishiwaki
  @since 1.0
  
*/
public class Closer extends WindowAdapter{
		Closable target = null;

		/**
		  このクラスのコンストラクター。
		  @param:Closable target
		*/
		public Closer(Closable target){
			this.target = target;
		}

		/**
		  Closableのオーバーライド
		*/
		@Override
			public void windowClosing(WindowEvent e){
				target.close();
			}
}
