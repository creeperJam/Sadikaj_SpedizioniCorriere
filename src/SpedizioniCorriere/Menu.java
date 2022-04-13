package SpedizioniCorriere;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	
	public Cliente infoCliente(Scanner sc) {
		System.out.println("Inserire inforazioni cliente:");
		System.out.print("Codice fiscale: ");
		String codiceFiscale = sc.next();
		System.out.print("Nome: ");
		String nome = sc.next();
		System.out.print("Cognome: ");
		String cognome = sc.next();
		System.out.print("Indirizzo: ");
		String indirizzo = sc.next();
		System.out.print("Città: ");
		String citta = sc.next();
		System.out.print("Numero di telefono: ");
		String telefono = sc.next();
		
		return new Cliente(codiceFiscale, nome, cognome, indirizzo, citta, telefono);
	}

	public static void main(String[] args) {
		Corriere c = new Corriere();
		Scanner sc = new Scanner(System.in);
		int scelta;
		do {
			System.out.println("---------------Menu---------------");
			System.out.println("| 1) Aggiunta cliente            |");
			System.out.println("| 2) Aggiunta spedizione         |");
			System.out.println("| 3) Visualizzazione spedizione  |");
			System.out.println("| 4) Salva clienti e spedizioni  |");
			System.out.println("| 5) Carica clienti e spedizioni |");
			System.out.println("| 0) Esci                        |");
			System.out.println("----------------------------------\n");
			
			System.out.print("Scelta: ");
			try {
				scelta = sc.nextInt();
			} catch (InputMismatchException e) {
				scelta = 6;
			}
			
			sc.nextLine();
			
			System.out.println("\n\n");
			
			
			switch(scelta) {
			case 1:{
				
				break;
			}
				
				
			case 2:{
				
				break;
			}
				
				
			case 3:{
				
				break;
			}
				
				
				
			case 4:{
				
				break;
			}
				
				
			case 5:{
				
				break;
			}
				
				
			case 0: {
				
				break;
			}
				
				
			default:{
				System.out.println("Perfavore, inserire un numero, non lettere o simboli.\n\n");
				break;
			}
		} while(scelta != 0);
	}

}
