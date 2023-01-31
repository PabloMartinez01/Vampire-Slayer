package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class AddBloodBankCommand extends Command{
	
	private int xPosition;
	private int yPosition;
	private int cost;
	
	private final static String bloodBankName = "bank";
	private final static String bloodBankShortcut = "b";
	private final static String bloodBankDetails = "[b]ank <x> <y> <z>";
	private final static String bloodBankHelp = "add a blood bank with cost z in position x, y.";
	private final static String bloodBankFailed = "Failed to add bloodbank";
	private final static String bloodBankNumberOfArgs = "Incorrect number of arguments for add bloodbank command: " + bloodBankDetails;
	private final static String bloodBankUnvalidArgs = "Unvalid argument for add bloodBank command, number expected: " + bloodBankDetails;
	
	
	public AddBloodBankCommand() {
		super(bloodBankName, bloodBankShortcut, bloodBankDetails, bloodBankHelp);
	}

	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.addBloodBank(xPosition, yPosition, cost);
			game.update();
		}
		catch (CommandExecuteException ex) {
			System.out.println(Command.errorMsg + ex.getMessage());
			throw new CommandExecuteException(Command.errorMsg + bloodBankFailed);
		}
		
		return true;
	}

	public Command parse(String[] commandWords) throws CommandParseException{
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == 4) {
				try {
					xPosition = Integer.parseInt(commandWords[1]);
					yPosition = Integer.parseInt(commandWords[2]);
					cost = Integer.parseInt(commandWords[3]);
					return this;
				}
				catch(NumberFormatException numberException) {
					throw new CommandParseException(Command.errorMsg + bloodBankUnvalidArgs);
				}
			}
			throw new CommandParseException(Command.errorMsg + bloodBankNumberOfArgs);
		}
		return null;
	}
}
