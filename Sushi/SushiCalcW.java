public class SushiCalcW{
	public static void main(String args[]){
		SushiCalc calc = new SushiCalc();
		SushiLoad loader = new SushiLoad(args[0]);

		String [] lines = loader.load();
		for(String line :  lines){
			String [] cols = line.split(",");
			SushiOrder order = new SushiOrder(cols);
			calc.add(order);
		}

		int sum = calc.sum();
		System.out.println("SUM : " + calc.sum());
		System.out.println("----------");

		int ave = calc.ave();
		System.out.println("AVE : " + calc.ave());

		SushiOrder max = calc.max();
		System.out.println("MAX : " + max.name + "," + max.price);
		
		SushiOrder min = calc.min();
		System.out.println("MIN : " + min.name + "," + min.price);

		SushiWindow sw = new SushiWindow(sum,ave,max.price,min.price);

	}
}
