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
	private Cliente mittente;
	private Cliente destinatario;
	
	
	public Spedizione(String codice, String descrizione, LocalDate dataConsegna, Cliente m, Cliente d) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataConsegna = dataConsegna;
		this.mittente = m;
		this.destinatario = d;
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

	public LocalDate getDataOraConsegna() {
		return dataConsegna;
	}

	public void setDataOraConsegna(LocalDate dataOraConsegna) {
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



	@Override
	public String toString() {
		String toString = "";
		toString += "Spedizione: " + codice;
		toString += "\n\tDescrizione: " + descrizione;
		toString += "\n\tData e ora consegna: " + dataConsegna;
		toString += "\n\tMittente: " + mittente.getNome() + " " + mittente.getCognome();
		toString += "\n\tDestinatario: " + destinatario.getNome() + " " + destinatario.getCognome();
		
		return toString;
	}
}
