package exceptions;

public class ItemNotFoundException extends Exception {

	public ItemNotFoundException() {
		super("You've tried loading an item that does not exist. ");
	}

	public ItemNotFoundException(String error) {
		super(error);
	}
	
	public ItemNotFoundException(Throwable cause) {
		super(cause);
	
	}
		

}




	
