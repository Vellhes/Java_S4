package games;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class Pendu extends UnicastRemoteObject implements PenduInterface{

	protected Pendu() throws RemoteException {
		super();
	}

}
