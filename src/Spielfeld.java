import java.util.*;


public class Spielfeld {
	List<Karte> H1 = new LinkedList<Karte>(); //Hand von Spieler1
	List<Karte> H2 = new LinkedList<Karte>(); //Hand von Spieler2

	Stack<Karte>[] F1 = (Stack<Karte>[]) new Stack[7]; //Spielfeldseite von Spieler1
	Stack<Karte>[] F2 = (Stack<Karte>[]) new Stack[7]; //Spielfeldseite von Spieler2
	
	Stack<Karte> ZiehStapel = new Stack<Karte>();
	
	Karte[] alleKarten = new Karte[28]; //Französisches Blatt ohne Buben
	
	public Spielfeld()
	{
		for (int i = 0; i<7; i++)
		{
			F1[i] = new Stack<Karte>();
			F2[i] = new Stack<Karte>();
		}
		
		for (int i =0 ; i<28; i++)
		{
			alleKarten[i] = new Karte(i); 
		}		
	}
	
	public void reset()
	{
		//Spielfeld leeren
		for (int i = 0; i<7; i++)
		{
			F1[i].clear();
			F2[i].clear();
		}
		H1.clear();
		H2.clear();
		ZiehStapel.clear();
		//Karten zufällig auf Ziehstapel legen
		List<Karte> alleKartenListe = new LinkedList<Karte>();
		for (int i =0 ; i<28; i++)
		{
			alleKartenListe.add(alleKarten[i]);
		}	
		for (int i = alleKartenListe.size(); i<0; i--)
		{
			//Zufällige Karte der noch in der Liste vorhandenen
			int nr = (int) ((Math.random()*100) % i);
			//Wird gepusht und aus der Liste entfernt
			ZiehStapel.push(alleKartenListe.get(nr));
			alleKartenListe.remove(nr);
		}
		//Karten austeilen
		for (int i = 0; i<5; i++)
		{
			H1.add(ZiehStapel.pop());
			H2.add(ZiehStapel.pop());
		}
	}
}
