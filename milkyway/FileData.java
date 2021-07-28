
/*********
  クラス：FileData
  このクラスは文字列の扱いになっているCSVファイル内の値をdouble型に直すために作成されたものです。
  
  @Author	 Ayana Tanaka
  ********/

public class FileData{
	String name = ""; // storename
	double lat  = 0;  // lat=latitude
	double lon  = 0;  // lon=longitude

	public FileData(){
	}

	public FileData(String s){
		this.name = s;
	}

/*******
  パラメータの要素を本クラスで扱う変数名で定義している。
        @param  String name
                double lat
                double lon
  *******/
	public FileData(String name,double lat,double lon){
		this.name = name;
		this.lat  = lat;
		this.lon  = lon;
	}

/*******
  ファイル内の「,」で区切られた２番目と３番目をdouble型にする。
 *******/
	public FileData(String [] data){
		this.name = data[0];
		this.lat  = Double.valueOf(data[1]).doubleValue();
		this.lon  = Double.valueOf(data[2]).doubleValue();
	}
}
