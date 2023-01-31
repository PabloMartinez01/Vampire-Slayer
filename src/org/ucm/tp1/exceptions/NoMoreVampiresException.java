package org.ucm.tp1.exceptions;

public class NoMoreVampiresException extends CommandExecuteException {

	private static final long serialVersionUID = -4521439468055723526L;

	public NoMoreVampiresException() {
		super();
	}
	
	public NoMoreVampiresException(String message) {
		super(message);
	}
	
	public NoMoreVampiresException(String message, Throwable cause) {
		super (message, cause);
	}
	
	public NoMoreVampiresException(Throwable cause) {
		super(cause);
	}
	
}
