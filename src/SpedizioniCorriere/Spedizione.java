package SpedizioniCorriere;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Spedizione implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codice;
	private String descrizione;
	private LocalDateTime dataOraConsegna;
	private Cliente mittente;
	private Cliente destinatario;
	
	
	public Spedizione(String codice, String descrizione, LocalDateTime dataOraConsegna, Cliente m, Cliente d) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataOraConsegna = dataOraConsegna;
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

	public LocalDateTime getDataOraConsegna() {
		return dataOraConsegna;
	}

	public void setDataOraConsegna(LocalDateTime dataOraConsegna) {
		this.dataOraConsegna = dataOraConsegna;
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
		return "Spedizione [codice=" + codice + ", descrizione=" + descrizione + ", dataOraConsegna=" + dataOraConsegna
				+ ", mittente=" + mittente + ", destinatario=" + destinatario + "]";
	}
}
