package TpPoo;

import java.util.*;

public class Formateur extends Utilisateur 
{
	private ArrayList<Formation> listeFormations = new ArrayList<Formation>();
	private int nbFormations;
	private ArrayList<Apprenant> listeApprenants = new ArrayList<Apprenant>();
	private int nbApprenants;
	
	public Formateur(String nom,String prenom,String dateNaissance,String adresse)
	{
		super(nom,prenom,dateNaissance,adresse);
		nbFormations=0;
		nbApprenants=0;
	}
	
	public int getNbFormations()
	{
		return nbFormations;
	}
	
	public ArrayList<Formation> getFormations()
	{
		return listeFormations;
	}
	
	public Formation creerFormation(String nom,String description,String dateDebut,String dateFin)
	{
		Formation formation = null;
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbFormations))
		{
			if (listeFormations.get(i).getNom().equalsIgnoreCase(nom)) trouv = true;
			else i++;
		}
		if (!trouv)
		{
			formation = new Formation(nom,description,dateDebut,dateFin);
			listeFormations.add(formation);
			nbFormations++;
		}
		return formation;
	}
	
	public Apprenant creerCompte(String nom,String prenom,String dateNaissance,String adresse)
	{
		boolean trouv = false;
		int i = ESIQuiz.nbApprenants;
		while ((!trouv)&&(i<ESIQuiz.nbApprenants))
		{
			if ((ESIQuiz.listeApprenants.get(i).getNom().equalsIgnoreCase(nom))&&(ESIQuiz.listeApprenants.get(i).getPrenom().equalsIgnoreCase(prenom))&&(ESIQuiz.listeApprenants.get(i).getAdresse().equalsIgnoreCase(adresse))&&(ESIQuiz.listeApprenants.get(i).getDateNaissance().equals(dateNaissance))) trouv = true;
			else i++;
		}
		if (!trouv)
		{
			Apprenant apprenant = new Apprenant(nom,prenom,dateNaissance,adresse);
			listeApprenants.add(apprenant);
			nbApprenants++;
			ESIQuiz.listeApprenants.add(apprenant);
			ESIQuiz.nbApprenants++;
			return apprenant;
		}
		else
		{
			Apprenant apprenant = ESIQuiz.listeApprenants.get(i);
			trouv = false;
			i = 0;
			while ((!trouv)&&(i<nbApprenants))
			{
				if ((listeApprenants.get(i).getNom().equalsIgnoreCase(apprenant.getNom()))&&(listeApprenants.get(i).getPrenom().equalsIgnoreCase(apprenant.getPrenom()))) trouv = true;
				else i++;
			}
			if (!trouv)
			{
				listeApprenants.add(apprenant);
				nbApprenants++;
			}
			return apprenant;
		}
	}
	
	public void modifierCompte(String login)
	{
		try
		{
			boolean trouv = false;
			int i = 0;
			while ((!trouv)&&(i<nbApprenants))
			{
				if (listeApprenants.get(i).getCompte().getLogin().equals(login)) trouv = true;
				else i++;
			}
			if (trouv)
			{
				System.out.println("Modifier Compte : ");
				System.out.println("1. Modifier mot de passe");
				System.out.println("2. Modifier le nom");
				System.out.println("3. Modifier le prenom");
				System.out.println("4. Modifier la date de naissance");
				System.out.println("5. Modifier l'adresse");
				System.out.println("Entrer votre choix =>");
				Scanner sc = new Scanner(System.in);
				int choix = sc.nextInt();
				if ((choix>0)&&(choix<6))
				{
					if (choix==1)
					{
						System.out.println("Entrer le nouveau mot de passe : ");
						sc = new Scanner(System.in);
						String chaine = sc.nextLine();
						listeApprenants.get(i).getCompte().setMotPasse(chaine);
					}
					if (choix==2)
					{
						System.out.println("Entrer le nouveau nom : ");
						sc = new Scanner(System.in);
						String chaine = sc.nextLine();
						listeApprenants.get(i).setNom(chaine);
					}
					if (choix==3)
					{
						System.out.println("Entrer le nouveau prenom : ");
						sc = new Scanner(System.in);
						String chaine = sc.nextLine();
						listeApprenants.get(i).setPrenom(chaine);
					}
					if (choix==4)
					{
						System.out.println("Entrer la nouvelle date de naissance : ");
						sc = new Scanner(System.in);
						String chaine = sc.nextLine();
						listeApprenants.get(i).setDate(chaine);
					}
					if (choix==5)
					{
						System.out.println("Entrer la nouvelle adresse : ");
						sc = new Scanner(System.in);
						String chaine = sc.nextLine();
						listeApprenants.get(i).setAdresse(chaine);
					}
				}
				else System.out.println("Votre choix n'est pas disponible !");
			}
		}
		catch (InputMismatchException e)
		{
			System.err.println("Erreur de saisie !");
		}
	}
	
	public void supprimerCompte(String login)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbApprenants))
		{
			if (listeApprenants.get(i).getCompte().getLogin().equals(login)) trouv = true;
			else i++;
		}
		if (trouv)
		{
			listeApprenants.set(i,listeApprenants.get(nbApprenants-1));
			listeApprenants.remove(nbApprenants-1);
			nbApprenants--;
		} 
	}
	
	public void ajouterApprenantsFormation(Apprenant[] tab,String formation)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbFormations))
		{
			if (listeFormations.get(i).getNom().equalsIgnoreCase(formation)) trouv = true;
			else i++;
		}
		if (trouv) listeFormations.get(i).ajouterApprenants(tab);
	}
	
	public void sauvegarderQuiz(Quiz quiz)
	{
		if (quiz!=null)
		{
			boolean trouv = false;
			int i = 0;
			while ((!trouv)&&(i<nbFormations))
			{
				if (listeFormations.get(i).getNom().equalsIgnoreCase(quiz.getNomF())) trouv = true;
				else i++;
			}
			if (trouv) listeFormations.get(i).ajouterQuiz(quiz);
		}
	}
	
	public void supprimerQuiz(String nomQuiz,String nomFormation)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbFormations))
		{
			if (listeFormations.get(i).getNom().equalsIgnoreCase(nomFormation)) trouv = true;
			else i++;
		}
		if (trouv) listeFormations.get(i).supprimerQuiz(nomQuiz);
	}
	
	public void modifierQuiz(String nomQuiz,String nomFormation)
	{
		try
		{
			boolean trouv = false;
			int i = 0;
			while ((!trouv)&&(i<nbFormations))
			{
				if (listeFormations.get(i).getNom().equalsIgnoreCase(nomFormation)) trouv = true;
				else i++;
			}
			if (trouv) 
			{
				Quiz quiz = listeFormations.get(i).chercherQuiz(nomQuiz);
				if (quiz!=null)
				{
					if ((!(quiz.quizOuvert()))&&(!(quiz.quizExpire())))
					{
						quiz.afficherQuiz();
						System.out.println("Modifier Quiz : ");
						System.out.println("1. Modifier le nom du quiz");
						System.out.println("2. Ajouter une question");
						System.out.println("3. Changer une question");
						System.out.println("4. Supprimer une question");
						System.out.println("Entrer votre choix =>");
						Scanner sc = new Scanner(System.in);
						int choix = sc.nextInt();
						if ((choix>0)&&(choix<5))
						{
							if (choix==1)
							{
								System.out.println("Entrer le nouveau nom : ");
								sc = new Scanner(System.in);
								String chaine = sc.nextLine();
								quiz.setNom(chaine);
							}
							if (choix==2)
							{
								Question question = creerQuestion();
								quiz.ajouterQuestion(question);
							}
							if (choix==3)
							{
								System.out.println("Entrer le numéro de la question : ");
								sc = new Scanner(System.in);
								int chaine = sc.nextInt();
								Question question = creerQuestion();
								quiz.changerQuestion(question,chaine);
							}
							if (choix==4)
							{
								System.out.println("Entrer le numéro de la question : ");
								sc = new Scanner(System.in);
								int chaine = sc.nextInt();
								quiz.supprimerQuestion(chaine);
							}
						}
						else System.out.println("Votre choix n'est pas disponible !");
					}
					else System.out.println("Vous ne pouvez pas effectuer des modifications sur le Quiz lorsqu'il est ouvert ou expiré !");
				}
			}
		}
		catch (InputMismatchException e)
		{
			System.err.println("Erreur de saisie !");
		}
	}
	
	public void afficherQuiz(String nomQuiz,String nomFormation)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbFormations))
		{
			if (listeFormations.get(i).getNom().equalsIgnoreCase(nomFormation)) trouv = true;
			else i++;
		}
		if (trouv) 
		{
			Quiz quiz = listeFormations.get(i).chercherQuiz(nomQuiz);
			if (quiz!=null) quiz.afficherQuiz();
		}
	}
	
	public void ajouterNotionsFormation(String nomFormation,Notion[] tab)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbFormations))
		{
			if (listeFormations.get(i).getNom().equalsIgnoreCase(nomFormation)) trouv = true;
			else i++;
		}
		if (trouv) listeFormations.get(i).ajouterNotions(tab);
	}
	
	public Notion introduireNotion(String nom)
	{
		return (new Notion(nom));
	}
	
	public void ajouterQuestionsNotion(Notion notion,Question[] tab)
	{
		for (int i=0;i<tab.length;i++)
		{
			if (tab[i]!=null) notion.ajouterQuestion(tab[i]);
		}
	}
	
	public void modifierNotion(String nomN,String nomF)
	{
		try
		{
			boolean trouv = false;
			int i = 0;
			while ((!trouv)&&(i<nbFormations))
			{
				if (listeFormations.get(i).getNom().equalsIgnoreCase(nomF)) trouv = true;
				else i++;
			}
			if (trouv)
			{
				Notion notion = listeFormations.get(i).chercherNotion(nomN);
				if (notion!=null)
				{
					notion.afficherQuestions();
					System.out.println("Modifier Notion : ");
					System.out.println("1. Modifier le nom de la notion");
					System.out.println("2. Ajouter une question");
					System.out.println("3. Changer une question");
					System.out.println("4. Supprimer une question");
					System.out.println("Entrer votre choix =>");
					Scanner sc = new Scanner(System.in);
					int choix = sc.nextInt();
					if ((choix>0)&&(choix<5))
					{
						if (choix==1)
						{
							System.out.println("Entrer le nouveau nom : ");
							String chaine = sc.nextLine();
							notion.setNom(chaine);
						}
						if (choix==2)
						{
							Question question = creerQuestion();
							notion.ajouterQuestion(question);
						}
						if (choix==3)
						{
							System.out.println("Entrer le numéro de la question : ");
							int chaine = sc.nextInt();
							Question question = creerQuestion();
							notion.changerQuestion(question,chaine);
						}
						if (choix==4)
						{
							System.out.println("Entrer le numéro de la question : ");
							int chaine = sc.nextInt();
							notion.supprimerQuestion(chaine);
						}
					}
					else System.out.println("Votre choix n'est pas disponible !");
				}
			}
		}
		catch (InputMismatchException e)
		{
			System.err.println("Erreur de saisie !");
		}
	}
	
	public void consulterApprenant(String nom,String prenom)
	{
		boolean trouv = false;
		int i = 0;
		while ((!trouv)&&(i<nbApprenants))
		{
			if ((listeApprenants.get(i).getNom().equalsIgnoreCase(nom))&&(listeApprenants.get(i).getPrenom().equalsIgnoreCase(prenom))) trouv = true;
			else i++;
		}
		if (trouv)
		{
			Apprenant a = listeApprenants.get(i);
			System.out.println("********** Apprenant **********");
			System.out.println("Nom et prénom : "+a.getNom()+" "+a.getPrenom());
			System.out.println("Date de naissance : "+a.getDateNaissance());
			System.out.println("Adresse : "+a.getAdresse());
			double moyenne = a.moyenneQuizExpire();
			if (moyenne==-1) System.out.println("La moyenne des quiz expiré : 0%");
			else System.out.println("La moyenne des quiz expiré : "+moyenne+"%");
			a.afficherQuizAccomplis();
		}
	}
	
	public void classementApprenant()
	{
		Apprenant[] tab1 = new Apprenant[nbApprenants];
		double[] tab2 = new double[nbApprenants];
		int nb = 0;
		for (int i=0;i<nbApprenants;i++)
		{
			tab1[i]=listeApprenants.get(i);
			tab2[i]=listeApprenants.get(i).moyenneQuizExpire();
			nb++;
		}
		double plusPetitEle;
		Apprenant a;
		int indice;
		for (int i=0;i<nb-1;i++)
		{
			plusPetitEle=tab2[i];
			a=tab1[i];
			indice=i;
			for (int k=i;k<nb;k++)
			{
				if (plusPetitEle>tab2[k])
				{
					plusPetitEle=tab2[k];
					a=tab1[k];
					indice=k;
				}
			}
			tab2[indice]=tab2[i];
			tab1[indice]=tab1[i];
			tab2[i]=plusPetitEle;
			tab1[i]=a;
		}
		System.out.println("Classement des apprenants du formateur '"+this.getNom()+" "+this.getPrenom()+"' : ");
		for (int i=nbApprenants-1;i>=0;i--)
		{
			System.out.println((nbApprenants-i)+". "+tab1[i].getNom()+" "+tab1[i].getPrenom()+" : "+tab2[i]+"%");
		}
	}
	
	public Question qcm(String[] propCorrectes,String[] propFausses,String enonce)
	{
		return (new QCM(propCorrectes,propFausses,enonce));
	}
	
	public Question qcu(String propCorrecte,String[] propFausses,String enonce)
	{
		return (new QCU(propCorrecte,propFausses,enonce));
	}
	
	public Question qo(String[] propCorrectes,String enonce)
	{
		return (new QO(propCorrectes,enonce));
	}
	
	public Question creerQuestion()
	{
		try
		{
			Question question = null;
			System.out.println("Création d'une question : ");
			System.out.println("1. QCM");
			System.out.println("2. QCU");
			System.out.println("3. QO");
			System.out.println("Entrer votre choix =>");
			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			if ((choix>0)&&(choix<4))
			{
				if (choix==1)
				{
					System.out.println("Entrer l'énoncé de la question : ");
					sc = new Scanner(System.in);
					String enonce = sc.nextLine();
					System.out.println("Entrer le nombre de propositions correctes : ");
					sc = new Scanner(System.in);
					int k = sc.nextInt();
					if (k>0)
					{
						String[] propCorrectes = new String[k];
						for (int i=0;i<k;i++)
						{
							System.out.println("Entrer une proposition correcte : ");
							sc = new Scanner(System.in);
							propCorrectes[i]=sc.nextLine();
						}
						System.out.println("Entrer le nombre de propositions fausses : ");
						sc = new Scanner(System.in);
						k = sc.nextInt();
						if (k>=0)
						{
							String[] propFausses = new String[k];
							for (int i=0;i<k;i++)
							{
								System.out.println("Entrer une proposition fausse : ");
								sc = new Scanner(System.in);
								propFausses[i]=sc.nextLine();
							}
							question = qcm(propCorrectes,propFausses,enonce);
						}
					}
				}
				else
				{
					if (choix==2)
					{
						System.out.println("Entrer l'énoncé de la question : ");
						sc = new Scanner(System.in);
						String enonce = sc.nextLine();
						System.out.println("Entrer le nombre de propositions fausses : ");
						sc = new Scanner(System.in);
						int k = sc.nextInt();
						if (k>=0)
						{
							String[] propFausses = new String[k];
							for (int i=0;i<k;i++)
							{
								System.out.println("Entrer une proposition fausse : ");
								sc = new Scanner(System.in);
								propFausses[i]=sc.nextLine();
							}
							System.out.println("Entrer la proposition correcte : ");
							sc = new Scanner(System.in);
							String propCorrecte = sc.nextLine();
							question = qcu(propCorrecte,propFausses,enonce);
						}
					}
					else
					{
						System.out.println("Entrer l'énoncé de la question : ");
						sc = new Scanner(System.in);
						String enonce = sc.nextLine();
						System.out.println("Entrer le nombre de propositions correctes : ");
						sc = new Scanner(System.in);
						int k = sc.nextInt();
						if (k>0)
						{
							String[] propCorrectes = new String[k];
							for (int i=0;i<k;i++)
							{
								System.out.println("Entrer une proposition correcte : ");
								sc = new Scanner(System.in);
								propCorrectes[i]=sc.nextLine();
							}
							question = qo(propCorrectes,enonce);
						}
					}
				}
			}
			else System.out.println("Votre choix n'est pas disponible !");
			return question;
		}
		catch (InputMismatchException e)
		{
			System.err.println("Erreur de saisie !");
			return null;
		}
	}
	
	public Quiz creerQuiz(String nom,String dateOuverture,String dateFermiture)
	{
		try
		{
			Quiz quiz = null;
			System.out.println("Génération d'un quiz : ");
			if (nbFormations>0)
			{
				System.out.println("Les formations disponibles sont : ");
				for (int i=0;i<nbFormations;i++) System.out.println(" - "+listeFormations.get(i).getNom());
				System.out.println("Entrer le nom de la formation : ");
				Scanner sc = new Scanner(System.in);
				String nomF = sc.nextLine();
				boolean trouv = false;
				int i = 0;
				while ((!trouv)&&(i<nbFormations))
				{
					if (listeFormations.get(i).getNom().equalsIgnoreCase(nomF)) trouv = true;
					else i++;
				}
				if (trouv)
				{
					Formation formation = listeFormations.get(i);
					formation.afficherNotions();
					System.out.println("Entrer le nombre de notions que vous voulez utiliser dans votre quiz : ");
					sc = new Scanner(System.in);
					int nbNotions = sc.nextInt();
					if (nbNotions>formation.getNbNotions()) nbNotions = formation.getNbNotions();
					if (nbNotions>0)
					{
						Notion[] tabNotions = new Notion[nbNotions];
						int[] tabNbQuestions = new int[nbNotions];
						int nb = 0;
						for (i=0;i<nbNotions;i++)
						{
							System.out.println("Entrer une notion : ");
							sc = new Scanner(System.in);
							nomF = sc.nextLine();
							boolean exist = false;
							int k = 0;
							while ((!exist)&&(k<nb))
							{
								if (tabNotions[k].getNom().equalsIgnoreCase(nomF)) exist = true;
								else k++;
							}
							if (!exist)
							{
								Notion notion = formation.chercherNotion(nomF);
								if (notion!=null)
								{
									System.out.println("Entrer le nombre de questions : ");
									sc = new Scanner(System.in);
									int nombreQuestions = sc.nextInt();
									if (nombreQuestions>0)
									{
										tabNotions[nb]=notion;
										tabNbQuestions[nb]=nombreQuestions;
										nb++;
									}
								}
								else System.out.println("Cette notion n'esxiste pas !");
							}
						}
						if (nb>0)
						{
							int cpt = 0;
							for (i=0;i<nb;i++) cpt=cpt+tabNotions[i].genererQuestions(tabNbQuestions[i]).length;
							Question[] questions = new Question[cpt];
							cpt=0;
							for (i=0;i<nb;i++)
							{
								Question[] tab = tabNotions[i].genererQuestions(tabNbQuestions[i]);
								for (int i1=0;i1<tab.length;i1++)
								{
									questions[cpt]=tab[i1];
									cpt++;
								}
							}
							if (questions.length>0) quiz = new Quiz(nom,dateOuverture,dateFermiture,questions,nomF);
						}
					}
					else System.out.println("Le nombre est incorrecte !");
				}
				else System.out.println("La formation que vous avez entrer n'est pas disponible !");
			}
			else System.out.println("Aucune formation n'est disponible");
			return quiz;
		}
		catch (InputMismatchException e)
		{
			System.err.println("Erreur de saisie !");
			return null;
		}
	}
}
