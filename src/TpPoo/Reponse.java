package TpPoo;

public class Reponse 
{
	private Question question;
	private String[] chaines;
	private int nbReponses;
	
	public Reponse(String[] chaines,Question question)
	{
		this.question=question;
		this.chaines=chaines;
		nbReponses = chaines.length;
	}
	
	public void afficherReponse()
	{
		for (int i=0;i<nbReponses;i++)
		{
			System.out.println(" - "+chaines[i]);
		}
	}
	
	public void modifierReponse(String[] chaines)
	{
		this.chaines=chaines;
	}
	
	public double evaluationQCM()
	{
		return ((QCM)question).evaluation(chaines);
	}
	
	public double evaluationQCU()
	{
		if (question.correcte(chaines[0])) return 1;
		else return 0;
	}
	
	public double evaluationQO()
	{
		if (question.correcte(chaines[0])) return 1;
		else return 0;
	}
	
	public Question getQuestion()
	{
		return question;
	}
	
	public String[] getChaine()
	{
		return chaines;
	}
}
