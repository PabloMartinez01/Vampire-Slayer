package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends Command{
	
	private static final String garlicPushName = "garlic";
	private static final String garlicPushShortcut = "g";
	private static final String garlicPushDetails = "[g]arlic";
	private static final String garlicPushHelp = "pushes back vampires";
	private static final String garlicPushFailedMsg = "Failed to garlic push";

	public GarlicPushCommand() {
		super (garlicPushName, garlicPushShortcut, garlicPushDetails, garlicPushHelp);
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.doGarlicPushAttack();
			game.update();
		}
		catch(NotEnoughCoinsException ex) {
			System.out.println(Command.errorMsg + ex.getMessage());
			throw new CommandExecuteException(Command.errorMsg + garlicPushFailedMsg);
		}
		
		return true;
	}


	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
