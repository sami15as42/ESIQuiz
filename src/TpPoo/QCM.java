package TpPoo;

import java.io.Serializable;
import java.util.*;

public class QCM implements Question,Serializable 
{
	private ArrayList<String> propCorrectes = new ArrayList<String>();
	private int nbPropCorrectes;
	private ArrayList<String> propFausses = new ArrayList<String>();
	private int nbPropFausses;
	private String enonce;
	
	public QCM(String[] propCorrectes,String[] propFausses,String enonce)
	{
		this.enonce=enonce;
		this.nbPropCorrectes=propCorrectes.length;
		this.nbPropFausses=propFausses.length;
		for (int i=0;i<nbPropCorrectes;i++) this.propCorrectes.add(propCorrectes[i]);
		for (int i=0;i<nbPropFausses;i++) this.propFausses.add(propFausses[i]);
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
	
	public double evaluation(String[] chaines)
	{
		double pts = 1./(nbPropCorrectes+nbPropFausses);
		double note = 0;
		int cpt1=0,cpt2=0;
		for (int i=0;i<chaines.length;i++)
		{
			if (correcte(chaines[i])) {note=note+pts;cpt1++;}
			else {note=note-pts;cpt2++;}
		}
		cpt1=nbPropCorrectes-cpt1;
		cpt2=nbPropFausses-cpt2;
		note=note-(cpt1*pts)+(cpt2*pts);
		if (note<0) note = 0;
		return note;
	}
	
	public void afficherQuestion()
	{
		System.out.println("Enoncé : "+enonce);
		System.out.println("Les propositions correctes : ");
		for (int i=0;i<nbPropCorrectes;i++) System.out.println((i+1)+". "+propCorrectes.get(i));
		System.out.println("Les propositions fausses : ");
		for (int i=0;i<nbPropFausses;i++) System.out.println((i+1)+". "+propFausses.get(i));
	}
	
	public String returnChaineQuestion()
	{
		String chaine = "Enoncé : "+enonce+"\n";
		chaine = chaine+"Les propositions correctes : "+"\n";
		for (int i=0;i<nbPropCorrectes;i++) chaine = chaine+(i+1)+". "+propCorrectes.get(i)+"\n";
		chaine = chaine+"Les propositions fausses : "+"\n";
		for (int i=0;i<nbPropFausses;i++) chaine = chaine+(i+1)+". "+propFausses.get(i)+"\n";
		return chaine;
	}
	
	public void afficherQuestionMasque()
	{
		System.out.println("Enoncé : "+enonce);
		int i1 = 0, i2 = 0, i = 0;
		int n = (int)(Math.random()*100);
		while (i<nbPropCorrectes+nbPropFausses)
		{
			if (n>49) 
			{
				if (i1<nbPropCorrectes)
				{
					System.out.println((i+1)+". "+propCorrectes.get(i1));
					n = (int)(Math.random()*100);
					i++;
					i1++;
				}
				else n=0;
			}
			else
			{
				if (i2<nbPropFausses)
				{
					System.out.println((i+1)+". "+propFausses.get(i2));
					n = (int)(Math.random()*100);
					i++;
					i2++;
				}
				else n=100;
			}
		}
	}
	
	public void modifierQuestion()
	{
		try
		{
			System.out.println("Modification d'une QCM : ");
			System.out.println("1. Modifier l'énoncé");
			System.out.println("2. Ajouter une proposition correcte");
			System.out.println("3. Ajouter une proposition fausse");
			System.out.println("4. Modifier une proposition correcte");
			System.out.println("5. Modifier une proposition fausse");
			System.out.println("6. Supprimer une proposition correcte");
			System.out.println("7. Supprimer une proposition fausse");
			System.out.println("=> Entrer votre choix : ");
			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			if ((choix>0)&&(choix<8))
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
					System.out.println("Entrer votre proposition fausse : ");
					String chaine = sc.nextLine();
					propFausses.add(chaine);
					nbPropFausses++;
				}
				if (choix==4)
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
				if (choix==5)
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
				if (choix==6)
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
				if (choix==7)
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
