package controller;

import java.util.ArrayList;
import java.util.List;

import dao.AnnuncioDaoService;
import exceptions.DaoServiceException;
import exceptions.InvalidSessionException;
import exceptions.InvalidTransactionException;
import model.Annuncio;
import model.CategoriaAnnuncio;

/**
 * The Class SearchController.
 */
public class SearchController {
	

    /**
     * Instantiates a new search controller.
     */
    public SearchController() {
    }
    
    
    /**
     * Realizza un'operazione di ricerca degli Annunci pubblici presenti nel sitema filtrando mediante un pattern di ricerca, eventualmente
     * esteso anche al contenuto della descrizione, ed una categoria di appartenenza.
     *
     * @param pattern pattern
     * @param description description
     * @param category category
     * @return the list
     */
    public List<Annuncio> search(String pattern, boolean description, String category){
    		 
    	
    	AnnuncioDaoService annuncioDaoService = new AnnuncioDaoService();
    	List<Annuncio> result = new ArrayList<Annuncio>();
    	
    	annuncioDaoService.openSession();
    	annuncioDaoService.openTransaction();
    	
    	try {
    		result = annuncioDaoService.search(pattern, description, CategoriaAnnuncio.equals(category));
    		annuncioDaoService.closeTransaction();
    		annuncioDaoService.closeSession();
	
	
		} 
    	catch (InvalidSessionException | InvalidTransactionException e) {}
    	catch (DaoServiceException e) {
    		annuncioDaoService.closeSession();
			
    		return null;
    	}
    	
    	return result;
    	
    	
    }
    
    
    
    /**
     * Realizza un'operazione di ricerca degli Annunci pubblici presenti nel sitema filtrando mediante un pattern di ricerca, eventualmente
     * esteso anche al contenuto della descrizione.
     *
     * @param pattern the pattern
     * @param description the description
     * @return the list
     */
    public List<Annuncio> search(String pattern, boolean description){

    	
    	AnnuncioDaoService annuncioDaoService = new AnnuncioDaoService();
    	List<Annuncio> result = new ArrayList<Annuncio>();
    	
    	annuncioDaoService.openSession();
    	annuncioDaoService.openTransaction();
    	
    	try {
    		result = annuncioDaoService.search(pattern, description);
    		
    		annuncioDaoService.closeTransaction();
    		annuncioDaoService.closeSession();
	
		} 
    	catch (InvalidSessionException | InvalidTransactionException e) {}
    	catch (DaoServiceException e) {
    		annuncioDaoService.closeSession();
			
    		return null;
    	}
    	
    	return result;
    	
   }
    
    
    
    
    /**
     * Realizza un'operazione di ricerca degli Annunci pubblici presenti nel sitema filtrando mediante un pattern di ricerca, eventualmente
     * esteso anche al contenuto della descrizione, una categoria di appartenenza, un prezzo di acquisto minimo ed uno massimo.
     *
     * @param pattern the pattern
     * @param description the description
     * @param category the category
     * @param minPrice the min price
     * @param maxPrice the max price
     * @return the list
     */
    public List<Annuncio> search(String pattern, boolean description, String category, float minPrice, float maxPrice){
		 
    	AnnuncioDaoService annuncioDaoService = new AnnuncioDaoService();
    	List<Annuncio> result = new ArrayList<Annuncio>();
    	
    	annuncioDaoService.openSession();
    	annuncioDaoService.openTransaction();
    	
    	try {
    		result = annuncioDaoService.search(pattern, description, 
    				category.isEmpty() ? null : CategoriaAnnuncio.equals(category), minPrice, maxPrice);
    		annuncioDaoService.closeTransaction();
    		annuncioDaoService.closeSession();
	
	
		} 
    	catch (InvalidSessionException | InvalidTransactionException e) {}
    	catch (DaoServiceException e) {
    		annuncioDaoService.closeSession();
			
    		return null;
    	}
    	
    	return result;
    	
   	
   }
    
    
    
    /**
     * Realizza un'operazione di ricerca degli Annunci pubblici presenti nel sitema filtrando mediante un pattern di ricerca, eventualmente
     * esteso anche al contenuto della descrizione, una categoria di appartenenza ed un prezzo di acquisto minimo.
     *
     * @param pattern the pattern
     * @param description the description
     * @param category the category
     * @param minPrice the min price
     * @return the list
     */
    public List<Annuncio> search(String pattern, boolean description, String category, float minPrice){
    	
    	AnnuncioDaoService annuncioDaoService = new AnnuncioDaoService();
    	List<Annuncio> result = new ArrayList<Annuncio>();
    	
    	annuncioDaoService.openSession();
    	annuncioDaoService.openTransaction();
    	
    	try {
    		result = annuncioDaoService.search(pattern, description, 
    				category.isEmpty() ? null : CategoriaAnnuncio.equals(category), minPrice);
    		annuncioDaoService.closeTransaction();
    		annuncioDaoService.closeSession();
	
	
		} 
    	catch (InvalidSessionException | InvalidTransactionException e) {}
    	catch (DaoServiceException e) {
    		annuncioDaoService.closeSession();
			
    		return null;
    	}
    	
    	return result;
    	
	
      }
   
    


}
