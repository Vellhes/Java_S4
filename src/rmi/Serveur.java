package rmi;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import games.Accueil;

public class Serveur{
	public static void main (String[] argv) {
		try {
			String hote = "127.0.0.1";
			int port = 1335;
			//Naming.rebind ("hello", new HelloImpl());
			LocateRegistry.createRegistry(port);
			Naming.rebind ("rmi://"+hote+":"+port+"/hello", new Accueil());
			System.out.println ("Hello Server prêt !");
		} catch (Exception e) {
			System.out.println ("Hello Server échec " + e);
		}
	}
}