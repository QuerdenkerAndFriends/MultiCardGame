import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;


public class MultiCardGameController {
	
	private HauptmenuView hauptmenuView;
	private SpielBeitretenView spielBeitretenView;
	private SpielErstellenView spielErstellenView;
	private LobbyView lobbyView;
	private Spiel mySpiel;
	
	
	public MultiCardGameController(HauptmenuView hauptmenuView, SpielBeitretenView spielBeitretenView, SpielErstellenView spielErstellenView, LobbyView lobbyView)
	{
		//Initialisierung der übergebenen Views
		this.hauptmenuView=hauptmenuView;
		this.lobbyView=lobbyView;
		this.spielBeitretenView=spielBeitretenView;
		this.spielErstellenView=spielErstellenView;	
		//Startpunkt ist das HauptmenuView
		this.hauptmenuView.setVisible(true);
		//Übergabe der ActionListener an die Views im Sinne von MVC
		HauptmenuViewActionListener hval = new HauptmenuViewActionListener();
		this.hauptmenuView.addBSpielBeitretenActionListener(hval);
		this.hauptmenuView.addBSpielErstellenActionListener(hval);
		SpielBeitretenViewActionListener sbval = new SpielBeitretenViewActionListener();
		this.spielBeitretenView.addBRefreshActionListener(sbval);
		this.spielBeitretenView.addBAbbrechenActionListener(sbval);
		LobbyViewActionListener lval = new LobbyViewActionListener();
		this.lobbyView.addBVerlassenActionListener(lval);
		this.lobbyView.addCBBereitActionListener(lval);
		SpielErstellenViewActionListener seval = new SpielErstellenViewActionListener();
		this.spielErstellenView.addBAbbrechenActionListener(seval);
		this.spielErstellenView.addBKickActionListener(seval);
		this.spielErstellenView.addBServerOeffnenActionListener(seval);
		this.spielErstellenView.addBSpielStartenActionListener(seval);		
	}
	
	public void befehlEmpfangen(Befehl befehl, Spiel spiel)
	{
		spiel.befehlSpeichern(befehl);
		System.out.println(befehl.toString()+ "empfangen");
		switch (befehl.toString())
		{
			case "join": 
				Spiel.schliesseServer();
				mySpiel = spiel; 
				MultiCardGameController.this.mySpiel.setSpieler1Name(MultiCardGameController.this.spielBeitretenView.tfSpielername.getText());
			break;
			case "leave":
				
				try {
					Spiel.oeffneServer(this, mySpiel.getSpieler1Name(), mySpiel.getSpielName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mySpiel=null;
			break;
			case "getSpielername1":
				spiel.befehlSenden(new Befehl("SpielerName1="+mySpiel.getSpieler1Name()));
			break;
			case "getSpielername2":
				spiel.befehlSenden(new Befehl("SpielerName2="+mySpiel.getSpieler2Name()));
			break;
			case "getSpielname":
				spiel.befehlSenden(new Befehl("SpielName="+mySpiel.getSpielName()));
			break;
		}
		
		if (befehl.toString().startsWith("SpielerName1="))
		{
			mySpiel.setSpieler1Name(befehl.toString().subSequence(13, befehl.toString().length()-1).toString());
		}
		else
		if (befehl.toString().startsWith("SpielerName2="))
		{
			mySpiel.setSpieler2Name(befehl.toString().subSequence(13, befehl.toString().length()-1).toString());
		}
		else
		if (befehl.toString().startsWith("SpielName="))
		{
			mySpiel.setSpielName(befehl.toString().subSequence(9, befehl.toString().length()-1).toString());
		}
		

	}
	
	public void sbvRefresh()
	{
		List<Spiel> spiele = null;
		try {
			spiele = Spiel.sucheOffeneSpiele(this);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SBVBBeitretenActionListener[] listeners = new SBVBBeitretenActionListener[spiele.size()];
		for (int i=0; i<spiele.size(); i++)
		{
			listeners[i]= new SBVBBeitretenActionListener(spiele.get(i));
		}
		MultiCardGameController.this.spielBeitretenView.resetLines();
		MultiCardGameController.this.spielBeitretenView.setLines(spiele, (ActionListener[]) listeners);
		
	}


	
	class SpielBeitretenViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComponent c = (JComponent) e.getSource();
			switch (c.getName())
			{
				case "bRefresh": 
					sbvRefresh();
				break;
				
				case "bAbbrechen":
					MultiCardGameController.this.spielBeitretenView.setVisible(false);
					MultiCardGameController.this.hauptmenuView.setVisible(true);
				break;		
			}
			
		}
		
	}
	

	
	class SBVBBeitretenActionListener implements ActionListener{
		private Spiel spiel;
		
		public SBVBBeitretenActionListener(Spiel spiel)
		{
			super();
			this.spiel=spiel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
		
			MultiCardGameController.this.spielBeitretenView.setVisible(false);
			MultiCardGameController.this.lobbyView.setVisible(true);
			MultiCardGameController.this.mySpiel = spiel;
			mySpiel.setSpieler2Name(MultiCardGameController.this.spielBeitretenView.getSpielername());
			mySpiel.befehlSenden(new Befehl("join"));
			MultiCardGameController.this.lobbyView.gui(spiel);
	
		}
	}

	class HauptmenuViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComponent c = (JComponent) e.getSource();
			switch (c.getName())
			{
				case "bSpielBeitreten": 
					MultiCardGameController.this.hauptmenuView.setVisible(false);
					MultiCardGameController.this.spielBeitretenView.setVisible(true);
					sbvRefresh();
				break;
				case "bSpielErstellen":
					MultiCardGameController.this.hauptmenuView.setVisible(false);
					MultiCardGameController.this.spielErstellenView.setVisible(true);
				break;
			}

			
		}
		
	}
		
	class SpielErstellenViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComponent c = (JComponent) e.getSource();
			switch (c.getName())
			{
				case  "bKick": 
					mySpiel.setSpieler2Name("");
					MultiCardGameController.this.spielErstellenView.lGastName.setText("");
					
				break;
				case "bServerOeffnen":
					MultiCardGameController.this.spielErstellenView.tfSpielName.setEditable(false);
					MultiCardGameController.this.spielErstellenView.tfSpielerName.setEditable(false);
					try {
						Spiel.oeffneServer(MultiCardGameController.this, MultiCardGameController.this.spielErstellenView.tfSpielerName.getText(), MultiCardGameController.this.spielErstellenView.tfSpielName.getText());
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
				break;
				case "bSpielStarten":
					Spielfeld spielfeld = new Spielfeld();
					spielfeld.reset();
					
				break;
				case "bAbbrechen":
					MultiCardGameController.this.mySpiel=null;
					MultiCardGameController.this.spielErstellenView.setVisible(false);
					MultiCardGameController.this.hauptmenuView.setVisible(true);		
					MultiCardGameController.this.spielErstellenView.tfSpielName.setEditable(true);
					MultiCardGameController.this.spielErstellenView.tfSpielerName.setEditable(true);
				break;
	     
			
			}
		
			
		}
		
	}

	class LobbyViewActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComponent c = (JComponent) e.getSource();
			
			
			switch (c.getName())
			{
				case "cbBereit":
					
				break;
				case "bVerlassen":
					MultiCardGameController.this.lobbyView.setVisible(false);
					MultiCardGameController.this.spielBeitretenView.setVisible(true);
					mySpiel=null;
					sbvRefresh();
				break;
			}
			
		}
		
	}
}
