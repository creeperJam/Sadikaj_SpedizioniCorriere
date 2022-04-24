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

	private Map<String, Cliente> clienti;
	private Map<String, Spedizione> spedizioni;
	private Input in = new Input();

	public Corriere() {
		super();
		clienti = new TreeMap<>();
		spedizioni = new HashMap<>();
	}

	public Corriere(TreeMap<String, Cliente> clienti, HashMap<String, Spedizione> spedizioni) {
		this.clienti = clienti;
		this.spedizioni = spedizioni;
	}

	public boolean memorizzazioneCliente() {
		Cliente c = infoCliente();

		if (!clienti.containsKey(c.getCodiceFiscale())) {
			clienti.put(c.getCodiceFiscale(), new Cliente(c));
			return true;
		}

		return false;

	}

	public boolean rimuoviCliente() {
		String codiceFiscale = in.inputString("Codice fiscale del cliente da cancellare:", 16);

		if (clienti.containsKey(codiceFiscale)) {
			clienti.remove(codiceFiscale);
			return true;
		}
		return false;
	}

	public boolean stampaClienti() {
		if (clienti.isEmpty())
			return false;

		String appoggio[] = clienti.keySet().toArray(new String[clienti.size()]);

		for (int i = 0; i < appoggio.length; i++) {
			System.out
					.println("Cliente " + (i + 1) + "°: " + clienti.get(appoggio[appoggio.length - i - 1]).toString());
		}

		return true;
	}

	public boolean controlloCodiceSpedizione(String codice) {
		String codici[] = spedizioni.keySet().toArray(new String[spedizioni.size()]);

		for (int i = 0; i < codici.length; i++) {
			if (codici[i].compareTo(codice) == 0)
				return true;
		}

		return false;
	}

	public boolean memorizzazioneSpedizione() {
		Spedizione s = infoSpedizione();
		if (!spedizioni.containsValue(s)) {
			spedizioni.put(s.getCodice(), new Spedizione(s));
			return true;
		}

		return false;
	}

	public boolean rimuoviSpedizione() {
		String codice = in.inputStringMaxLen("Inserire codice della spedizione (anche parte iniziale):", 10);
		Spedizione s = ricercaSpedizione(codice);

		if (s == null) {
			return false;
		}

		spedizioni.remove(s.getCodice());
		return true;
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
	
	public void visualizzaSpedizioni() {
		if (!spedizioni.isEmpty()) {
			String appoggio[] = spedizioni.keySet().toArray(new String[spedizioni.size()]);
			
			System.out.println("-----------------------------------------------------------------");
			for (int i = 0; i < appoggio.length; i++) {
				System.out.println("| ");
			}
		} else {
			System.out.println("Nessuna spedizione presente da stampare");
		}
		
	}

	public void salva() {
		salvaClienti(clienti);
		salvaSpedizioni(spedizioni);
	}

	private void salvaClienti(Map<String, Cliente> clienti) {
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

	public TreeMap<String, Cliente> caricaClienti() {
		TreeMap<String, Cliente> clienti;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("clienti.bin"));
			clienti = (TreeMap<String, Cliente>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file clienti.bin");
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
		citta = in.inputString("Citta:");
		indirizzo = in.inputString("Indirizzo:");
		telefono = in.inputPhoneNumber();

		return new Cliente(codiceFiscale, nome, cognome, indirizzo, citta, telefono);
	}

	private Cliente estraiCliente() {
		int opzione;
		String chiavi[] = clienti.keySet().toArray(new String[clienti.size()]);

		System.out.println("Clienti presenti:");
		for (int i = 0; i < chiavi.length; i++) {
			System.out.println(
					(i + 1) + "°) " + clienti.get(chiavi[i]).getNome() + " " + clienti.get(chiavi[i]).getCognome());
		}

		opzione = in.inputInt("Quale cliente si vuole (numero):", 1, clienti.size()) - 1;

		return clienti.get(chiavi[opzione]);
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
			String yn;
			do {
				yn = String.valueOf(in.inputString("Si vuole usare un cliente presente nel sistema? (yes/no)").toLowerCase().charAt(0));

				if (yn.compareTo("y") != 0 && yn.compareTo("n") != 0)
					System.out.println("Risposta non valida.");
			} while (yn.compareTo("y") != 0 && yn.compareTo("n") != 0);

			if (yn.compareTo("y") == 0)
				mittente = estraiCliente();
			else
				mittente = infoCliente();
		} else {
			mittente = infoCliente();
		}

		if (!clienti.containsKey(mittente.getCodiceFiscale())) {
			String yn;
			do {
				yn = String.valueOf(in.inputString("Si vuole memorizzare il mittente tra i clienti? (yes/no)").toLowerCase().charAt(0));
				
				if (yn.compareTo("y") != 0 && yn.compareTo("n") != 0)
					System.out.println("Risposta non valida.");
			} while (yn.compareTo("y") != 0 && yn.compareTo("n") != 0);

			if (yn == "y")
			{
				clienti.put(mittente.getCodiceFiscale(), mittente);
				System.out.println("Ciao mondo: " + clienti.get(mittente.getCodiceFiscale()).toString());
			}
				
		}

		Cliente destinatario = null;
		System.out.println("\n\nDESTINATARIO");
		if (clienti.size() > 0) {
			String yn;
			do {
				yn = in.inputString("Si vuole usare un cliente presente nel sistema? (yes/no)").toLowerCase();

				if (yn.compareTo("y") != 0 && yn.compareTo("n") != 0)
					System.out.println("Risposta non valida.");
			} while (yn.compareTo("y") != 0 && yn.compareTo("n") != 0);

			if (yn.compareTo("y") == 0) {
				do {
					destinatario = estraiCliente();
					if (mittente.equals(destinatario)) System.out.println("Il mittente non può essere anche destinatario.");
				} while (mittente.equals(destinatario));
			}
				
			else
				destinatario = infoCliente();
		} else {
			destinatario = infoCliente();
		}
		
		return new Spedizione(codice, descrizione, dataConsegna, mittente, destinatario);
	}
}
