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
			System.out.println("| 6) Visualizzazione clienti     |");
			System.out.println("| 7) Salva clienti e spedizioni  |");
			System.out.println("| 8) Carica clienti e spedizioni |");
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
					System.out.println("Spedizione memorizzata con successo.");
				} else {
					System.out.println("Errore durante la memorizzazione (spedizione gia' presente).");
				}

				break;
			}

			case 4: {
				if (c.rimuoviSpedizione()) {
					System.out.println("Spedizione rimossa con successo dal sistema.");
				} else {
					System.out.println(
							"Errore durante la rimozione della spedizione dal sistema (spedizione non presente).");
				}
				break;
			}

			case 5: {

				System.out.println("-------Opzioni-------");
				System.out.println("| 1) Con codice     |");
				System.out.println("| 2) Tutte          |");
				System.out.println("| 3) Torna indietro |");
				System.out.println("---------------------\n");

				int opzione = in.inputInt("Scelta:", 1, 3);

				switch (opzione) {
				case 1: {
					String codice = in.inputString("Codice della spedizione desiderata (anche iniziali):");

					c.visualizzazioneSpedizione(codice);
					break;
				}

				case 2: {
					c.visualizzaSpedizioni();
					break;
				}
				} // Fine switch 2

				break;
			} // Fine case 5 - switch 1

			case 6: {

				System.out.println("-------Opzioni-------");
				System.out.println("| 1) Con codice     |");
				System.out.println("| 2) Tutte          |");
				System.out.println("| 3) Torna indietro |");
				System.out.println("---------------------\n");

				int opzione = in.inputInt("Scelta:", 1, 3);

				switch (opzione) {
				case 1: {
					System.out.print("Codice fiscale del cliente desiderato: ");
					break;
				}

				case 2: {
					if (!c.stampaClienti()) {
						System.out.println(
								"Nessun cliente presente nel sistema, inserirne almeno uno prima di stampare.");
					}
					break;
				}
				}

				break;
			}

			case 7: {
				c.salva();
				break;
			}

			case 8: {
				c = new Corriere(c.caricaClienti(), c.caricaSpedizioni());
				break;
			}

			case 0: {
				break;
			}

			default: {
				System.out.println(
						"Perfavore, inserire un numero, non lettere o simboli, compreso nell'intervallo del menu.");
				break;
			}
			} // Fine Switch

			System.out.println("\n");
		}while(scelta!=0);
}}
