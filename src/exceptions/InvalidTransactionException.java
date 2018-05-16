package exceptions;

public class InvalidTransactionException extends Exception {

	public InvalidTransactionException() {
		super();
	}

	public InvalidTransactionException(String error) {
		super(error);
	}
	
	public InvalidTransactionException(Throwable cause) {
		super(cause);
	
	}
		

}
