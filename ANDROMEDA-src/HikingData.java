/* $B%/%i%9(BHikingData
   $B$3$N%/%i%9$O(BX,Y$B$rDj5A$9$k%/%i%9(B
  @author Chiho Nishiwaki
  @since  1.0 */

public class HikingData{

	double x = 0;
	double y = 0;

	public HikingData(){
	}

	/*x$B$H(By$B$r(Bthis$B$GDj5A$9$k%/%i%9(B*/
	public HikingData(double x,double y){
		this.x = x;
		this.y = y;

	}

	/*String$B$GFI$_9~$s$@$b$N$r(Bdouble$B$KJQ49$9$k%/%i%9(B*/
	public HikingData(String [] data){
		
		this.x = Double.valueOf(data[0]);
		this.y = Double.valueOf(data[1]);
		 
	}
}
