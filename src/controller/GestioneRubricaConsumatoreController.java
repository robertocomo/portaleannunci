package controller;

import java.util.List;

import dao.ConsumatoreDao;
import dao.IndirizzoSpedizioneDao;
import exceptions.ItemNotFoundException;
import model.IndirizzoSpedizione;

/**
 * The Class GestioneRubricaConsumatoreController.
 */
public class GestioneRubricaConsumatoreController {

			
	

    /**
     * Instantiates a new gestione rubrica consumatore controller.
     */
    public GestioneRubricaConsumatoreController() {
    }
	
    
        
    /**
     * Ritrova tutti gli Indirizzi di Spedizione associati ad un Utente.
     *
     * @param idUser id user
     * @return the list
     * @throws ItemNotFoundException item not found exception
     */
    public List<IndirizzoSpedizione> cercaIndirizzi(int idUser) throws ItemNotFoundException{
    	
    	
    	List<IndirizzoSpedizione> risultati = IndirizzoSpedizioneDao.cercaIndirizzi(idUser);
    	
    	if(risultati == null)
    		throw new ItemNotFoundException();
    	
    	return risultati;
    	
    	
    }
    
    
  /**
   * Carica un specifico Indirizzo di Spedizione.
   *
   * @param idIndirizzoSpedizione id indirizzo spedizione
   * @return the indirizzo spedizione
   * @throws ItemNotFoundException item not found exception
   */
  public IndirizzoSpedizione caricaIndirizzo(int idIndirizzoSpedizione) throws ItemNotFoundException{
    	
    	
    	IndirizzoSpedizione indirizzo = IndirizzoSpedizioneDao.findById(idIndirizzoSpedizione);
    	
    	if(indirizzo == null)
    		throw new ItemNotFoundException();
    	
    	return indirizzo;
    	
    	
    }
    
    
    
 
    /**
     * Aggiungi un Indirizzo di Spedizione associato ad un Consumatore nel sistema.
     *
     * @param idUtente id utente
     * @param nominativo nominativo
     * @param presso presso
     * @param via via
     * @param città città
     * @param cap cap
     * @param provincia t provincia
     * @param recapitoTelefonico recapito telefonico
     * @return true, if successful
     */
    public boolean aggiungiIndirizzoSpedizione(int idUtente, 
			String nominativo, String presso, String via, String città, String cap, String provincia, String recapitoTelefonico) {
    	
    	
    	IndirizzoSpedizione indirizzo = new IndirizzoSpedizione(nominativo, presso, via, città, cap, provincia, recapitoTelefonico);
    	
    	return ConsumatoreDao.aggiungiIndirizzoSpedizione(idUtente, indirizzo);
    	
    	
    }
    
    
    /*
    public synchronized Consumatore cercaConsumatorebyId(int idConsumatore) {
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        Consumatore utente = null;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            utente = (Consumatore) session.get(Consumatore.class, idConsumatore);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
           // if(!(e instanceof javax.persistence.NoResultException))
            	e.printStackTrace();
                                   
            
        } finally {
            session.close();
        }
        return utente;
    }
    
    
    
    
	public boolean modificaIndirizzoSpedizione(int idConsumatore, int idIndirizzoDaModificare, IndirizzoSpedizione indirizzoModificato) {
	    	
		Session session = HibernateUtil.openSession();
	    Transaction tx = null;
	    boolean done = false;
	    
	    Consumatore utente = this.cercaConsumatorebyId(idConsumatore);
	    
	    if(annuncio.getCreatoreID() != idUtente)
	    	throw new NotAutorizedException();
	    
	    if(!( (GestioneAcquisto.getInstance().cercaOrdinebyIDAnnuncio(idAnnuncio)).isEmpty()) )
	    	done = this.setAnnuncioAsGone(idAnnuncio);
	    
	    else {
	    
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
		    }
		    
		    return done;
		}

*/
    
}