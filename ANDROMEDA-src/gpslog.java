import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;

/* $B%/%i%9(Bgpslog
   $B$3$N%/%i%9$O(BGPS$B%G%P%$%9$+$i>pJs$r<hF@$7!"=PNO$9$k%/%i%9(B
  @author Chio Nishiwaki
  @since  1.0 */
class gpslog{
	static class GpsLogger extends Thread{
		String dev;
		String log;
		boolean flag = false;

		FileInputStream fin   = null;
		FileOutputStream fout = null;

		/*GPS$B%G%P%$%9$+$i>pJs$r<hF@$9$k%/%i%9(B*/
		public GpsLogger(String dev,String log) throws Exception{
			this.dev = dev;
			this.log = log;

			try{
				this.fin = new FileInputStream(this.dev);
				this.fout = new FileOutputStream(this.log);
			}
			catch(Exception e){
				throw e;
			}
		}

		/*GPS$B%G%P%$%9$+$i0LCV>pJs$r<hF@$7=q$-=P$9%a%=%C%I(B*/
		@Override
			public void run(){
				flag = true;

				try{
					InputStreamReader isr = new InputStreamReader(fin);
					BufferedReader reader = new BufferedReader(isr);
					PrintStream ps = new PrintStream(fout);

					while(flag){
						String line = reader.readLine();
						ps.println(line);
						System.out.println(line);
					}
				}
				catch(Exception e){
					System.out.println(e.toString());
				}
			}
	}

	/* $B%a%$%s%a%=%C%I(B
     $BK\%/%i%9$r%$%s%9%?%s%92=$7!"5/F0$9$k$?$a$N%a%=%C%I!#(B
     @param args $B%3%^%s%I%i%$%s$+$i$N%Q%i%a!<%?$r<u$1<h$kJ8;zNs$NG[Ns(B
     */
	public static void main(String args[]){
		String dev = null;
		String file = null;

		if(args.length < 4){  //usage$B$rI=<((B
			System.out.println("usage...");
			System.out.println("java gpslog -d [dev] -o [output-file]");
			System.out.println("java gpslog -dev [dev] -o [output-file]");
		}
		else{
			for(int i=0;i<args.length;i++){
				switch(args[i]){
					case "-d":
					case "-dev":
						dev = args[i+1];
						break;
					case "-o":
						file = args[i+1];
						break;
					default:
						break;
				}
			}
			System.out.println("DEV : " + dev);
			System.out.println("LOG : " + file);

			try{
				GpsLogger logger = new GpsLogger(dev,file);
				logger.start();

				logger.join();
			}
			catch(Exception e){				
				System.out.println(e.toString());
			}
		}
	}
}
