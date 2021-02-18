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
	
	 @FXML
	 private AnchorPane pane_player;

	 @FXML
	 private Button btn_one;

	 @FXML
	 private Button btn_two;

	 @FXML
	 private Label lbl_nb;

	 @FXML
	 private Label lbl_recap;

	 @FXML
	 private AnchorPane pane_cpu;

	 @FXML
	 private Label lbl_cpu;
	 
	 @Override
	 public void initialize(URL location, ResourceBundle resources){
		Matches m = null;
			try {
				m = new Matches();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		int nbMatches = 0;
		try {
			nbMatches = m.generateMatches();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		 Alert alert = new Alert(AlertType.INFORMATION);
		 alert.setContentText("La partie commence avec "+nbMatches+" allumettes");
		 alert.setHeaderText(null);
		 alert.showAndWait();
		 lbl_nb.setText(nbMatches+"");
		 lbl_recap.setText("");
		 ButtonType foo = new ButtonType("1", ButtonData.OK_DONE);
		 ButtonType bar = new ButtonType("2", ButtonData.CANCEL_CLOSE);
		 Alert alert2 = new Alert(AlertType.WARNING,
		         "Voulez vous jouer en 1er ou en 2e ?",
		         foo, bar);
		 Optional<ButtonType> result = alert2.showAndWait();
		 int order;
		 if (result.get().getText() == "1") order = 1;
		 else order = 2;
		 
		 if(order == 2)
			try {
				cpuTurn();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		else pane_cpu.setVisible(false);
	 }
	 
	 @FXML
	 public void playerTurn(ActionEvent event) throws NumberFormatException, RemoteException {
		 
		 Button but = (Button)event.getSource();
		 Matches m = new Matches();
		 lbl_nb.setText(""+m.subMatches(Integer.parseInt(lbl_nb.getText()), Integer.parseInt(but.getText())));
		 cpuTurn();
	 }
	 
	 public void cpuTurn() throws RemoteException {
		 Matches m = new Matches();
		 PauseTransition pause = new PauseTransition(Duration.seconds(1));
		 pane_cpu.setVisible(true);
		 pane_player.setVisible(false);
		 
		 //pause à mettre en place
		 
		 int sub = m.rand();
		 lbl_nb.setText(""+m.subMatches(Integer.parseInt(lbl_nb.getText()), sub));
		 pane_cpu.setVisible(false);
		 pane_player.setVisible(true);
		 pause.play();
	}	 
}
	
	
