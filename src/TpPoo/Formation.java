package TpPoo;

import java.util.*;

public class Formation 
{
	private String nom,description,dateDebut,dateFin;
	private ArrayList<Apprenant> listeApprenants = new ArrayList<Apprenant>();
	private int nbApprenants;
	private ArrayList<Quiz> listeQuiz = new ArrayList<Quiz>();
	private int nbQuiz;
	private ArrayList<Notion> listeNotions = new ArrayList<Notion>();
	private int nbNotions;
	
	public Formation(String nom,String description,String dateDebut,String dateFin)
	{
		this.nom=nom;
		this.description=description;
		this.dateDebut=dateDebut;
		this.dateFin=dateFin;
		nbApprenants=0;
		nbQuiz=0;
		nbNotions=0;
	}
	
	public ArrayList<Quiz> getListeQuiz()
	{
		return listeQuiz;
	}
	
	public void ajouterNotions(Notion[] tab)
	{
		if (tab!=null)
		{
			for (int i=0;i<tab.length;i++)
			{
				if (tab[i]!=null)
				{
					if (chercherNotion(tab[i].getNom())==null)
					{
							listeNotions.add(tab[i]);
							nbNotions++;
					}
				}
			}
		}
	}
	
	public void afficherNotions()
	{
		if (nbNotions>0) System.out.println("Les notions de la formation '"+nom+"' sont : ");
		else System.out.println("La formation '"+nom+"' n'a aucune notion.");
		for (int i=0;i<nbNotions;i++) System.out.println(" - "+listeNotions.get(i).getNom());
	}
	
	public void afficherQuizOuverts()
	{
		for (int i=0;i<nbQuiz;i++) 
		{
			if (listeQuiz.get(i).quizOuvert()) System.out.println(" - "+listeQuiz.get(i).getNom()+" *** Date d'ouverture : "+listeQuiz.get(i).getDateOuverture()+" *** Date d'expiration : "+listeQuiz.get(i).getDateExpiration()+" *** Nom de la formation : "+listeQuiz.get(i).getNomF());
		}
	}
	
	public void ajouterQuiz(Quiz quiz)
	{
		if (quiz!=null)
		{
			boolean trouv = false;
			int i = 0;
			while ((!trouv)&&(i<nbQuiz))
			{
				if (listeQuiz.get(i).getNom().equalsIgnoreCase(quiz.getNom())) trouv = true;
				else i++;
			}
			if (!trouv)
			{
				listeQuiz.add(quiz);
				nbQuiz++;
			}
		}
	}
	
	public void supprimerQuiz(String nom)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbQuiz))
		{
			if ((listeQuiz.get(i).getNom().equalsIgnoreCase(nom))&&((!(listeQuiz.get(i).quizOuvert())))) trouv = true;
			else i++;
		}
		if (trouv)
		{
			listeQuiz.set(i,listeQuiz.get(nbQuiz-1));
			listeQuiz.remove(nbQuiz-1);
			nbQuiz--;
		}
	}
	
	public void ajouterApprenants(Apprenant[] tab)
	{
		if (tab!=null)
		{
			for (int i=0;i<tab.length;i++)
			{
				boolean trouv = false;
				int k = 0;
				while ((!trouv)&&(i<nbApprenants))
				{
					if ((listeApprenants.get(k).getNom().equalsIgnoreCase(tab[i].getNom()))&&(listeApprenants.get(k).getPrenom().equalsIgnoreCase(tab[i].getPrenom()))) trouv = true;
					else k++;
				}
				if (!trouv)
				{
					if (tab[i]!=null)
					{
						tab[i].ajouterFormation(this);
						listeApprenants.add(tab[i]);
						nbApprenants++;
					}
				}
			}
		}
	}
	
	public int getNbNotions()
	{
		return nbNotions;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getDateDebut()
	{
		return dateDebut;
	}
	
	public String getDateFin()
	{
		return dateFin;
	}
	
	public ArrayList<Notion> getNotions()
	{
		return listeNotions;
	}
	
	public Notion chercherNotion(String nom)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbNotions))
		{
			if (listeNotions.get(i).getNom().equalsIgnoreCase(nom)) trouv = true;
			else i++;
		}
		if (trouv) return listeNotions.get(i);
		else return null;
	}
	
	public Quiz chercherQuiz(String nom)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbQuiz))
		{
			if (listeQuiz.get(i).getNom().equalsIgnoreCase(nom)) trouv = true;
			else i++;
		}
		if (trouv) return listeQuiz.get(i);
		else return null;
	}
	
	public int getNbQuizExpire()
	{
		int nbQuizExpire = 0;
		for (int i=0;i<nbQuiz;i++) {if (listeQuiz.get(i).quizExpire()) nbQuizExpire++;}
		return nbQuizExpire;
	}
}
