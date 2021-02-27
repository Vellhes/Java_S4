package games;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Random;

@SuppressWarnings("serial")
public class Pendu extends UnicastRemoteObject implements PenduInterface{

	public Pendu() throws RemoteException {
		super();
	}

	@Override
	public String selectWord() throws RemoteException {
		List<String> dico = null;
		try {
			dico = Files.readAllLines(new File("src/games/ressources/dictionnaire.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Random rand = new Random();
		return dico.get(rand.nextInt(dico.size()));
	}

	@Override
	public boolean checkLetter(char letter, String word) throws RemoteException {
		boolean check = word.indexOf(letter) > 0;
		return check;
	}

	@Override
	public String avancement(char letter, String word, String guessWord) throws RemoteException {
		String newWord = "";
		for(int i = 0;i<word.length();i++ ) {
			int rank = i*2;
			if(word.charAt(i)==letter) {
				newWord = newWord+letter+" ";
			}
			else if(guessWord.charAt(rank) != '_') {
				newWord = newWord+guessWord.charAt(rank)+" ";
			}
			else {
				newWord = newWord+"_ ";
			}
		}
		return newWord;
	}

	@Override
	public String endGame(int mistakes) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
