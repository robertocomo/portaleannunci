package bean;
import controller.LoginController;
import exceptions.AuthenticationFailedException;
import model.Consumatore;
import model.TipologiaUtente;
import model.Utente;

/**
 * The Class LoginBean.
 */
public class LoginBean {
	
	private int id = -1;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private TipologiaUtente tipo;

	public LoginBean() {
		this.email = "";
		this.password = "";
		this.tipo = null;
	}

	
	
	public boolean userIsNotLogged() {
		
		return id == -1;
		
	}
	
	
	public boolean userIsLogged() {
		return id != -1;
		
	}
	

	public int getId() {
		return id;
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

	
	public TipologiaUtente getTipo() {
		return tipo;
	}


	/**
	 * Controllo sintattico di validazione dei campi inseriti.
	 *
	 * @return true, if successful
	 */
	public boolean validate() {
		// Controllo sintattico
		if (this.email.trim().isEmpty() || this.password.trim().isEmpty()) 
			return false;

		return true;
	}
	
	
	/**
	 * Incapsula l'operazione di Login di un Utente.
	 *
	 * @return true, if successful
	 */
	public boolean login() {
		
		LoginController controller =  new LoginController();
	
		Utente found;
		boolean done = false;
		
		try {
			found = controller.login(this.email, this.password);
			
		
			this.nome = found.getNome();
			this.cognome = found.getCognome();
			this.email = found.getEmail();
			this.id =found.getId();
			
			if(found instanceof Consumatore)
				this.tipo=TipologiaUtente.CONSUMATORE;
			else
				this.tipo=TipologiaUtente.PRODUTTORE;
			
			done = true;
		
		} catch (AuthenticationFailedException e) {
			
			done = false;
			e.printStackTrace();
		}
		
		
		return done;
}


	
	
	
	
}
