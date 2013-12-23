import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LobbyView extends JFrame{
	

    JButton bVerlassen = new JButton("Verlassen");
    JCheckBox cbBereit = new JCheckBox("Bereit");
    JLabel lHostname;
    JLabel lClientname; 
    JLabel lSpielname; 
    	

	
	public LobbyView()
    {
        super ("Lobby");
    }
	
	public void gui(Spiel spiel)
    {
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Center
        JPanel pCenter= new JPanel();
        this.add(pCenter,BorderLayout.CENTER);
        pCenter.setLayout(new GridLayout(3, 2, 2, 10)); 
        lSpielname = new JLabel(spiel.getSpielName());
        pCenter.add(lSpielname);
        lHostname= new JLabel(spiel.getSpieler1Name());
        pCenter.add(lHostname);
        pCenter.add(new JLabel(""));
        lClientname= new JLabel(spiel.getSpieler2Name());
        pCenter.add(lClientname);
        pCenter.add(cbBereit);
        cbBereit.setToolTipText("Bereitschaft dem Host übermitteln");
        cbBereit.setName("cbBereit");
        pCenter.add(new JLabel(""));
        //South
        JPanel pSouth= new JPanel();
        this.add(pSouth,BorderLayout.SOUTH);
        bVerlassen.setName("bVerlassen");
        pSouth.add(bVerlassen);
            
    }
        
	public void addCBBereitActionListener(ActionListener listener)
	{
		cbBereit.addActionListener(listener);
	}
	
	public void addBVerlassenActionListener(ActionListener listener)
	{
		bVerlassen.addActionListener(listener);
	}
	
}
