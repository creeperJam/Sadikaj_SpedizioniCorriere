package SpedizioniCorriere;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Corriere {

	private static int contatore = 0;
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

	public boolean memorizzazioneCliente() {
		Cliente c = infoCliente();
		if (!clienti.containsValue(c)) {
			clienti.put(contatore++, c);
			return true;
		} else {
			return false;
		}
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

		if (index == 0)
			return null;
		if (index == 1)
			codice = trovati[0];
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
				if (scelta < 1 || scelta > index)
					System.out.println("Inserire uno dei numeri delle squadre, non fuori dall'intervallo.");
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

	public Cliente infoCliente() {
		
		Scanner sc = new Scanner(System.in);
		String codiceFiscale, nome, cognome, indirizzo, citta, telefono;

		
		do {
			System.out.println("Inserire informazioni cliente:");
			System.out.print("Codice fiscale (Formato ABCDEF12A12B123C: ");
			codiceFiscale = sc.next();
			if (Pattern.matches("\\p{Alpha}{6}\\d{2}\\p{Alpha}\\d{2}\\p{Alpha}\\d{3}\\p{Alpha}", codiceFiscale))
				System.out.println("Il codice fiscale inserito non rispetta il formato richiesto.");
		} while (Pattern.matches("\\p{Alpha}{6}\\d{2}\\p{Alpha}\\d{2}\\p{Alpha}\\d{3}\\p{Alpha}", codiceFiscale));

		System.out.print("Nome: ");
		nome = sc.next();
		System.out.print("Cognome: ");
		cognome = sc.next();
		System.out.print("Indirizzo: ");
		sc.nextLine();
		indirizzo = sc.nextLine();
		System.out.print("Città: ");
		citta = sc.nextLine();

		do {
			System.out.print("Numero di telefono (lungo 10, no spazi): ");
			telefono = sc.next();
			if (telefono.length() != 10)
				System.out.println("Numero di telefono inserito invalido.");
		} while (telefono.length() != 10);

		return new Cliente(codiceFiscale, nome, cognome, indirizzo, citta, telefono);
	}

	public Spedizione infoSpedizione() {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Inserire informazioni spedizione:");
		String chars = "0123456789abcdefghijklmnopqrstuvwxyz";
		String codice = "";
		Random rnd = new Random();
		// Creazione casuale del codice spedizione da 10 lettere (minuscole) e numeri
		for (int i = 0; i < 10; i++) {
			codice += chars.charAt(rnd.nextInt(chars.length()));
		}
		System.out.println("Codice della spedizione: " + codice);
		System.out.print("Descrizione: ");
		String descrizione = sc.nextLine();

		// Inserimento orario
		System.out.println("Inserimento data e ora spedizione:");
		int anno = 0, mese = 0, giorno = 0;
		boolean test = false;

		do {
			try {
				System.out.print("Anno: ");
				anno = Integer.parseInt(sc.next());
			} catch (NumberFormatException e) {
				System.out.println("Per favore inserire un numero, non lettere o simboli.");
				test = true;
			}

			System.out.println("Anno inserito: " + anno);
		} while (test || anno < 2000 || anno > 2022);

		do {
			try {
				System.out.print("Mese: ");
				mese = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Per favore inserire un numero, non lettere o simboli.");
				test = true;
			}
		} while (test && (mese < 1 || mese > 12));

		do {
			try {
				System.out.print("Giorno: ");
				giorno = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Per favore inserire un numero, non lettere o simboli.");
				test = true;
			}
		} while (test && (giorno < 1 || giorno > 31));

		int ora = 0, minuti = 0;

		do {
			try {
				System.out.print("Ora: ");
				ora = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Per favore inserire un numero, non lettere o simboli.");
				test = true;
			}
		} while (test && (ora < 0 || ora > 23));

		do {
			try {
				System.out.print("Minuti: ");
				minuti = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Per favore inserire un numero, non lettere o simboli.");
				test = true;
			}
		} while (test && (minuti < 0 || minuti > 59));

		LocalDateTime dataOraConsegna = LocalDateTime.of(anno, mese, giorno, ora, minuti);

		System.out.println("MITTENTE");
		System.out.println("Si vuole usare un cliente già presente nel sistema? (y/n)");
		String scelta = sc.next("\\p{ALPHA}");
		Cliente mittente;
		if (scelta.toLowerCase().equals("y")) {

			System.out.println("Clienti presenti:");
			for (int i = 0; i < clienti.size(); i++) {
				System.out.println((i + 1) + "°) " + clienti.get(i).getNome() + " " + clienti.get(i).getCognome());
			}
			
			int opzione = -1;
			do {
				try {
					System.out.println("Quale cliente si vuole (numero): ");
					opzione = sc.nextInt();
					sc.nextLine();
					opzione--;
				} catch (InputMismatchException e) {
					System.out.println("Inserire un numero tra quelli presenti, non fuori dall'intervallo.");
				}
			} while (opzione < 0 || opzione > clienti.size() - 1);
			
			
			
			mittente = clienti.get(opzione);
		} else {
			mittente = infoCliente();
		}

		System.out.println("\n\nDESTINATARIO");
		System.out.println("Si vuole usare un cliente già presente nel sistema? (y/n)");
		String yn = sc.next("\\p{ALPHA}");
		System.out.println("DESTINATARIO");
		Cliente destinatario = infoCliente();

		return new Spedizione(codice, descrizione, dataOraConsegna, mittente, destinatario);
	}
}
