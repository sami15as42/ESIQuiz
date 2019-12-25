package TpPoo;

import java.util.*;

public class Apprenant extends Utilisateur
{
	private ArrayList<Formation> listeFormations = new ArrayList<Formation>();
	private int nbFormations;
	private ArrayList<QuizA> quizAccomplis = new ArrayList<QuizA>();
	private int nbQuizAccomplis;
	private ArrayList<QuizA> quizNonAccomplis = new ArrayList<QuizA>();
	private int nbQuizNonAccomplis;
	
	public Apprenant(String nom,String prenom,String dateNaissance,String adresse)
	{
		super(nom,prenom,dateNaissance,adresse);
		nbFormations = 0;
		nbQuizAccomplis = 0;
		nbQuizNonAccomplis = 0;
	}
	
	public void afficherQuizOuverts()
	{
		if (nbFormations>0) System.out.println("Les quiz ouverts sont : ");
		else System.out.println("Aucun quiz n'est ouvert.");
		for (int i=0;i<nbFormations;i++) listeFormations.get(i).afficherQuizOuverts();
	}
	
	public void afficherQuizAccomplis()
	{
		if (nbQuizAccomplis>0) System.out.println("Les quiz accomplis sont : ");
		else System.out.println("Aucun quiz n'est accomlis.");
		for (int i=0;i<nbQuizAccomplis;i++)
		{
			System.out.println("************************************************");
			System.out.println("Nom du quiz : "+quizAccomplis.get(i).getQuiz().getNom());
			System.out.println("Nom de la formation : "+quizAccomplis.get(i).getQuiz().getNomF());
			System.out.println("Date d'ouverture : "+quizAccomplis.get(i).getQuiz().getDateOuverture());
			System.out.println("Date d'expiration : "+quizAccomplis.get(i).getQuiz().getDateExpiration());
			System.out.println("Taux de réussite : "+quizAccomplis.get(i).getTauxReussite()+"%");
			System.out.println("************************************************");
		}
	}
	
	public void ajouterFormation(Formation nomFormation)
	{
		if (nomFormation!=null)
		{
			boolean trouv = false;
			int i = 0;
			while ((!trouv)&&(i<nbFormations))
			{
				if (listeFormations.get(i).getNom().equalsIgnoreCase(nomFormation.getNom())) trouv = true;
				else i++;
			}
			if (!trouv)
			{
				listeFormations.add(nomFormation);
				nbFormations++;
			}
		}
	}
	
	public double moyenneQuizExpire()
	{
		double moy = 0;
		int nbQuiz = 0;
		for (int i=0;i<nbQuizAccomplis;i++) {if (quizAccomplis.get(i).getQuiz().quizExpire()) moy = moy + quizAccomplis.get(i).getTauxReussite();}
		for (int i=0;i<nbFormations;i++) nbQuiz = nbQuiz + listeFormations.get(i).getNbQuizExpire();
		if (nbQuiz>0) return ((double)moy/nbQuiz);
		else return -1;
	}
	
	public ArrayList<Quiz> chercherQuizNonAccomplis()
	{
		ArrayList<Quiz> list = new ArrayList<Quiz>();
		ArrayList<Quiz> listSec = new ArrayList<Quiz>();
		for (int i=0;i<nbFormations;i++)
		{
			listSec = listeFormations.get(i).getListeQuiz();
			for (int k=0;k<listSec.size();k++) {if (rechercheQuizDansQuizAccomplis(listSec.get(k))==null) list.add(listSec.get(k));}
		}
		return list;
	}
	
	public void afficherQuizNonAccomplis()
	{
		ArrayList<Quiz> list = chercherQuizNonAccomplis();
		if (list.size()>0) System.out.println("Voici la liste des quiz non accomplis : ");
		else System.out.println("Pas de quiz non accomplis.");
		for (int i=0;i<list.size();i++)
		{
			QuizA quiz = rechercheQuizDansQuizNonAccomplis(list.get(i));
			if (quiz==null)
			{
				System.out.println("*********************************");
				System.out.println("Nom du quiz : "+list.get(i).getNom());
				System.out.println("Nom de la formation : "+list.get(i).getNomF());
				System.out.println("Date d'ouverture : "+list.get(i).getDateOuverture());
				System.out.println("Date d'expiration : "+list.get(i).getDateExpiration());
				System.out.println("Taux d'accomplissement : 0%");
				System.out.println("*********************************");
			}
			else
			{
				System.out.println("*********************************");
				System.out.println("Nom du quiz : "+list.get(i).getNom());
				System.out.println("Nom de la formation : "+list.get(i).getNomF());
				System.out.println("Date d'ouverture : "+list.get(i).getDateOuverture());
				System.out.println("Date d'expiration : "+list.get(i).getDateExpiration());
				System.out.println("Taux d'accomplissement : "+quiz.getTauxAccomplissement()+"%");
				System.out.println("*********************************");
			}
		}
	}
		
