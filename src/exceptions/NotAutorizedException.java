package exceptions;

public class NotAutorizedException extends Exception {

	public NotAutorizedException() {
		super("You don't have enought permission to do this operation");
	}

	public NotAutorizedException(String error) {
		super(error);
	}

	public NotAutorizedException(Throwable cause) {
		super(cause);
	
	}
		

}




	
