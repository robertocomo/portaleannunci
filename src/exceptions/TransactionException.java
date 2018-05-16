package exceptions;

public class TransactionException extends Exception {

	public TransactionException() {
		super();
	}

	public TransactionException(String error) {
		super(error);
	}
	
	public TransactionException(Throwable cause) {
		super(cause);
	
	}
		

}




	
