package SpedizioniCorriere;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Corriere {
	
	private static int contatore = 1;
	private Map<Integer, Cliente> clienti;
	private Map<String, Spedizione> spedizioni;
	
	public Corriere() {
		super();
		clienti = new TreeMap<>();
		spedizioni = new HashMap<>();
	}

	public Corriere(TreeMap<Integer, Cliente> clienti, HashMap<String, Spedizione> spedizioni) {
		this.clienti = clienti;
		this.spedizioni = spedizioni;
	}
	
	
	public boolean memorizzazioneCliente(Cliente c) {
		if (!clienti.containsValue(c)) {
			clienti.put(contatore++, c);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean memorizzazioneSpedizione(Spedizione s) {
		if (!spedizioni.containsValue(s)) {
			spedizioni.put(s.getCodice(), s);
			return true;
		} else {
			return false;
		}
	}
	
	public Spedizione ricercaSpedizione(String codice) {
		codice = codice.toLowerCase();
		String trovati[] = new String[spedizioni.size()];
		String codici[] = spedizioni.keySet().toArray(new String[spedizioni.size()]);
		
		int index = 0;
		for (int i = 0; i < spedizioni.size(); i++) {
			if (codici[i].startsWith(codice)) {
				trovati[index++] = codici[i];
			}
		}
		
		if (index == 0) return null;
		if (index == 1) codice = trovati[0];
		else {
			System.out.println("Ci son più squadre con il prefisso indicato, sceglierne una:");
			for (int i = 0; i < index; i++) {
				System.out.println((i + 1) + "°) " + spedizioni.get(trovati[i]));
			}
			
			Scanner sc = new Scanner(System.in);
			int scelta;
			do {
				System.out.print("Squadra numero: ");
				scelta = sc.nextInt();
				sc.nextLine();
				if (scelta < 1 || scelta > index) System.out.println("Inserire uno dei numeri delle squadre, non fuori dall'intervallo.");
			} while (scelta < 1 || scelta > index);
			
			codice = trovati[scelta - 1];
		}
		
		
		return spedizioni.get(codice);
	}
	
	
	public void visualizzazioneSpedizione(String codice) {
		Spedizione temp;
		if ((temp = ricercaSpedizione(codice)) == null) {
			System.out.println("Nessuna spedizione presente con il codice inserito.");
		} else {
			System.out.println("Spedizione: " + temp.getCodice());
			System.out.println(temp.toString());
		}
	}
	
	
	public void salva() {
		salvaClienti(clienti);
		salvaSpedizioni(spedizioni);
	}
	
	
	private void salvaClienti(Map<Integer, Cliente> clienti)  {
		ObjectOutputStream oos = null;
		try {
			// APRO IL FILE IN SCRITTURA
			oos = new ObjectOutputStream(new FileOutputStream("clienti.bin"));
			// SERIALIZZO L'OGGETTO lista di studenti
			oos.writeObject(clienti);
			oos.close();
			System.out.println("Dati salvati nel file clienti.bin");
		} catch (IOException e) {
			System.out.println("Errore nella scrittura del file");
		}
	}
	
	private void salvaSpedizioni(Map<String, Spedizione> spedizioni2)  {
		ObjectOutputStream oos = null;
		try {
			// APRO IL FILE IN SCRITTURA
			oos = new ObjectOutputStream(new FileOutputStream("spedizioni.bin"));
			// SERIALIZZO L'OGGETTO lista di studenti
			oos.writeObject(spedizioni2);
			oos.close();
			System.out.println("Dati salvati nel file spedizioni.bin");
		} catch (IOException e) {
			System.out.println("Errore nella scrittura del file");
		}
	}
	
	public TreeMap<Integer, Cliente> caricaClienti() {
		TreeMap<Integer, Cliente> clienti;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("studenti.bin"));
			clienti = (TreeMap<Integer, Cliente>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file studenti.bin");
			return clienti;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Errore nella lettura da file");
		}
		return null;
	}
	
	public HashMap<String, Spedizione> caricaSpedizioni() {
		HashMap<String, Spedizione> spedizioni;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("studenti.bin"));
			spedizioni = (HashMap<String, Spedizione>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file studenti.bin");
			return spedizioni;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Errore nella lettura da file");
		}
		return null;
	}
}
