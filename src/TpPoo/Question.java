package TpPoo;

public interface Question
{
	boolean correcte(String o);
	void afficherQuestion();
	void afficherQuestionMasque();
	void modifierQuestion();
	String returnChaineQuestion();
}
