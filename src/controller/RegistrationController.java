package controller;

import dao.UtenteDao;
import exceptions.UserAlreadyRegisteredException;
import model.Consumatore;
import model.Produttore;
import model.Utente;

/**
 * The Class RegistrationController.
 */
public class RegistrationController {

    /**
     * Instantiates a new registration controller.
     */
    public RegistrationController() {
    }
    
    
    private boolean isUserAlreadyRegistered(String email) {
      
    	
    	Utente user = UtenteDao.findByEmail(email);
    	
    	return (user!=null);
    	
    }
    
    
    /**
     * Effettua la registrazione nel sistema di un utente Consumatore.
     *
     * @param nome nome
     * @param cognome cognome
     * @param email email
     * @param password password
     * @return true, if successful
     * @throws UserAlreadyRegisteredException user already registered exception
     */
    public boolean registraConsumatore(String nome, String cognome, String email, String password) throws UserAlreadyRegisteredException {
    	   	
    	if(this.isUserAlreadyRegistered(email))
    		throw new UserAlreadyRegisteredException();
    	
    	Consumatore consumatore = new Consumatore(nome, cognome, email, password);
    	
    	return UtenteDao.store(consumatore);
    	
    }
    
    
    
    /**
     * Effettua la registrazione nel sistema di un utente Produttore.
     *
     * @param nome nome
     * @param cognome cognome
     * @param email email
     * @param password password
     * @param codiceFiscale codice fiscale
     * @return true, if successful
     * @throws UserAlreadyRegisteredException user already registered exception
     */
    public boolean registraProduttore(String nome, String cognome, String email, String password, String codiceFiscale) throws UserAlreadyRegisteredException {
	   	
    	
    	if(this.isUserAlreadyRegistered(email))
    		throw new UserAlreadyRegisteredException();
    	
    	Produttore produttore = new Produttore(nome, cognome, email, password, codiceFiscale);
    	
    	return UtenteDao.store(produttore);
    	
    }
    	
    	
    	
}
    
    


