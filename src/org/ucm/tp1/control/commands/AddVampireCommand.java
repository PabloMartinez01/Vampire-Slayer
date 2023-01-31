package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class AddVampireCommand extends Command {
	
	private int xPosition;
	private int yPosition;
	private String type;
	
	private static final String addVampireName = "vampire";
	private static final String addVampireShortcut = "v";
	private static final String addVampireDetails = "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}";
	private static final String addVampireHelp = " add a vampire in position x, y";
	private static final String addVampireNumberOfArgsMsg = "Incorrect number of arguments for add vampire command: " + addVampireDetails;
	private static final String addVampireUnvalidArgsMsg = "Unvalid argument for add vampire command, number expected: " + addVampireDetails;
	private static final String addVampireUnvalidTypeMsg = "Unvalid type: " + addVampireDetails;
	private static final String addVampireFailedMsg = "Failed to add this vampire";

	
	public AddVampireCommand() {
		super (addVampireName, addVampireShortcut, addVampireDetails, addVampireHelp);
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			switch (type) {
			case "v":
				game.addVampireFromCommand(xPosition, yPosition); break;
			case "d":
				game.addDraculaFromCommand(xPosition, yPosition); break;
			case "e":
				game.addExplosiveFromCommand(xPosition, yPosition); break;
			default:
				//añadir excepcion
				
			}
		}
		catch (CommandExecuteException ex) {
			System.out.println(Command.errorMsg + ex.getMessage());
			throw new CommandExecuteException (Command.errorMsg + addVampireFailedMsg);
		}
		
		return true;
	}

	public Command parse(String[] commandWords) throws CommandParseException{
		if (matchCommandName(commandWords[0])){
			try {
				if (commandWords.length == 3) {
					type = "v";
					xPosition = Integer.parseInt(commandWords[1]);
					yPosition = Integer.parseInt(commandWords[2]);
					return this;
				}
				else if (commandWords.length == 4){
					type = commandWords[1];
					if (!type.equals("d") && !type.equals("e")){
						throw new CommandParseException(Command.errorMsg + addVampireUnvalidTypeMsg);
					}
					xPosition = Integer.parseInt(commandWords[2]);
					yPosition = Integer.parseInt(commandWords[3]);
					return this;
				}
				throw new CommandParseException (Command.errorMsg + addVampireNumberOfArgsMsg);
			}
			catch (NumberFormatException ex) {
				throw new CommandParseException(Command.errorMsg + addVampireUnvalidArgsMsg);
			}
		}
		return null;
	}
	
	
	
	
}
