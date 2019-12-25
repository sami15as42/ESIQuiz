package TpPoo;

public class Main 
{

	public static void main(String[] args) 
	{
		ESIQuiz esiQuiz = new ESIQuiz();
		esiQuiz.creerFormateur("Assenine", "Sami", "14/07/1999", "cite AADL-Ouled Yaich-Blida-Algerie");
		Formateur formateur = esiQuiz.authentificationFormateur("sassenine","assenine14/07/1999"); 
		Formation formation = formateur.creerFormation("java formation", "une formation gratuite en java", "12/04/2019", "12/05/2019");
		Apprenant apprenant1 = formateur.creerCompte("Ait saadi", "Madjid", "20/02/1999", "Baba ali-Alger");
		formateur.creerCompte("Laghoub", "Nassim", "18/09/1999", "Ain Naadja-Alger");
		Apprenant apprenant2 = esiQuiz.authentificationApprenant("nlaghoub","laghoub18/09/1999");
		Apprenant apprenant3 = formateur.creerCompte("Tayeb Cherif", "Yacine", "05/03/2000", "Boumerdes");
		Apprenant[] tab = {apprenant1,apprenant2,apprenant3};
		formateur.ajouterApprenantsFormation(tab, "java formation");
		/*formateur.modifierCompte("mait saadi");
		formateur.consulterApprenant("Ait Saadi", "madjid");
		formateur.supprimerCompte("mait saadi");
		formateur.consulterApprenant("Ait Saadi", "madjid");*/
		Notion notion1 = formateur.introduireNotion("Les capitales");
		Notion notion2 = formateur.introduireNotion("Musique");
		Notion notion3 = formateur.introduireNotion("La coupe du monde");
		Notion notion4 = formateur.introduireNotion("CAN");
		Notion notion5 = formateur.introduireNotion("poo");
		Notion notion6 = formateur.introduireNotion("président");
		Notion[] tabNotions = {notion1,notion2,notion3,notion4,notion5,notion6};
		formateur.ajouterNotionsFormation("Java formation",tabNotions);
		formation.afficherNotions();
		/***************************/
		Question question1 = formateur.creerQuestion();
		Question question2 = formateur.creerQuestion();
		Question question3 = formateur.creerQuestion();
		Question question4 = formateur.creerQuestion();
		Question question5 = formateur.creerQuestion();
		Question question6 = formateur.creerQuestion();
		Question question7 = formateur.creerQuestion();
		Question[] tab1 = {question1,question2};
		Question[] tab2 = {question3};
		Question[] tab3 = {question4};
		Question[] tab4 = {question5};
		Question[] tab5 = {question6};
		Question[] tab6 = {question7};
		formateur.ajouterQuestionsNotion(notion1,tab1);
		formateur.ajouterQuestionsNotion(notion2,tab2);
		formateur.ajouterQuestionsNotion(notion3,tab3);
		formateur.ajouterQuestionsNotion(notion4,tab4);
		formateur.ajouterQuestionsNotion(notion5,tab5);
		formateur.ajouterQuestionsNotion(notion6,tab6);
		/***********************************/
		Quiz quiz1 = formateur.creerQuiz("quiz java formation", "15/04/2019", "20/06/2019");
		Quiz quiz2 = formateur.creerQuiz("quiz culture générale", "16/04/2019", "29/07/2019");
		Quiz quiz3 = formateur.creerQuiz("quiz culture sport", "16/04/2019", "28/07/2019");
		formateur.sauvegarderQuiz(quiz1);
		formateur.sauvegarderQuiz(quiz2);
		formateur.sauvegarderQuiz(quiz3);
		formateur.afficherQuiz("Quiz JaVa formation", "java Formation");
		formateur.afficherQuiz("Quiz culture générale", "java Formation");
		formateur.afficherQuiz("Quiz culture sport", "java Formation");
		
		/*formateur.modifierQuiz("quiz java formation", "java formation");
		formateur.modifierQuiz("quiz1", "java formation");
		formateur.modifierQuiz("quiz1", "java formation");
		formateur.modifierQuiz("quiz1", "java formation");*/
		
		apprenant2.afficherQuizOuverts();
		apprenant2.RepondreQuiz("quiz java formation");
		apprenant2.RepondreQuiz("quiz culture générale");
		apprenant2.RepondreQuiz("quiz culture sport");
		apprenant2.afficherQuizAccomplis();
		apprenant2.afficherQuizNonAccomplis();
	}
	
}
