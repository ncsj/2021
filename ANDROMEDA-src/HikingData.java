/* クラスHikingData
   このクラスはX,Yを定義するクラス
  @author Chiho Nishiwaki
  @since  1.0 */

public class HikingData{

	double x = 0;
	double y = 0;

	public HikingData(){
	}

	/*xとyをthisで定義するクラス*/
	public HikingData(double x,double y){
		this.x = x;
		this.y = y;

	}

	/*Stringで読み込んだものをdoubleに変換するクラス*/
	public HikingData(String [] data){
		
		this.x = Double.valueOf(data[0]);
		this.y = Double.valueOf(data[1]);
		 
	}
}
