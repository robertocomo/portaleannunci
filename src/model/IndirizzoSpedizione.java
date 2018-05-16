package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Indirizzo")
public class IndirizzoSpedizione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1464382696519029650L;
	
	@Id
	@GeneratedValue
	private int id;

	private String nominativo;
	private String presso;
	private String via;
	private String città;
	private String cap;
	private String provincia;
	private String recapitoTelefonico;

	
	public IndirizzoSpedizione(String nominativo, String presso, String via, String città, String cap, String provincia,
			String recapitoTelefonico) {
		super();

		this.nominativo = nominativo;
		this.presso = presso;
		this.via = via;
		this.città = città;
		this.cap = cap;
		this.provincia = provincia;
		this.recapitoTelefonico = recapitoTelefonico;
	}

	public IndirizzoSpedizione() {
		this.nominativo = "";
		this.presso = "";
		this.via = "";
		this.città = "";
		this.cap = "";
		this.provincia = "";
		this.recapitoTelefonico = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNominativo() {
		return nominativo;
	}

	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}

	public String getPresso() {
		return presso;
	}

	public void setPresso(String presso) {
		this.presso = presso;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRecapitoTelefonico() {
		return recapitoTelefonico;
	}

	public void setRecapitoTelefonico(String recapitoTelefonico) {
		this.recapitoTelefonico = recapitoTelefonico;
	}

	@Override
	public String toString() {

		return (this.getNominativo() + " " + (this.getPresso().trim().isEmpty() ? "" : this.getPresso() + " ") + this.getVia()
				+ " " + this.getCap() + " " + this.getCittà() + " " + this.getProvincia()
				+ this.getRecapitoTelefonico());

	}

}
