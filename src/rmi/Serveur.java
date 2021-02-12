package rmi;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

import games.Matches;

public class Serveur{
	public static void main (String[] argv) {
		try {
			String hote = "127.0.0.1";
			int port = 1338;
			//Naming.rebind ("hello", new HelloImpl());
			LocateRegistry.createRegistry(port);
			Naming.rebind ("rmi://"+hote+":"+port+"/hello", new Matches());
			System.out.println ("Hello Server prêt !");
		} catch (Exception e) {
			System.out.println ("Hello Server échec " + e);
		}
	}
}