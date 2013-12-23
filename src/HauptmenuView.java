import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class HauptmenuView extends JFrame{

	
		JButton bSpielBeitreten =new JButton("Spiel beitreten");
		JButton bSpielErstellen =new JButton("Spiel erstellen");
		
		public HauptmenuView()
		{
			//create
			super("Hauptmenü");
			gui(); 
		
		}
		
		
		private void gui()
		{
			this.setVisible(false);
			this.setSize(1000, 500);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			//Center
			JPanel pCenter = new JPanel();
			pCenter.setLayout(new GridLayout(1, 3, 10, 10));
			   
			bSpielBeitreten.setName("bSpielBeitreten");
			pCenter.add(bSpielBeitreten);
			  
			bSpielErstellen.setName("bSpielErstellen");
			pCenter.add(bSpielErstellen);
			this.add(pCenter, BorderLayout.CENTER);	
		}
		
		
		public void addBSpielBeitretenActionListener(ActionListener listener)
		{
			bSpielBeitreten.addActionListener(listener);
		}
		
		public void addBSpielErstellenActionListener(ActionListener listener)
		{
			bSpielErstellen.addActionListener(listener);
		}
		
		
		
		
		
}

