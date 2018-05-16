package bean;


import controller.AcquistaAnnuncioController;
import exceptions.InvalidRequestException;
import exceptions.ItemNotFoundException;
import exceptions.PaymentDeclinedException;
import exceptions.TransactionException;

/**
 * The Class CompraBean.
 */
public class CompraBean {


	private int idAnnuncio;
	private int versioneAnnuncio;
	private int idIndirizzoSpedizione;
	private int quantit‡DaAcquistare;
	
	private String nominativo;
	private String numeroCarta;
	private String scadenza;
	private String cvv;
	

	public int getIdAnnuncio() {
		return idAnnuncio;
	}


	public void setIdAnnuncio(int idAnnuncio) {
		this.idAnnuncio = idAnnuncio;
	}

	public int getIdIndirizzoSpedizione() {
		return idIndirizzoSpedizione;
	}


	public void setIdIndirizzoSpedizione(int idIndirizzoSpedizione) {
		this.idIndirizzoSpedizione = idIndirizzoSpedizione;
	}

	public int getQuantit‡DaAcquistare() {
		return quantit‡DaAcquistare;
	}

	public void setQuantit‡DaAcquistare(int quantit‡DaAcquistare) {
		this.quantit‡DaAcquistare = quantit‡DaAcquistare;
	}


	public String getNominativo() {
		return nominativo;
	}


	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}


	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	
	public String getScadenza() {
		return scadenza;
	}


	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}


	public String getCvv() {
		return cvv;
	}


	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	

	public int getVersioneAnnuncio() {
		return versioneAnnuncio;
	}


	public void setVersioneAnnuncio(int versioneAnnuncio) {
		this.versioneAnnuncio = versioneAnnuncio;
	}


	/**
	 * Controllo sintattico di validazione dei campi inseriti.
	 *
	 * @return true, if successful
	 */
	public boolean validate() {

		return !( this.nominativo.trim().isEmpty() || this.numeroCarta.isEmpty() || this.scadenza.trim().isEmpty() || this.cvv.isEmpty() ||
				this.quantit‡DaAcquistare < 1) && (this.numeroCarta.matches("^[0-9]*$")) && (this.cvv.matches("^[0-9]*$"));

		//return false;
	}
	


	/**
	 * Incapsula l'operazione di acquisto di un Annuncio.
	 * @param idUtente id Utente
	 * @return true, if successful
	 */
	public int compra(int idUtente){
				
		int result = 0;
		boolean done;
		
		AcquistaAnnuncioController controller = new AcquistaAnnuncioController();
		
		try {
			done = controller.effettuaAcquisto(idUtente, idAnnuncio, versioneAnnuncio,
					idIndirizzoSpedizione, quantit‡DaAcquistare, nominativo, numeroCarta, scadenza, cvv);
			if(done)
				result = 1;
			
			this.invalidate();
			
		} catch (ItemNotFoundException e) {
			result = -1;
			e.printStackTrace();
			
		} catch (InvalidRequestException e) {
			result = -2;
			e.printStackTrace();
			
		} catch (PaymentDeclinedException e) {
			result = -3;
			e.printStackTrace();
			
		} catch (TransactionException e) {
			result = -4;
			e.printStackTrace();
		}
		
		
		return result;
		
	}
	
	
	private void invalidate() {
		
		this.idAnnuncio = -1;
		this.idIndirizzoSpedizione = -1;
		this.quantit‡DaAcquistare = -1;
		
		this.nominativo = null;
		this.numeroCarta = null;
		this.scadenza = null;
		this.cvv = null;
		
		
	}
	
	
	
}
