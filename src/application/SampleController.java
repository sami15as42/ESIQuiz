package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import TpPoo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SampleController {
	
	private Formateur formateur;
	private Formation formation;
	private String dateOuverture;
	private String dateFermiture;
	private String nomQ;
	ArrayList<Notion> tabNotions = new ArrayList<Notion>();
	ArrayList<Integer> tabNbQuestions = new ArrayList<Integer>();
	
	@FXML
    private Label nom;

    @FXML
    private Label prenom;
	
    @FXML
    private JFXButton formateurBtn;
    
    @FXML
    private ImageView alertImg;
    
    @FXML
    private Label alert;
    
    @FXML
    private JFXButton retourBtn;
    
    @FXML
    private JFXButton loginBtn;
    
    @FXML
    private JFXTextField userName;
    
    @FXML
    private JFXPasswordField password;
    
    @FXML
    private AnchorPane formateurGui;
    
    @FXML
    private AnchorPane loginGui;
    
    @FXML
    private JFXTextField nomQuizField;

    @FXML
    private JFXDatePicker dateFermitureCal;

    @FXML
    private JFXDatePicker dateOuvertureCal;
    
    @FXML
    private JFXComboBox<String> selecteurFormations;
    
    @FXML
    private AnchorPane gui1;
    
    @FXML
    private AnchorPane gui2;
    
    @FXML
    private AnchorPane gui3;
    
    @FXML
    private AnchorPane gui4;
    
    @FXML
    private AnchorPane gui5;
    
    @FXML
    private AnchorPane gui6;
    
    @FXML
    private AnchorPane gui7;
    
    @FXML
    private JFXComboBox<String> selecteurNotions;
    
    @FXML
    private JFXTextField nbQuestionsField;
    
    @FXML
    private TextArea textAreaField;
    
    @FXML
    private TextArea textAreaQuizAfficher;
    
    @FXML
    private JFXTextField nomFormation;

    @FXML
    private JFXDatePicker dateOuvertureFormation;

    @FXML
    private JFXDatePicker dateFermitureFormation;
    
    @FXML
    private TextArea description;
    
    @FXML
    private JFXTextField nomNotion;

    @FXML
    private JFXTextField nomFormationN;
    
    @FXML
    private JFXComboBox<String> selecteurFormationsAfficher;
    
    @FXML
    void afficherQuiz(ActionEvent event) 
    {
    	gui4.setVisible(false);
    	gui6.setVisible(true);
    	textAreaQuizAfficher.setText("");
    	selecteurFormationsAfficher.getItems().clear();
    	for (int i=0;i<formateur.getNbFormations();i++)
    	{
    		selecteurFormationsAfficher.getItems().add(formateur.getFormations().get(i).getNom());
    	}
    }
    
    @FXML
    void quitterClic(ActionEvent event) 
    {
    	gui2.setVisible(false);
    	gui1.setVisible(true);
    	tabNotions.clear();
    	tabNbQuestions.clear();
    }
    
    @FXML
    void displayQuiz(ActionEvent event) 
    {
    	String nomF = selecteurFormationsAfficher.getValue();
    	if (nomF!=null)
    	{
    		ArrayList<Formation> listeFormations = formateur.getFormations();
	    	boolean trouv = false;
			int i = 0;
			while (!trouv)
			{
				if (listeFormations.get(i).getNom().equalsIgnoreCase(nomF)) trouv = true;
				else i++;
			}
	    	Formation f = listeFormations.get(i);
	    	textAreaQuizAfficher.setText("Nom de la formation : "+f.getNom()+"\n");
	    	textAreaQuizAfficher.setText(textAreaQuizAfficher.getText()+"Date début de la formation : "+f.getDateDebut()+"\n");
	    	textAreaQuizAfficher.setText(textAreaQuizAfficher.getText()+"Date fin de la formation : "+f.getDateFin()+"\n");
	    	textAreaQuizAfficher.setText(textAreaQuizAfficher.getText()+"Description de la formation : "+f.getDescription()+"\n");
	    	ArrayList<Quiz> listeQuiz = f.getListeQuiz();
	    	for (i=0;i<listeQuiz.size();i++)
	    	{
	    		textAreaQuizAfficher.setText(textAreaQuizAfficher.getText()+listeQuiz.get(i).returnChaineQuiz()+"************************************************"+"\n");
	    	}
    	}
    }
    
    @FXML
    void backClic4(ActionEvent event) 
    {
    	gui6.setVisible(false);
    	gui4.setVisible(true);
    }
    
    @FXML
    void nomFormationNClic(MouseEvent event) 
    {
    	nomFormationN.setUnFocusColor(Color.web("0x4d4d4d"));
    }

    @FXML
    void nomNotionClic(MouseEvent event) 
    {
    	nomNotion.setUnFocusColor(Color.web("0x4d4d4d"));
    }
    
    @FXML
    void creerNotionClic(ActionEvent event) 
    {
    	String nomN = nomNotion.getText();
    	if (!nomN.equals(""))
    	{
	    	String nomFormationNotion = nomFormationN.getText();
	    	if (!nomFormationNotion.equals(""))
	    	{
		    	Notion[] tab = {formateur.introduireNotion(nomN)};
		    	formateur.ajouterNotionsFormation(nomFormationNotion,tab);
		    	nomNotion.setText("");
		    	nomFormationN.setText("");
		    	gui7.setVisible(false);
		    	gui4.setVisible(true);
	    	}
	    	else nomFormationN.setUnFocusColor(Color.RED);
    	}
    	else nomNotion.setUnFocusColor(Color.RED);
    }
    
    @FXML
    void creerFormationClic(ActionEvent event) 
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	String nomF = nomFormation.getText();
    	if (!nomF.equals(""))
    	{
    		if (dateOuvertureFormation.getValue()!=null)
    		{
    			if (dateFermitureFormation.getValue()!=null)
        		{
    				LocalDate localDate1 = dateOuvertureFormation.getValue();
		    		LocalDate localDate2 = dateFermitureFormation.getValue();
		    		if (localDate1.isAfter(localDate2)) {LocalDate local = localDate1 ;localDate1=localDate2;localDate2=local; } 
		    		String dateOuvertureF = localDate1.format(formatter);
			    	String dateFermitureF = localDate2.format(formatter);
			    	String des = description.getText();
			    	formateur.creerFormation(nomF, des, dateOuvertureF, dateFermitureF);
			    	description.setText("");
			    	nomFormation.setText("");
			    	dateOuvertureFormation.setValue(null);
			    	dateFermitureFormation.setValue(null);
			    	gui5.setVisible(false);
			    	gui4.setVisible(true);
        		}
    			else dateFermitureFormation.setPromptText("Ajouter la date");
    		}
    		else dateOuvertureFormation.setPromptText("Ajouter la date");
    	}
    	else nomFormation.setUnFocusColor(Color.RED);
    }
    
    @FXML
    void dateFermitureFormationClic(MouseEvent event) 
    {
    	dateFermitureFormation.setPromptText("");
    }
    
    @FXML
    void dateOuvertureFormationClic(MouseEvent event) 
    {
    	dateOuvertureFormation.setPromptText("");
    }
    
    @FXML
    void nomFormationClic(MouseEvent event) 
    {
    	nomFormation.setUnFocusColor(Color.web("0x4d4d4d"));
    }
    
    @FXML
    void creerFormation(ActionEvent event) 
    {
    	gui4.setVisible(false);
    	gui5.setVisible(true);
    }

    @FXML
    void creerNotion(ActionEvent event) 
    {
    	gui4.setVisible(false);
    	gui7.setVisible(true);
    }
    
    @FXML
    void genererQuiz(ActionEvent event) 
    {
    	ArrayList<Formation> formations = formateur.getFormations();
    	selecteurFormations.getItems().clear();
    	for (int i=0;i<formateur.getNbFormations();i++)
    	{
    		selecteurFormations.getItems().add(formations.get(i).getNom());
    	}
    	gui4.setVisible(false);
    	gui1.setVisible(true);
    }
    
    @FXML
    void backClic1(ActionEvent event) 
    {
    	gui5.setVisible(false);
    	gui4.setVisible(true);
    }
    
    @FXML
    void backClic(ActionEvent event) 
    {
    	gui7.setVisible(false);
    	gui4.setVisible(true);
    }
    
    @FXML
    void backClic2(ActionEvent event) 
    {
    	gui1.setVisible(false);
    	gui4.setVisible(true);
    }
    
    @FXML
    void backClic3(ActionEvent event) 
    {
    	gui3.setVisible(false);
    	gui1.setVisible(true);
    	selecteurFormations.setValue("");
    	nomQuizField.setText("");
    	dateOuvertureCal.setValue(null);
    	dateFermitureCal.setValue(null);
    }
    
    @FXML
    void formateurBtnClic(ActionEvent event) throws IOException 
    {
    	Parent newPage = FXMLLoader.load(getClass().getResource("InterfaceFormateur.fxml"));
    	Scene newScene = new Scene(newPage);
    	Stage main = (Stage) ( (Node) event.getSource()).getScene().getWindow();
    	main.hide();
    	main.setScene(newScene);
    	main.show();
    }
    
    @FXML
    void userNameClic(MouseEvent event) 
    {
    	alert.setVisible(false);
		alertImg.setVisible(false);
    }
    
    @FXML
    void passwordClic(MouseEvent event) 
    {
    	alert.setVisible(false);
		alertImg.setVisible(false);
    }
    
    @FXML
    void retourBtnClic(ActionEvent event) throws IOException
    {
    	Parent newPage = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
    	Scene newScene = new Scene(newPage);
    	Stage main = (Stage) ( (Node) event.getSource()).getScene().getWindow();
    	main.hide();
    	main.setScene(newScene);
    	main.show();
    }
    
    @FXML
    void loginBtnClic(ActionEvent event) throws IOException
    {
    	alert.setVisible(false);
		alertImg.setVisible(false);
    	String motPasse = password.getText();
    	String login = userName.getText();
    	formateur = Main.esiQuiz.authentificationFormateur(login, motPasse);
    	if (formateur==null)
    	{
    		alert.setVisible(true);
    		alertImg.setVisible(true);
    	}
    	else 
    	{
    		loginGui.setVisible(false);
    		formateurGui.setVisible(true);
    		nom.setText(formateur.getNom());
    		prenom.setText(formateur.getPrenom());
    	}
    }
    
    @FXML
    void dateFermitureCalClic(MouseEvent event) 
    {
    	dateFermitureCal.setPromptText("");
    }

    @FXML
    void dateOuvertureCalClic(MouseEvent event) 
    {
    	dateOuvertureCal.setPromptText("");
    }
    
    @FXML
    void nomQuizFieldClic(MouseEvent event) 
    {
    	nomQuizField.setUnFocusColor(Color.web("0x4d4d4d"));
    }
    
    @FXML
    void selecteurFormationsClic(MouseEvent event) 
    {
    	selecteurFormations.setUnFocusColor(Color.web("0x4d4d4d"));
    }
    
    @FXML
    void suivantClic(ActionEvent event) 
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");        
    	nomQ = nomQuizField.getText();
    	if (!nomQ.equals(""))
    	{
    		if (dateOuvertureCal.getValue()!=null)
    		{
		    	if (dateFermitureCal.getValue()!=null)
		    	{
		    		LocalDate localDate1 = dateOuvertureCal.getValue();
		    		LocalDate localDate2 = dateFermitureCal.getValue();
		    		if (localDate1.isAfter(localDate2)) {LocalDate local = localDate1 ;localDate1=localDate2;localDate2=local; } 
		    		dateOuverture = localDate1.format(formatter);
			    	dateFermiture = localDate2.format(formatter);
			    	String nomF = selecteurFormations.getValue();
			    	if (nomF!=null)
			    	{
			    		gui1.setVisible(false);
				    	gui2.setVisible(true);
				    	ArrayList<Formation> listeFormations = formateur.getFormations();
				    	boolean trouv = false;
						int i = 0;
						while (!trouv)
						{
							if (listeFormations.get(i).getNom().equalsIgnoreCase(nomF)) trouv = true;
							else i++;
						}
				    	formation = listeFormations.get(i);
				    	selecteurNotions.getItems().clear();
				    	for (i=0;i<formation.getNbNotions();i++)
				    	{
				    		Notion notion = formation.getNotions().get(i);
				    		selecteurNotions.getItems().add(notion.getNom());
				    	}
			    	}
			    	else selecteurFormations.setUnFocusColor(Color.RED);
		    	}
		    	else dateFermitureCal.setPromptText("Ajouter une date");
    		}
    		else dateOuvertureCal.setPromptText("Ajouter une date");
    	}
    	else nomQuizField.setUnFocusColor(Color.RED);
    }
    
    @FXML
    void ajouterNotionClic(ActionEvent event) 
    {
    	String nomF = selecteurNotions.getValue();
    	if (nomF!=null)
    	{
    		if (!nbQuestionsField.getText().equals(""))
    		{
				Notion notion = formation.chercherNotion(nomF);
				if (notion!=null)
				{
					if (!tabNotions.contains(notion))
					{
						try
						{
							int nombreQuestions = Integer.parseInt(nbQuestionsField.getText());
							if (nombreQuestions>0)
							{
								tabNotions.add(notion);
								tabNbQuestions.add(nombreQuestions);
								selecteurNotions.setValue("");
						    	nbQuestionsField.setText("");
							}
							else {nbQuestionsField.setUnFocusColor(Color.RED);nbQuestionsField.setText("");}
						}
						catch(Exception e)
						{
							nbQuestionsField.setUnFocusColor(Color.RED);
							nbQuestionsField.setText("");
						}
					}
					else 
					{
						selecteurNotions.setValue("");
				    	nbQuestionsField.setText("");
					}
				}
    		}
    		else nbQuestionsField.setUnFocusColor(Color.RED);
    	}
    	else selecteurNotions.setUnFocusColor(Color.RED);
    }
    
    @FXML
    void selecteurNotionsClic(MouseEvent event) 
    {
    	selecteurNotions.setUnFocusColor(Color.web("0x4d4d4d"));
    }
    
    @FXML
    void nbQuestionsFieldClic(MouseEvent event) 
    {
    	nbQuestionsField.setUnFocusColor(Color.web("0x4d4d4d"));
    }

    @FXML
    void genererQuizClic(ActionEvent event) 
    {
    	String nomF = selecteurNotions.getValue();
    	if (nomF!=null)
    	{
    		if (!nbQuestionsField.getText().equals(""))
    		{
				Notion notion = formation.chercherNotion(nomF);
				if (notion!=null)
				{
					if (!tabNotions.contains(notion))
					{
						try
						{
							int nombreQuestions = Integer.parseInt(nbQuestionsField.getText());
							if (nombreQuestions>0)
							{
								tabNotions.add(notion);
								tabNbQuestions.add(nombreQuestions);
								selecteurNotions.setValue("");
						    	nbQuestionsField.setText("");
							}
							else {nbQuestionsField.setUnFocusColor(Color.RED);nbQuestionsField.setText("");return;}
						}
						catch(Exception e)
						{
							nbQuestionsField.setUnFocusColor(Color.RED);
							nbQuestionsField.setText("");
							return;
						}
					}
					else 
					{
						selecteurNotions.setValue("");
				    	nbQuestionsField.setText("");
				    	return;
					}
				}
    		}
    		else {nbQuestionsField.setUnFocusColor(Color.RED);return;}
    	}
    	else {selecteurNotions.setUnFocusColor(Color.RED);return;}
    	int nb = tabNotions.size();
    	if (nb!=0)
    	{
	    	int cpt = 0;
			for (int i=0;i<nb;i++) cpt=cpt+tabNotions.get(i).genererQuestions(tabNbQuestions.get(i)).length;
			Question[] questions = new Question[cpt];
			cpt=0;
			for (int i=0;i<nb;i++)
			{
				Question[] tab = tabNotions.get(i).genererQuestions(tabNbQuestions.get(i));
				for (int i1=0;i1<tab.length;i1++)
				{
					questions[cpt]=tab[i1];
					cpt++;
				}
			}
			Quiz quiz = null;
			if (questions.length>0) quiz = new Quiz(nomQ,dateOuverture,dateFermiture,questions,formation.getNom());
			gui2.setVisible(false);
			gui3.setVisible(true);
			if (quiz!=null)
			{
				textAreaField.setText(quiz.returnChaineQuiz());
				formateur.sauvegarderQuiz(quiz);
			}
			else textAreaField.setText("Aucun quiz n'a été créé !");
    	}
    	else 
    	{
    		gui2.setVisible(false);
			gui3.setVisible(true);
			textAreaField.setText("Aucun quiz n'a été créé !");
    	}
    	tabNotions.clear();
    	tabNbQuestions.clear();
    }
}
