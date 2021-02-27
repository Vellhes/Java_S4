package games;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PenduInterface extends Remote {
	
	public String selectWord() throws RemoteException;
	public boolean checkLetter(char letter, String word) throws RemoteException;
	public String avancement(char letter, String word, String guessWord) throws RemoteException;
	public String endGame(int mistakes) throws RemoteException;
}
