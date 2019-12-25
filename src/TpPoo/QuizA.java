package TpPoo;

import java.util.*;

public class QuizA 
{
	private Quiz quiz;
	private double tauxReussite,tauxAccomplissement;
	private ArrayList<Reponse> reponses = new ArrayList<Reponse>();
	private int nbReponses;
	
	public QuizA(Quiz quiz)
	{
		this.quiz=quiz;
		nbReponses=0;
		tauxReussite=-1;
		tauxAccomplissement=0;
	}
	
	public Quiz getQuiz()
	{
		return quiz;
	}
	
	public double getTauxReussite()
	{
		return tauxReussite;
	}
	
	public double getTauxAccomplissement()
	{
		return tauxAccomplissement;
	}
	
	public Reponse getReponse(Question question)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbReponses))
		{
			if (reponses.get(i).getQuestion().equals(question)) trouv = true;
			else i++;
		}
		if (trouv) return reponses.get(i);
		else return null;
	}

	public void afficherQuizA()
	{
		System.out.println("********** Quiz: "+quiz.getNom()+" **********");
		System.out.println("Date d'ouverture : "+quiz.getDateOuverture());
		System.out.println("Date d'expiration : "+quiz.getDateExpiration());
		System.out.println("Nom de la formation : "+quiz.getNomF());
		if (tauxReussite!=-1) System.out.println("Taux de reussite : "+tauxReussite+"%");
		ArrayList<Question> list = quiz.getEnsembleQuestions();
		for (int i=0;i<quiz.getNbQuestions();i++)
		{
			String classe = list.get(i).getClass().toString();
			char car = classe.charAt(classe.length()-1);
			if (car=='M') System.out.println("Qestion "+(i+1)+" (QCM) : ");
			else
			{
				if (car=='U') System.out.println("Qestion "+(i+1)+" (QCU) : ");
				else System.out.println("Qestion "+(i+1)+" (QO) : ");
			}
			if (tauxReussite==-1) list.get(i).afficherQuestionMasque();
			else list.get(i).afficherQuestion();
			Reponse reponse = chercherReponse(list.get(i));
			if (reponse!=null)
			{
				System.out.println("Votre reponse :");
				reponse.afficherReponse();
			}
		}
	}
	
	public Reponse chercherReponse(Question question) //charcher dans le tableau "reponses" une reponse dont la question est "question"
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbReponses))
		{
			if (reponses.get(i).getQuestion().equals(question)) trouv = true;
			else i++;
		}
		if (trouv) return reponses.get(i);
		else return null;
	}
	
	public void ajouterReponse(Reponse reponse)
	{
		if (reponse!=null)
		{
			if (quiz.quizOuvert()) 
			{
				if (chercherReponse(reponse.getQuestion())==null)
				{
					reponses.add(reponse);
					nbReponses++;
					tauxAccomplissement=((double) nbReponses/quiz.getNbQuestions())*100;
				}
			}
			else System.out.println("Vous ne pouvez pas repondre !");
		}
	}
	
	public void evaluation()
	{
		tauxReussite = 0;
		if (tauxAccomplissement==100)
		{
			double note = 0;
			for (int i=0;i<nbReponses;i++)
			{
				String classe = reponses.get(i).getQuestion().getClass().toString();
				char car = classe.charAt(classe.length()-1);
				if (car=='M') note=reponses.get(i).evaluationQCM();
				else
				{
					if (car=='U') note=reponses.get(i).evaluationQCU();
					else note=reponses.get(i).evaluationQO();
				}
				tauxReussite=tauxReussite+(100./nbReponses)*note;
			}
		}
		else System.out.println("Vous ne pouvez pas evaluer votre quiz tant que vous n'avez pas répondu à toutes les questions !");
	}
}
