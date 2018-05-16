package bean;

import java.util.ArrayList;
import java.util.List;

import controller.GestioneRubricaConsumatoreController;
import exceptions.ItemNotFoundException;
import model.IndirizzoSpedizione;

public class GestioneIndirizzoSpedizioneBean {

	private List<IndirizzoSpedizioneBean> risultati;

	public List<IndirizzoSpedizioneBean> getRisultati() {
		return risultati;
	}

	public void setRisultati(List<IndirizzoSpedizioneBean> risultati) {
		this.risultati = risultati;
	}

	public int cercaIndirizzi(int idUser) {

		this.risultati = new ArrayList<>();

		try {
			for (IndirizzoSpedizione iS : new GestioneRubricaConsumatoreController().cercaIndirizzi(idUser))
				this.risultati.add(new IndirizzoSpedizioneBean(iS.getId(), iS.getNominativo(), iS.getPresso(),
						iS.getVia(), iS.getCittà(), iS.getCap(), iS.getProvincia(), iS.getRecapitoTelefonico()));
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
			return -1;

		}

		return this.risultati.size();

	}

}
