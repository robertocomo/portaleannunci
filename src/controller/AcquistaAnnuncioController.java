package controller;

import java.util.UUID;

import org.hibernate.LockMode;
import dao.AnnuncioDaoService;
import dao.IndirizzoSpedizioneDao;
import dao.OrdineDaoService;
import exceptions.DaoServiceException;
import exceptions.InvalidRequestException;
import exceptions.InvalidSessionException;
import exceptions.InvalidTransactionException;
import exceptions.ItemNotFoundException;
import exceptions.PaymentDeclinedException;
import exceptions.TransactionException;
import model.Annuncio;
import model.IndirizzoSpedizione;
import model.Ordine;
import model.StatoAnnuncio;

/**
 * The Class AcquistaAnnuncioController.
 */
public class AcquistaAnnuncioController {
	
	
		/**
		 * Instantiates a new controller that manages and performers the buy procedure
		 */
		public AcquistaAnnuncioController() {
		}

		
		
		private String paga(String nominativo, String numeroCarta, String scadenza, String cvv, float importo) throws PaymentDeclinedException {
			
			if(false)
				throw new PaymentDeclinedException();
			
			//return new BigInteger(130, new SecureRandom()).toString(32);
			return UUID.randomUUID().toString();
		}
		


		
		/**
		 * Effettua acquisto.
		 *
		 * @param idUtente id Utente che procede all'acquisto
		 * @param idAnnuncio id Annuncio da acquistare
		 * @param versioneAnnuncio versione dell'Annuncio da acuistare
		 * @param idIndirizzoSpedizione  id dell'Indirizzo Spedizione associato all'acquisto
		 * @param quantit‡DaAcquistare quantit‡ da acquistare
		 * @param nominativo nominativo associato al metodo di pagamento
		 * @param numeroCarta numero carta associata al pagamento
		 * @param scadenza scadenza della carta associata al pagamento
		 * @param cvv cvv della carta associata al pagamento
		 * @return true, if successful
		 * @throws ItemNotFoundException item not found exception
		 * @throws InvalidRequestException invalid request exception
		 * @throws PaymentDeclinedException payment declined exception
		 * @throws TransactionException transaction exception
		 */
		public boolean effettuaAcquisto(int idUtente, int idAnnuncio, int versioneAnnuncio, int idIndirizzoSpedizione, int quantit‡DaAcquistare,
						String nominativo, String numeroCarta, String scadenza,	String cvv ) throws  ItemNotFoundException, InvalidRequestException, PaymentDeclinedException, TransactionException {
			

				Annuncio annuncio;
				try {
					annuncio = new GestioneAnnuncioController().ritrovaAnnuncioPubblico(idAnnuncio);
					
				} catch (ItemNotFoundException e1) {
					e1.printStackTrace();
					throw new ItemNotFoundException();
				}
				
				IndirizzoSpedizione indirizzo = IndirizzoSpedizioneDao.findById(idIndirizzoSpedizione);
				
				if(indirizzo == null)
					throw new ItemNotFoundException();

				if(annuncio.getQuantit‡() < quantit‡DaAcquistare)
					throw new InvalidRequestException();
				
				if(annuncio.getState() == StatoAnnuncio.GONE)
					throw new InvalidRequestException();
			
			

		        AnnuncioDaoService annuncioDaoService = new AnnuncioDaoService();
		        
				
				annuncioDaoService.openSession();
				annuncioDaoService.openTransaction();
				
				try {
					annuncio = annuncioDaoService.lockAndGetByID(idAnnuncio, LockMode.PESSIMISTIC_WRITE);
			        
					
					if(annuncio.getUltimaModifica() != versioneAnnuncio)
		            	throw new TransactionException();
		            
					
					String uniqueTransactionPayment = this.paga(nominativo, numeroCarta, scadenza, cvv, annuncio.getPrezzo()*quantit‡DaAcquistare);
					
		            if(annuncio.getQuantit‡() == quantit‡DaAcquistare)
		            	annuncio.setState(StatoAnnuncio.GONE);
					
		            annuncio.setQuantit‡(annuncio.getQuantit‡() - quantit‡DaAcquistare);
		            
		            
		            OrdineDaoService ordineDaoService = new OrdineDaoService(annuncioDaoService.getSession(), annuncioDaoService.getTransaction());
	
		            //FIX:	(...indirizzoSpedizione, uniqueTransactionPayment)
		            Ordine ordine = new Ordine(idAnnuncio, idUtente, annuncio.getCreatoreID(), idIndirizzoSpedizione, 
		            		quantit‡DaAcquistare, annuncio.getPrezzo(), uniqueTransactionPayment);
	
		            
		            ordineDaoService.store(ordine);
		            
		            
		            ordineDaoService.closeTransaction();
		            ordineDaoService.closeSession();
		
				} 	
				catch (InvalidSessionException | InvalidTransactionException e) {}
				catch ( DaoServiceException e) {
					
					annuncioDaoService.closeSession();
					return false;
				}
				
				catch (TransactionException e) {
					annuncioDaoService.transactionRoolback();
					annuncioDaoService.closeSession();
					throw new TransactionException();
					
				}
				
				catch (PaymentDeclinedException e) {
					annuncioDaoService.transactionRoolback();
					annuncioDaoService.closeSession();
					throw new PaymentDeclinedException();
				}
				
				
				return true;
				
			

		}


}