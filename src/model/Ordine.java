package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ordine")
public class Ordine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8208648677291436192L;
	
	@Id
	@GeneratedValue
	private int idOrdine;
	
	private int idAnnuncio;
	private int idConsomatore;
	private int idProduttore;
	private int idIndirizzoSpedizione;
	private int quantità;
	private float prezzo;
	private float prezzoComplessivo;
	private String tracking;
	private String timestamp;
	private boolean isShipped;
	private String paymentTransaction;

	public Ordine(int IDAnnuncio, int iDConsomatore, int iDProduttore, int idIndirizzoSpedizione, int quantità,
			float prezzo, String paymentTransaction) {
		super();
		this.idAnnuncio = IDAnnuncio;
		this.idConsomatore = iDConsomatore;
		this.idProduttore = iDProduttore;
		this.quantità = quantità;
		this.prezzo = prezzo;
		this.prezzoComplessivo = quantità * prezzo;
		this.timestamp = DateFormat.getDateInstance().format(new Date());
		this.isShipped = false;
		this.tracking = "";
		this.idIndirizzoSpedizione = idIndirizzoSpedizione;
		this.paymentTransaction = paymentTransaction;
	}

	public Ordine() {
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getIdAnnuncio() {
		return idAnnuncio;
	}

	public void setIdAnnuncio(int idAnnuncio) {
		this.idAnnuncio = idAnnuncio;
	}

	public int getIdConsomatore() {
		return idConsomatore;
	}

	public void setIdConsomatore(int idConsomatore) {
		this.idConsomatore = idConsomatore;
	}

	public int getIdProduttore() {
		return idProduttore;
	}

	public void setIdProduttore(int idProduttore) {
		this.idProduttore = idProduttore;
	}

	public int getIdIndirizzoSpedizione() {
		return idIndirizzoSpedizione;
	}

	public void setIdIndirizzoSpedizione(int idIndirizzoSpedizione) {
		this.idIndirizzoSpedizione = idIndirizzoSpedizione;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public float getPrezzoComplessivo() {
		return prezzoComplessivo;
	}

	public void setPrezzoComplessivo(float prezzoComplessivo) {
		this.prezzoComplessivo = prezzoComplessivo;
	}

	public String getTracking() {
		return tracking;
	}

	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isShipped() {
		return isShipped;
	}

	public void setShipped(boolean isShipped) {
		this.isShipped = isShipped;
	}

	public String getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(String paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

}
