import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintStream;

/* クラスgpslog
   このクラスはGPSデバイスから情報を取得し、出力するクラス
  @author Chio Nishiwaki
  @since  1.0 */
class gpslog{
	static class GpsLogger extends Thread{
		String dev;
		String log;
		boolean flag = false;

		FileInputStream fin   = null;
		FileOutputStream fout = null;

		/*GPSデバイスから情報を取得するクラス*/
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

		/*GPSデバイスから位置情報を取得し書き出すメソッド*/
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

	/* メインメソッド
     本クラスをインスタンス化し、起動するためのメソッド。
     @param args コマンドラインからのパラメータを受け取る文字列の配列
     */
	public static void main(String args[]){
		String dev = null;
		String file = null;

		if(args.length < 4){  //usageを表示
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
