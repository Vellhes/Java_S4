package games;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatchesInterface extends Remote {
	public void play() throws RemoteException;
}
