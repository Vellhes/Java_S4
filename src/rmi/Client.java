package rmi;
import java.rmi.*;

import games.AllumettesInterface;


public class Client {
	public static void main (String[] argv) {
			try {
				String host = "127.0.0.1";
				int port = 1338;
				AllumettesInterface obj =
						(AllumettesInterface) Naming.lookup("rmi://"+host+":"+port+"/hello");

			}
			catch(Exception e){
				System.out.println("Erreur : "+e);
			}
	}
}