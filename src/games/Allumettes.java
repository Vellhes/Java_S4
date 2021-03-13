package games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Allumettes extends UnicastRemoteObject implements AllumettesInterface{

	Scanner sc = new Scanner(System.in);
	
	//Constructeur
	
	public Allumettes() throws RemoteException {
		super();
	}

	//Méthode permettant la génération d'un nombre impair aléatoire [11;21] d'allumettes pour la partie
	
	public int generationAllumettes() throws RemoteException {
		int n = new Random().nextInt(12)+11;
		if(n%2==0)
			n+=1;
		return n;
	}
	
	//Méthode permettant la génération d'un entier entre 1 et 2 pour le tour de l'ordinateur

	public int aleatoire() throws RemoteException {
		return new Random().nextInt(1)+1;
	}
	
	//Méthode permettant de renvoyer le nombre d'allumettes après le tour d'un joueur

	public int soustraireAllumettes(int allum, int moins) throws RemoteException {
		return allum-moins;
	}
	
	//Méthode permettant de déterminer si le joueur à gagné ou perdu à la fin de la partie (nombre d'allumettes == 0)
	
	public String finDuJeu(int nbAllumettes) throws RemoteException {
		if(nbAllumettes%2==0){
			return "Vous avez perdu... :( Vous ferez mieux la prochaine fois";
		}
		else {
			return "Vous avez gagné ! :D GG ";
		}
	}

}
