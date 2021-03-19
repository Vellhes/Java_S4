package games.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccueilController {

	@FXML
	private VBox Vbox_accueil;
	
    @FXML
    private Button btn_pendu;

    @FXML
    private Button btn_matches;
    
    @FXML
    private Button btn_morpion;

    @FXML
    void lancementPendu() {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/pendu.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Pendu");
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (Exception e) {
			System.out.println("Can't load new window " + e);
		}
    }
    
    @FXML
    void lancementAllumettes() {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/allumettes.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Allumettes");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			System.out.println("Can't load new window " + e);
		}
    }
    
    @FXML
    void lancementMorpion() {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/morpion.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Morpion");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			System.out.println("Can't load new window " + e);
		}
    }
    
}
