package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.DatabaseManager;
import model.StatoAnnuncio;
import model.Annuncio;
import model.Annuncio_;

/**
 * The Class AnnuncioDao.
 */
public class AnnuncioDao {

	/** The Constant _ERROR. */
	public static final int _ERROR = -1;
		
	/**
	 * Memorizza nel sistema un Annuncio.
	 *
	 * @param annuncio annuncio
	 * @return true, if successful
	 */
	public static boolean store(Annuncio annuncio) {
		

		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.save(annuncio);
            
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
	 * Memorizza nel sistema un Annuncio ed ottieni il suo identificativo.
	 * In caso di errore restituisce il valore AnnuncioDao._ERROR;
	 *
	 * @param annuncio annuncio
	 * @return the id
	 */
	public static int storeAndGetId(Annuncio annuncio) {
		

		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        int done = AnnuncioDao._ERROR;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.save(annuncio);
            done = annuncio.getId();
            
            tx.commit();
          
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                done = AnnuncioDao._ERROR;
                
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return done;
	
	}
	
	
	
	/**
	 * Rimuovi un Annuncio dal sistema.
	 *
	 * @param annuncio annuncio
	 * @return true, if successful
	 */
	public static boolean delete(Annuncio annuncio) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.delete(annuncio);
            
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
	 * Aggiorna un Annuncio presente nel sistema.
	 *
	 * @param annuncio annuncio
	 * @return true, if successful
	 */
	public static boolean update(Annuncio annuncio) {
		

		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.update(annuncio);
            
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
	 * Aggiorna un Annuncio presente nel sistema con una sua nuova istanza.
	 *
	 * @param vecchioAnnuncio vecchio annuncio
	 * @param nuovoAnnuncio nuovo annuncio
	 * @return true, if successful
	 */
	public static boolean update(Annuncio vecchioAnnuncio, Annuncio nuovoAnnuncio) {
		
		Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            vecchioAnnuncio = (Annuncio) session.get(Annuncio.class, vecchioAnnuncio.getId(), LockMode.PESSIMISTIC_WRITE);
            
            vecchioAnnuncio.setTitolo(nuovoAnnuncio.getTitolo());
            vecchioAnnuncio.setDescrizione(nuovoAnnuncio.getDescrizione());
            vecchioAnnuncio.setCategoria(nuovoAnnuncio.getCategoria());
            vecchioAnnuncio.setQuantità(nuovoAnnuncio.getQuantità());
            vecchioAnnuncio.setPrezzo(nuovoAnnuncio.getPrezzo());
            vecchioAnnuncio.setFoto(nuovoAnnuncio.getFoto());
            vecchioAnnuncio.setCreatoreID(nuovoAnnuncio.getCreatoreID());
            vecchioAnnuncio.setState(nuovoAnnuncio.getState());
        	
            
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
	 * Cerca un Annuncio nel sistema tramite il suo identificativo.
	 *
	 * @param idAnnuncio id annuncio
	 * @return the annuncio
	 */
	public static Annuncio findById(int idAnnuncio) {
    	
    	Annuncio annuncio = null;
        Session session = DatabaseManager.getSession();
        Transaction tx = null;
        
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            annuncio = (Annuncio) session.get(Annuncio.class, idAnnuncio);
            
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                
                
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return annuncio;
           
        
    }
	
	
	
	/**
	 * Cerca un Annuncio nel sistema tramite il suo identificativo ed il suo Stato.
	 *
	 * @param idAnnuncio the id annuncio
	 * @param stato stato
	 * @return the annuncio
	 */
	public static Annuncio findByIdAndState(int idAnnuncio, StatoAnnuncio stato) {
    	
    	Annuncio annuncio = null;
        Session session = DatabaseManager.getSession();
        Transaction tx = null;
        
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Annuncio> criteria = builder.createQuery(Annuncio.class);
            Root<Annuncio> root = criteria.from(Annuncio.class);
                        
            criteria.select(root).where(builder.and(
            		builder.equal(root.get(Annuncio_.state), stato),
            		builder.equal(root.get(Annuncio_.id), idAnnuncio)));

            annuncio = session.createQuery(criteria).getSingleResult();
            
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                
                
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return annuncio;
           
        
    }
	
	
	/**
	 * Cerca tutti gli Annunci Pubblici o Privati creati da un Utente.
	 *
	 * @param idUser the id user
	 * @return the list
	 */
	public static List<Annuncio> findByIdUser(int idUser) {
	   	
	    
    	Session session = DatabaseManager.getSession();
        Transaction tx = null;
        List<Annuncio> result = new ArrayList<Annuncio>();
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Annuncio> criteria = builder.createQuery(Annuncio.class);
            Root<Annuncio> root = criteria.from(Annuncio.class);
                        
            criteria.select(root).where(builder.and(
            		builder.or(	builder.equal(root.get(Annuncio_.state), StatoAnnuncio.PUBLIC),
            					builder.equal(root.get(Annuncio_.state), StatoAnnuncio.PRIVATE)),
            		builder.equal(root.get(Annuncio_.creatoreID), idUser)));

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
	 * Modifica lo Stato di un Annuncio memorizzato nel sistema.
	 *
	 * @param idAnnuncio the id annuncio
	 * @param stato the stato
	 * @return true, if successful
	 */
	public static boolean modificaStatoAnnuncio(int idAnnuncio, StatoAnnuncio stato) {

    	Session session = DatabaseManager.getSession();
        Transaction tx = null;
        Annuncio annuncio;
        
        boolean done = false;
    
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            annuncio = (Annuncio) session.get(Annuncio.class, idAnnuncio);
            annuncio.setState(stato);                                    
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
