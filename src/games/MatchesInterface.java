package games;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatchesInterface extends Remote {
	public void play() throws RemoteException;
	public int generateMatches() throws RemoteException;
	public int rand() throws RemoteException;
	public int subMatches(int matches, int sub) throws RemoteException;
	public String endGame(int nbMatches, int order) throws RemoteException;
}
