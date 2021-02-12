package rmi;
import java.rmi.*;
import java.rmi.server.*;

import games.GameInterface;


public class Client {
	public static void main (String[] argv) {
			try {
				String host = "127.0.0.1";
				int port = 1337;
				GameInterface obj =
						(GameInterface) Naming.lookup("rmi://"+host+":"+port+"/hello");
				obj.play();
			}
			catch(Exception e){
				System.out.println("Erreur : ");
			}
	}
}