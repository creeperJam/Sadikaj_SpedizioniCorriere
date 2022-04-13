package SpedizioniCorriere;

import java.time.LocalDateTime;

public class Spedizione {
	
	private String codice;
	private String descrizione;
	private LocalDateTime dataOraConsegna;
	
	
	
	public Spedizione(String codice, String descrizione, LocalDateTime dataOraConsegna) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataOraConsegna = dataOraConsegna;
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



	@Override
	public String toString() {
		return "Spedizione [codice=" + codice + ", descrizione=" + descrizione + ", dataOraConsegna=" + dataOraConsegna
				+ "]";
	}
}
