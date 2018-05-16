package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.DatabaseManager;
import model.IndirizzoSpedizione;

/**
 * The Class IndirizzoSpedizioneDao.
 */
public class IndirizzoSpedizioneDao {
	
	
	/**
	 * Memorizza un Indirizzo di Spedizione nel sistema.
	 *
	 * @param indirizzo indirizzo
	 * @return true, if successful
	 */
	public static boolean store(IndirizzoSpedizione indirizzo) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.save(indirizzo);
            
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
	 * Ritrova un Indirizzo di Spedizione mediante il suo identificativo presente nel sistema.
	 *
	 * @param idIndirizzo the id indirizzo
	 * @return the indirizzo spedizione
	 */
	public static IndirizzoSpedizione findById(int idIndirizzo) {
    	
		IndirizzoSpedizione indirizzo = null;
        Session session = DatabaseManager.getSession();
        Transaction tx = null;
        
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            indirizzo = (IndirizzoSpedizione) session.get(IndirizzoSpedizione.class, idIndirizzo);
            
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                
                
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return indirizzo;
           
        
    }
	
	
	/**
	 * Rimuovi dal sistema un Indirizzo di Spedizione.
	 *
	 * @param indirizzo the indirizzo
	 * @return true, if successful
	 */
	public static boolean delete(IndirizzoSpedizione indirizzo) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.delete(indirizzo);
            
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
	 * Cerca tutti gli Indirizzi di Spedizione associati ad un Utente.
	 *
	 * @param idUser the id user
	 * @return the list
	 */
	public static List<IndirizzoSpedizione> cercaIndirizzi(int idUser) {
	   	
	    
    	Session session = DatabaseManager.getSession();
        Transaction tx = null;
        List<IndirizzoSpedizione> result = new ArrayList<IndirizzoSpedizione>();
        
        String stringQuery = "from " + IndirizzoSpedizione.class.getName() + " where idUtente='"+idUser+"'";
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            //	CriteriaBuilder builder = session.getCriteriaBuilder();
            //	CriteriaQuery<IndirizzoSpedizione> criteria = builder.createQuery(IndirizzoSpedizione.class);
            //	Root<IndirizzoSpedizione> root = criteria.from(IndirizzoSpedizione.class );
                        
            //	criteria.select(root).where(builder.equal(root.get(IndirizzoSpedizione_.id), idUser));

            // result = session.createQuery(criteria).getResultList();
            
            result = session.createQuery(stringQuery).getResultList();
            
                        
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                               
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return result;
    }
	
	
	
	

}
