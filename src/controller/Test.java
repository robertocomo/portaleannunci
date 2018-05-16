package controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.AnnuncioBean;
import bean.GestioneAnnuncioBean;
import bean.LoginBean;
import bean.RegistrationBean;
import dao.OrdineDao;
import hibernate.DatabaseManager;
import model.CategoriaAnnuncio;
import model.Consumatore;
import model.IndirizzoSpedizione;
import model.Ordine;
import model.Produttore;
import model.TipologiaUtente;
import model.Utente;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*RegistrationBean bean = new RegistrationBean();
		
		bean.setEmail("ciao");
		bean.setPassword("casa");
		bean.setNome("nome");
		bean.setCognome("cognome");
		
		bean.registerUser();*/
		
		
	/*	LoginBean lBean = new LoginBean();
		
		lBean.setEmail("eevee");
		lBean.setPassword("eevee");
		
		if(lBean.validate())
			System.out.println("ok" + lBean.getTipo());
		else
			System.out.println("ko");
		
		
		lBean = new LoginBean();
		
		lBean.setEmail("frida");
		lBean.setPassword("frida");
		
		if(lBean.validate())
			System.out.println("ok" + lBean.getTipo());
		else
			System.out.println("ko");
		
		
		lBean = new LoginBean();
		
		lBean.setEmail("ciao");
		lBean.setPassword("frida");
		
		if(lBean.validate())
			System.out.println("ok" + lBean.getTipo());
		else
			System.out.println("ko");
		
		
		lBean = new LoginBean();
		
		lBean.setEmail("eevee");
		lBean.setPassword("frida");
		
		if(lBean.validate())
			System.out.println("ok" + lBean.getTipo());
		else
			System.out.println("ko");
		
		
		*/
		
		/*ControlloreUtente.getInstance().aggiungiIndirizzoSpedizione(4, new IndirizzoSpedizione("String 1 ", "String 2", "String 3", "String 4", "String 5",
    			"String 6", "String 7"));
		*/
		
		//System.out.println(registraProduttore());
		//System.out.println(registraConsumatore());
		
		//System.out.println(TipologiaUtente.CONSUMATORE);
		//System.out.println(TipologiaUtente.CONSUMATORE.toString());
		
		//shipped();
		
		//System.out.println(CategorieAnnuncio.contains("elettronica"));
		//System.out.println(CategorieAnnuncio.contains("ELETTRONICA"));
		//System.out.println(CategorieAnnuncio.contains("test"));
		/*
		AnnuncioBean annuncio = new AnnuncioBean();
		
		annuncio.setTitolo("eevee");
		
		GestioneAnnuncioBean gestione = new GestioneAnnuncioBean();
		
		gestione.setAnnuncioCorrente(annuncio);
		
		gestione.getAnnuncioCorrente().setTitolo("bulbasaur");
		
		System.out.println(annuncio.getTitolo());
		System.out.println(gestione.getAnnuncioCorrente().getTitolo());
		
		annuncio.setTitolo("eevee");
		System.out.println(annuncio.getTitolo());
		System.out.println(gestione.getAnnuncioCorrente().getTitolo());*/
		
		GestioneAnnuncioController controller = new GestioneAnnuncioController();
		
		//System.out.println(controller.copyPicture("picture/" + 34 + ".jpeg", "picture/" + 80 + ".jpeg"));
		
	}
	
	
	
	public static void testPoli() {
		
		Utente user;
		
		user = new Consumatore();
		
		
		
	}
	
	
	public static void shipped() {
		
		
		Ordine ordine = OrdineDao.findById(17);
		
		ordine.setShipped(true);
		
		OrdineDao.update(ordine);
		
		
	}
	
	
	public synchronized static boolean registraProduttore() {
	    	
	    	Produttore produttore = new Produttore("String nome", "String cognome", "String email", "String password", "String codiceFiscale");
	    	
	    	Session session = DatabaseManager.getSession();
	        Transaction tx = null;
	        boolean done = false;
	        
	        try {
	            tx = session.getTransaction();
	            tx.begin();
	            
	            session.save(produttore);
	            
	            tx.commit();
	            done = true;
	        } catch (Exception e) {
	            if (tx != null) {
	                tx.rollback();
	                done = false;
	                
	            }
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        
	        return done;
	    }
	
	
	
	public synchronized static boolean registraConsumatore() {
    	
    	Consumatore consumatore = new Consumatore("String nome", "String cognome", "String email", "String password");
    	
    	Session session = DatabaseManager.getSession();
        Transaction tx = null;
        boolean done = false;
        
        try {
            tx = session.getTransaction();
            tx.begin();
            
            session.save(consumatore);
            
            tx.commit();
            done = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                done = false;
                
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return done;
    }
    	
    	
    	
    }
	
	
	


