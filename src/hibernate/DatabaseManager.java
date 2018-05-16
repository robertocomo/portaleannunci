package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class DatabaseManager {
	
	//////////////////////
	//		LAZY		//
	//////////////////////
	
	private SessionFactory sessionFactory;
	
	private DatabaseManager() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	private static class Contenitore { 
		private final static DatabaseManager instance = new DatabaseManager();
	}
	
	public static Session getSession() {
		return Contenitore.instance.sessionFactory.openSession();
	}
		
	
	
	
	/*
	private static SessionFactory sessionFactory;
		
	private static void initialize() {

		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	public static Session getSession() {
		if(sessionFactory == null)
			DatabaseManager.initialize();
		
		return sessionFactory.openSession();
	
		
		*/
	

	
	
	
	
		
	//////////////////////
	//		EAGER		//
	//////////////////////
	
	/* 
	private final static DatabaseManager instance = new DatabaseManager();
	private SessionFactory sessionFactory;
	
	
	private DatabaseManager() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	public static Session getSession() {
		return instance.sessionFactory.openSession();
	
	}
	*/
	

}