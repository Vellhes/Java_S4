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

	
	//M�thode s�lectionnant un mot al�atoire du fichier dictionnaire.txt
	@Override
	public String selectMot() throws RemoteException {
		List<String> dico = null;
		try {
			dico = Files.readAllLines(new File("src/games/ressources/dictionnaire.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Random aleatoire = new Random();
		return dico.get(aleatoire.nextInt(dico.size())).trim();
	}

	
	//M�thode v�rifiant si la lettre choisie se trouve dans le mot � trouver
	@Override
	public boolean verifLettre(char lettre, String mot) throws RemoteException {
		boolean verif = mot.indexOf(lettre) >= 0;
		return verif;
	}

	//M�thode r�v�lant les emplacement de la lettre choisie dans le mot � trouver
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

	//M�thode annon�ant si on a gagn� ou perdu
	@Override
	public String finDuJeu(int erreurs) throws RemoteException {
		if(erreurs == 9) {
			return "Vous avez perdu... :( Vous ferez mieux la prochaine fois";
		}
		else {
			return "Vous avez gagn� ! :D GG ";
		}
	}

}
