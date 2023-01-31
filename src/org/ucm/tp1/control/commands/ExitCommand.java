package org.ucm.tp1.control.commands;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ExitCommand extends Command{
	
	private static final String exitName = "exit";
	private static final String exitShortcut = "e";
	private static final String exitDetails = "[e]xit";
	private static final String exitHelp = "exit game";
	
	public ExitCommand() {
		super(exitName, exitShortcut, exitDetails, exitHelp);
	}
	
	public boolean execute(Game game)  {
		game.doExit();
		return false;	
	}
	
	public Command parse(String[] commandWords) throws CommandParseException{
		return parseNoParamsCommand(commandWords);
	}
	
}
