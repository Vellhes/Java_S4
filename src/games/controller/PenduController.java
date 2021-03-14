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

	String mot = null;
	
	String motCache = "";
	
	int erreurs = 0;
	
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
			mot = p.selectMot();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		erreurs = 0;
		
		for(int i = 0; i<mot.length();i++)
			motCache += "_ ";
		lbl_pendu.setText(motCache);
		Image img = new Image("games/ressources/img/pendu0.png");
		img_pendu.setImage(img);
	}
	
	@FXML
	public void choixLettre(ActionEvent evenement) throws RemoteException{
		
		Pendu p = new Pendu();
		
		Button bouton = (Button)evenement.getSource();
		String chaineLettre = bouton.getText();
		chaineLettre = chaineLettre.toLowerCase();
		char lettre = chaineLettre.charAt(0);
		
		boolean verif = p.verifLettre(lettre, mot);
		if(verif==true) {
			motCache = p.avancement(lettre, mot, motCache);
			lbl_pendu.setText(motCache);
			for(int i = 0 ; i < motCache.length() ; i+=2) {
				if(motCache.charAt(i)=='_') {
					break;
				}
				else {
					if(i==(motCache.length()-2)) {
						System.out.println("mot trouvé");
					}
				}
			}
		}
		else {
			erreurs += 1;
			Image img = new Image("games/ressources/img/pendu"+erreurs+".png");
			img_pendu.setImage(img);
			if(erreurs == 9) {
				System.out.println("Vous avez perdu");
			}
		}
		
		bouton.setVisible(false);
	
	}

}

