package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.DatabaseManager;
import model.Consumatore;
import model.IndirizzoSpedizione;


/**
 * The Class ConsumatoreDao.
 */
public class ConsumatoreDao {
	
	
	/**
	 * Memorizza nel sistema un utente Consumatore.
	 *
	 * @param consumatore consumatore
	 * @return true, if successful
	 */
	public static boolean store(Consumatore consumatore) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.save(consumatore);
            
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
	 * Aggiungi un Indirizzo di Spedizione ad un utente Consumatore presente nel sistema.
	 *
	 * @param idUtente the id utente
	 * @param indirizzo the indirizzo
	 * @return true, if successful
	 */
	public static boolean aggiungiIndirizzoSpedizione(int idUtente, IndirizzoSpedizione indirizzo) {
		
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        Consumatore utente;
        
        boolean done = false;

        try {
            tx = session.getTransaction();
            tx.begin();
            
            utente = (Consumatore) session.get(Consumatore.class, idUtente);
            utente.aggiungiIndirizzo(indirizzo);                                    
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
	 * Rimuovi dal sistema un utente Consumatore.
	 *
	 * @param consumatore the consumatore
	 * @return true, if successful
	 */
	public static boolean delete(Consumatore consumatore) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.delete(consumatore);
            
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
	

}
