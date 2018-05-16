package controller;


import javax.persistence.NonUniqueResultException;
import dao.UtenteDao;
import exceptions.AuthenticationFailedException;
import model.Utente;

/**
 * The Class LoginController.
 */
public class LoginController {

    

    /**
     * Instantiates a new login controller.
     */
    public LoginController() {
    }

    

    /**
     * Effettua la procedura di Login per un Utente tramite le proprie credenziali di accesso.
     *
     * @param email email
     * @param password password
     * @return the utente
     * @throws AuthenticationFailedException authentication failed exception
     */
    public Utente login(String email, String password) throws AuthenticationFailedException {
    	
    	Utente user;
    	
    	try {
    		user = UtenteDao.findByEmailAndPassword(email, password);
    	}
    	catch(NonUniqueResultException e) {
    		throw new AuthenticationFailedException();
    	}
    	
    	if(user == null)
    		throw new AuthenticationFailedException();
        
        return user;
    }

    
    
    
    
    
}
