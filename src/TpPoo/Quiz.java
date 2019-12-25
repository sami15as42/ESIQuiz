package TpPoo;

import java.util.*;

public class Quiz 
{
	private String nom,dateOuverture,dateExpiration,nomFormation;
	private ArrayList<Question> ensembleQuestions = new ArrayList<Question>();
	private int nbQuestions;
	
	public Quiz(String nom,String dateOuverture,String dateExpiration,Question[] tabQuestions,String nomFormation)
	{
		this.nom=nom;
		this.dateOuverture=dateOuverture;
		this.dateExpiration=dateExpiration;
		this.nomFormation=nomFormation;
		this.nbQuestions=tabQuestions.length;
		for (int i=0;i<nbQuestions;i++) ensembleQuestions.add(tabQuestions[i]);
	}
	
	public String getNomF()
	{
		return nomFormation;
	}
	
	public int getNbQuestions()
	{
		return nbQuestions;
	}
	
	public Question getQuestion(int i)
	{
		if (i<nbQuestions) return ensembleQuestions.get(i);
		else return null;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getDateOuverture()
	{
		return dateOuverture;
	}
	
	public String getDateExpiration()
	{
		return dateExpiration;
	}
	
	public ArrayList<Question> getEnsembleQuestions()
	{
		return ensembleQuestions;
	}
	
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	
	public void setDateOuverture(String date)
	{
		this.dateOuverture=date;
	}
	
	public void setDateExpiration(String date)
	{
		this.dateExpiration=date;
	}
	
	public boolean quizOuvert()
	{
		boolean ouvert = false;
		int jourOuverture = Integer.parseInt(""+dateOuverture.charAt(0)+dateOuverture.charAt(1));
		int moisOuverture = Integer.parseInt(""+dateOuverture.charAt(3)+dateOuverture.charAt(4));
		int anneeOuverture = Integer.parseInt(""+dateOuverture.charAt(6)+dateOuverture.charAt(7)+dateOuverture.charAt(8)+dateOuverture.charAt(9));
		int jourActuelle = Integer.parseInt(""+ESIQuiz.dateActuelle.charAt(0)+ESIQuiz.dateActuelle.charAt(1));
		int moisActuelle = Integer.parseInt(""+ESIQuiz.dateActuelle.charAt(3)+ESIQuiz.dateActuelle.charAt(4));
		int anneeActuelle = Integer.parseInt(""+ESIQuiz.dateActuelle.charAt(6)+ESIQuiz.dateActuelle.charAt(7)+ESIQuiz.dateActuelle.charAt(8)+ESIQuiz.dateActuelle.charAt(9));
		if (anneeActuelle>anneeOuverture) ouvert = true;
		else 
		{
			if (anneeActuelle==anneeOuverture) 
			{
				if (moisActuelle>moisOuverture) ouvert = true;
				else 
				{
					if (moisActuelle==moisOuverture)
					{
						if (jourActuelle>jourOuverture) ouvert = true;
						else
						{
							if (jourActuelle==jourOuverture) ouvert = true;
						}
					}
				}
			}
		}
		if (ouvert) {if (quizExpire()) ouvert = false;}
		return ouvert;
	}
	
	public boolean quizExpire()
	{
		boolean expire = false;
		int jourExpiration = Integer.parseInt(""+dateExpiration.charAt(0)+dateExpiration.charAt(1));
		int moisExpiration = Integer.parseInt(""+dateExpiration.charAt(3)+dateExpiration.charAt(4));
		int anneeExpiration = Integer.parseInt(""+dateExpiration.charAt(6)+dateExpiration.charAt(7)+dateExpiration.charAt(8)+dateExpiration.charAt(9));
		int jourActuelle = Integer.parseInt(""+ESIQuiz.dateActuelle.charAt(0)+ESIQuiz.dateActuelle.charAt(1));
		int moisActuelle = Integer.parseInt(""+ESIQuiz.dateActuelle.charAt(3)+ESIQuiz.dateActuelle.charAt(4));
		int anneeActuelle = Integer.parseInt(""+ESIQuiz.dateActuelle.charAt(6)+ESIQuiz.dateActuelle.charAt(7)+ESIQuiz.dateActuelle.charAt(8)+ESIQuiz.dateActuelle.charAt(9));
		if (anneeActuelle>anneeExpiration) expire = true;
		else 
		{
			if (anneeActuelle==anneeExpiration) 
			{
				if (moisActuelle>moisExpiration) expire = true;
				else 
				{
					if (moisActuelle==moisExpiration)
					{
						if (jourActuelle>jourExpiration) expire = true;
					}
				}
			}
		}
		return expire;
	}
	
	public void afficherQuiz()
	{
		System.out.println("********** Quiz: "+nom+" **********");
		System.out.println("Date d'ouverture : "+dateOuverture);
		System.out.println("Date d'expiration : "+dateExpiration);
		System.out.println("Nom de la formation : "+nomFormation);
		if (nbQuestions>0) System.out.println("Les questions : ");
		for (int i=0;i<nbQuestions;i++)
		{
			String classe = ensembleQuestions.get(i).getClass().toString();
			char car = classe.charAt(classe.length()-1);
			if (car=='M') System.out.println("Qestion "+(i+1)+" (QCM) : ");
			else
			{
				if (car=='U') System.out.println("Qestion "+(i+1)+" (QCU) : ");
				else System.out.println("Qestion "+(i+1)+" (QO) : ");
			}
			ensembleQuestions.get(i).afficherQuestion();
		}
	}
	
	public String returnChaineQuiz()
	{
		String chaine = "********** Quiz: "+nom+" **********"+"\n";
		chaine = chaine+"Date d'ouverture : "+dateOuverture+"\n";
		chaine = chaine+"Date d'expiration : "+dateExpiration+"\n";
		chaine = chaine+"Nom de la formation : "+nomFormation+"\n";
		if (nbQuestions>0) chaine = chaine+"Les questions : "+"\n";
		for (int i=0;i<nbQuestions;i++)
		{
			String classe = ensembleQuestions.get(i).getClass().toString();
			char car = classe.charAt(classe.length()-1);
			if (car=='M') chaine = chaine+"Qestion "+(i+1)+" (QCM) : "+"\n";
			else
			{
				if (car=='U') chaine = chaine+"Qestion "+(i+1)+" (QCU) : "+"\n";
				else chaine = chaine+"Qestion "+(i+1)+" (QO) : "+"\n";
			}
			chaine = chaine+ensembleQuestions.get(i).returnChaineQuestion();
		}
		return chaine;
	}
	
	public void supprimerQuestion(int numQuestion)
	{
		if ((!(quizOuvert()))&&(!(quizExpire())))
		{
			if ((numQuestion<=nbQuestions)&&(numQuestion>0))
			{
				ensembleQuestions.set(numQuestion-1,ensembleQuestions.get(nbQuestions-1));
				ensembleQuestions.remove(nbQuestions-1);
				nbQuestions--;
			}
			else System.out.println("Erreur au niveau du numéro de la question !");
		}
		else System.out.println("Vous ne pouvez pas effectuer des modifications sur le Quiz lorsqu'il est ouvert ou expiré !");
	}
	
	public void changerQuestion(Question question,int numQuestion)
	{
		if ((!(quizOuvert()))&&(!(quizExpire())))
		{
			if ((numQuestion<=nbQuestions)&&(numQuestion>0))
			{
				ensembleQuestions.set(numQuestion-1,question);
			}
			else System.out.println("Erreur au niveau du numéro de la question !");
		}
		else System.out.println("Vous ne pouvez pas effectuer des modifications sur le Quiz lorsqu'il est ouvert ou expiré !");
	}
	
	public void ajouterQuestion(Question question)
	{
		if (question!=null)
		{
			if ((!(quizOuvert()))&&(!(quizExpire())))
			{
				ensembleQuestions.add(question);
				nbQuestions++;
			}
			else System.out.println("Vous ne pouvez pas effectuer des modifications sur le Quiz lorsqu'il est ouvert ou expiré !");
		}
	}
}
