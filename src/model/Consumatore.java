package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class Consumatore extends Utente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1320447122069589853L;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "idUtente")
	private List<IndirizzoSpedizione> listaIndirizziSpedizione;

	public Consumatore() {
		super();
		this.listaIndirizziSpedizione = new ArrayList<>();
		
	}

	public Consumatore(String nome, String cognome, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.listaIndirizziSpedizione = new ArrayList<>();
		
	}

	public List<IndirizzoSpedizione> getListaIndirizziSpedizione() {
		return listaIndirizziSpedizione;
	}

	public void aggiungiIndirizzo(IndirizzoSpedizione indirizzoSpedizione) {

		this.listaIndirizziSpedizione.add(indirizzoSpedizione);

	}

}
