import  java.util.ArrayList;

public class SushiCalc{
	ArrayList <SushiOrder> list = new ArrayList <SushiOrder> ();

	public SushiCalc(){
	}

	public void add(SushiOrder order){
		list.add(order);
	}

	public int sum(){
		int n = 0;
		for(SushiOrder order : list){
			System.out.println(order.name + " : " + order.price);
			n = n + order.price;
		}
		return n;
	}

	public int ave(){
		int n = 0;
		for(SushiOrder order : list){
			n = n + order.price;
		}
		return n / list.size();
	}

	public SushiOrder max(){
		SushiOrder order = null;

		for(SushiOrder o : list){
			if(order == null){
				order = o;
			}
			else{
				if(o.price > order.price){
					order = o;
				}
			}
		}

		return order;
	}

	public SushiOrder min(){
		SushiOrder order = null;
		
		for(SushiOrder o : list){
			if(order == null){
				order = o;
			}
			else{
				if(o.price < order.price){
					order = o;
				}
			}
		}

		return order;
	}

	public static void main(String args[]){
		SushiCalc calc = new SushiCalc();
		SushiLoad loader = new SushiLoad(args[0]);

		String [] lines = loader.load();
		for(String line :  lines){
			String [] cols = line.split(",");
			SushiOrder order = new SushiOrder(cols);
			calc.add(order);
		}

		System.out.println("SUM : " + calc.sum());
		System.out.println("----------");

		System.out.println("AVE : " + calc.ave());

		SushiOrder max = calc.max();
		System.out.println("MAX : " + max.name + "," + max.price);
		
		SushiOrder min = calc.min();
		System.out.println("MIN : " + min.name + "," + min.price);

	}
}
