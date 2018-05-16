package bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import controller.GestioneAnnuncioController;
import exceptions.ItemNotFoundException;
import exceptions.NotAutorizedException;
import exceptions.TransactionException;
import model.Annuncio;
import model.StatoAnnuncio;

public class GestioneAnnuncioBean {

	private List<AnnuncioBean> risultati;
	private boolean userIsAuthorized;
	private boolean itemFound;
	private boolean transactionError;

	private AnnuncioBean annuncioCorrente;
	private GestioneAnnuncioController controller;

	public GestioneAnnuncioBean() {

		this.itemFound = false;
		this.userIsAuthorized = false;
		this.transactionError = false;
	}

	public AnnuncioBean getAnnuncioCorrente() {
		return annuncioCorrente;
	}

	public void setAnnuncioCorrente(AnnuncioBean annuncioCorrente) {
		this.annuncioCorrente = annuncioCorrente;
	}

	public List<AnnuncioBean> getRisultati() {
		return risultati;
	}

	public boolean queryAlreadyDone() {

		return this.risultati != null;

	}

	public boolean isUserIsAuthorized() {
		return userIsAuthorized;
	}

	public boolean isItemFound() {
		return itemFound;
	}

	public boolean isTransactionError() {
		return transactionError;
	}

	public int parseRequest(String request) {

		int idAnnuncio;

		try {
			idAnnuncio = Integer.parseInt(request);

		} catch (Exception e) {
			idAnnuncio = -1;
		}

		return idAnnuncio;

	}

	public AnnuncioBean getSingoloAnnuncioByIdAnnuncio(int idAnnuncio, int idUtente, int versioneAnnuncio) {

		this.userIsAuthorized = true;
		this.itemFound = true;

		if (controller == null)
			controller = new GestioneAnnuncioController();

		Annuncio annuncio = null;
		try {
			annuncio = controller.caricaAnnuncioPerModifica(idAnnuncio, idUtente, versioneAnnuncio);
			this.userIsAuthorized = true;
			this.itemFound = true;
			this.transactionError = false;

		} catch (NotAutorizedException e) {
			this.userIsAuthorized = false;
		}

		catch (ItemNotFoundException e) {
			this.itemFound = false;
		}

		catch (TransactionException e) {
			this.transactionError = true;
		}

		if (annuncio != null)
			return (new AnnuncioBean(annuncio.getId(), annuncio.getUltimaModifica(), annuncio.getTitolo(),
					annuncio.getDescrizione(), annuncio.getCategoria().toString(), annuncio.getQuantità(),
					annuncio.getPrezzo(), annuncio.getFoto(), annuncio.getCreatoreID(),
					annuncio.getState().toString()));
		return null;

	}

	public AnnuncioBean getSingoloAnnuncioByIdAnnuncio(int idAnnuncio, int idUtente) {

		this.userIsAuthorized = true;
		this.itemFound = true;

		if (controller == null)
			controller = new GestioneAnnuncioController();

		Annuncio annuncio = null;
		try {
			annuncio = controller.caricaAnnuncioPerLettura(idAnnuncio, idUtente);
			this.userIsAuthorized = true;
			this.itemFound = true;

		} catch (NotAutorizedException e) {
			this.userIsAuthorized = false;
		}

		catch (ItemNotFoundException e) {
			this.itemFound = false;
		}

		if (annuncio != null)
			return (new AnnuncioBean(annuncio.getId(), annuncio.getUltimaModifica(), annuncio.getTitolo(),
					annuncio.getDescrizione(), annuncio.getCategoria().toString(), annuncio.getQuantità(),
					annuncio.getPrezzo(), annuncio.getFoto(), annuncio.getCreatoreID(),
					annuncio.getState().toString()));
		return null;

	}

	public boolean modificaAnnuncio(int idAnnuncio, int idUtente, int versioneAnnuncio, FileItem picture) {

		boolean result = false;
		this.userIsAuthorized = true;
		this.itemFound = true;
		this.transactionError = false;

		if (controller == null)
			controller = new GestioneAnnuncioController();

		try {
			result = controller.modificaAnnuncio(idAnnuncio, idUtente, versioneAnnuncio, annuncioCorrente.getTitolo(),
					annuncioCorrente.getDescrizione(), annuncioCorrente.getCategoria(), annuncioCorrente.getQuantità(),
					annuncioCorrente.getPrezzo(), annuncioCorrente.getFoto(), annuncioCorrente.getStatoAnnuncio(),
					picture);

			this.userIsAuthorized = true;
			this.itemFound = true;

		} catch (NotAutorizedException e) {
			this.userIsAuthorized = false;
		}

		catch (ItemNotFoundException e) {
			this.itemFound = false;
		}

		catch (TransactionException e) {
			this.transactionError = true;
		}

		return (result && this.userIsAuthorized && this.itemFound && !(this.transactionError));

	}

	public boolean eliminaAnnuncio(int idAnnuncio, int idUtente, int versioneAnnuncio) {

		boolean result = false;
		this.userIsAuthorized = true;
		this.itemFound = true;
		this.transactionError = false;

		if (controller == null)
			controller = new GestioneAnnuncioController();

		try {
			result = controller.rimuoviAnnuncio(idAnnuncio, idUtente, versioneAnnuncio);

			this.userIsAuthorized = true;
			this.itemFound = true;
		} catch (NotAutorizedException e) {
			this.userIsAuthorized = false;
		}

		catch (ItemNotFoundException e) {
			this.itemFound = false;
		}

		catch (TransactionException e) {
			this.transactionError = true;
		}

		return (result && this.userIsAuthorized && this.itemFound && !(this.transactionError));

	}

	public int getAllAnnunci(int userID) {

		this.risultati = new ArrayList<>();

		if (controller == null)
			controller = new GestioneAnnuncioController();

		try {
			for (Annuncio annuncio : controller.ritrovaAnnunciUtente(userID))
				this.risultati.add(new AnnuncioBean(annuncio.getId(), annuncio.getTitolo(), annuncio.getDescrizione(),
						annuncio.getCategoria().toString(), annuncio.getQuantità(), annuncio.getPrezzo(),
						annuncio.getFoto(), annuncio.getCreatoreID(), annuncio.getState().toString()));
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
			return 0;

		}

		return this.risultati.size();

	}

}
