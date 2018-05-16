package exceptions;


public class DaoServiceException extends Exception {

	public DaoServiceException() {
		super();
	}

	public DaoServiceException(String error) {
		super(error);
	}

	public DaoServiceException(Throwable cause) {
		super(cause);
	
	}
		

}
