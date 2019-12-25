package TpPoo;

import java.util.*;

public class Notion 
{
	private String nom;
	private ArrayList<Question> listeQuestions = new ArrayList<Question>();
	private int nbQuestions;
	
	public Notion(String nom)
	{
		nbQuestions=0;
		this.nom=nom;
	}
	
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public void ajouterQuestion(Question question)
	{
		if (question!=null)
		{
			boolean trouv = false;
			int i = 0;
			while ((!trouv)&&(i<nbQuestions))
			{
				if (listeQuestions.get(i)==question) trouv = true;
				else i++;
			}
			if (!trouv)
			{
				listeQuestions.add(question);
				nbQuestions++;
			}
		}
	}
	
	public void changerQuestion(Question question,int numQuestion)
	{
		if ((numQuestion>0)&&(numQuestion<=nbQuestions)) listeQuestions.set(numQuestion-1,question);
		else System.out.println("Erreur au niveau du numéro de la question");
	}
	
	public void afficherQuestions()
	{
		System.out.println("Les questions disponibles pour la notion "+nom+" sont : ");
		for (int i=0;i<nbQuestions;i++)
		{
			System.out.println("Question "+(i+1)+" : ");
			listeQuestions.get(i).afficherQuestion();
		}
	}
	
	public void supprimerQuestion(int numQuestion)
	{
		if ((numQuestion>0)&&(numQuestion<=nbQuestions))
		{
			listeQuestions.set(numQuestion-1,listeQuestions.get(nbQuestions-1));
			listeQuestions.remove(nbQuestions-1);
			nbQuestions--;
		}
		else System.out.println("Erreur au niveau du numéro de la question");
	}
	
	public Question[] genererQuestions(int n) 
	{
		if (n<=nbQuestions)
		{
			Question[] questions = new Question[n];
			double rand = Math.random();
			int choix;
			if (rand>50) choix=3;
			else 
			{
				if (rand>25) choix=2;
				else choix=1;
			}
			if (choix==1)
			{
				for (int i=0;i<n;i++) questions[i]=listeQuestions.get(i);
				return questions;
			}
			else 
			{
				if (choix==2)
				{
					for (int i=0;i<n;i++) questions[i]=listeQuestions.get(n-i-1);
					return questions;
				}
				else
				{
					int cpt=0;
					for (int i=0;i<n;i++) 
					{
						if (Math.floorMod(i,2)==0) questions[i]=listeQuestions.get(cpt);
						else {questions[i]=listeQuestions.get(n-cpt-1);cpt++;}
					}
					return questions;
				}
			}
		}
		else
		{
			Question[] questions = new Question[nbQuestions];
			for (int i=0;i<nbQuestions;i++) questions[i]=listeQuestions.get(i);
			return questions;
		}
	}
}
