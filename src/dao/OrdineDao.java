package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.DatabaseManager;
import model.Ordine;
import model.Ordine_;

/**
 * The Class OrdineDao.
 */
public class OrdineDao {
	
	
	/**
	 * Memorizza un Ordine nel sistema.
	 *
	 * @param ordine the ordine
	 * @return true, if successful
	 */
	public static boolean store(Ordine ordine) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.save(ordine);
            
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
	 * Aggiorna un Ordine presente nel sistema.
	 *
	 * @param ordine the ordine
	 * @return true, if successful
	 */
	public static boolean update(Ordine ordine) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.update(ordine);
            
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
	 * Cerca un Ordine nel sistema mediante il proprio identificativo.
	 *
	 * @param idOrdine the id ordine
	 * @return the ordine
	 */
	public static Ordine findById(int idOrdine) {
    	
		Ordine ordine = null;
        Session session = DatabaseManager.getSession();
        Transaction tx = null;
        
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            ordine = (Ordine) session.get(Ordine.class, idOrdine);
            
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                
                
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return ordine;
           
        
    }
	
	
	 /**
 	 * Ritrova tutti gli Ordini effettuati da un utente Consumatore.
 	 *
 	 * @param idConsumatore the id consumatore
 	 * @return the list
 	 */
 	public static List<Ordine> findByIdConsumatore(int idConsumatore) {
		   	
	        
	    	Session session = DatabaseManager.getSession();
	        Transaction tx = null;
	        
	        List<Ordine> result = null;
	        
	        try {
	            tx = session.getTransaction();
	            tx.begin();
	            
	           
	            
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<Ordine> criteria = builder.createQuery(Ordine.class);
	            Root<Ordine> root = criteria.from(Ordine.class);
	                        
	            criteria.select(root).where(builder.equal(root.get(Ordine_.idConsomatore), idConsumatore));

	            result = session.createQuery(criteria).getResultList();
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
	 
	 
	 
	 /**
 	 * Cerca tutti gli Ordini nel sistema associati ad un Annuncio memorizzato.
 	 *
 	 * @param idAnnuncio the id annuncio
 	 * @return the list
 	 */
 	public static List<Ordine> findByIdAnnuncio(int idAnnuncio) {
		   	
	        
	    	Session session = DatabaseManager.getSession();
	        Transaction tx = null;
	        
	        List<Ordine> result = new ArrayList<Ordine>();
	        
	        try {
	            tx = session.getTransaction();
	            tx.begin();
	            
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<Ordine> criteria = builder.createQuery(Ordine.class);
	            Root<Ordine> root = criteria.from(Ordine.class);
	                        
	            criteria.select(root).where(builder.equal(root.get(Ordine_.idAnnuncio), idAnnuncio));

	            result = session.createQuery(criteria).getResultList();
	                        
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
	 
	 

	 /**
 	 * Cerca tutti gli Ordini nel sistema associati ad un Indirizzo di Spedizione memorizzato.
 	 *
 	 * @param idIndirizzoSpedizione the id indirizzo spedizione
 	 * @return the list
 	 */
 	public static List<Ordine> findByIdIndirizzoSpedizione(int idIndirizzoSpedizione) {
		   	
	        
	    	Session session = DatabaseManager.getSession();
	        Transaction tx = null;
 	        List<Ordine> result = new ArrayList<Ordine>();
	        
	        try {
	            tx = session.getTransaction();
	            tx.begin();
	            
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<Ordine> criteria = builder.createQuery(Ordine.class);
	            Root<Ordine> root = criteria.from(Ordine.class);
	               

	            criteria.select(root).where(builder.equal(root.get(Ordine_.idIndirizzoSpedizione), idIndirizzoSpedizione));

	            result = session.createQuery(criteria).getResultList();
	                        
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




	/**
	 * Cerca tutti gli Ordini presenti nel sistema associati ad un utente Produttore relativamente ai suoi Annunci.
	 *
	 * @param idProduttore the id produttore
	 * @return the list
	 */
	public static List<Ordine> findByIdProduttore(int idProduttore) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        
        List<Ordine> result = new ArrayList<Ordine>();
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
           
            
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Ordine> criteria = builder.createQuery(Ordine.class);
            Root<Ordine> root = criteria.from(Ordine.class);
                        
            criteria.select(root).where(builder.equal(root.get(Ordine_.idProduttore), idProduttore));

            result = session.createQuery(criteria).getResultList();
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
