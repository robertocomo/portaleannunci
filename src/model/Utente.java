package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class Utente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5976558597445390806L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUtente;
	
	protected String nome;
	protected String cognome;
	protected String email;
	protected String password;
	

	public Utente() {
	}

	public Utente(String nome, String cognome, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return idUtente;
	}

	public void setId(int id) {
		this.idUtente = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String firstName) {
		this.nome = firstName;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String lastName) {
		this.cognome = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
