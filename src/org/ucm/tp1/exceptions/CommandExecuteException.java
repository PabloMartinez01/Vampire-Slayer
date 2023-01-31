package org.ucm.tp1.exceptions;

public class CommandExecuteException extends GameException{

	private static final long serialVersionUID = 7271186352401310135L;
	
	public CommandExecuteException() {
		super();
	}
	
	public CommandExecuteException(String message) {
		super(message);
	}
	
	public CommandExecuteException(String message, Throwable cause) {
		super (message, cause);
	}
	
	public CommandExecuteException(Throwable cause) {
		super(cause);
	}

}
