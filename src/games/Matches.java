package games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Matches extends UnicastRemoteObject implements GameInterface{

	public Matches() throws RemoteException {
		super();
	}

	@Override
	public void play() throws RemoteException {
		System.out.println("Lancement de la partie");
		
	}

}
