package games;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PenduInterface extends Remote {
	
	public String selectMot() throws RemoteException;
	public boolean verifLettre(char lettre, String mot) throws RemoteException;
	public String avancement(char lettre, String mot, String motCache) throws RemoteException;
	public String finDuJeu(int erreurs) throws RemoteException;
}
