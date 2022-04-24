package SpedizioniCorriere;

import java.io.Serializable;
import java.time.LocalDate;

public class Spedizione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codice;
	private String descrizione;
	private LocalDate dataConsegna;
	protected Cliente mittente;
	private Cliente destinatario;

	public Spedizione(String codice, String descrizione, LocalDate dataConsegna, Cliente m, Cliente d) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataConsegna = dataConsegna;
		this.mittente = m;
		this.destinatario = d;
	}

	public Spedizione(Spedizione s) {
		this.codice = s.codice;
		this.descrizione = s.descrizione;
		this.dataConsegna = s.dataConsegna;
		this.mittente = s.mittente;
		this.destinatario = s.destinatario;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(LocalDate dataOraConsegna) {
		this.dataConsegna = dataOraConsegna;
	}

	public Cliente getMittente() {
		return mittente;
	}

	public void setMittente(Cliente mittente) {
		this.mittente = mittente;
	}

	public Cliente getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Cliente destinatario) {
		this.destinatario = destinatario;
	}

	public String toStringFormattato() {
		String descrizione;
		if (this.descrizione.length() > 15) {
			descrizione = this.descrizione.substring(0, 15) + "...";
		} else {
			descrizione = this.descrizione;
		}
		
		return String.format("| %-11s | %-18s | %-15s | %-20s -> %-20s |", codice, descrizione, dataConsegna,
				mittente.getNome() + " " + mittente.getCognome(), destinatario.getNome() + " " + destinatario.getCognome());
	}
}
