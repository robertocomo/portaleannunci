package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.hibernate.LockMode;
import dao.AnnuncioDao;
import dao.AnnuncioDaoService;
import model.StatoAnnuncio;
import model.Annuncio;
import model.CategoriaAnnuncio;
import exceptions.DaoServiceException;
import exceptions.InvalidSessionException;
import exceptions.InvalidTransactionException;
import exceptions.ItemNotFoundException;
import exceptions.NotAutorizedException;
import exceptions.TransactionException;


/**
 * The Class GestioneAnnuncioController.
 */
public class GestioneAnnuncioController {
	
	

    /**
     * Instantiates a new gestione annuncio controller.
     */
    public GestioneAnnuncioController() {
    }
	
    
    
    /**
     * Aggiungi Annuncio nel sistema.
     *
     * @param titolo titolo
     * @param descrizione descrizione
     * @param categoria categoria
     * @param quantità quantità
     * @param prezzo prezzo
     * @param foto foto
     * @param idCreatore id creatore
     * @param picture picture
     * @return true, if successful
     */
    public boolean aggiungiAnnuncio(String titolo, String descrizione, 
			String categoria, int quantità, float prezzo, String foto, int idCreatore, FileItem picture) {
    	
    	Annuncio annuncio = new Annuncio(titolo, descrizione, CategoriaAnnuncio.equals(categoria), 
    			quantità, prezzo, foto, idCreatore);

    	int idAnnuncio = AnnuncioDao.storeAndGetId(annuncio);
    	
    	if(idAnnuncio == AnnuncioDao._ERROR)
    		return false;

    	annuncio = AnnuncioDao.findById(idAnnuncio);
    	
    	if(annuncio == null)
    		return false;
    	
    	annuncio.setFoto("picture/" + idAnnuncio + ".jpeg");
        picture.setFieldName(annuncio.getFoto());
        
        AnnuncioDao.update(annuncio);
        
        return savePicture(picture);
        
       

    }
    
    
    
    /**
     * Rimuovi Annuncio dal sistema.
     *
     * @param idAnnuncio id annuncio
     * @param idUtente id utente
     * @param versioneAnnuncio versione annuncio
     * @return true, if successful
     * @throws NotAutorizedException not autorized exception
     * @throws ItemNotFoundException item not found exception
     * @throws TransactionException transaction exception
     */
    public boolean rimuoviAnnuncio(int idAnnuncio, int idUtente, int versioneAnnuncio) throws NotAutorizedException, ItemNotFoundException, TransactionException {
    	
    	 
        boolean done = false;
        
        Annuncio annuncio = AnnuncioDao.findById(idAnnuncio);
        
        if(annuncio == null)
        	throw new ItemNotFoundException();
        
        if(annuncio.getCreatoreID() != idUtente)
        	throw new NotAutorizedException();
        
        if(annuncio.getUltimaModifica() != versioneAnnuncio)
        	throw new TransactionException();
        
        if(!( ( new GestioneAcquistoController().cercaOrdinebyIDAnnuncio(idAnnuncio)).isEmpty()) )
        	done = AnnuncioDao.modificaStatoAnnuncio(idAnnuncio, StatoAnnuncio.GONE);
        
        else 
        {
        	done = AnnuncioDao.delete(annuncio);
        	removePicture(annuncio.getFoto());
        	
        }
        
        
        
        return done;
    }
    
    
 
	/**
	 * Ritrova Annuncio nel sistema tramite il suo identificativo.
	 *
	 * @param idAnnuncio id annuncio
	 * @return the annuncio
	 * @throws ItemNotFoundException the item not found exception
	 */
	public Annuncio ritrovaAnnuncio(int idAnnuncio) throws ItemNotFoundException {
    	
    	Annuncio annuncio = null;
    	
    	annuncio = AnnuncioDao.findById(idAnnuncio);
    	
    	if(annuncio == null)
    		throw new ItemNotFoundException();
        
        
        return annuncio;
           
        
    }
	
	
	
