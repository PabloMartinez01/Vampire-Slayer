package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class UpdateCommand extends Command{
	
	private static final String updateName = "none";
	private static final String updateShortcut = "n";
	private static final String updateDetails = "[n]one | []";
	private static final String updateHelp = "update";

	public UpdateCommand() {
		super (updateName, updateShortcut, updateDetails, updateHelp);
	}
	
	public boolean execute(Game game) {
		game.update();
		return true;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException{
		if (commandWords.length == 1 && commandWords[0].equals("")) {
			return this;
		}
		return parseNoParamsCommand(commandWords);
	}
	
	
}
