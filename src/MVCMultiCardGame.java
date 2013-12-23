
public class MVCMultiCardGame {
	
	public static void main(String args[])
	{
		HauptmenuView hv = new HauptmenuView();	
		SpielBeitretenView sbv = new SpielBeitretenView();
		SpielErstellenView sev = new SpielErstellenView();
		LobbyView lv = new LobbyView();
		MultiCardGameController mcgc = new MultiCardGameController(hv, sbv, sev, lv);
		
		//Spiel.SucheOffeneSpiele();
		
		
	}

}
