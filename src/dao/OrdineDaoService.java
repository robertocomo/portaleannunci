package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import exceptions.DaoServiceException;
import exceptions.InvalidSessionException;
import exceptions.InvalidTransactionException;
import hibernate.DatabaseManager;
import model.Ordine;

/**
 * The Class OrdineDaoService.
 */
public class OrdineDaoService {

	private Session session;
	private Transaction transaction;
	
	
	/**
	 * Instantiates a new ordine dao service to manually manage the connection to the Database.
	 */
	public OrdineDaoService() {
		
	}
	
	
	/**
	 * Instantiates a new ordine dao service to manually manage the connection to the Database.
	 *
	 * @param session the session
	 * @param transaction the transaction
	 */
	public OrdineDaoService(Session session, Transaction transaction) {
		super();
		this.session = session;
		this.transaction = transaction;
	}
	
	
	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}
	
	/**
	 * Sets the session.
	 *
	 * @param session the new session
	 */
	public void setSession(Session session) {
		this.session = session;
	}
	
	/**
	 * Gets the transaction.
	 *
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	
	/**
	 * Sets the transaction.
	 *
	 * @param transaction the new transaction
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	/**
	 * Open a new session.
	 */
	public void openSession() {
		
		session = DatabaseManager.getSession();
	}
	
	
	/**
	 * Close the session.
	 */
	public void closeSession() {
		 session.close();
	}
	
	/**
	 * Open a new transaction.
	 */
	public void openTransaction() {
		transaction = session.getTransaction();
		transaction.begin();
	}
	
	/**
	 * Close the transaction.
	 */
	public void closeTransaction() {
		transaction.commit();
	}
	
	/**
	 * Do a Transaction roolback.
	 */
	public void transactionRoolback() {
		transaction.rollback();
	}
	
	
	/**
	 * Memorizza nel sistema un Ordine.
	 *
	 * @param ordine the ordine
	 * @throws InvalidSessionException the invalid session exception
	 * @throws InvalidTransactionException the invalid transaction exception
	 * @throws DaoServiceException the dao service exception
	 */
	public void store(Ordine ordine) throws InvalidSessionException, InvalidTransactionException, DaoServiceException {
		
		if(session == null)
			throw new InvalidSessionException();
		
		if(transaction == null)
			throw new InvalidTransactionException();
			
		
		try {
			session.save(ordine);
		}
		catch(Exception e) {
			
			if (this.transaction != null)
			{
               this.transaction.rollback();
               throw new DaoServiceException(); 
                               
            }
            e.printStackTrace();
        
		}
	}
	
	
	/**
	 * Ritrova un Ordine nel sistema mediante il suo identificativo.
	 *
	 * @param idOrdine the id ordine
	 * @return the by ID
	 * @throws InvalidSessionException the invalid session exception
	 * @throws InvalidTransactionException the invalid transaction exception
	 * @throws DaoServiceException the dao service exception
	 */
	public Ordine getByID(int idOrdine) throws InvalidSessionException, InvalidTransactionException, DaoServiceException {
		
		if(session == null)
			throw new InvalidSessionException();
		
		if(transaction == null)
			throw new InvalidTransactionException();
			
		Ordine ordine = null;
		
		try {
			 ordine = session.get(Ordine.class, idOrdine);
		}
		catch(Exception e) {
			
			if (this.transaction != null)
			{
               this.transaction.rollback();
               throw new DaoServiceException(); 
                               
            }
            e.printStackTrace();
        } 
		
		return ordine;
		
        
	}
	
	
}
