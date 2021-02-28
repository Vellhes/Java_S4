package games.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import games.Pendu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PenduController implements Initializable {

	String word = null;
	
	String guessWord = "";
	
	int mistakes = 0;
	
    @FXML
    private ImageView img_pendu;

    @FXML
    private Label lbl_pendu;

    @FXML
    private Button btn_a;

    @FXML
    private Button btn_c;

    @FXML
    private Button btn_b;

    @FXML
    private Button btn_d;

    @FXML
    private Button btn_e;

    @FXML
    private Button btn_f;

    @FXML
    private Button btn_g;

    @FXML
    private Button btn_h;

    @FXML
    private Button btn_j;

    @FXML
    private Button btn_i;

    @FXML
    private Button btn_k;

    @FXML
    private Button btn_l;

    @FXML
    private Button btn_m;

    @FXML
    private Button btn_n;

    @FXML
    private Button btn_o;

    @FXML
    private Button btn_p;

    @FXML
    private Button btn_q;

    @FXML
    private Button btn_r;

    @FXML
    private Button btn_s;

    @FXML
    private Button btn_t;

    @FXML
    private Button btn_u;

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_w;

    @FXML
    private Button btn_x;

    @FXML
    private Button btn_y;

    @FXML
    private Button btn_z;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Pendu p = null;
		try {
			p = new Pendu();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	
		try {
			word = p.selectWord();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		mistakes = 0;
		
		for(int i = 0; i<word.length();i++)
			guessWord += "_ ";
		lbl_pendu.setText(guessWord);
		Image img = new Image("games/ressources/img/pendu0.png");
		img_pendu.setImage(img);
	}
	
	@FXML
	public void letterChoice(ActionEvent event) throws RemoteException{
		
		Pendu p = new Pendu();
		
		Button but = (Button)event.getSource();
		String letterString = but.getText();
		letterString = letterString.toLowerCase();
		char letter = letterString.charAt(0);
		
		boolean check = p.checkLetter(letter, word);
		if(check==true) {
			guessWord = p.avancement(letter, word, guessWord);
			lbl_pendu.setText(guessWord);
			for(int i = 0 ; i < guessWord.length() ; i+=2) {
				if(guessWord.charAt(i)=='_') {
					break;
				}
				else {
					if(i==(guessWord.length()-2)) {
						System.out.println("mot trouvé");
					}
				}
			}
		}
		else {
			mistakes += 1;
			Image img = new Image("games/ressources/img/pendu"+mistakes+".png");
			img_pendu.setImage(img);
			if(mistakes == 9) {
				System.out.println("Vous avez perdu");
			}
		}
		
		but.setVisible(false);
	
	}

}

