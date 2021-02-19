package games;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatchesInterface extends Remote {
	public int generateMatches() throws RemoteException;
	public int rand() throws RemoteException;
	public int subMatches(int matches, int sub) throws RemoteException;
	public String endGame(int nbMatches) throws RemoteException;
}
