package dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.DatabaseManager;
import model.Utente;
import model.Utente_;

/**
 * The Class UtenteDao.
 */
public class UtenteDao {
	
	
	/**
	 * Memorizza un Utente nel sistema.
	 *
	 * @param utente the utente
	 * @return true, if successful
	 */
	public static boolean store(Utente utente) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.save(utente);
            
            tx.commit();
            done = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                done = false;
                
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return done;
	}
	
	
	/**
	 * Ritrova un Utente nel sistema mediante il proprio indirizzo email.
	 *
	 * @param email the email
	 * @return the utente
	 */
	public static Utente findByEmail(String email) {
    	
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        Utente user = null;
        
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Utente> criteria = builder.createQuery(Utente.class);
            Root<Utente> root = criteria.from(Utente.class);
                        
            criteria.select(root).where(builder.equal(root.get(Utente_.email), email));

            user = session.createQuery(criteria).getSingleResult();
            
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return user;
           
        
    }
	
	
	
	/**
	 * Ritrova un Utente nel sistema mediante il proprio indirizzo email e la propria password.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the utente
	 */
	public static Utente findByEmailAndPassword(String email, String password) {
    	
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        Utente user = null;
        
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Utente> criteria = builder.createQuery(Utente.class);
            Root<Utente> root = criteria.from(Utente.class);
                        
            criteria.select(root).where(builder.and(
            		builder.equal(root.get(Utente_.email), email),
            		builder.equal(root.get(Utente_.password), password)));

            user = session.createQuery(criteria).getSingleResult();
            
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return user;
           
        
    }
	
	
	

	/**
	 * Ritrova un Utente nel sistema mediante il proprio identificativo.
	 *
	 * @param idUtente the id utente
	 * @return the utente
	 */
	public static Utente findById(int idUtente) {
    	
		Utente utente = null;
        Session session = DatabaseManager.getSession();
        Transaction tx = null;
        
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            utente = (Utente) session.get(Utente.class, idUtente);
            
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                
                
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return utente;
           
        
    }
	
	
	
	

}
