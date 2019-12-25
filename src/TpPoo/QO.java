package TpPoo;

import java.io.Serializable;
import java.util.*;

public class QO implements Question,Serializable 
{
	private ArrayList<String> propCorrectes = new ArrayList<String>();
	private int nbPropCorrectes;
	private String enonce;
	
	public QO(String[] propCorrectes,String enonce)
	{
		this.enonce=enonce;
		this.nbPropCorrectes=propCorrectes.length;
		for (int i=0;i<nbPropCorrectes;i++) this.propCorrectes.add(propCorrectes[i]);
	}
	
	public boolean correcte(String o)
	{
		boolean trouv = false;
		int i=0;
		while ((!trouv)&&(i<nbPropCorrectes))
		{
			if (propCorrectes.get(i).equalsIgnoreCase(o)) trouv = true;
			else i++;
		}
		return trouv;
	}
	
	public void afficherQuestion()
	{
		System.out.println("Enoncé : "+enonce);
		System.out.println("Les propositions correctes : ");
		for (int i=0;i<nbPropCorrectes;i++) System.out.println((i+1)+". "+propCorrectes.get(i));
	}
	
	public String returnChaineQuestion()
	{
		String chaine = "Enoncé : "+enonce+"\n";
		chaine = chaine+"Les propositions correctes : "+"\n";
		for (int i=0;i<nbPropCorrectes;i++) chaine = chaine+(i+1)+". "+propCorrectes.get(i)+"\n";
		return chaine;
	}
	
	public void afficherQuestionMasque()
	{
		System.out.println("Enoncé : "+enonce);
	}
	
	public void modifierQuestion()
	{
		try
		{
			System.out.println("Modification d'une QO : ");
			System.out.println("1. Modifier l'énoncé");
			System.out.println("2. Ajouter une proposition correcte");
			System.out.println("3. Modifier une proposition correcte");
			System.out.println("4. Supprimer une proposition correcte");
			System.out.println("=> Entrer votre choix : ");
			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			if ((choix>0)&&(choix<5))
			{
				if (choix==1)
				{
					System.out.println("Entrer le nouveau énoncé : ");
					String chaine = sc.nextLine();
					enonce=chaine;
				}
				if (choix==2)
				{
					System.out.println("Entrer votre proposition correcte : ");
					String chaine = sc.nextLine();
					propCorrectes.add(chaine);
					nbPropCorrectes++;
				}
				if (choix==3)
				{
					System.out.println("Entrer le numéro de la proposition correcte que vous voulez la modifier : ");
					choix = sc.nextInt();
					if ((choix>0)&&(choix<=nbPropCorrectes))
					{
						System.out.println("Entrer la nouvelle proposition correcte : ");
						String chaine = sc.nextLine();
						propCorrectes.set(choix-1,chaine);
					}
					else System.out.println("Vous avez entrer un numéro incorrecte.");
				}
				if (choix==4)
				{
					System.out.println("Entrer le numéro de la proposition correcte que vous voulez la supprimer : ");
					choix = sc.nextInt();
					if ((choix>0)&&(choix<=nbPropCorrectes))
					{
						propCorrectes.set(choix-1,propCorrectes.get(nbPropCorrectes-1));
						propCorrectes.remove(nbPropCorrectes-1);
						nbPropCorrectes--;
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
