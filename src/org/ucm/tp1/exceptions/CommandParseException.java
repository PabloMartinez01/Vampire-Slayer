package org.ucm.tp1.exceptions;

public class CommandParseException extends GameException{

	private static final long serialVersionUID = -7031618854197396432L;
	
	public CommandParseException() {
		super();
	}
	
	public CommandParseException(String message) {
		super(message);
	}
	
	public CommandParseException(String message, Throwable cause) {
		super (message, cause);
	}
	
	public CommandParseException(Throwable cause) {
		super(cause);
	}

}
