package bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import controller.GestioneAcquistoController;
import controller.GestioneAnnuncioController;
import controller.GestioneRubricaConsumatoreController;
import exceptions.ItemNotFoundException;
import model.Annuncio;
import model.IndirizzoSpedizione;
import model.Ordine;

public class VisualizzaAcquistiBean {
	
	private Ordine ordine;
	private String titoloAnnuncio;
	private String fotoAnnuncio;
	private String indidizzoSpedizione;
	private List<Ordine> ordini;
	private int index;
	

	public VisualizzaAcquistiBean() {
		
	}

	public int getIdOrdine() {
		return this.ordine.getIdOrdine();
	}
	
	public int getIdAnnuncio() {
		return this.ordine.getIdAnnuncio();
	}
	
	
	public String getTitolo() {
		return this.titoloAnnuncio;
	}
	
	public int getQuantità() {
		return this.ordine.getQuantità();
	}
	
	public float getPrezzo() {
		return this.ordine.getPrezzo();
	}
	
	public float getPrezzoComplessivo() {
		return this.ordine.getPrezzoComplessivo();
	}
	
	public String getTracking() {
		return this.ordine.getTracking();
	}
	
	public String getDate() {
		return this.ordine.getTimestamp();
	}
	
	public boolean isShipped() {
		return this.ordine.isShipped();
	}
	
	
	public String getFoto() {
		return this.fotoAnnuncio;
	}
	
	
	public String getIndidizzoSpedizione() {
		return indidizzoSpedizione;
	}

	
	
	public boolean trovaAcquisti(int idConsumatore) {
		
		boolean result;
				
		try {
			this.ordini =  new GestioneAcquistoController().ritrovaOrdiniConsumatore(idConsumatore);
			result = true;
			this.index = 0;
			
		} catch (ItemNotFoundException e) {
			
			result = false;
			e.printStackTrace();
		}
		
		
		return result;
	
	}
	
	
	public boolean estraiOrdine() {
		
		if(this.index < this.ordini.size())
		{

			this.ordine = this.ordini.get(this.index);
			this.index++;

			try {
				Annuncio annuncio = new GestioneAnnuncioController().ritrovaAnnuncio(this.ordine.getIdAnnuncio());
				this.titoloAnnuncio = annuncio.getTitolo();
				this.fotoAnnuncio = annuncio.getFoto();
				
				IndirizzoSpedizione indirizzoSpedezione = new GestioneRubricaConsumatoreController().caricaIndirizzo(this.ordine.getIdIndirizzoSpedizione());
				this.indidizzoSpedizione = indirizzoSpedezione.toString();
				
			} catch (ItemNotFoundException e) {
				this.titoloAnnuncio = "Errore nel caricare le informazioni";
				this.indidizzoSpedizione = "Errore nel caricare le informazioni";
				e.printStackTrace();
			}
			
			
			return true;
		}
		
		return false;
		
	}
	
	
	public void ordinaPerUltimoOrdine() {
		
		
		 Collections.sort(this.ordini , new Comparator<Ordine>() {
		        @Override
		        public int compare(Ordine ordine1, Ordine ordine2) {

		        	Date dataOrdine1; 
					Date dataOrdine2;

		        	DateFormat format = DateFormat.getDateInstance();
		        	try {
						dataOrdine1 = format.parse(ordine1.getTimestamp());
						dataOrdine2 = format.parse(ordine2.getTimestamp());

					} catch (ParseException e) {
						return 0;
					}
		        	
		        	if(dataOrdine1.before(dataOrdine2))
						return 1;
				
		        	if(dataOrdine1.after(dataOrdine2))
						return -1;
				
		        	
		        	if(ordine1.getIdOrdine() < ordine2.getIdOrdine())
		        		return 1;
		        	
		        	if(ordine1.getIdOrdine() > ordine2.getIdOrdine())
		        		return -1;
		        	
		        	
		        	return 0;
		        	
	
		        }
		    });
		
		
	}
	

}
