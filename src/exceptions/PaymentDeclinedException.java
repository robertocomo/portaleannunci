package exceptions;

public class PaymentDeclinedException extends Exception {

	public PaymentDeclinedException() {
		super("You've tried loading an item that does not exist. ");
	}

	public PaymentDeclinedException(String error) {
		super(error);
	}
	
	public PaymentDeclinedException(Throwable cause) {
		super(cause);
	
	}
		

}




	
