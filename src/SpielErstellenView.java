import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class SpielErstellenView extends JFrame {
	
	//Button mit Server öffenen
	//JTextField für Spieler und 
	JTextField tfSpielerName = new JTextField("Host");
	JTextField tfSpielName = new JTextField("Hosts Game");
	JLabel lSpielerName;
	JLabel lSpielName;
	JLabel lGastName=new JLabel();
	JButton bServerOeffnen = new JButton("Server Öffnen"); 
	JButton bSpielStarten = new JButton("Spiel starten");
	JButton bKick = new JButton("Gast kicken");
	JButton bAbbrechen = new JButton("Abbrechen");
	
	public void addBServerOeffnenActionListener(ActionListener listener)
	{
		bServerOeffnen.addActionListener(listener);
	}
	
	public void addBSpielStartenActionListener(ActionListener listener)
	{
		bSpielStarten.addActionListener(listener);
	}
	
	public void addBKickActionListener(ActionListener listener)
	{
		bKick.addActionListener(listener);
	}
	
	public void addBAbbrechenActionListener(ActionListener listener)
	{
		bAbbrechen.addActionListener(listener);
	}
	
	public SpielErstellenView ()
	{
		super("Spiel erstellen");
		gui();
	}

	private void gui()
	{
		this.setVisible(false);
		this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pCenter= new JPanel();
        pCenter.setLayout(new GridLayout(2,7));
        pCenter.add(new JLabel("Spielname:"));
        pCenter.add(new JLabel("Spielername:"));
        pCenter.add(new JLabel("Gastname"));
        pCenter.add(new JLabel(""));
        pCenter.add(new JLabel(""));
        pCenter.add(new JLabel(""));
        pCenter.add(new JLabel(""));
        pCenter.add(tfSpielName);
        pCenter.add(tfSpielerName);
        pCenter.add(lGastName);
        bKick.setName("bKick");
        pCenter.add(bKick);
        bServerOeffnen.setName("bServerOeffnen");
        pCenter.add(bServerOeffnen);
        bSpielStarten.setName("bSpielStarten");
        pCenter.add(bSpielStarten);
        bAbbrechen.setName("bAbbrechen");
        pCenter.add(bAbbrechen);
        
        this.add(pCenter, BorderLayout.CENTER);

		
		
	}
	
}
