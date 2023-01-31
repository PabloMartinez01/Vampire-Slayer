package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public class Dracula extends Vampire{

	private static final String draculaSymbol = "D";
	
	public static boolean DRACULAONBOARD = false;
	
	
	
	public Dracula(int xPosition, int yPosition, Game game) {
		super (xPosition, yPosition, game, draculaSymbol);
		DRACULAONBOARD = true;
	}
	
	public void die() {
		super.die();
		DRACULAONBOARD = false;
	}
	
	public boolean receiveLightFlash() {
		return false;
	}
	
	public void attack() {
		if(isAlive()) {
			IAttack other = game.getAttackableInPosition(xPosition - 1, yPosition);
			if (other != null) other.receiveDraculaAttack(); 
		}
	}
}
