
import java.io.*;
import java.net.*;
import java.util.*;


public class Spiel extends Thread{

	private static Server server;
	
	MultiCardGameController controller;
	private String spielName = null, spieler1Name = null, spieler2Name =null;
	private Stack<Befehl> befehlsStack = new Stack<Befehl>();
	private int spielZugZustand;
	private int spielZugAnzahl;	
	private String  hostIP, userIP;
	private Socket socket = null;
	PrintWriter pwOut = null;
	BufferedReader brIn = null;
	
	public void run()
	{
		while(true)
		{
			String incoming;
			try 
			{
				incoming = brIn.readLine();
				System.out.println("incoming="+incoming);
				controller.befehlEmpfangen(new Befehl(incoming), this);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
	}
	
	public static List<Spiel> sucheOffeneSpiele(MultiCardGameController controller) throws UnknownHostException, IOException, InterruptedException 
	{
		List<Spiel> spiele =  new  LinkedList<Spiel>(); 		
		String[] ips;
		ips = new String[1];
		ips[0] = new String();
		ips[0] = "localhost";
		for (int i =0; i<ips.length; i++)
		{	
			Spiel spiel = new Spiel(controller, new Socket(ips[i], 4711));	
			spiele.add(spiel);
			System.out.println("Verbindung mit IP "+ips[i]+" steht");
		}
		System.out.println("Es wurden so viele Spiele gefunden: "+spiele.size());
		Thread.sleep(spiele.size()*1000);
		return spiele;		
	}
	
	public static void oeffneServer(MultiCardGameController controller, String spieler1Name, String  spielName) throws Exception
	{
		server = new Server(4711, controller, spieler1Name, spielName);
		server.los();		
	}
	
	public static void schliesseServer()
	{
		server.halt();
		server.reset();
	}
	
	public Spiel(MultiCardGameController controller, Socket socket) throws IOException
	{
		this.socket=socket;
		this.controller=controller;
		pwOut = new PrintWriter(socket.getOutputStream(),true);
		brIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void setSpieler1Name(String spieler1Name) 
	{
		this.spieler1Name = spieler1Name;
		this.befehlSenden(new Befehl("Spieler1Name="+spieler1Name));
	}
	
	public void setSpieler2Name(String spieler2Name) {
		this.spieler2Name = spieler2Name;
		this.befehlSenden(new Befehl("Spieler2Name="+spieler2Name));
	}
	
	public void setSpielName(String spielName) {
		this.spielName = spielName;
		this.befehlSenden(new Befehl("SpielName="+spielName));
	}
	
	public String getSpieler1Name() {
		return spieler1Name;
	}
	
	public String getSpieler2Name() {
		return spieler2Name;
	}
	
	public String getSpielName() {
		return spielName;
	}	
	
	public void befehlSpeichern(Befehl befehl)
	{
		befehlsStack.push(befehl);	
	}
	
	public void befehlSenden(Befehl befehl)
	{
		pwOut.println(befehl.toString());
		pwOut.flush();
		befehlSpeichern(befehl);
		System.out.println(befehl.toString()+ " gesendet");
	}
}
