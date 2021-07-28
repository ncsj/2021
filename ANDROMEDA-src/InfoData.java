import	java.io.Serializable;

/**
	このクラスは地図データを保持するクラス

	@author		Takuya Nakaigawa
	@since		1.0
**/
public class InfoData implements Serializable{
	String time;
	String dist;
	String expo;
	String precautions;

	public InfoData(){
	}

	public InfoData(String time){
		this.time = time;
	}

	public InfoData(String time,String dist,String expo,String precautions){
		this.time = time;
		this.dist = dist;
		this.expo = expo;
		this.precautions = precautions;
	}

}
