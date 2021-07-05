public class SushiCalcWW{
	public static void main(String args[]){
		String [] titles = {"SUSHI","BBQ","Itarian"};
		String [] files = {"sushi.csv","bbq.csv","itarian.csv"};
		for(int i=0;i<files.length;i++){
			SushiCalc calc = new SushiCalc();
			SushiLoad loader = new SushiLoad(files[i]);

			String [] lines = loader.load();
			for(String line :  lines){
				String [] cols = line.split(",");
				SushiOrder order = new SushiOrder(cols);
				calc.add(order);
			}

			int sum = calc.sum();
			int ave = calc.ave();
			SushiOrder max = calc.max();
			SushiOrder min = calc.min();

			SushiWindow sw = new SushiWindow(sum,ave,max.price,min.price);
			sw.setTitle(titles[i]);
		}
	}
}
