package bean;

import controller.GestioneRubricaConsumatoreController;

public class IndirizzoSpedizioneBean {

	private int id;
	
	private String nominativo;
	private String presso;
	private String via;
	private String citt�;
	private String cap;
	private String provincia;
	private String recapitoTelefonico;
	
	

	public IndirizzoSpedizioneBean(int id, String nominativo, String presso, String via, String citt�, String cap,
			String provincia, String recapitoTelefonico) {
		super();
		this.id = id;
		this.nominativo = nominativo;
		this.presso = presso;
		this.via = via;
		this.citt� = citt�;
		this.cap = cap;
		this.provincia = provincia;
		this.recapitoTelefonico = recapitoTelefonico;
	}
	
	
	public IndirizzoSpedizioneBean() {
		this.presso=" ";
	}
	
	
	public boolean validate() {
				
		return (!( this.nominativo.trim().isEmpty() || this.via.trim().isEmpty() 
				|| this.citt�.trim().isEmpty() || this.cap.trim().isEmpty() || this.provincia.trim().isEmpty() 
				|| this.recapitoTelefonico.trim().isEmpty()) &&	(this.recapitoTelefonico.matches("^[0-9]*$")));	
		
		
	}
	
	
	
	public boolean creaIndirizzo(int idUtente) {
		
		return  new GestioneRubricaConsumatoreController().aggiungiIndirizzoSpedizione(idUtente, 
				this.nominativo, this.presso, this.via, this.citt�, this.cap, this.provincia, this.recapitoTelefonico);
		
		
		
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
	public String getCitt�() {
		return citt�;
	}
	public void setCitt�(String citt�) {
		this.citt� = citt�;
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
	
	
	
	
	
	
	
}
