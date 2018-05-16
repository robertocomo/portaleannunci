package bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.SearchController;
import model.Annuncio;

public class SearchBean {

	private String title;
	private String extendToDescription;
	private String category;
	private boolean checked;

	private float minPrice;
	private float maxPrice;
	private List<AnnuncioBean> risultati;

	private SearchController searchController = null;

	public SearchBean() {
		this.extendToDescription = "";
		this.title = "";
		this.category = "";
		this.minPrice = 0;
		this.maxPrice = 0;
	}

	public void orderByPrezzoCrescente() {

		Collections.sort(risultati,
				(a, b) -> a.getPrezzo() < b.getPrezzo() ? -1 : a.getPrezzo() == b.getPrezzo() ? 0 : 1);

	}

	public void orderByPrezzoDecrescente() {

		Collections.sort(risultati,
				(a, b) -> a.getPrezzo() > b.getPrezzo() ? -1 : a.getPrezzo() == b.getPrezzo() ? 0 : 1);

	}

	public boolean isChecked() {
		this.parseData();

		return checked;
	}

	public void setChecked(boolean flag) {
		this.checked = flag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExtendToDescription() {
		return extendToDescription;
	}

	public void setExtendToDescription(String extendToDescription) {
		this.extendToDescription = extendToDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	private void parseData() {

		this.checked = Boolean.parseBoolean(extendToDescription);

		if (this.extendToDescription.isEmpty() || this.extendToDescription == null)
			this.checked = false;

		if (this.title.trim().isEmpty())
			this.title = "";

		if (this.category.trim().isEmpty())
			this.category = "";

	}

	public List<AnnuncioBean> getRisultati() {
		return risultati;
	}

	public void setRisultati(List<AnnuncioBean> risultati) {
		this.risultati = risultati;
	}

	private int parseSearch(List<Annuncio> listaAnnunci) {

		if (listaAnnunci == null)
			return -2;

		this.risultati = new ArrayList<>();

		for (Annuncio annuncio : listaAnnunci)
			this.risultati.add(new AnnuncioBean(annuncio.getId(), annuncio.getTitolo(), annuncio.getDescrizione(),
					annuncio.getCategoria().toString(), annuncio.getQuantità(), annuncio.getPrezzo(),
					annuncio.getFoto(), annuncio.getCreatoreID()));

		return this.risultati.size();

	}

	private boolean badRequest() {

		if ((this.minPrice < 0) || (this.maxPrice < 0) || ((this.minPrice > this.maxPrice) && (this.maxPrice != 0)))
			return true;

		return false;
	}

	public int advanceSearch() {

		int result = -1;

		if (this.badRequest())
			return -1;

		if (this.searchController == null)
			searchController = new SearchController();

		this.parseData();

		if (this.maxPrice > 0)
			return this.parseSearch(searchController.search(title, checked, category, minPrice, maxPrice));

		if (this.minPrice > 0)
			return this.parseSearch(searchController.search(title, checked, category, minPrice));

		return this.basicSearch();

	}

	public int basicSearch() {

		int result = -1;

		if (this.badRequest())
			return -1;

		if (this.searchController == null)
			searchController = new SearchController();

		this.parseData();

		if (this.category.trim().isEmpty())
			return this.parseSearch(searchController.search(title, checked));

		return this.parseSearch(searchController.search(title, checked, category));

	}

}
