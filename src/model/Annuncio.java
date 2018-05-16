package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "Annuncio")

public class Annuncio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7756936426581898266L;
	@Id
	@GeneratedValue
	private int id;
	private String titolo;
	private String descrizione;

	private int quantità;
	private float prezzo;
	private String foto;
	private int creatoreID;

	@Enumerated(EnumType.STRING)
	@Column(name = "categoria")
	private CategoriaAnnuncio categoria;

	@Enumerated(EnumType.STRING)
	@Column(name = "state")
	private StatoAnnuncio state;

	@Version
	@Column(name = "UltimaModifica")
	private int ultimaModifica;

	public Annuncio(String titolo, String descrizione, CategoriaAnnuncio categoria, int quantità, float prezzo,
			String foto, int creatoreID) {

		this.titolo = titolo;
		this.categoria = categoria;
		this.foto = foto;
		this.quantità = quantità;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.creatoreID = creatoreID;
		this.state = StatoAnnuncio.PUBLIC;
	}

	public Annuncio() {

	}

	public Annuncio(String nuovoTitolo, String nuovaDescrizione, CategoriaAnnuncio nuovaCategoria, int nuovaQuantità,
			float nuovoPrezzo, String nuovaFoto, int idUtente, StatoAnnuncio nuovoStato) {

		this(nuovoTitolo, nuovaDescrizione, nuovaCategoria, nuovaQuantità, nuovoPrezzo, nuovaFoto, idUtente);

		this.state = nuovoStato;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public CategoriaAnnuncio getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaAnnuncio categoria) {
		this.categoria = categoria;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
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

	public StatoAnnuncio getState() {
		return state;
	}

	public void setState(StatoAnnuncio state) {
		this.state = state;
	}

	public int getUltimaModifica() {
		return ultimaModifica;
	}

	public void setUltimaModifica(int ultimaModifica) {
		this.ultimaModifica = ultimaModifica;
	}

	public boolean equalContent(Annuncio confronto) {

		return ((this.titolo.equals(confronto.getTitolo())) && (this.descrizione.equals(confronto.getDescrizione()))
				&& (this.categoria.equals(confronto.getCategoria())) && (this.prezzo == confronto.getPrezzo())
				&& (this.quantità == confronto.getQuantità()) && (this.foto.equals(confronto.getFoto()))
				&& (this.creatoreID == confronto.getCreatoreID()));

	}

}
