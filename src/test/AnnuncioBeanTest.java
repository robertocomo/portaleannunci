package test;

import static org.junit.Assert.*;

import org.junit.Test;
import bean.AnnuncioBean;
import model.CategoriaAnnuncio;

public class AnnuncioBeanTest {

	@Test
	public void testValidateFalse() {
		
		
		AnnuncioBean annuncioBean = new AnnuncioBean();
		annuncioBean.setTitolo("Titolo");
		annuncioBean.setDescrizione("");
		annuncioBean.setPrezzo(-5);
		annuncioBean.setCategoria("");
		annuncioBean.setQuantità(-100);
		
		boolean validate = annuncioBean.validate();
		
		
		assertEquals("Testing AnnuncioBean.validate()", false, validate);
		
		
	}
	

	@Test
	public void testValidateTrue() {
		
		

		AnnuncioBean annuncioBean = new AnnuncioBean("String titolo", "String descrizione", CategoriaAnnuncio.LIBRI.toString(),
				1, 1, "String foto", 0);
		
		
		boolean validate = annuncioBean.validate();
		
		
		assertEquals("Testing AnnuncioBean.validate()", true, validate);
		
	}
	


}
