package games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import main.Main;


@SuppressWarnings("serial")
public class Accueil extends UnicastRemoteObject implements AccueilInterface{

	public Accueil() throws RemoteException {
		super();
	}

	@Override
	public void lancement() throws RemoteException{
		Main.main(null);
	}

}
