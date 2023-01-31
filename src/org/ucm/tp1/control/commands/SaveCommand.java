package org.ucm.tp1.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.ucm.tp1.BuffyTheVampireSlayer;
import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SaveCommand extends Command{
	
	private final static String saveName = "save";
	private final static String saveShortcut = "s";
	private final static String saveDetails = "[S]ave <filename>";
	private final static String saveHelp = "Save the state of the game to a file.";
	private final static String saveExtension = ".dat.";
	private final static String saveNumberOfArgsMsg = "Incorrect number of arguments for save command: " + saveDetails;
	private final static String saveFailedMsg = "Failed to save";
	private final static String saveSuccessful = "Game successfully saved to file ";
	
	
	private String filename;

	public SaveCommand() {
		super(saveName, saveShortcut, saveDetails, saveHelp);
	}


	public boolean execute(Game game) throws CommandExecuteException {
		try (BufferedWriter savefile = new BufferedWriter (new FileWriter(filename))){
			savefile.write(BuffyTheVampireSlayer.welcomeMsg + "\n"+  game.doSerialize());
			System.out.println(saveSuccessful + filename);
		}
		catch (IOException ex) {
			System.out.println(Command.errorMsg + saveFailedMsg);
		}
		
		return false;
	}

	
	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length != 2) {
				throw new CommandParseException(saveNumberOfArgsMsg);
			}
			filename = commandWords[1] + saveExtension;
			return this;
		}
		return null;
	}

}
