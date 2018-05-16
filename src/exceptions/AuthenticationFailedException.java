package exceptions;

public class AuthenticationFailedException extends Exception {

	public AuthenticationFailedException() {
		super();
	}

	public AuthenticationFailedException(String error) {
		super(error);
	}

	public AuthenticationFailedException(Throwable cause) {
		super(cause);
	
	}
		

}
