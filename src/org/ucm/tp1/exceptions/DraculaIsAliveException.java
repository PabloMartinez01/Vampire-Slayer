package org.ucm.tp1.exceptions;

public class DraculaIsAliveException extends CommandExecuteException{

	private static final long serialVersionUID = 1507897704397728471L;

	public DraculaIsAliveException() {
		super();
	}
	
	public DraculaIsAliveException(String message) {
		super(message);
	}
	
	public DraculaIsAliveException(String message, Throwable cause) {
		super (message, cause);
	}
	
	public DraculaIsAliveException(Throwable cause) {
		super(cause);
	}
	
}
