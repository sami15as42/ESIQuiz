package TpPoo;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class ESIQuiz 
{
	private ArrayList<Formateur> listeFormateurs = new ArrayList<Formateur>();
	private int nbFormateurs;
	public static ArrayList<Apprenant> listeApprenants = new ArrayList<Apprenant>();
	public static int nbApprenants;
	public static String dateActuelle;
	
	public ESIQuiz()
	{
		nbFormateurs = 0;
		nbApprenants = 0;
		ESIQuiz.miseAjourDate();
	}
	
	public static void miseAjourDate() 
	{
		String format = "dd/MM/yyyy";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		Date date = new Date();
		dateActuelle = formater.format(date);
	}
	
	public Apprenant authentificationApprenant(String login,String motPasse)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbApprenants))
		{
			if ((listeApprenants.get(i).getCompte().getLogin().equals(login))&&(listeApprenants.get(i).getCompte().getMotPasse().equals(motPasse))) trouv = true;
			else i++;
		}
		if (trouv) return listeApprenants.get(i);
		else return null;
	}
	
	public Formateur authentificationFormateur(String login,String motPasse)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbFormateurs))
		{
			if ((listeFormateurs.get(i).getCompte().getLogin().equals(login))&&(listeFormateurs.get(i).getCompte().getMotPasse().equals(motPasse))) trouv = true;
			else i++;
		}
		if (trouv) return listeFormateurs.get(i);
		else return null;
	}
	
	public Formateur creerFormateur(String nom,String prenom,String dateNaissance,String adresse)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbFormateurs))
		{
			if ((listeFormateurs.get(i).getNom().equalsIgnoreCase(nom))&&(listeFormateurs.get(i).getPrenom().equalsIgnoreCase(prenom))) trouv = true;
			else i++;
		}
		if (trouv) return null;
		else 
		{
			listeFormateurs.add(new Formateur(nom,prenom,dateNaissance,adresse));
			nbFormateurs++;
			return listeFormateurs.get(nbFormateurs-1);
		}
	}
}
