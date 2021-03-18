package games;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccueilInterface extends Remote{
	public void lancement() throws RemoteException;
}