	public QuizA rechercheQuizDansQuizAccomplis(Quiz quiz)
	{
		QuizA quizA = null;
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbQuizAccomplis))
		{
			if (quizAccomplis.get(i).getQuiz().equals(quiz)) 
			{
				trouv = true;
				quizA = quizAccomplis.get(i);
			}
			else i++;
		}
		return quizA;
	}
	
	public QuizA rechercheQuizDansQuizNonAccomplis(Quiz quiz)
	{
		QuizA quizA = null;
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbQuizNonAccomplis))
		{
			if (quizNonAccomplis.get(i).getQuiz().equals(quiz)) 
			{
				trouv = true;
				quizA = quizNonAccomplis.get(i);
			}
			else i++;
		}
		return quizA;
	}
	
	public void RepondreQuiz(String nom)
	{
		try 
		{
			ArrayList<Quiz> liste = chercherQuizNonAccomplis();
			boolean stop = false;
			int i = 0;
			while ((!stop)&&(i<liste.size()))
			{
				if (liste.get(i).getNom().equalsIgnoreCase(nom)) stop = true;
				else i++;
			}
			if (stop)
			{
				if (liste.get(i).quizOuvert())
				{
					QuizA quizA = rechercheQuizDansQuizNonAccomplis(liste.get(i));
					if (quizA!=null)
					{
						stop=false;
						while (!stop)
						{
							quizA.afficherQuizA();
							System.out.println("Répondre au quiz : ");
							System.out.println("1. Ajouter une réponse");
							System.out.println("2. Modifier une réponse");
							System.out.println("3. Quitter");
							System.out.println("Entrer votre choix : ");
							Scanner sc = new Scanner(System.in);
							int choix = sc.nextInt();
							if ((choix>0)&&(choix<4))
							{
								if (choix==1)
								{
									System.out.println("Entrer le numero de la question : ");
									sc = new Scanner(System.in);
									int num = sc.nextInt();
									if ((num<=quizA.getQuiz().getNbQuestions())&&(num>0))
									{
										Question question = quizA.getQuiz().getQuestion(num-1);
										String classe = question.getClass().toString();
										char car = classe.charAt(classe.length()-1);
										if (car=='M')
										{
											System.out.println("Entrer le nombre de réponses : ");
											sc = new Scanner(System.in);
											num = sc.nextInt();
											String[] chaines = new String[num];
											for (i=0;i<num;i++)
											{
												System.out.println("Entrer votre réponse : ");
												sc = new Scanner(System.in);
												chaines[i]=sc.nextLine();
											}
											quizA.ajouterReponse(new Reponse(chaines,question));
										}
										if (car=='U')
										{
											String[] chaines = new String[1];
											System.out.println("Entrer votre réponse : ");
											sc = new Scanner(System.in);
											chaines[0]=sc.nextLine();
											quizA.ajouterReponse(new Reponse(chaines,question));
										}
										if (car=='O')
										{
											String[] chaines = new String[1];
											System.out.println("Entrer votre réponse : ");
											sc = new Scanner(System.in);
											chaines[0]=sc.nextLine();
											quizA.ajouterReponse(new Reponse(chaines,question));
										}
									}
									else System.out.println("Votre choix n'est pas valide !");
								}
								if (choix==2)
								{
									System.out.println("Entrer le numero de la question : ");
									sc = new Scanner(System.in);
									int num = sc.nextInt();
									if ((num<=quizA.getQuiz().getNbQuestions())&&(num>0))
									{
										Question question = quizA.getQuiz().getQuestion(num-1);
										String classe = question.getClass().toString();
										char car = classe.charAt(classe.length()-1);
										if (car=='M')
										{
											System.out.println("Entrer le nombre de réponses : ");
											sc = new Scanner(System.in);
											num = sc.nextInt();
											String[] chaines = new String[num];
											for (i=0;i<num;i++)
											{
												System.out.println("Entrer votre réponse : ");
												sc = new Scanner(System.in);
												chaines[i]=sc.nextLine();
											}
											Reponse rep = quizA.getReponse(question);
											if (rep!=null) rep.modifierReponse(chaines);
										}
										if (car=='U')
										{
											String[] chaines = new String[1];
											System.out.println("Entrer votre réponse : ");
											sc = new Scanner(System.in);
											chaines[0]=sc.nextLine();
											Reponse rep = quizA.getReponse(question);
											if (rep!=null) rep.modifierReponse(chaines);
										}
										if (car=='O')
										{
											String[] chaines = new String[1];
											System.out.println("Entrer votre réponse : ");
											sc = new Scanner(System.in);
											chaines[0]=sc.nextLine();
											Reponse rep = quizA.getReponse(question);
											if (rep!=null) rep.modifierReponse(chaines);
										}
									}
									else System.out.println("Votre choix n'est pas valide !");
								}
							}
							else System.out.println("Votre choix n'est pas disponible !");
							System.out.println("Est ce que vous voulez repondre à une autre question ? (o/n)");
							sc = new Scanner(System.in);
							String cha = sc.nextLine();
							if (!(cha.equalsIgnoreCase("o"))) stop = true;
						}
						if (quizA.getTauxAccomplissement()==100)
						{
							quizAccomplis.add(quizA);
							nbQuizAccomplis++;
							boolean trouv = false;
							i = 0;
							while (!trouv)
							{
								if (quizNonAccomplis.get(i).equals(quizA)) trouv = true;
								else i++;
							}
							quizNonAccomplis.remove(i);
							nbQuizNonAccomplis--;
							quizA.evaluation();
							System.out.println("Votre resultat : ");
							quizA.afficherQuizA();
						}
					}
					else 
					{
						Quiz quiz = liste.get(i);
						quizA = new QuizA(quiz);
						quizNonAccomplis.add(quizA);
						nbQuizNonAccomplis++;
						stop=false;
						while (!stop)
						{
							quizA.afficherQuizA();
							System.out.println("Répondre au quiz : ");
							System.out.println("1. Ajouter une réponse");
							System.out.println("2. Modifier une réponse");
							System.out.println("3. Quitter");
							System.out.println("Entrer votre choix : ");
							Scanner sc = new Scanner(System.in);
							int choix = sc.nextInt();
							if ((choix>0)&&(choix<4))
							{
								if (choix==1)
								{
									System.out.println("Entrer le numero de la question : ");
									sc = new Scanner(System.in);
									int num = sc.nextInt();
									if ((num<=quizA.getQuiz().getNbQuestions())&&(num>0))
									{
										Question question = quizA.getQuiz().getQuestion(num-1);
										String classe = question.getClass().toString();
										char car = classe.charAt(classe.length()-1);
										if (car=='M')
										{
											System.out.println("Entrer le nombre de réponses : ");
											sc = new Scanner(System.in);
											num = sc.nextInt();
											String[] chaines = new String[num];
											for (i=0;i<num;i++)
											{
												System.out.println("Entrer votre réponse : ");
												sc = new Scanner(System.in);
												chaines[i]=sc.nextLine();
											}
											quizA.ajouterReponse(new Reponse(chaines,question));
										}
										if (car=='U')
										{
											String[] chaines = new String[1];
											System.out.println("Entrer votre réponse : ");
											sc = new Scanner(System.in);
											chaines[0]=sc.nextLine();
											quizA.ajouterReponse(new Reponse(chaines,question));
										}
										if (car=='O')
										{
											String[] chaines = new String[1];
											System.out.println("Entrer votre réponse : ");
											sc = new Scanner(System.in);
											chaines[0]=sc.nextLine();
											quizA.ajouterReponse(new Reponse(chaines,question));
										}
									}
									else System.out.println("Votre choix n'est pas valide !");
								}
								if (choix==2)
								{
									System.out.println("Entrer le numero de la question : ");
									sc = new Scanner(System.in);
									int num = sc.nextInt();
									if ((num<=quizA.getQuiz().getNbQuestions())&&(num>0))
									{
										Question question = quizA.getQuiz().getQuestion(num-1);
										String classe = question.getClass().toString();
										char car = classe.charAt(classe.length()-1);
										if (car=='M')
										{
											System.out.println("Entrer le nombre de réponses : ");
											sc = new Scanner(System.in);
											num = sc.nextInt();
											String[] chaines = new String[num];
											for (i=0;i<num;i++)
											{
												System.out.println("Entrer votre réponse : ");
												sc = new Scanner(System.in);
												chaines[i]=sc.nextLine();
											}
											Reponse rep = quizA.getReponse(question);
											if (rep!=null) rep.modifierReponse(chaines);
										}
										if (car=='U')
										{
											String[] chaines = new String[1];
											System.out.println("Entrer votre réponse : ");
											sc = new Scanner(System.in);
											chaines[0]=sc.nextLine();
											Reponse rep = quizA.getReponse(question);
											if (rep!=null) rep.modifierReponse(chaines);
										}
										if (car=='O')
										{
											String[] chaines = new String[1];
											System.out.println("Entrer votre réponse : ");
											sc = new Scanner(System.in);
											chaines[0]=sc.nextLine();
											Reponse rep = quizA.getReponse(question);
											if (rep!=null) rep.modifierReponse(chaines);
										}
									}
									else System.out.println("Votre choix n'est pas valide !");
								}
							}
							else System.out.println("Votre choix n'est pas disponible !");
							System.out.println("Est ce que vous voulez repondre à une autre question ? (o/n)");
							sc = new Scanner(System.in);
							String cha = sc.nextLine();
							if (!(cha.equalsIgnoreCase("o"))) stop = true;
						}
						if (quizA.getTauxAccomplissement()==100)
						{
							quizAccomplis.add(quizA);
							nbQuizAccomplis++;
							boolean trouv = false;
							i = 0;
							while (!trouv)
							{
								if (quizNonAccomplis.get(i).equals(quizA)) trouv = true;
								else i++;
							}
							quizNonAccomplis.remove(i);
							nbQuizNonAccomplis--;
							quizA.evaluation();
							System.out.println("Votre resultat : ");
							quizA.afficherQuizA();
						}
					}
				}
			}
		}
		catch (InputMismatchException e)
		{
			System.err.println("Erreur de saisie !");
		}
	}
}
