package games.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;

import games.Pendu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PenduController implements Initializable {

	String mot = null;
	
	String motCache = "";
	
	int erreurs = 0;
	
	@FXML
	private VBox boxJeu;
	
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
		boxJeu.setDisable(false);
		motCache = "";
		erreurs = 0;
		btn_a.setVisible(true);
		btn_b.setVisible(true);
		btn_c.setVisible(true);
		btn_d.setVisible(true);
		btn_e.setVisible(true);
		btn_f.setVisible(true);
		btn_g.setVisible(true);
		btn_h.setVisible(true);
		btn_i.setVisible(true);
		btn_j.setVisible(true);
		btn_k.setVisible(true);
		btn_l.setVisible(true);
		btn_m.setVisible(true);
		btn_n.setVisible(true);
		btn_o.setVisible(true);
		btn_p.setVisible(true);
		btn_q.setVisible(true);
		btn_r.setVisible(true);
		btn_s.setVisible(true);
		btn_t.setVisible(true);
		btn_u.setVisible(true);
		btn_v.setVisible(true);
		btn_w.setVisible(true);
		btn_x.setVisible(true);
		btn_y.setVisible(true);
		btn_z.setVisible(true);

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
						alerteDeFin(erreurs);
					}
				}
			}
		}
		else {
			erreurs += 1;
			Image img = new Image("games/ressources/img/pendu"+erreurs+".png");
			img_pendu.setImage(img);
			if(erreurs == 9) {
				alerteDeFin(erreurs);
			}
		}
		
		bouton.setVisible(false);
	
	}
	
	public void alerteDeFin(int erreurs) throws RemoteException {
		boxJeu.setDisable(true);
		Pendu p = new Pendu();
		Alert alerteFin = new Alert(AlertType.INFORMATION);
		alerteFin.setContentText(p.finDuJeu(erreurs));
		alerteFin.setHeaderText(null);
		alerteFin.showAndWait();
		ButtonType oui = new ButtonType("oui", ButtonData.OK_DONE);
		ButtonType non = new ButtonType("non", ButtonData.CANCEL_CLOSE);
		Alert alertRecommencer = new Alert(AlertType.CONFIRMATION, "Voulez vous faire une nouvelle partie ?",oui,non);
		Optional<ButtonType> resultat = alertRecommencer.showAndWait();
		if(resultat.get().getText()=="oui") {
			initialize(null,null);
		}
	}

}

