package application;
	
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import TpPoo.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	public static ESIQuiz esiQuiz;
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			Scene scene = new Scene(root,700,400);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("ESIQuiz !");
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		esiQuiz = new ESIQuiz();
		Formateur formateur = esiQuiz.creerFormateur("Assenine", "Sami", "14/07/1999", "cite AADL-Ouled Yaich-Blida-Algerie");
		/*Question question1 = formateur.creerQuestion();
		Question question2 = formateur.creerQuestion();
		Question question3 = formateur.creerQuestion();
		Question question4 = formateur.creerQuestion();
		Question question5 = formateur.creerQuestion();
		Question question6 = formateur.creerQuestion();
		Question question7 = formateur.creerQuestion();
		Question question8 = formateur.creerQuestion();
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Questions.txt"))));
			out.writeObject(question1);
			out.writeObject(question2);
			out.writeObject(question3);
			out.writeObject(question4);
			out.writeObject(question5);
			out.writeObject(question6);
			out.writeObject(question7);
			out.writeObject(question8);
			out.close();
		}
		catch (Exception e)
		{
			e.getMessage();
		}*/
		Formation formation1 = formateur.creerFormation("Sciences et Technologies", "La technologie c'est le futur", "12/07/2019", "12/08/2019");
		Formation formation2 = formateur.creerFormation("Géographie", "Il est prémordiale de connaitre la géographie du monde", "16/07/2019", "16/08/2019");
		Apprenant apprenant1 = formateur.creerCompte("Ait saadi", "Madjid", "20/02/1999", "Baba ali-Alger");
		Apprenant apprenant2 = formateur.creerCompte("Laghoub", "Nassim", "18/09/1999", "Ain Naadja-Alger");
		Notion notion1 = formateur.introduireNotion("Les maladies");
		Notion notion2 = formateur.introduireNotion("Les scientifiques");
		Notion notion3 = formateur.introduireNotion("Les capitales du monde");
		Notion notion4 = formateur.introduireNotion("Les monuments");
		Notion[] tabNotions1 = {notion1,notion2};
		Notion[] tabNotions2 = {notion3,notion4};
		formateur.ajouterNotionsFormation("Sciences et Technologies",tabNotions1);
		formateur.ajouterNotionsFormation("Géographie",tabNotions2);
		
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Questions.txt"))));
			Question question1 = (Question) in.readObject();
			Question question2 = (Question) in.readObject();
			Question question3 = (Question) in.readObject();
			Question question4 = (Question) in.readObject();
			Question question5 = (Question) in.readObject();
			Question question6 = (Question) in.readObject();
			Question question7 = (Question) in.readObject();
			Question question8 = (Question) in.readObject();
			Question[] tab1 = {question1,question2};
			Question[] tab2 = {question3,question4};
			Question[] tab3 = {question5,question6};
			Question[] tab4 = {question7,question8};
			formateur.ajouterQuestionsNotion(notion1,tab1);
			formateur.ajouterQuestionsNotion(notion2,tab2);
			formateur.ajouterQuestionsNotion(notion3,tab3);
			formateur.ajouterQuestionsNotion(notion4,tab4);
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		launch(args);
	}
}