	/**
	 * Ritrova un Annuncio pubblico nel sistema.
	 *
	 * @param idAnnuncio  id annuncio
	 * @return the annuncio
	 * @throws ItemNotFoundException the item not found exception
	 */
	public Annuncio ritrovaAnnuncioPubblico(int idAnnuncio) throws ItemNotFoundException {
    	
    	Annuncio annuncio = null;
    	
    	annuncio = AnnuncioDao.findByIdAndState(idAnnuncio, StatoAnnuncio.PUBLIC);
    	
    	if(annuncio == null)
    		throw new ItemNotFoundException();
        
        
        return annuncio;
           
        
    }
	
	
	
	
	/**
	 * Carica un Annuncio per un'operazione di lettura.
	 *
	 * @param idAnnuncio id annuncio
	 * @param idUtente id utente
	 * @return the annuncio
	 * @throws NotAutorizedException not autorized exception
	 * @throws ItemNotFoundException item not found exception
	 */
	public Annuncio caricaAnnuncioPerLettura(int idAnnuncio, int idUtente) throws NotAutorizedException, ItemNotFoundException{ 
    	
    	Annuncio annuncio = null;
     
    	annuncio = AnnuncioDao.findById(idAnnuncio);
    	
    	if(annuncio == null)
    		throw new ItemNotFoundException();
    	
    	if(annuncio.getCreatoreID() != idUtente)
    		throw new NotAutorizedException();
    	
    	
    	
    	    	
    	return annuncio;
    	
    	
	}
	
	
	
	
	/**
	 * Carica un Annuncio per un'operazione di modifica.
	 *
	 * @param idAnnuncio id annuncio
	 * @param idUtente id utente
	 * @param versioneAnnuncio versione annuncio
	 * @return the annuncio
	 * @throws NotAutorizedException not autorized exception
	 * @throws ItemNotFoundException item not found exception
	 * @throws TransactionException transaction exception
	 */
	public Annuncio caricaAnnuncioPerModifica(int idAnnuncio, int idUtente, int versioneAnnuncio) throws NotAutorizedException, ItemNotFoundException, TransactionException{ 
    	
    	Annuncio annuncio = null;
     
    	annuncio = AnnuncioDao.findById(idAnnuncio);
    	
    	if(annuncio == null)
    		throw new ItemNotFoundException();
    	
    	if(annuncio.getCreatoreID() != idUtente)
    		throw new NotAutorizedException();

    	 if(annuncio.getUltimaModifica() != versioneAnnuncio)
         	throw new TransactionException();
    	
    	return annuncio;
    	
    	
	}
	
	
	/**
	 * Modifica un Annuncio presente nel sistema.
	 *
	 * @param idAnnuncio id annuncio
	 * @param idUtente id utente
	 * @param versioneAnnuncio versione annuncio
	 * @param nuovoTitolo nuovo titolo
	 * @param nuovaDescrizione nuova descrizione
	 * @param nuovaCategoria nuova categoria
	 * @param nuovaQuantità nuova quantità
	 * @param nuovoPrezzo nuovo prezzo
	 * @param nuovaFoto nuova foto
	 * @param nuovoStato nuovo stato
	 * @param picture picture
	 * @return true, if successful
	 * @throws NotAutorizedException not autorized exception
	 * @throws ItemNotFoundException item not found exception
	 * @throws TransactionException transaction exception
	 */
	public boolean modificaAnnuncio(int idAnnuncio, int idUtente, int versioneAnnuncio, String nuovoTitolo, String nuovaDescrizione,
			String nuovaCategoria, int nuovaQuantità, float nuovoPrezzo, String nuovaFoto, String nuovoStato, FileItem picture ) throws NotAutorizedException, ItemNotFoundException, TransactionException{


		Annuncio vecchioAnnuncio = null;
		
		vecchioAnnuncio = AnnuncioDao.findById(idAnnuncio);
		
		if(vecchioAnnuncio == null)
        	throw new ItemNotFoundException();
        
        if(vecchioAnnuncio.getCreatoreID() != idUtente)
        	throw new NotAutorizedException();
        
        if(vecchioAnnuncio.getUltimaModifica() != versioneAnnuncio)
        	throw new TransactionException();
		

        
        Annuncio nuovoAnnuncio = new Annuncio(nuovoTitolo, nuovaDescrizione, CategoriaAnnuncio.equals(nuovaCategoria), 
    			nuovaQuantità, nuovoPrezzo, nuovaFoto, idUtente, StatoAnnuncio.equals(nuovoStato) );
		 
        nuovoAnnuncio.setFoto("picture/" + idAnnuncio + ".jpeg");
        picture.setFieldName(nuovoAnnuncio.getFoto());
        
        //
        if(!( (new GestioneAcquistoController().cercaOrdinebyIDAnnuncio(idAnnuncio)).isEmpty()) )
            return this.modificaCreaNuovoAnnuncio(vecchioAnnuncio.getId(), nuovoAnnuncio, picture);
        

        
        else
        
        	if(AnnuncioDao.update(vecchioAnnuncio, nuovoAnnuncio))
        		return savePicture(picture);
        	
        
        return false;
        	

    }
		
	
	
	
	private boolean modificaCreaNuovoAnnuncio(int idVecchioAnnuncio,Annuncio nuovoAnnuncio, FileItem picture) {
		
		AnnuncioDaoService annuncioDaoService = new AnnuncioDaoService();
		
		annuncioDaoService.openSession();
		annuncioDaoService.openTransaction();
		int idNuovoAnnuncio = -1;
		
		try {
			Annuncio vecchioAnnuncio = annuncioDaoService.lockAndGetByID(idVecchioAnnuncio, LockMode.PESSIMISTIC_WRITE);
			vecchioAnnuncio.setState(StatoAnnuncio.GONE);
		
			annuncioDaoService.store(nuovoAnnuncio);
			idNuovoAnnuncio = nuovoAnnuncio.getId();
			nuovoAnnuncio.setFoto("picture/" + idNuovoAnnuncio + ".jpeg");
			
			annuncioDaoService.closeTransaction();
			annuncioDaoService.closeSession();
			
			
		} 	
			catch (InvalidSessionException | InvalidTransactionException e) {}
			catch ( DaoServiceException e) {
				annuncioDaoService.closeSession();
				return false;
			}
		
		if(!picture.getName().isEmpty())
		{
			picture.setFieldName(nuovoAnnuncio.getFoto());
			this.savePicture(picture);
		}
		else
			this.copyPicture("picture/" + idVecchioAnnuncio + ".jpeg", "picture/" + idNuovoAnnuncio + ".jpeg");
		
		
		return true;
		}
	
	
	
	

    
    /**
     * Ritrova tutti gli Annunci associati ad un Utente.
     *
     * @param idUser id user
     * @return the list
     * @throws ItemNotFoundException the item not found exception
     */
    public List<Annuncio> ritrovaAnnunciUtente(int idUser) throws ItemNotFoundException {
    	   	
    
    	List<Annuncio> result = AnnuncioDao.findByIdUser(idUser);
    	
    	if(result == null)
    		throw new ItemNotFoundException();
        
        return result;
    }
    
    
    /**
     * Effettua il salvataggio in locale della foto associata ad un Annuncio
     *
     * @param picture picture
     * @return true, if successful of if picture is null
     */
    private boolean savePicture(FileItem picture) {
    	
    	if(picture.getName().isEmpty())
    		return true;
    	
    	try {
			picture.write(new File(System.getProperty("user.dir") + "/WebContent/" + picture.getFieldName() ));
			return true;
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
    	
    	return false;
    }
    
    
    /**
     * Rimuove dallo storage locale una foto precedentemente salvata nel sistema.
     *
     * @param path the path
     * @return true, if successful
     */
    private boolean removePicture(String path) {
    	
    	try {
			Files.delete(Paths.get(System.getProperty("user.dir") + "/WebContent/" + path));
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
    	
    }
    
    
    /**
     * Effettua una copia di una foto precedentemente salvata nello storage.
     *
     * @param pathSource path sorgente
     * @param pathDestination path destinazione
     * @return true, if successful
     */
    private boolean copyPicture(String pathSource, String pathDestination)
    {
    	boolean result = false;
  
    	 Path source = Paths.get(System.getProperty("user.dir") + "/WebContent/" + pathSource);
         Path dest = Paths.get(System.getProperty("user.dir") + "/WebContent/" + pathDestination);
         
         try {
			Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
    	
    	return result;
    }
    
    

	

}
