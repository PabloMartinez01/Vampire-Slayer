package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SuperCoinsCommand extends Command{
	
	private final static int coins = 1000;
	private final static String coinsName = "coins";
	private final static String coinsShortcut = "c";
	private final static String coinsDetails ="[c]oins";
	private final static String coinsHelp = "add " + coins + " coins";
	
	public SuperCoinsCommand() {
		super(coinsName, coinsShortcut, coinsDetails, coinsHelp);
	}
	
	public boolean execute(Game game) {
		game.addPlayerCoins(coins);
		return true;	
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
