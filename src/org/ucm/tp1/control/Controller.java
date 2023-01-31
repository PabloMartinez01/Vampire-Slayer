package org.ucm.tp1.control;

import java.util.Scanner;

import org.ucm.tp1.control.commands.Command;
import org.ucm.tp1.control.commands.CommandGenerator;
import org.ucm.tp1.exceptions.GameException;
import org.ucm.tp1.logic.Game;


public class Controller {

	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");
	
	

    private Game game;
    private Scanner scanner;
  
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
    	 System.out.println(game);
   }
    
    
    
    public void run() {
    	boolean refreshDisplay = true;

	    while (!game.gameIsFinished()){
	    		
	    	if (refreshDisplay) printGame();
        		 refreshDisplay = false;
        		 
			  System.out.println(prompt);	
			  String s = scanner.nextLine();
			  String[] parameters = s.toLowerCase().trim().split(" ");
			  System.out.println("[DEBUG] Executing: " + s);
			try {
				  Command command = CommandGenerator.parse(parameters);
				  refreshDisplay = command.execute(game);
			}
			catch (GameException ex) {
				  System.out.format(ex.getMessage() + " %n %n");
			}
		}
	    
        	if (refreshDisplay) printGame();
    		System.out.println ("[GAME OVER] " + game.getEndGameInfo());

    }
   

}

