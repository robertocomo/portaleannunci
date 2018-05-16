package bean;

import controller.RegistrationController;
import exceptions.UserAlreadyRegisteredException;
import model.TipologiaUtente;

/**
 * The Class RegistrationBean.
 */
public class RegistrationBean {

	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String confermaEmail;
	private String confermaPassword;
	private String tipoUtente;
	private String codiceFiscale;

	public RegistrationBean() {

		this.nome = "";
		this.cognome = "";
		this.email = "";
		this.password = "";
		this.confermaEmail = "";
		this.confermaPassword = "";
		this.tipoUtente = "";
		this.codiceFiscale = "";
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

	public String getConfermaEmail() {
		return confermaEmail;
	}

	public void setConfermaEmail(String confermaEmail) {
		this.confermaEmail = confermaEmail;
	}

	public String getConfermaPassword() {
		return confermaPassword;
	}

	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

	public String getTipoUtente() {
		return tipoUtente;
	}

	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public boolean uguale() {

		return this.tipoUtente.equals(TipologiaUtente.CONSUMATORE.toString());
	}

	
	/**
	 * Controllo sintattico di validazione dei campi inseriti.
	 *
	 * @return true, if successful
	 */
	public boolean validate() {

		return !(this.nome.trim().isEmpty() || this.cognome.trim().isEmpty() || this.email.trim().isEmpty()
				|| this.password.trim().isEmpty())
				&& (this.tipoUtente.equals(TipologiaUtente.CONSUMATORE.toString())
						|| (this.tipoUtente.equals(TipologiaUtente.PRODUTTORE.toString())
								&& (!this.codiceFiscale.trim().isEmpty())));

	}

	public boolean checkEmail() {

		if ((this.email == null) || (this.confermaEmail == null))
			return false;

		return this.email.equals(this.confermaEmail);

	}

	public boolean checkPassword() {

		if ((this.password == null) || (this.confermaPassword == null))
			return false;

		return this.password.equals(this.confermaPassword);

	}

	public int registerUser() {

		boolean done = false;
		int result = -1;

		try {
			if (this.tipoUtente.equals(TipologiaUtente.CONSUMATORE.toString()))
				done = new RegistrationController().registraConsumatore(nome, cognome, email, password);

			if (this.tipoUtente.equals(TipologiaUtente.PRODUTTORE.toString()))
				done = new RegistrationController().registraProduttore(nome, cognome, email, password, codiceFiscale);

			if (done)
				result = 0;
		} catch (UserAlreadyRegisteredException e) {
			result = -2;
		}

		return result;

	}

}
