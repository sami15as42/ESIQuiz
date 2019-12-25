package TpPoo;

public class Compte 
{
	private String login,motPasse;
	
	public Compte(String login,String motPasse)
	{
		this.login=login;
		this.motPasse=motPasse;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public String getMotPasse()
	{
		return motPasse;
	}
	
	public void setMotPasse(String motPasse)
	{
		this.motPasse=motPasse;
	}
}
