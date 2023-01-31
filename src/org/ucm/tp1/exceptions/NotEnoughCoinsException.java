package org.ucm.tp1.exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {

	private static final long serialVersionUID = -8904705273390869251L;
	
	public NotEnoughCoinsException() {
		super();
	}
	
	public NotEnoughCoinsException(String message) {
		super(message);
	}
	
	public NotEnoughCoinsException(String message, Throwable cause) {
		super (message, cause);
	}
	
	public NotEnoughCoinsException(Throwable cause) {
		super(cause);
	}

}
