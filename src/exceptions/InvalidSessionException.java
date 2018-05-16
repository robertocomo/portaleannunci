package exceptions;


public class InvalidSessionException extends Exception {

	public InvalidSessionException() {
		super();
	}

	public InvalidSessionException(String error) {
		super(error);
	}
	
	public InvalidSessionException(Throwable cause) {
		super(cause);
	
	}
		

}

