package SpedizioniCorriere;

import java.io.Serializable;

public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String citta;
	private String telefono;
	
	public Cliente(String codiceFiscale, String nome, String cognome, String indirizzo, String citta, String telefono) {
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.telefono = telefono;
	}
	
	public Cliente(Cliente c) {
		this.codiceFiscale = c.codiceFiscale;
		this.nome = c.nome;
		this.cognome = c.cognome;
		this.indirizzo = c.indirizzo;
		this.citta = c.citta;
		this.telefono = c.telefono;
	}
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return String.format("| %-16s | %-10s %-13s | %-12s | %-25s | %-10s |", codiceFiscale, nome, cognome, citta, indirizzo, telefono);
	}
	
}
