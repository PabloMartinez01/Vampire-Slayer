package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.logic.Game;


public class LightFlashCommand extends Command{
	
	private static final String lightFlashName = "light";
	private static final String lightFlashShortcut = "l";
	private static final String lightFlashDetails = "[l]ight";
	private static final String lightFlashHelp = "kills all the vampires";
	private static final String lightFlashFailedMsg = "Failed to light flash";

	public LightFlashCommand() {
		super(lightFlashName, lightFlashShortcut, lightFlashDetails, lightFlashHelp);
	}

	
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.doLightFlash();
			game.update();
		}
		catch (NotEnoughCoinsException ex) {
			System.out.println(Command.errorMsg + ex.getMessage());
			throw new CommandExecuteException(Command.errorMsg + lightFlashFailedMsg);
		}
		
		return true;
	}

	public Command parse(String[] commandWords) throws CommandParseException{
		return parseNoParamsCommand(commandWords);
	}

}
