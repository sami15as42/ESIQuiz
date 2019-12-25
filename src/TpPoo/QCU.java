package TpPoo;

import java.io.Serializable;
import java.util.*;

public class QCU implements Question,Serializable 
{
	private String propCorrecte;
	private ArrayList<String> propFausses = new ArrayList<String>();
	private int nbPropFausses;
	private String enonce;
	
	public QCU(String propCorrecte,String[] propFausses,String enonce)
	{
		this.enonce=enonce;
		this.propCorrecte=propCorrecte;
		this.nbPropFausses=propFausses.length;
		for (int i=0;i<nbPropFausses;i++) this.propFausses.add(propFausses[i]);
	}
	
	public boolean correcte(String o)
	{
		return propCorrecte.equalsIgnoreCase(o);
	}
	
	public void afficherQuestion()
	{
		System.out.println("Enoncé : "+enonce);
		System.out.println("La proposition correcte : ");
		System.out.println(propCorrecte);
		System.out.println("Les propositions fausses : ");
		for (int i=0;i<nbPropFausses;i++) System.out.println((i+1)+". "+propFausses.get(i));
	}
	
	public String returnChaineQuestion()
	{
		String chaine = "Enoncé : "+enonce+"\n";
		chaine = chaine+"La proposition correctes : "+"\n";
		chaine = chaine+propCorrecte+"\n";
		chaine = chaine+"Les propositions fausses : "+"\n";
		for (int i=0;i<nbPropFausses;i++) chaine = chaine+(i+1)+". "+propFausses.get(i)+"\n";
		return chaine;
	}
	
	public void afficherQuestionMasque()
	{
		int n = (int)(Math.random() * (nbPropFausses+1));
		int i = 0,cpt = 0;
		System.out.println("Enoncé : "+enonce);
		while (i<nbPropFausses+1)
		{
			if (i==n) System.out.println((i+1)+". "+propCorrecte);
			else 
			{
				System.out.println((i+1)+". "+propFausses.get(cpt));
				cpt++;
			}
			i++;
		}
	}
	
	public void modifierQuestion()
	{
		try
		{
			System.out.println("Modification d'une QCU : ");
			System.out.println("1. Modifier l'énoncé");
			System.out.println("2. Ajouter une proposition fausse");
			System.out.println("3. Modifier la proposition correcte");
			System.out.println("4. Modifier une proposition fausse");
			System.out.println("5. Supprimer une proposition fausse");
			System.out.println("=> Entrer votre choix : ");
			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			if ((choix>0)&&(choix<6))
			{
				if (choix==1)
				{
					System.out.println("Entrer le nouveau énoncé : ");
					String chaine = sc.nextLine();
					enonce=chaine;
				}
				if (choix==2)
				{
					System.out.println("Entrer votre proposition fausse : ");
					String chaine = sc.nextLine();
					propFausses.add(chaine);
					nbPropFausses++;
				}
				if (choix==3)
				{
					System.out.println("Entrer la nouvelle proposition correcte : ");
					String chaine = sc.nextLine();
					propCorrecte=chaine;
				}
				if (choix==4)
				{
					System.out.println("Entrer le numéro de la proposition fausse que vous voulez la modifier : ");
					choix = sc.nextInt();
					if ((choix>0)&&(choix<=nbPropFausses))
					{
						System.out.println("Entrer la nouvelle proposition fausse : ");
						String chaine = sc.nextLine();
						propFausses.set(choix-1,chaine);
					}
					else System.out.println("Vous avez entrer un numéro incorrecte.");
				}
				if (choix==5)
				{
					System.out.println("Entrer le numéro de la proposition fausse que vous voulez la supprimer : ");
					choix = sc.nextInt();
					if ((choix>0)&&(choix<=nbPropFausses))
					{
						propFausses.set(choix-1,propFausses.get(nbPropFausses-1));
						propFausses.remove(nbPropFausses-1);
						nbPropFausses--;
					}
					else System.out.println("Vous avez entrer un numéro incorrecte.");
				}
			}
			else System.out.println("Votre choix ne figure pas parmi les choix disponibles.");
		}
		catch (InputMismatchException e)
		{
			System.err.println("Erreur de saisie !");
		}
	}
}
