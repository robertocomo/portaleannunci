package bean;

import controller.GestioneAcquistoController;
import exceptions.ItemNotFoundException;
import model.Ordine;

public class OrdineBean {

	private Ordine ordine;
	private String titoloAnnuncio;
	private String indirizzoSpedizione;
	private String nominativoConsumatore;
	private String nominativoProduttore;

	public OrdineBean(Ordine ordine, String titoloAnnuncio) {

		this.ordine = ordine;
		this.titoloAnnuncio = titoloAnnuncio;

	}

	public OrdineBean(Ordine ordine, String titoloAnnuncio, String indirizzoSpedizione) {

		this.ordine = ordine;
		this.titoloAnnuncio = titoloAnnuncio;
		this.indirizzoSpedizione = indirizzoSpedizione;

	}

	public boolean inserisciTracking(String tracking) {

		try {
			return new GestioneAcquistoController().impostaTrackingSpedizione(tracking, this.ordine.getIdOrdine());

		} catch (ItemNotFoundException e) {

			e.printStackTrace();
			return false;
		}

	}

	public boolean rimuoviTracking() {

		try {
			return new GestioneAcquistoController().rimuoviTrackingSpedizione(this.ordine.getIdOrdine());

		} catch (ItemNotFoundException e) {

			e.printStackTrace();
			return false;
		}

	}

	public String getTitoloAnnuncio() {
		return this.titoloAnnuncio;
	}

	public int getIDAnnuncio() {
		return this.ordine.getIdAnnuncio();
	}

	public int getIDOrder() {
		return this.ordine.getIdOrdine();
	}

	public int getIDConsomatore() {
		return this.ordine.getIdConsomatore();
	}

	public int getIDProduttore() {
		return this.ordine.getIdProduttore();
	}

	public int getQuantità() {
		return this.ordine.getQuantità();
	}

	public float getPrezzoComplessivo() {
		return this.ordine.getPrezzoComplessivo();
	}

	public String getTracking() {
		return this.ordine.getTracking();
	}

	public String getTimestamp() {
		return this.ordine.getTimestamp();
	}

	public boolean isShipped() {
		return this.ordine.isShipped();
	}

	public float getPrezzo() {
		return this.ordine.getPrezzo();
	}

	public String getPaymentTransaction() {
		return this.ordine.getPaymentTransaction();
	}

	public int getIdIndirizzoSpedizione() {
		return this.ordine.getIdIndirizzoSpedizione();
	}

	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

}
