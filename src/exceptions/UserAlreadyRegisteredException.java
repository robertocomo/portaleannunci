package exceptions;

public class UserAlreadyRegisteredException extends Exception {

	public UserAlreadyRegisteredException() {
		super("This email has been already used");
	}

	public UserAlreadyRegisteredException(String error) {
		super(error);
	}

	public UserAlreadyRegisteredException(Throwable cause) {
		super(cause);
	
	}
		

}
