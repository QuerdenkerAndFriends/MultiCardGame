import java.util.Stack;



public class Befehl {
	private String befehl;
	
	private Stack<String> tokens = new Stack<String>();
	
	public Befehl(String befehl)
	{
		this.befehl=befehl;
		this.anfertigen();
	}
	
	@Override 
	public String toString()
	{
		return befehl;
	}
	
	public void add(String token)
	{
		befehl+= (","+token);
	}
	
	public void anfertigen()
	{
		//diverse StringOps und dann auf die Tokens

	}
	
	public String getNextToken()
	{
		return tokens.pop();
	}
	
	
	
}
