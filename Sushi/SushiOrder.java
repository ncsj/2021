public class SushiOrder{
	String	name = "";
	int		price = 0;

	public SushiOrder(){
	}

	public SushiOrder(String s){
		this.name = s;
	}

	public SushiOrder(String name,int price){
		this.name = name;
		this.price = price;
	}

	public SushiOrder(String [] data){
		this.name = data[0];
		this.price = Integer.valueOf(data[1]).intValue();
	}
}
