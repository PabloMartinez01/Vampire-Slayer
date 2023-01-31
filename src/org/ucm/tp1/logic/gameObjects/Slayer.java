package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject{
	
	private static final int initialHealth = 3;
	private static final String slayerSymbol = "S";
	
	public Slayer(int xPosition, int yPosition, Game game) {
		super(xPosition, yPosition, initialHealth, game, slayerSymbol);
	}
	
	public void advance() {};
	public void die() {};
	
	public void attack() {
		int position = 1;
		if(isAlive()) {
			while(xPosition + position < game.getDimX()) {
				IAttack other = game.getAttackableInPosition(xPosition + position, yPosition);
				if (other != null) {
					if (other.receiveSlayerAttack(HARM)) break;	
				}
				position++;
			}
			
			
		}
	}
	
	public boolean receiveVampireAttack(int damage) {
		health--;
		return true;
	}
	
	public boolean receiveDraculaAttack() {
		health = 0;
		return true;
	}

	
	
	
	
	
	
	
}