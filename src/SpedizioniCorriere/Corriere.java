package SpedizioniCorriere;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Corriere {

	private static int contatore = 0;
	private Map<Integer, Cliente> clienti;
	private Map<String, Spedizione> spedizioni;
	private Input in = new Input();

	public Corriere() {
		super();
		clienti = new TreeMap<>();
		spedizioni = new HashMap<>();
	}

	public Corriere(TreeMap<Integer, Cliente> clienti, HashMap<String, Spedizione> spedizioni) {
		this.clienti = clienti;
		this.spedizioni = spedizioni;
	}

	private int controlloClienti(String codiceFiscale) {
		Cliente temp;
		for (int i = 0; i < clienti.size(); i++) {
			if (clienti.containsKey(i)) {
				temp = clienti.get(i);
				if (temp.getCodiceFiscale().compareToIgnoreCase(codiceFiscale) == 0)
					return i;
			}
		}

		return -1;
	}

	public boolean memorizzazioneCliente() {
		Cliente c = infoCliente();
		if (controlloClienti(c.getCodiceFiscale()) != -1) {
			clienti.put(contatore++, c);
			return true;
		} else {
			return false;
		}
	}

	public boolean rimuoviCliente() {
		String codiceFiscale = in.inputString("Codice fiscale del cliente da cancellare:");
		int i;
		if ((i = controlloClienti(codiceFiscale)) != -1) {
			clienti.remove(i);
			return true;
		}
		return false;
	}

	public boolean memorizzazioneSpedizione() {
		Spedizione s = infoSpedizione();
		if (!spedizioni.containsValue(s)) {
			spedizioni.put(s.getCodice(), s);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean rimuoviSpedizione() { 
		String codice = in.inputString("Inserire codice della spedizione (anche parte iniziale):", 10);
		Spedizione s;
		if ((s = ricercaSpedizione(codice)) != null) {
			spedizioni.remove(s);
			return true;
		}
		return false;
	}
	
	

	public Spedizione ricercaSpedizione(String codice) {
		Input in = new Input();

		codice = codice.toLowerCase();
		String trovati[] = new String[spedizioni.size()];
		String codici[] = spedizioni.keySet().toArray(new String[spedizioni.size()]);

		int index = 0;
		for (int i = 0; i < spedizioni.size(); i++) {
			if (codici[i].startsWith(codice)) {
				trovati[index++] = codici[i];
			}
		}

		if (index == 0)
			return null;
		if (index == 1)
			codice = trovati[0];
		else {
			System.out.println("Ci sono più spedizione che iniziano con " + codice + ", scelga una delle seguenti: ");
			for (int i = 0; i < index; i++) {
				System.out.println((i + 1) + "°) " + spedizioni.get(trovati[i]));
			}

			int scelta;
			scelta = in.inputInt("Spedizione numero:", 1, index);

			codice = trovati[scelta - 1];
		}

		return spedizioni.get(codice);
	}

	public void visualizzazioneSpedizione(String codice) {
		Spedizione temp;
		if ((temp = ricercaSpedizione(codice)) == null) {
			System.out.println("Nessuna spedizione presente con il codice inserito.");
		} else {
			System.out.println(temp.toString());
		}
	}

	public void salva() {
		salvaClienti(clienti);
		salvaSpedizioni(spedizioni);
	}

	private void salvaClienti(Map<Integer, Cliente> clienti) {
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

	private void salvaSpedizioni(Map<String, Spedizione> spedizioni2) {
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

	public void carica(Corriere c) {
		c = new Corriere(caricaClienti(), caricaSpedizioni());
	}

	private TreeMap<Integer, Cliente> caricaClienti() {
		TreeMap<Integer, Cliente> clienti;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("clienti.bin"));
			clienti = (TreeMap<Integer, Cliente>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file clienti.bin");
			return clienti;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Errore nella lettura da file");
		}
		return null;
	}

	private HashMap<String, Spedizione> caricaSpedizioni() {
		HashMap<String, Spedizione> spedizioni;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("spedizioni.bin"));
			spedizioni = (HashMap<String, Spedizione>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file spedizioni.bin");
			return spedizioni;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Errore nella lettura da file");
		}
		return null;
	}

	private Cliente infoCliente() {

		Input in = new Input();

		String codiceFiscale, nome, cognome, indirizzo, citta, telefono;

		do {
			System.out.println();
			codiceFiscale = in.inputString(
					"Inserire informazioni cliente:\nCodice fiscale (Formato AAAAAA-11-A-11-A-111-A, senza trattini):");
			if (!Pattern.matches("\\p{Alpha}{6}\\d{2}\\p{Alpha}\\d{2}\\p{Alpha}\\d{3}\\p{Alpha}", codiceFiscale))
				System.out.println("Il codice fiscale inserito non rispetta il formato richiesto.");
		} while (!Pattern.matches("\\p{Alpha}{6}\\d{2}\\p{Alpha}\\d{2}\\p{Alpha}\\d{3}\\p{Alpha}", codiceFiscale));

		nome = in.inputString("Nome:");
		cognome = in.inputString("Cognome:");
		indirizzo = in.inputString("Indirizzo:");
		citta = in.inputString("Citta:");

		telefono = in.inputPhoneNumber();

		return new Cliente(codiceFiscale, nome, cognome, indirizzo, citta, telefono);
	}

	private Cliente estraiCliente(String yn) {
		int opzione;

		System.out.println("Clienti presenti:");
		for (int i = 0; i < clienti.size(); i++) {
			System.out.println((i + 1) + "°) " + clienti.get(i).getNome() + " " + clienti.get(i).getCognome());
		}

		opzione = in.inputInt("Quale cliente si vuole (numero):", 0, clienti.size() - 1);
		opzione--;

		return clienti.get(opzione);
	}

	private Spedizione infoSpedizione() {

		System.out.println("Inserire informazioni spedizione:");
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		String codice = "";
		Random rnd = new Random();
		// Creazione casuale del codice spedizione da 10 lettere (minuscole) e numeri
		for (int i = 0; i < 10; i++) {
			codice += chars.charAt(rnd.nextInt(chars.length()));
		}
		System.out.println("Codice della spedizione: " + codice);
		String descrizione = in.inputString("Descrizione:");

		// Inserimento orario
		LocalDate dataConsegna = in.inputDate("Data spedizione:");

		System.out.println("\nMITTENTE");
		Cliente mittente = null;
		if (clienti.size() > 0) {
			System.out.println("Si vuole usare un cliente già presente nel sistema? (y/n)");

			String yn;
			do {
				yn = in.inputString(" ");
				yn = yn.toLowerCase();
				if (yn.length() > 1)
					yn = String.valueOf(yn.charAt(0));
				if (!Pattern.matches("\\p{Lower}", yn))
					System.out.print("Inserito un carattere diverso da y/n, reinserire: ");
			} while (!Pattern.matches("\\p{Lower}", yn));

			if (yn == "y")
				mittente = estraiCliente(yn);
			else
				mittente = infoCliente();
		} else {
			mittente = infoCliente();
		}

		Cliente destinatario;
		String yn = "n";
		System.out.println("\n\nDESTINATARIO");
		if (clienti.size() > 0) {
			System.out.println("Si vuole usare un cliente già presente nel sistema? (y/n)");

			do {
				yn = in.inputString(" ");
				yn = yn.toLowerCase();
				if (yn.length() > 1)
					yn = String.valueOf(yn.charAt(0));
				if (!Pattern.matches("\\p{Lower}", yn))
					System.out.print("Inserito un carattere diverso da y/n, reinserire: ");
			} while (!Pattern.matches("\\p{Lower}", yn));
		}

		if (yn == "y")
			destinatario = estraiCliente(yn);
		else
			destinatario = infoCliente();

		return new Spedizione(codice, descrizione, dataConsegna, mittente, destinatario);
	}
}
