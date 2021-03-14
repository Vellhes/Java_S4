package games;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Random;

@SuppressWarnings("serial")
public class Pendu extends UnicastRemoteObject implements PenduInterface{

	public Pendu() throws RemoteException {
		super();
	}

	@Override
	public String selectMot() throws RemoteException {
		List<String> dico = null;
		try {
			dico = Files.readAllLines(new File("src/games/ressources/dictionnaire.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Random aleatoire = new Random();
		return dico.get(aleatoire.nextInt(dico.size()));
	}

	@Override
	public boolean verifLettre(char lettre, String mot) throws RemoteException {
		boolean verif = mot.indexOf(lettre) >= 0;
		return verif;
	}

	@Override
	public String avancement(char lettre, String mot, String motCache) throws RemoteException {
		String nouveauMot = "";
		for(int i = 0;i<mot.length();i++ ) {
			int rang = i*2;
			if(mot.charAt(i)==lettre) {
				nouveauMot = nouveauMot+lettre+" ";
			}
			else if(motCache.charAt(rang) != '_') {
				nouveauMot = nouveauMot+motCache.charAt(rang)+" ";
			}
			else {
				nouveauMot = nouveauMot+"_ ";
			}
		}
		return nouveauMot;
	}

	@Override
	public String finDuJeu(int erreurs) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
