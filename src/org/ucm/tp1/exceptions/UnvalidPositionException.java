package org.ucm.tp1.exceptions;

public class UnvalidPositionException extends CommandExecuteException {


	private static final long serialVersionUID = -3830046222464109625L;

	public UnvalidPositionException() {
		super();
	}
	
	public UnvalidPositionException(String message) {
		super(message);
	}
	
	public UnvalidPositionException(String message, Throwable cause) {
		super (message, cause);
	}
	
	public UnvalidPositionException(Throwable cause) {
		super(cause);
	}
}
