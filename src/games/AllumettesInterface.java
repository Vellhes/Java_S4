package games;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AllumettesInterface extends Remote {
	public int generationAllumettes() throws RemoteException;
	public int coupOrdi() throws RemoteException;
	public int soustraireAllumettes(int allum, int moins) throws RemoteException;
	public String finDuJeu(int nbAllumettes) throws RemoteException;
}
