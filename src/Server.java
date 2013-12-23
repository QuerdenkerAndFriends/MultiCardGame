import java.io.*;

import java.net.*;
import java.util.*;

public class Server extends Thread 
{	
	ServerSocket server;
	MultiCardGameController controller;
	String spieler1Name;
	String spielName;
	
	boolean nochmal;
	List <Spiel> spiele = new LinkedList<Spiel>();
	Server(int portNr, MultiCardGameController controller, String spieler1Name, String spielName) throws Exception
	{
		server =  new ServerSocket(portNr);
		this.controller=controller;
		this.spieler1Name=spieler1Name;
		this.spielName=spielName;
		
	}

	public void reset()
	{
		spiele.clear();
	}
	
	public void run()
	{
		while(nochmal)
		{
			try 
			{
				Spiel spiel = new Spiel(controller, server.accept());
				spiel.setSpieler1Name(spieler1Name);
				spiel.setSpielName(spielName);
				spiele.add (spiel);				 
			}	
			catch (IOException e)
			{
				System.out.println("Fehler - ServerSocket.accept()");
			}	
		}
		this.stop();
	}
	
	public void los()
	{
		nochmal = true;
		this.start();
	}

	public void halt() {
		nochmal = false;
		
	}

}
