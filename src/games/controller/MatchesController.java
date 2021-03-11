package games.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;

import games.Matches;
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

public class MatchesController implements Initializable {
	
	//Variable d�crivant le nombre d'allumettes r�cup�r�e par le joueur
	
	int nbMatchesPlayer = 0;
	
	//Chaine d�crivant le r�sultat final de la partie
	
	String result;
	
	@FXML
	private AnchorPane pane_player;

	@FXML
	private Button btn_one;

	@FXML
	private Button btn_two;

	@FXML
	private Label lbl_nb;
	 
	@FXML
	private Label lbl_player;

	@FXML
	private Label lbl_recap;

	@FXML
	private AnchorPane pane_cpu;

	@FXML
	private Label lbl_cpu;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		
		/*Cr�ation d'une nouvelle variable Matches pour utiliser les m�thodes du jeu d'allumettes
		 * Oblig� de cr�er � l'int�rieur de la m�thode, car hors il y a des probl�mes avec le constructeur 
		 * Try catch obligatoire du au "RemoteException" */
		Matches m = null;
		try {
			m = new Matches();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Nombre d'allumettes de la partie
		int nbMatches = 0;
		
		//G�n�ration du nombre d'allumettes de la partie
		try {
			nbMatches = m.generateMatches();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Clear des labels pour le d�but de la partie
		lbl_nb.setText(nbMatches+"");
		lbl_recap.setText("");
		
		//Annonce du nombre d'allumettes de la partie
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("La partie commence avec "+nbMatches+" allumettes");
		alert.setHeaderText(null);
		alert.showAndWait();
		
		//Proposition au joueur de choisir s'il commence en 1er ou en 2e
			//Cr�ation de boutons personnalis�s
		ButtonType first = new ButtonType("1", ButtonData.OK_DONE);
		ButtonType second = new ButtonType("2", ButtonData.CANCEL_CLOSE);
		Alert alert2 = new Alert(AlertType.NONE, "Voulez vous jouer en 1er ou en 2e ?",first, second);
		
			//R�cup�ration du choix du joueur (si il ferme la fen�tre il joue en 2e)
		Optional<ButtonType> result = alert2.showAndWait();
			
			//Action si le joueur joue en 2e
		if (result.get().getText() == "2") {
			pane_cpu.setVisible(true);
			pane_player.setVisible(false);
			cpuTurn();
		}
		else {
			pane_cpu.setVisible(false);
			pane_player.setVisible(true);
		}
	}


	
	//M�thode utilis� lorsque le joueur choisit d'enlever 1 ou 2 allumettes (actions sur les boutons)
	
	@FXML
	public void playerTurn(ActionEvent event) throws NumberFormatException, RemoteException, InterruptedException {
		
		//R�cup�ration du nombre d'allumettes que le joueur choisit d'enlever (en cliquant sur un des deux boutons)
		Button but = (Button)event.getSource();
		int sub = Integer.parseInt(but.getText());
		
		//Changement du nombre d'allumettes que le joueur a retir� au total
		nbMatchesPlayer += sub;
		lbl_player.setText(" Total Allumettes retir�es : "+nbMatchesPlayer);
		
		//Label permettant de rappeler ce qu'il s'est pass� au tour pr�c�dent
		lbl_recap.setText("Vous avez retir� "+sub+" allumettes");
		
		//Cr�ation d'un objet Matches pour utiliser les m�thodes
		Matches m = new Matches();
		
		//Actualisation du nombre d'allumettes restantes dans le tas
		int nbMatches = Integer.parseInt(lbl_nb.getText());
		nbMatches = m.subMatches(nbMatches, sub);
		lbl_nb.setText(""+nbMatches);
		pane_cpu.setVisible(true);
		pane_player.setVisible(false);
		
		//Condition d'arr�t de jeu
		if(nbMatches==0) {
			//Alerte permettant au joueur de savoir si il a gagn� ou perdu
			result = m.endGame(nbMatchesPlayer);
			Alert alertEnd = new Alert(AlertType.INFORMATION);
			alertEnd.setContentText(result);
			alertEnd.setHeaderText(null);
			alertEnd.showAndWait();
			reload();
		}
		//Autrement, lancement du tour de l'ordinateur
		else cpuTurn();
	} 
	
	
	//M�thode d�crivant le tour de l'ordinateur
	
	public void cpuTurn() {
		
		int nbMatches = Integer.parseInt(lbl_nb.getText());
		//G�n�ration du nombre d'allumettes que l'ordinateur retire
		int sub = 0;
		Matches m = null;
		try {
			m = new Matches();
			if(nbMatches == 1) {
				sub = 1;
			}
			else {
				sub = m.rand();
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		//Actualisation du nombre d'allumettes restantes dans le tas
		try {
			nbMatches = m.subMatches(Integer.parseInt(lbl_nb.getText()), sub);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String nbMatchesText = ""+nbMatches;
		String subText = "L'ordinateur � retir� "+sub+" allumettes";
		
		//Pause de deux secondes pendant que l'ordinateur joue
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event ->
		{	
			lbl_nb.setText(nbMatchesText);
			
			//R�capitulatif de ce qu'� fait l'ordinateur durant son tour
			lbl_recap.setText(subText);	
			pane_cpu.setVisible(false);
			pane_player.setVisible(true);
		});
		//Condition d'arr�t de jeu
		if(nbMatches==0) {
			try {
				lbl_nb.setText(nbMatchesText);
				lbl_recap.setText(subText);	
				//Alerte permettant au joueur de savoir si il a gagn� ou perdu
				result = m.endGame(nbMatchesPlayer);
				Alert alertEnd = new Alert(AlertType.INFORMATION);
				alertEnd.setContentText(result);
				alertEnd.setHeaderText(null);
				alertEnd.showAndWait();
				reload();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		//Si il ne reste qu'une allumette on autorise au joueur de ne cliquer que sur le bouton 1
		else if(nbMatches==1) {
			btn_two.setDisable(true);
			//Lancement de la pause
			pause.play();
		}
		else {
			pause.play();
		}
		
			
	}
	
	//Fonction permettant de relancer la partie ou de quitter le jeu
	public void reload() {
		ButtonType yes = new ButtonType("oui", ButtonData.OK_DONE);
		ButtonType no = new ButtonType("non", ButtonData.CANCEL_CLOSE);
		Alert alertRestart = new Alert(AlertType.CONFIRMATION, "Voulez vous faire une nouvelle partie ?",yes,no);
		Optional<ButtonType> result = alertRestart.showAndWait();
		if(result.get().getText()=="oui") {
			initialize(null,null);
		}
	}
}
	
	
