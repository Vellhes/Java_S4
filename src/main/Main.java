package main;

import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../games/fxml/accueil.fxml"));
		primaryStage.setTitle("LAMpixel");
		primaryStage.setScene(new Scene(root,600,400));
		primaryStage.show();
	}
	
	public static void main(String[] args) throws RemoteException {
		launch(args);
	}
}