package rmi;
import java.rmi.*;

import games.AccueilInterface;


public class Client {
	public static void main (String[] argv) {
			try {
				String host = "127.0.0.1";
				int port = 1339;
				AccueilInterface obj =
						(AccueilInterface) Naming.lookup("rmi://"+host+":"+port+"/hello");
				obj.lancement();
			}
			catch(Exception e){
				System.out.println("Erreur : "+e);
			}
	}
}