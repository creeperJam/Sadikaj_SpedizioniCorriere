package SpedizioniCorriere;

public class Menu {

	public static void main(String[] args) {
		Corriere c = new Corriere();
		Input in = new Input();

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

			scelta = in.inputInt("Scelta:");

			System.out.println("\n");

			switch (scelta) {
			case 1: {
				if (c.memorizzazioneCliente()) {
					System.out.println("Cliente memorizzato con successo sul sistema.");
				} else {
					System.out.println("Errore durante la memorizzazione (cliente gia' presente).");
				}
				break;
			}

			case 2: {
				if (c.rimuoviCliente()) {
					System.out.println("Cliente rimosso con successo dal sistema.");
				} else {
					System.out.println("Errore durante la rimozione del cliente dal sistema (cliente non presente).");
				}
				break;
			}

			case 3: {
				if (c.memorizzazioneSpedizione()) {
					System.out.println("Cliente memorizzato con successo.");
				} else {
					System.out.println("Errore durante la memorizzazione (cliente gia' presente).");
				}

				break;
			}

			case 4: {
				if (c.rimuoviSpedizione()) {
					System.out.println("Spedizione rimossa con successo dal sistema.");
				} else {
					System.out.println("Errore durante la rimozione della spedizione dal sistema (spedizione non presente).");
				}
				break;
			}

			case 5: {
				String codice = in.inputString("Codice della spedizione desiderata (anche iniziali):");

				c.visualizzazioneSpedizione(codice);

				break;
			}

			case 6: {
				c.salva();
				break;
			}

			case 7: {
				c.carica(c);
				break;
			}

			case 0: {
				break;
			}

			default: {
				System.out.println(
						"Perfavore, inserire un numero, non lettere o simboli, compreso nell'intervallo del menu.\n\n");
				break;
			}
			} // Fine Switch
		} while (scelta != 0);
	}
}
