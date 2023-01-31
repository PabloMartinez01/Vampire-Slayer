package org.ucm.tp1.control.commands;


import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class AddCommand extends Command{
	private int x;
	private int y;
	
	private static final String addSlayerName = "add";
	private static final String addSlayerShortcut = "a";
	private static final String addSlayerDetails = "[a]dd <x> <y>" ;
	private static final String addSlayerHelp = "add a slayer in position x, y";
	private static final String addSlayerNumberOfArgsMsg = "Incorrect number of arguments for add command: " + addSlayerDetails;
	private static final String addSlayerUnvalidArgsMsg = "Unvalid argument for add slayer command, number expected: "+ addSlayerDetails;
	private static final String addSlayerFailedMsg = "Failed to add slayer";
	
	
	public AddCommand() {
		super (addSlayerName, addSlayerShortcut, addSlayerDetails, addSlayerHelp);
	}

	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.addSlayer(x, y);
			game.update();
		}
		catch (CommandExecuteException ex) {
			System.out.println(Command.errorMsg + ex.getMessage());
			throw new CommandExecuteException(Command.errorMsg + addSlayerFailedMsg);
		}
		return true;	
			
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == 3) {
				try {
					x = Integer.parseInt(commandWords[1]);
					y = Integer.parseInt(commandWords[2]);
					return this;
				}
				catch(NumberFormatException e){
					throw new CommandParseException(Command.errorMsg + addSlayerUnvalidArgsMsg);
				}
				
			}
			throw new CommandParseException(Command.errorMsg + addSlayerNumberOfArgsMsg);
		}
		return null;
	}

}
