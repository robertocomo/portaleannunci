package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import exceptions.DaoServiceException;
import exceptions.InvalidSessionException;
import exceptions.InvalidTransactionException;
import hibernate.DatabaseManager;
import model.Annuncio;
import model.Annuncio_;
import model.CategoriaAnnuncio;
import model.StatoAnnuncio;

/**
 * The Class AnnuncioDaoService.
 */
public class AnnuncioDaoService {

	private Session session;
	private Transaction transaction;
	
	private CriteriaBuilder builder;
    private CriteriaQuery<Annuncio> criteria;
    private Root<Annuncio> root;
	
	
	/**
	 * Instantiates a new annuncio dao service to manually manage the connection to the Database.
	 */
	public AnnuncioDaoService() {
		
	}
	
	
	/**
	 * Instantiates a new annuncio dao service to manually manage the connection to the Database.
	 *
	 * @param session the session
	 * @param transaction the transaction
	 */
	public AnnuncioDaoService(Session session, Transaction transaction) {
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
	 * Memorizza un Annuncio nel sistema.
	 *
	 * @param annuncio  annuncio
	 * @throws InvalidSessionException invalid session exception
	 * @throws InvalidTransactionException invalid transaction exception
	 * @throws DaoServiceException dao service exception
	 */
	public void store(Annuncio annuncio) throws InvalidSessionException, InvalidTransactionException, DaoServiceException {
		
		if(session == null)
			throw new InvalidSessionException();
		
		if(transaction == null)
			throw new InvalidTransactionException();
			
		
		try {
			session.save(annuncio);
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
	 * Ritrova un Annuncio nel sistema tramite il suo identificativo.
	 *
	 * @param idAnnuncio the id annuncio
	 * @return the by ID
	 * @throws InvalidSessionException the invalid session exception
	 * @throws InvalidTransactionException the invalid transaction exception
	 * @throws DaoServiceException the dao service exception
	 */
	public Annuncio getByID(int idAnnuncio) throws InvalidSessionException, InvalidTransactionException, DaoServiceException {
		
		if(session == null)
			throw new InvalidSessionException();
		
		if(transaction == null)
			throw new InvalidTransactionException();
			
		Annuncio annuncio = null;
		
		try {
			 annuncio = session.get(Annuncio.class, idAnnuncio);
		}
		catch(Exception e) {
			
			if (this.transaction != null)
			{
               this.transaction.rollback();
               throw new DaoServiceException(); 
                               
            }
            e.printStackTrace();
        } 
		
		return annuncio;
		
        
	}
	
	
	
	/**
	 * Cerca ed ottieni, in modo eventualmente esclusivo, un Annuncio presente nel sistema.
	 *
	 * @param idAnnuncio id annuncio
	 * @param lockMode lock mode
	 * @return the annuncio
	 * @throws InvalidSessionException invalid session exception
	 * @throws InvalidTransactionException invalid transaction exception
	 * @throws DaoServiceException dao service exception
	 */
	public Annuncio lockAndGetByID(int idAnnuncio, LockMode lockMode) throws InvalidSessionException, InvalidTransactionException, DaoServiceException {
		
		if(session == null)
			throw new InvalidSessionException();
		
		if(transaction == null)
			throw new InvalidTransactionException();
			
		Annuncio annuncio = null;
		
		try {
			 annuncio =  session.get(Annuncio.class, idAnnuncio, lockMode);
		}
		catch(Exception e) {
			
			if (this.transaction != null)
			{
               this.transaction.rollback();
               throw new DaoServiceException(); 
                               
            }
            e.printStackTrace();
        } 
		
		return annuncio;
		
        
	}
	
	
	
	/**
	 * Inizializza il metodi di ricerca.
	 */
	public void inizializeSearch() {
		
		 this.builder = session.getCriteriaBuilder();
         this.criteria = builder.createQuery(Annuncio.class);
         this.root = criteria.from(Annuncio.class);
	
	}
	
	
	
	/**
	 * Realizza un'operazione di ricerca degli Annunci pubblici presenti nel sitema filtrando mediante un pattern di ricerca, eventualmente
     * esteso anche al contenuto della descrizione, ed una categoria di appartenenza.
	 *
	 * @param pattern the pattern
	 * @param description the description
	 * @param category the category
	 * @return the list
	 * @throws InvalidSessionException the invalid session exception
	 * @throws InvalidTransactionException the invalid transaction exception
	 * @throws DaoServiceException the dao service exception
	 */
	public List<Annuncio> search(String pattern, boolean description, CategoriaAnnuncio category) throws InvalidSessionException, InvalidTransactionException, DaoServiceException{
		
		
		this.inizializeSearch();
		
		Predicate criteriaPattern;
		
		if(description)
			criteriaPattern = builder.or(builder.like(root.get(Annuncio_.titolo), "%" + pattern + "%"), builder.like(root.get(Annuncio_.descrizione), "%" + pattern + "%"));
		
		else
			criteriaPattern = builder.like(root.get(Annuncio_.titolo), "%" + pattern + "%");
			
			
		criteria.select(root).where(builder.and(
					criteriaPattern,
	         		builder.equal(root.get(Annuncio_.categoria), category),
	         		builder.equal(root.get(Annuncio_.state), StatoAnnuncio.PUBLIC)));

		return this.excecuteSearch();
		

	}
	
	
	
	/**
     * Realizza un'operazione di ricerca degli Annunci pubblici presenti nel sitema filtrando mediante un pattern di ricerca, eventualmente
     * esteso anche al contenuto della descrizione.
	 *
	 * @param pattern the pattern
	 * @param description the description
	 * @return the list
	 * @throws InvalidSessionException the invalid session exception
	 * @throws InvalidTransactionException the invalid transaction exception
	 * @throws DaoServiceException the dao service exception
	 */
	public List<Annuncio> search(String pattern, boolean description) throws InvalidSessionException, InvalidTransactionException, DaoServiceException{
		
		this.inizializeSearch();
		
		Predicate criteriaPattern;
		
		if(description)
			criteriaPattern = builder.or(builder.like(root.get(Annuncio_.titolo), "%" + pattern + "%"), builder.like(root.get(Annuncio_.descrizione), "%" + pattern + "%"));
		
		else
			criteriaPattern = builder.like(root.get(Annuncio_.titolo), "%" + pattern + "%");
			
			
		criteria.select(root).where(builder.and(
					criteriaPattern,
	         		builder.equal(root.get(Annuncio_.state), StatoAnnuncio.PUBLIC)));

		return this.excecuteSearch();
		

	}
	
	
	
	/**
     * Realizza un'operazione di ricerca degli Annunci pubblici presenti nel sitema filtrando mediante un pattern di ricerca, eventualmente
     * esteso anche al contenuto della descrizione, una categoria di appartenenza, un prezzo di acquisto minimo ed uno massimo.
	 *
	 * @param pattern the pattern
	 * @param description the description
	 * @param category the category
	 * @param minPrice the min price
	 * @param maxPrice the max price
	 * @return the list
	 * @throws InvalidSessionException the invalid session exception
	 * @throws InvalidTransactionException the invalid transaction exception
	 * @throws DaoServiceException the dao service exception
	 */
	public List<Annuncio> search(String pattern, boolean description, CategoriaAnnuncio category, float minPrice, float maxPrice) throws InvalidSessionException, InvalidTransactionException, DaoServiceException{
		
		
		this.inizializeSearch();
		
		Predicate criteriaPattern;
		Predicate criteriaCategoryState;
		
		
		if(description)
			criteriaPattern = builder.or(builder.like(root.get(Annuncio_.titolo), "%" + pattern + "%"), builder.like(root.get(Annuncio_.descrizione), "%" + pattern + "%"));
		
		else
			criteriaPattern = builder.like(root.get(Annuncio_.titolo), "%" + pattern + "%");
			
			
		if(category == null)
			criteriaCategoryState = builder.equal(root.get(Annuncio_.state), StatoAnnuncio.PUBLIC);
		else
			criteriaCategoryState = builder.and(
					builder.equal(root.get(Annuncio_.categoria), category),
					builder.equal(root.get(Annuncio_.state), StatoAnnuncio.PUBLIC));
		
		criteria.select(root).where(builder.and(
					criteriaPattern,
					criteriaCategoryState,
	         		builder.greaterThanOrEqualTo(root.get(Annuncio_.prezzo), minPrice), 
	         		builder.lessThanOrEqualTo(root.get(Annuncio_.prezzo), maxPrice)));

		return this.excecuteSearch();
		

	}
	
	
	/**
     * Realizza un'operazione di ricerca degli Annunci pubblici presenti nel sitema filtrando mediante un pattern di ricerca, eventualmente
     * esteso anche al contenuto della descrizione, una categoria di appartenenza ed un prezzo di acquisto minimo.
	 *
	 * @param pattern the pattern
	 * @param description the description
	 * @param category the category
	 * @param minPrice the min price
	 * @return the list
	 * @throws InvalidSessionException the invalid session exception
	 * @throws InvalidTransactionException the invalid transaction exception
	 * @throws DaoServiceException the dao service exception
	 */
	public List<Annuncio> search(String pattern, boolean description, CategoriaAnnuncio category, float minPrice) throws InvalidSessionException, InvalidTransactionException, DaoServiceException{
		
		
		this.inizializeSearch();
		
		Predicate criteriaPattern;
		Predicate criteriaCategoryState;
		
		
		if(description)
			criteriaPattern = builder.or(builder.like(root.get(Annuncio_.titolo), "%" + pattern +"%"), builder.like(root.get(Annuncio_.descrizione), "%" + pattern + "%"));
		
		else
			criteriaPattern = builder.like(root.get(Annuncio_.titolo), "%" + pattern + "%");
			
			
		if(category == null)
			criteriaCategoryState = builder.equal(root.get(Annuncio_.state), StatoAnnuncio.PUBLIC);
		else
			criteriaCategoryState = builder.and(
					builder.equal(root.get(Annuncio_.categoria), category),
					builder.equal(root.get(Annuncio_.state), StatoAnnuncio.PUBLIC));
		
		criteria.select(root).where(builder.and(
					criteriaPattern,
					criteriaCategoryState,
	         		builder.greaterThanOrEqualTo(root.get(Annuncio_.prezzo), minPrice)));

		return this.excecuteSearch();
		

	}
	
	
	
	private List<Annuncio> excecuteSearch() throws InvalidSessionException, InvalidTransactionException, DaoServiceException {
		
		if(session == null)
			throw new InvalidSessionException();
		
		if(transaction == null)
			throw new InvalidTransactionException();
			
		List<Annuncio> result = new ArrayList<Annuncio>();
		
		try {
			result = session.createQuery(criteria).getResultList();
		}
		catch(Exception e) {
			
			if (this.transaction != null)
			{
               this.transaction.rollback();
               throw new DaoServiceException(); 
                               
            }
            e.printStackTrace();
        } 
		
		return result;
		
        
	}
	
	
	
}
