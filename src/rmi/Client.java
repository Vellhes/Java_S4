package rmi;
import java.rmi.*;
import java.rmi.server.*;

import games.MatchesInterface;


public class Client {
	public static void main (String[] argv) {
			try {
				String host = "127.0.0.1";
				int port = 1338;
				MatchesInterface obj =
						(MatchesInterface) Naming.lookup("rmi://"+host+":"+port+"/hello");
				obj.play();
			}
			catch(Exception e){
				System.out.println("Erreur : "+e);
			}
	}
}