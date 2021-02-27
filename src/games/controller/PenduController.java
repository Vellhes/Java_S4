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
		
		String word = null;
		
		try {
			word = p.selectWord();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		lbl_pendu.setText(word);
		Image img = new Image("games/ressources/img/pendu0.png");
		img_pendu.setImage(img);
		
	}
	
	@FXML
	public void letterChoice(ActionEvent event){
		Button but = (Button)event.getSource();
	}

}

