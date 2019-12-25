package TpPoo;

public class Utilisateur 
{
	private String nom,prenom,dateNaissance,adresse;
	private Compte compte;
	
	public Utilisateur(String nom,String prenom,String dateNaissance,String adresse)
	{
		this.nom=nom;
		this.prenom=prenom;
		this.dateNaissance=dateNaissance;
		this.adresse=adresse;
		String login = prenom.charAt(0)+nom;
		String motPasse = nom.toLowerCase()+dateNaissance;
		this.compte=new Compte(login.toLowerCase(),motPasse);
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getPrenom()
	{
		return prenom;
	}
	
	public String getDateNaissance()
	{
		return dateNaissance;
	}
	
	public String getAdresse()
	{
		return adresse;
	}
	
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	
	public void setPrenom(String prenom)
	{
		this.prenom=prenom;
	}
	
	public void setDate(String date)
	{
		this.dateNaissance=date;
	}
	
	public void setAdresse(String adresse)
	{
		this.adresse=adresse;
	}
	
	public boolean authentification(String login,String motPasse)
	{
		return ((compte.getLogin().equals(login))&&(compte.getMotPasse().equals(motPasse)));
	}
	
	public Compte getCompte()
	{
		return compte;
	}
}
