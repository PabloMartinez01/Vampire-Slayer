package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SerializeCommand extends Command{
	
	private static final String serializeName = "serialize";
	private static final String serializeShortcut = "z";
	private static final String serializeDetails = "Seriali[z]e";
	private static final String serializeHelp = "Serializes the board.";
	
	public SerializeCommand () {
		super(serializeName, serializeShortcut, serializeDetails, serializeHelp);
	}

	public boolean execute(Game game)  {
		System.out.println(game.doSerialize());
		return false;	
	}
	
	public Command parse(String[] commandWords) throws CommandParseException{
		return parseNoParamsCommand(commandWords);
	}
}
