package games.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;

import games.Allumettes;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AllumettesController implements Initializable {
	
	//Variable décrivant le nombre d'allumettes récupérée par le joueur
	
	int nbAllumettesJoueur = 0;
	
	//Chaine décrivant le résultat final de la partie
	
	String resultat;
	
	@FXML
	private AnchorPane pane_joueur;

	@FXML
	private Button btn_un;

	@FXML
	private Button btn_deux;

	@FXML
	private Label lbl_nb;
	 
	@FXML
	private Label lbl_joueur;

	@FXML
	private Label lbl_recap;

	@FXML
	private AnchorPane pane_ordi;

	@FXML
	private Label lbl_ordi;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		
		/*Création d'une nouvelle variable Allumettes pour utiliser les méthodes du jeu d'allumettes
		 * Obligé de créer à l'intérieur de la méthode, car hors il y a des problèmes avec le constructeur 
		 * Try catch obligatoire du au "RemoteException" */
		Allumettes a = null;
		try {
			a = new Allumettes();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Nombre d'allumettes de la partie
		int nbAllumettes = 0;
		
		//Génération du nombre d'allumettes de la partie
		try {
			nbAllumettes = a.generationAllumettes();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Clear des labels pour le début de la partie
		lbl_nb.setText(nbAllumettes+"");
		lbl_recap.setText("");
		
		//Annonce du nombre d'allumettes de la partie
		Alert alerteNb = new Alert(AlertType.INFORMATION);
		alerteNb.setContentText("La partie commence avec "+nbAllumettes+" allumettes");
		alerteNb.setHeaderText(null);
		alerteNb.showAndWait();
		
		//Proposition au joueur de choisir s'il commence en 1er ou en 2e
			//Création de boutons personnalisés
		ButtonType premier = new ButtonType("1", ButtonData.OK_DONE);
		ButtonType second = new ButtonType("2", ButtonData.CANCEL_CLOSE);
		Alert alerteMain = new Alert(AlertType.NONE, "Voulez vous jouer en 1er ou en 2e ?",premier, second);
		
			//Récupération du choix du joueur (si il ferme la fenêtre il joue en 2e)
		Optional<ButtonType> resultat = alerteMain.showAndWait();
			
			//Action si le joueur joue en 2e
		if (resultat.get().getText() == "2") {
			pane_ordi.setVisible(true);
			pane_joueur.setVisible(false);
			tourOrdi();
		}
		else {
			pane_ordi.setVisible(false);
			pane_joueur.setVisible(true);
		}
	}


	
	//Méthode utilisé lorsque le joueur choisit d'enlever 1 ou 2 allumettes (actions sur les boutons)
	
	@FXML
	public void tourJoueur(ActionEvent evenement) throws NumberFormatException, RemoteException, InterruptedException {
		
		//Récupération du nombre d'allumettes que le joueur choisit d'enlever (en cliquant sur un des deux boutons)
		Button bouton = (Button)evenement.getSource();
		int sub = Integer.parseInt(bouton.getText());
		
		//Changement du nombre d'allumettes que le joueur a retiré au total
		nbAllumettesJoueur += sub;
		lbl_joueur.setText(" Total Allumettes retirées : "+nbAllumettesJoueur);
		
		//Label permettant de rappeler ce qu'il s'est passé au tour précédent
		lbl_recap.setText("Vous avez retiré "+sub+" allumettes");
		
		//Création d'un objet Allumettes pour utiliser les méthodes
		Allumettes m = new Allumettes();
		
		//Actualisation du nombre d'allumettes restantes dans le tas
		int nbMatches = Integer.parseInt(lbl_nb.getText());
		nbMatches = m.soustraireAllumettes(nbMatches, sub);
		lbl_nb.setText(""+nbMatches);
		pane_ordi.setVisible(true);
		pane_joueur.setVisible(false);
		
		//Condition d'arrêt de jeu
		if(nbMatches==0) {
			//Alerte permettant au joueur de savoir si il a gagné ou perdu
			resultat = m.finDuJeu(nbAllumettesJoueur);
			Alert alertEnd = new Alert(AlertType.INFORMATION);
			alertEnd.setContentText(resultat);
			alertEnd.setHeaderText(null);
			alertEnd.showAndWait();
			rejouer();
		}
		//Autrement, lancement du tour de l'ordinateur
		else tourOrdi();
	} 
	
	
	//Méthode décrivant le tour de l'ordinateur
	
	public void tourOrdi() {
		
		int nbAllumettes = Integer.parseInt(lbl_nb.getText());
		//Génération du nombre d'allumettes que l'ordinateur retire
		int moins = 0;
		Allumettes a = null;
		try {
			a = new Allumettes();
			if(nbAllumettes == 1) {
				moins = 1;
			}
			else {
				moins = a.aleatoire();
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Actualisation du nombre d'allumettes restantes dans le tas
		try {
			nbAllumettes = a.soustraireAllumettes(Integer.parseInt(lbl_nb.getText()), moins);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String nbAllumettesTexte = ""+nbAllumettes;
		String moinsText = "L'ordinateur à retiré "+moins+" allumettes";
		
		//Pause de deux secondes pendant que l'ordinateur joue
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event ->
		{	
			lbl_nb.setText(nbAllumettesTexte);
			
			//Récapitulatif de ce qu'à fait l'ordinateur durant son tour
			lbl_recap.setText(moinsText);	
			pane_ordi.setVisible(false);
			pane_joueur.setVisible(true);
		});
		//Condition d'arrêt de jeu
		if(nbAllumettes==0) {
			try {
				lbl_nb.setText(nbAllumettesTexte);
				lbl_recap.setText(moinsText);	
				//Alerte permettant au joueur de savoir si il a gagné ou perdu
				resultat = a.finDuJeu(nbAllumettesJoueur);
				Alert alertFin = new Alert(AlertType.INFORMATION);
				alertFin.setContentText(resultat);
				alertFin.setHeaderText(null);
				alertFin.showAndWait();
				rejouer();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		//Si il ne reste qu'une allumette on autorise au joueur de ne cliquer que sur le bouton 1
		else if(nbAllumettes==1) {
			btn_deux.setDisable(true);
			//Lancement de la pause
			pause.play();
		}
		else {
			pause.play();
		}
		
			
	}
	
	//Fonction permettant de relancer la partie ou de quitter le jeu
	public void rejouer() {
		ButtonType oui = new ButtonType("oui", ButtonData.OK_DONE);
		ButtonType non = new ButtonType("non", ButtonData.CANCEL_CLOSE);
		Alert alertRecommencer = new Alert(AlertType.CONFIRMATION, "Voulez vous faire une nouvelle partie ?",oui,non);
		Optional<ButtonType> resultat = alertRecommencer.showAndWait();
		if(resultat.get().getText()=="oui") {
			initialize(null,null);
		}
	}
}
	
	
