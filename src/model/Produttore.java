package model;

import javax.persistence.Entity;

@Entity
public class Produttore extends Utente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5895902505078806902L;
	private String codiceFiscale;

	public Produttore(String nome, String cognome, String email, String password, String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.codiceFiscale = codiceFiscale;

	}

	public Produttore() {
		super();

	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

}
