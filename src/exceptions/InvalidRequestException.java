package exceptions;

public class InvalidRequestException extends Exception {

	public InvalidRequestException() {
		super("You've tried loading an item that does not exist. ");
	}

	public InvalidRequestException(String error) {
		super(error);
	}
	
	public InvalidRequestException(Throwable cause) {
		super(cause);
	
	}
		

}




	
