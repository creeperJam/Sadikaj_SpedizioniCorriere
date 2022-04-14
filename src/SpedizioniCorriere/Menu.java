package SpedizioniCorriere;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Menu {

	public static Cliente infoCliente(Scanner sc) {
		System.out.println("Inserire informazioni cliente:");
		System.out.print("Codice fiscale: ");
		String codiceFiscale = sc.next();
		System.out.print("Nome: ");
		String nome = sc.next();
		System.out.print("Cognome: ");
		String cognome = sc.next();
		System.out.print("Indirizzo: ");
		sc.nextLine();
		String indirizzo = sc.nextLine();
		System.out.print("Citt�: ");
		String citta = sc.nextLine();
		System.out.print("Numero di telefono (no spazi): ");
		String telefono = sc.next();

		return new Cliente(codiceFiscale, nome, cognome, indirizzo, citta, telefono);
	}

	public static Spedizione infoSpedizione(Scanner sc) {
		System.out.println("Inserire informazioni spedizione:");
		String chars = "0123456789abcdefghijk";
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
		Cliente mittente = infoCliente(sc);
		System.out.println("DESTINATARIO");
		Cliente destinatario = infoCliente(sc);

		return new Spedizione(codice, descrizione, dataOraConsegna, mittente, destinatario);
	}

	public static void main(String[] args) {
		Corriere c = new Corriere();
		Scanner sc = new Scanner(System.in);
		int scelta;
		do {
			System.out.println("---------------Menu---------------");
			System.out.println("| 1) Memorizzazione cliente      |");
			System.out.println("| 2) Cancellazione cliente       |");
			System.out.println("| 3) Memorizzazione spedizione   |");
			System.out.println("| 4) Annulla spedizione          |");
			System.out.println("| 5) Visualizzazione spedizioni  |");
			System.out.println("| 6) Salva clienti e spedizioni  |");
			System.out.println("| 7) Carica clienti e spedizioni |");
			System.out.println("| 0) Esci                        |");
			System.out.println("----------------------------------\n");

			System.out.print("Scelta: ");
			try {
				scelta = sc.nextInt();
			} catch (InputMismatchException e) {
				scelta = -1;
			}

			sc.nextLine();

			System.out.println("\n\n");

			switch (scelta) {
			case 1: {
				Cliente cliente = infoCliente(sc);
				if (c.memorizzazioneCliente(cliente)) {
					System.out.println("Cliente memorizzato con successo.");
				} else {
					System.out.println("Errore durante la memorizzazione (cliente gia' presente).");
				}
				break;
			}

			case 2: {
				Spedizione spedizione = infoSpedizione(sc);
				if (c.memorizzazioneSpedizione(spedizione)) {
					System.out.println("Cliente memorizzato con successo.");
				} else {
					System.out.println("Errore durante la memorizzazione (cliente gia' presente).");
				}
				break;
			}

			case 3: {
				System.out.print("Codice della spedizione desiderata (anche iniziali): ");
				String codice = sc.next();
				
				c.visualizzazioneSpedizione(codice);
				break;
			}

			case 4: {
				c.salva();
				break;
			}

			case 5: {
				c = new Corriere(c.caricaClienti(), c.caricaSpedizioni());
				break;
			}

			case 0: {
				break;
			}

			default: {
				System.out.println("Perfavore, inserire un numero, non lettere o simboli.\n\n");
				break;
			}
			} // Fine Switch
		} while (scelta != 0);
	}
}