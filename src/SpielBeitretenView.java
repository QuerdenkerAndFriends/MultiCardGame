
import java.awt.*;
import java.awt.event.*;
import java.util.List;


import javax.swing.*;


public class SpielBeitretenView extends JFrame{

	JButton bRefresh = new JButton("Refresh");
	JButton bAbbrechen = new JButton("Abbrechen");
	JTextField tfSpielername = new JTextField("Spieler2");
	JPanel pTable=new JPanel();
	
	
	public SpielBeitretenView()
	{
		super("Spiel beitreten");
		gui();
		
	}
	
	private void gui()
	{
		this.setVisible(false);
		this.setSize(1000, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pNorth = new JPanel();
        this.add(pNorth, BorderLayout.NORTH);
        pNorth.setLayout(new GridLayout(1,2));
        pNorth.add(new JLabel("Dein Spielername:"));
        pNorth.add(tfSpielername);
        
        this.add(pTable, BorderLayout.CENTER);
        
        JPanel pSouth= new JPanel();
        pSouth.setLayout(new GridLayout(1,3));
        this.add(pSouth, BorderLayout.SOUTH);
        pSouth.add(bRefresh);
        bRefresh.setName("bRefresh");
        pSouth.add(new JLabel(""));
        pSouth.add(bAbbrechen);		
        bAbbrechen.setName("bAbbrechen");
	}
	
	public String getSpielername()
	{
		return tfSpielername.getText();
	}
	
	
	
	public void addBRefreshActionListener(ActionListener listener)
	{
		bRefresh.addActionListener(listener);
	}
	
	public void addBAbbrechenActionListener(ActionListener listener)
	{
		bAbbrechen.addActionListener(listener);
	}
	
	public void resetLines()
	{
		pTable.removeAll();
	}
	
	public void setLines(List<Spiel> spiele, ActionListener[] listeners)
	{		
		pTable.setLayout(new GridLayout(spiele.size()+1, 3));
		pTable.add(new JLabel("Spielname"));
		pTable.add(new JLabel("Hostname"));
		pTable.add(new JLabel(""));
	

		for (int i=0; i<spiele.size(); i++)
		{
			pTable.add(new JLabel(spiele.get(i).getSpieler1Name()));
			pTable.add(new JLabel(spiele.get(i).getSpielName()));
			JButton b = new JButton("Beitreten");
			b.addActionListener(listeners[i]);
			pTable.add(b);	
		}
	}
	
/*	
	public void setLines(int rows, int cols, String[] spaltenNamen, String[][] spielInformationen, ActionListener[] listeners)
	{
		pTable = new JPanel();
		pTable.setLayout(new GridLayout(rows+1, cols+1));
		//Überschrift
		for(int i=0; i<rows; i++)
		{
			pTable.add(new JLabel( spaltenNamen[i] ));
		}
		pTable.add(new JLabel(" "));
		
		//Zeilen
		for (int i=0; i<cols; i++)
		{
			for (int j=0; j<rows; j++)
			{
				pTable.add(new JLabel( spielInformationen[i][j] )); 
			}
			JButton b = new JButton("Beitreten");
			b.addActionListener(listeners[i]);
			pTable.add(b);
		}
	}
	*/
}
