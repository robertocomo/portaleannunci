package test;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.RegistrationBean;
import model.TipologiaUtente;

public class RegistrationBeanTest {

	@Test
	public void testValidate() {

		RegistrationBean bean = new RegistrationBean();
		
		bean.setNome("nome");
		bean.setCognome("cognome");
		bean.setEmail("email@email.com");
		bean.setConfermaEmail(bean.getEmail());
		bean.setPassword("password");
		bean.setConfermaPassword(bean.getPassword());
		
		assertEquals("Testing RegistrationBean.validate() Utente Non riconosciuto", false, bean.validate());
		
		bean.setTipoUtente(TipologiaUtente.CONSUMATORE.toString());
		
		assertEquals("Testing RegistrationBean.validate() Consumatore", true, bean.validate());
		
		bean.setTipoUtente(TipologiaUtente.PRODUTTORE.toString());
		
		assertEquals("Testing RegistrationBean.validate() Produttore", false, bean.validate());
		
		bean.setCodiceFiscale("codice fiscale");
		
		assertEquals("Testing RegistrationBean.validate() Produttore", true, bean.validate());
		
		
		

	}

	@Test
	public void testCheckEmailFalse() {

		RegistrationBean bean = new RegistrationBean();
		
		bean.setEmail("Email ");
		bean.setConfermaEmail("Email diversa");
		
		assertEquals("Testing RegistrationBean.checkEmail()", false, bean.checkEmail());
		
		
	}
	
	
	@Test
	public void testCheckEmailTrue() {

		RegistrationBean bean = new RegistrationBean();
		
		bean.setEmail("Email ");
		bean.setConfermaEmail(bean.getEmail());
		
		assertEquals("Testing RegistrationBean.checkEmail()", true, bean.checkEmail());
		
		
	}
	
	
	
	@Test
	public void testCheckPasswordTrue() {

		RegistrationBean bean = new RegistrationBean();
		
		bean.setPassword("password ");
		bean.setConfermaPassword(bean.getPassword());
		
		assertEquals("Testing RegistrationBean.checkPassword()", true, bean.checkPassword());
	}
	
	
	@Test
	public void testCheckPasswordFalse() {

		RegistrationBean bean = new RegistrationBean();
		
		bean.setPassword("password ");
		bean.setConfermaEmail("password diversa");
		
		assertEquals("Testing RegistrationBean.checkPassword()", false, bean.checkPassword());
	}
	
	
	

}
