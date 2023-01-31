package org.ucm.tp1.control.commands;

import org.ucm.tp1.exceptions.CommandParseException;

public class CommandGenerator {
	
	private static final String commandGeneratorHelpMsg = "Available commands:\n";
	private static final String unknownCommandMsg = "Unknown command";
	private static Command[] aviableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new AddBloodBankCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SaveCommand(),
			new SerializeCommand()
			
		
	};
	
	public static Command parse(String[] commandWords) throws CommandParseException{
		for(Command c : aviableCommands) {
			Command parsedCommand = c.parse(commandWords);	
			if (parsedCommand != null) {
				return parsedCommand;
			}
		}
		throw new CommandParseException(Command.errorMsg + unknownCommandMsg);
	}
	
	public static String helpCommandsText() {
		String helpMsg = commandGeneratorHelpMsg;
		for (Command command : aviableCommands) {
			helpMsg +=command.helpText(); 
		}
		
		return helpMsg;
	}

	
}
