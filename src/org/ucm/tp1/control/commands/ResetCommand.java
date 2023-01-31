package org.ucm.tp1.control.commands;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ResetCommand extends Command{
	
	private static final String resetName = "reset";
	private static final String resetShortcut = "r";
	private static final String resetDetails = "[r]eset";
	private static final String resetHelp = "reset game";

	public ResetCommand() {
		super (resetName, resetShortcut, resetDetails, resetHelp);	
	}

	public boolean execute(Game game) {
		game.startNewGame();
		return true;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
	
}
