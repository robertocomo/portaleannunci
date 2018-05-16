package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.AcquistaAnnuncioController;
import dao.AnnuncioDao;
import dao.ConsumatoreDao;
import dao.IndirizzoSpedizioneDao;
import dao.UtenteDao;
import exceptions.InvalidRequestException;
import exceptions.ItemNotFoundException;
import exceptions.PaymentDeclinedException;
import exceptions.TransactionException;
import model.Annuncio;
import model.CategoriaAnnuncio;
import model.Consumatore;
import model.IndirizzoSpedizione;

public class AcquistaAnnuncioControllerTest {

	boolean unitTestResult = false;
	
	@Test
	public void testEffettuaAcquisto() throws InterruptedException {

		boolean partialResult;
		
		final String email = "test@test";
		final String password = "password";
		
		
		//CREO E SALVO CONSUMATORE
		Consumatore consumatore = new Consumatore("nome","cognome",email,password);
		partialResult = UtenteDao.store(consumatore);
		assertTrue("Registrazione Consumatore", partialResult);
		
		int idConsumatore = UtenteDao.findByEmailAndPassword(email, password).getId();
		
		
		
		//CREO E SALVO ANNUNCIO
		Annuncio annuncio = new Annuncio("String titolo", "String descrizione", CategoriaAnnuncio.CREATIVITÀ,
				100, 1, "String foto", 0);
		int idAnnuncio = AnnuncioDao.storeAndGetId(annuncio);
		assertTrue("Creazione Annuncio", idAnnuncio != AnnuncioDao._ERROR);
		
		//CREO E SALVO INDIRIZZO SPEDIZIONE
		IndirizzoSpedizione indirizzo = new IndirizzoSpedizione("nominativo", "presso", "via", "città", "cap", "provincia", "recapitoTelefonico");
    	partialResult = ConsumatoreDao.aggiungiIndirizzoSpedizione(idConsumatore, indirizzo);
    	assertTrue("Creazione Indirizzo Spedizione", partialResult);
		
    	int idIndirizzoSpedizione = IndirizzoSpedizioneDao.cercaIndirizzi(idConsumatore).get(0).getId();
    	
    	
    	//CONTROLLER E MUTEX
    	AcquistaAnnuncioController controller = new AcquistaAnnuncioController();
    	Object MUTEX = new Object();
    	
    	
    	Thread threadConsumatore = new Thread(new Runnable() {

			@Override
			public void run() {
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					unitTestResult = false;
					e.printStackTrace();
				}
				
				
				synchronized(MUTEX) {
					Annuncio annuncio = AnnuncioDao.findById(idAnnuncio);
					annuncio.setPrezzo(500);
					boolean consumatoreResult = AnnuncioDao.update(annuncio);
					
					assertTrue("Update Annuncio", consumatoreResult);
					MUTEX.notify();
				
				}
	
			}});
    	
    	
    	
    	Thread threadProduttore = new Thread(new Runnable() {

			@Override
			public void run() {
				
				synchronized(MUTEX) {
					try {
						int versioneAnnuncio = AnnuncioDao.findById(idAnnuncio).getUltimaModifica();
						MUTEX.wait();
						
						
						controller.effettuaAcquisto(idConsumatore, idAnnuncio, versioneAnnuncio, idIndirizzoSpedizione, 1, 
								"nominativo", "1", "scadenza", "1");
						
						
						
					} catch (InterruptedException e) {
						e.printStackTrace();
						unitTestResult = false;
					} 
					catch (ItemNotFoundException e) {

						e.printStackTrace();
						unitTestResult = false;
					} 
					catch (InvalidRequestException e) {

						e.printStackTrace();
						unitTestResult = false;
					} 
					catch (PaymentDeclinedException e) {
						e.printStackTrace();
						unitTestResult = false;
						
						
					} 
					catch (TransactionException e) {
						unitTestResult = true;
						
					}
					
				
				}
				
				
			}});
    	
    	
    	
    	
    	threadConsumatore.start();
    	
    	threadProduttore.start();
    	
    	
    	threadConsumatore.join();
    	threadProduttore.join();
    	
    	IndirizzoSpedizioneDao.delete(indirizzo);
    	ConsumatoreDao.delete(consumatore);
    	
    	AnnuncioDao.delete(annuncio);
    	
    	
    	System.out.println("Transazione rifiutata:\t " + unitTestResult);
    	
    	assertTrue("Acquisto Annuncio", unitTestResult);
    	
    	
    	
		

	}
	
	
	

}
