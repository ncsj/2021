import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
  $B%/%i%9(BCloser
  $B3F%&%#%s%I%&$rJD$8$k$?$a$N%/%i%9(B
  @author Chiho Nishiwaki
  @since 1.0
  
*/
public class Closer extends WindowAdapter{
		Closable target = null;

		/**
		  $B$3$N%/%i%9$N%3%s%9%H%i%/%?!<!#(B
		  @param:Closable target
		*/
		public Closer(Closable target){
			this.target = target;
		}

		/**
		  Closable$B$N%*!<%P!<%i%$%I(B
		*/
		@Override
			public void windowClosing(WindowEvent e){
				target.close();
			}
}
