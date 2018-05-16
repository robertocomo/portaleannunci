package bean;

import org.apache.commons.fileupload.FileItem;

import controller.GestioneAnnuncioController;
import exceptions.ItemNotFoundException;
import model.Annuncio;
import model.CategoriaAnnuncio;
import model.StatoAnnuncio;

/**
 * The Class AnnuncioBean.
 */
public class AnnuncioBean {

	private int id;
	private int ultimaModifica;
	private String titolo;
	private String categoria;
	private String foto;
	private int quantit�;
	private String descrizione;
	private float prezzo;
	private int creatoreID;
	private String statoAnnuncio;

	
	public AnnuncioBean(String titolo, String descrizione, String categoria, int quantit�, float prezzo, String foto,
			int creatoreID) {
		super();
		this.titolo = titolo;
		this.categoria = categoria;
		this.foto = foto;
		this.quantit� = quantit�;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.creatoreID = creatoreID;
	}

	
	public AnnuncioBean(int ID, String titolo, String descrizione, String categoria, int quantit�, float prezzo,
			String foto, int creatoreID, String statoAnnuncio) {
		super();
		this.id = ID;
		this.titolo = titolo;
		this.categoria = categoria;
		this.foto = foto;
		this.quantit� = quantit�;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.creatoreID = creatoreID;
		this.statoAnnuncio = statoAnnuncio;
	}

	
	public AnnuncioBean(int ID, String titolo, String descrizione, String categoria, int quantit�, float prezzo,
			String foto, int creatoreID) {
		super();
		this.id = ID;
		this.titolo = titolo;
		this.categoria = categoria;
		this.foto = foto;
		this.quantit� = quantit�;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.creatoreID = creatoreID;

	}

	
	public AnnuncioBean(int ID, int ultimaModifica, String titolo, String descrizione, String categoria, int quantit�,
			float prezzo, String foto, int creatoreID, String statoAnnuncio) {
		super();

		this.ultimaModifica = ultimaModifica;
		this.id = ID;
		this.titolo = titolo;
		this.categoria = categoria;
		this.foto = foto;
		this.quantit� = quantit�;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.creatoreID = creatoreID;
		this.statoAnnuncio = statoAnnuncio;
	}

	
	public AnnuncioBean() {
		super();

	}

	
	public int getId() {
		return id;
	}

	
	public String getTitolo() {
		return titolo;
	}

	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	
	public String getCategoria() {
		return categoria;
	}

	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	public String getFoto() {
		return foto;
	}

	
	public void setFoto(String foto) {
		this.foto = foto;
	}

	
	public int getQuantit�() {
		return quantit�;
	}

	
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}

	
	public String getDescrizione() {
		return descrizione;
	}

	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
	public float getPrezzo() {
		return prezzo;
	}

	
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	
	public int getCreatoreID() {
		return creatoreID;
	}

	
	public void setCreatoreID(int creatoreID) {
		this.creatoreID = creatoreID;
	}

	
	public int getUltimaModifica() {
		return ultimaModifica;
	}

	
	public String getStatoAnnuncio() {
		return statoAnnuncio;
	}

	
	public void setQuantit�(String quantit�) {

		try {
			this.quantit� = Integer.parseInt(quantit�);

		} catch (Exception e) {
			this.quantit� = -1;
		}

	}

	
	public void setPrezzo(String prezzo) {

		try {
			this.prezzo = Float.parseFloat(prezzo);

		} catch (Exception e) {
			this.prezzo = -1;
		}
	}

	/**
	 * Controlla se l'Annuncio caricato � nello stato Privato.
	 *
	 * @return true, if is annuncio privato
	 */
	public boolean isAnnuncioPrivato() {

		if (this.statoAnnuncio != null)
			if (this.statoAnnuncio.equals(StatoAnnuncio.PRIVATE.toString()))
				return true;

		return false;

	}

	
	public void setStatoAnnuncio(String statoAnnuncio) {
		this.statoAnnuncio = statoAnnuncio;
	}

	
	/**
	 * Controllo sintattico di validazione dei campi inseriti.
	 *
	 * @return true, if successful
	 */
	public boolean validate() {
		// Controllo sintattico
		
		if (this.categoria.trim().equals("") || this.foto.trim().equals("") || this.quantit� <= 0 || this.prezzo < 0
				|| this.descrizione.trim().equals("") || !CategoriaAnnuncio.contains(this.categoria))
			return false;

		if (this.statoAnnuncio != null)
			return StatoAnnuncio.contains(this.statoAnnuncio);

		return true;
	}

	/**
	 * Ritorna la stringa passata come parametro modificando il primo carattere in maiuscolo.  
	 *
	 * @param input the input
	 * @return the capitalized name
	 */
	public String getCapitalizedName(String input) {

		if (input == null || input.length() == 0)
			return input;

		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();

	}

	/**
	 * Popola ed incapsula gli attribiti dell'Annuncio per un'operazione di lettura.
	 *
	 * @param idAnnuncio the id annuncio
	 * @return true, if successful
	 */
	public boolean popolaAnnuncioPerLettura(int idAnnuncio) {

		Annuncio annuncio;

		GestioneAnnuncioController controller = new GestioneAnnuncioController();

		try {
			annuncio = controller.ritrovaAnnuncioPubblico(idAnnuncio);
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
			return false;

		}

		if (annuncio == null)
			return false;

		this.titolo = annuncio.getTitolo();
		this.descrizione = annuncio.getDescrizione();
		this.prezzo = annuncio.getPrezzo();
		this.quantit� = annuncio.getQuantit�();
		this.descrizione = annuncio.getDescrizione();
		this.foto = annuncio.getFoto();
		this.id = annuncio.getId();
		this.ultimaModifica = annuncio.getUltimaModifica();
		this.categoria = annuncio.getCategoria().toString();

		return true;

	}

	/**
	 * Prende una Stringa e ritorna il suo parse in intero. In caso di errore ritorna -1.
	 *
	 * @param request the request
	 * @return the int
	 */
	public int parseRequest(String request) {

		int result;

		try {
			result = Integer.parseInt(request);

		} catch (Exception e) {
			result = -1;
		}

		return result;

	}

	/**
	 * Incapsula la creazione di un Annuncio.
	 *
	 * @param idCreatore the id creatore
	 * @param picture the picture
	 * @return true, if successful
	 */
	public boolean creaAnnuncio(int idCreatore, FileItem picture) {

		this.creatoreID = idCreatore;

		return new GestioneAnnuncioController().aggiungiAnnuncio(this.titolo, this.descrizione, this.categoria,
				this.quantit�, this.prezzo, this.foto, idCreatore, picture);

	}

}
