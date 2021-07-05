import java.awt.*;

public class SushiWindow extends Frame{
	public SushiWindow(int sum,int ave,int max,int min){
		setLayout(null);
		{
			String s = String.format("SUM : %d",sum);
			Label label = new Label(s);

			add(label);
			label.setBounds(100,50,200,20);
		}
		{
			String s = String.format("AVE : %d",ave);
			Label label = new Label(s);

			add(label);
			label.setBounds(100,75,200,20);
		}
		{
			String s = String.format("MAX : %d",max);
			Label label = new Label(s);

			add(label);
			label.setBounds(100,100,200,20);
		}
		{
			String s = String.format("MIN : %d",min);
			Label label = new Label(s);

			add(label);
			label.setBounds(100,125,200,20);
		}

		setBounds(100,20,400,600);
		setVisible(true);
	}
}
