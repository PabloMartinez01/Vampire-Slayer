package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class HelpCommand extends Command{
	
	private static final String helpName = "help";
	private static final String helpShortcut = "h";
	private static final String helpDetails = "[h]elp";
	private static final String helpHelp = "show this help";
	
	public HelpCommand() {
		super (helpName, helpShortcut, helpDetails, helpHelp);
	}

	public boolean execute(Game game) {
		System.out.println(CommandGenerator.helpCommandsText());
		return false;
	}

	public Command parse(String[] commandWords) throws CommandParseException{
		return parseNoParamsCommand(commandWords);
	}
	
	
}
