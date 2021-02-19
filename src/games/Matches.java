package games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Matches extends UnicastRemoteObject implements MatchesInterface{

	Scanner sc = new Scanner(System.in);
	
	//Constructeur
	
	public Matches() throws RemoteException {
		super();
	}

	//M�thode permettant la g�n�ration d'un nombre impair al�atoire [11;21] d'allumettes pour la partie
	
	public int generateMatches() throws RemoteException {
		int n = new Random().nextInt(12)+11;
		if(n%2==0)
			n+=1;
		return n;
	}
	
	//M�thode permettant la g�n�ration d'un entier entre 1 et 2 pour le tour de l'ordinateur

	public int rand() throws RemoteException {
		return new Random().nextInt(1)+1;
	}
	
	//M�thode permettant de renvoyer le nombre d'allumettes apr�s le tour d'un joueur

	public int subMatches(int matches, int sub) throws RemoteException {
		return matches-sub;
	}
	
	//M�thode permettant de d�terminer si le joueur � gagn� ou perdu � la fin de la partie (nombre d'allumettes == 0)
	
	public String endGame(int nbMatches) throws RemoteException {
		if(nbMatches%2==0){
			return "Vous avez perdu... :( Vous ferez mieux la prochaine fois";
		}
		else {
			return "Vous avez gagn� ! :D GG ";
		}
	}

}
