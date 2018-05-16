package controller;

import java.util.ArrayList;
import java.util.List;
import bean.OrdineBean;
import dao.AnnuncioDao;
import dao.IndirizzoSpedizioneDao;
import dao.OrdineDao;
import exceptions.ItemNotFoundException;
import model.Annuncio;
import model.IndirizzoSpedizione;
import model.Ordine;

/**
 * The Class GestioneAcquisto.
 */
public class GestioneAcquistoController {
	

    /**
     * Instantiates a new gestione acquisto.
     */
    public GestioneAcquistoController() {
    }
	
    
   
    
    /**
     * Imposta tracking spedizione relativamente ad un Ordine.
     *
     * @param tracking the tracking
     * @param idOrdine the id ordine
     * @return true, if successful
     * @throws ItemNotFoundException the item not found exception
     */
    public boolean impostaTrackingSpedizione(String tracking, int idOrdine) throws ItemNotFoundException {
    	
    	
    	Ordine ordine = OrdineDao.findById(idOrdine);
    	
    	if(ordine == null)
    		throw new ItemNotFoundException();
    	
    	ordine.setShipped(true);
    	ordine.setTracking(tracking);
    	
    	return OrdineDao.update(ordine);
    	
    	
    }
    
    
   /**
    * Rimuovi tracking spedizione relativamente ad un Ordine.
    *
    * @param idOrdine the id ordine
    * @return true, if successful
    * @throws ItemNotFoundException the item not found exception
    */
   public boolean rimuoviTrackingSpedizione(int idOrdine) throws ItemNotFoundException {
    	
    	
    	Ordine ordine = OrdineDao.findById(idOrdine);
    	
    	if(ordine == null)
    		throw new ItemNotFoundException();
    	
    	ordine.setShipped(false);
    	ordine.setTracking("");
    	
    	return OrdineDao.update(ordine);
    	
    	
    }
    
        
    
    /**
     * Cerca tutti gli ordini associati ed relativi ad un specifico Annuncio.
     *
     * @param idAnnuncio the id annuncio
     * @return the list
     */
    public List<Ordine> cercaOrdinebyIDAnnuncio(int idAnnuncio) {
	   	
    	return OrdineDao.findByIdAnnuncio(idAnnuncio);
    }
    
    
	/**
	 * Cerca tutti gli ordini associati e relativi ad un specifico Indirizzo di Spedizione.
	 *
	 * @param idIndirizzoSpedizione the id indirizzo spedizione
	 * @return the list
	 */
	public List<Ordine> cercaOrdinebyIdIndirizzoSpedizione(int idIndirizzoSpedizione) {
		   	
	    	return OrdineDao.findByIdIndirizzoSpedizione(idIndirizzoSpedizione);
	    }
    

	
    /**
     * Cerca tutti gli Ordini effettuati da un Consumatore
     *
     * @param idConsumatore the id consumatore
     * @return the list
     * @throws ItemNotFoundException the item not found exception
     */
    public List<Ordine> ritrovaOrdiniConsumatore(int idConsumatore) throws ItemNotFoundException {
	   	
    	
    	List<Ordine> ordini = null;
        
    	ordini = OrdineDao.findByIdConsumatore(idConsumatore);
    	
    	if(ordini == null)
    		throw new ItemNotFoundException();
    	
    	if(ordini.isEmpty())
    		throw new ItemNotFoundException();
    	
    	return ordini;
    }
    
    
    /**
     * Cerca tutti gli Ordini che un Produttore ha ricevuto per acquisti di propri Annunci.
     *
     * @param idProduttore the id produttore
     * @return the list
     * @throws ItemNotFoundException the item not found exception
     */
    public List<Ordine> ritrovaOrdiniProduttore(int idProduttore) throws ItemNotFoundException {
	   	
    	
    	List<Ordine> ordini = null;
        
    	ordini = OrdineDao.findByIdProduttore(idProduttore);
    	
    	if(ordini == null)
    		throw new ItemNotFoundException();
    	
    	if(ordini.isEmpty())
    		throw new ItemNotFoundException();
    	
    	return ordini;
    }
    
    
    /**
     * Cerca ed incapsula tutti gli Ordini che un Produttore ha ricevuto per acquisti di propri Annunci.
     *
     * @param idProduttore the id produttore
     * @return the list
     * @throws ItemNotFoundException the item not found exception
     */
    public List<OrdineBean> estraiOrdiniProduttore(int idProduttore) throws ItemNotFoundException {
	   	
    	
    	List<Ordine> ordini = null;
    	List<OrdineBean> ordiniBean = new ArrayList<OrdineBean>();
        
    	ordini = OrdineDao.findByIdProduttore(idProduttore);
    	
    	if(ordini == null)
    		throw new ItemNotFoundException();
    	
    	if(ordini.isEmpty())
    		throw new ItemNotFoundException();
    	
    	for(Ordine order : ordini)
    	{
    		Annuncio annuncio = AnnuncioDao.findById(order.getIdAnnuncio());
    		IndirizzoSpedizione indirizzo = IndirizzoSpedizioneDao.findById(order.getIdIndirizzoSpedizione());
    		ordiniBean.add(new OrdineBean(order, (annuncio == null ? "Titolo annuncio non disponibile " : annuncio.getTitolo()),
    				indirizzo == null ? "Indirizzo non disponibile" : indirizzo.toString() ));
    
    	}
    
    	
    	return ordiniBean;    	
    	
    }
    
    
    
    
    
	
	

}
