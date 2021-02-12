package games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;

public class Matches extends UnicastRemoteObject implements GameInterface{

	Scanner sc = new Scanner(System.in);
	
	public Matches() throws RemoteException {
		super();
	}

	@Override
	public void play() throws RemoteException {
		System.out.println("Lancement de la partie");
		int nbMatches = generateMatches();
		System.out.println("La partie se joue avec "+nbMatches+" allumettes.");
		System.out.println("Voulez vous jouer en premier ou en deuxi�me ?");
		int order = sc.nextInt();
		int subMatches = 0;
		while(nbMatches>=2) {
			if(order == 1) {
				System.out.println("Combien d'allumettes voulez vous retirer ?");
				subMatches = sc.nextInt();
				order = 2;
			}
			else {
				subMatches = new Random().nextInt(2)+1;
				System.out.println("L'adversaire retire "+subMatches+" allumette(s)");
				order = 1;
			}
			nbMatches = nbMatches - subMatches;
			System.out.println("Il reste "+nbMatches+" allumettes");
		}
		if(nbMatches==1 && order==1 || nbMatches==0 && order==2){
			System.out.println("Vous avez perdu... :(");
		}
		else {
			System.out.println("Vous avez gagn� ! :D");
		}
	}
	
	public int generateMatches() {
		int n = new Random().nextInt(12)+11;
		if(n%2==0)
			n+=1;
		return n;
	}

}
