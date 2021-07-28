
/*********
  $B%/%i%9!'(BFileData
  $B$3$N%/%i%9$OJ8;zNs$N07$$$K$J$C$F$$$k(BCSV$B%U%!%$%kFb$NCM$r(Bdouble$B7?$KD>$9$?$a$K:n@.$5$l$?$b$N$G$9!#(B
  
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
  $B%Q%i%a!<%?$NMWAG$rK\%/%i%9$G07$&JQ?tL>$GDj5A$7$F$$$k!#(B
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
  $B%U%!%$%kFb$N!V(B,$B!W$G6h@Z$i$l$?#2HVL\$H#3HVL\$r(Bdouble$B7?$K$9$k!#(B
 *******/
	public FileData(String [] data){
		this.name = data[0];
		this.lat  = Double.valueOf(data[1]).doubleValue();
		this.lon  = Double.valueOf(data[2]).doubleValue();
	}
}
