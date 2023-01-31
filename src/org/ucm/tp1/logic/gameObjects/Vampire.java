package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public class Vampire extends GameObject{

	private int turn;
	
	private static final int initialHealth = 5;
	private static final int advanceSpeed = 2;
	
	private static final String vampireSymbol = "V";
	
	public static int CURRENT_VAMPIRES;
	public static int REMAINING_VAMPIRES;
	public static boolean VAMPIRE_HAS_ARRIVED = false;
	
	
	
	public Vampire(int xPosition, int yPosition, Game game) {
		super(xPosition, yPosition, initialHealth, game, vampireSymbol);
		initializeVampire();
	}
	
	public Vampire(int xPosition, int yPosition, Game game, String id) { 
		super(xPosition, yPosition, initialHealth, game, id);
		initializeVampire();
	}
	
	public void initializeVampire() {
		turn = advanceSpeed - 1;
		CURRENT_VAMPIRES++;
		REMAINING_VAMPIRES--;
	}
		
	public boolean vampireCanAdvance() {
		return xPosition > -1 && turnToAdvance();
	}
	
	public void hasArrived() {
		if (xPosition == -1) {
			VAMPIRE_HAS_ARRIVED = true;
		}
	}
	public static boolean vampireEnds() {
		return VAMPIRE_HAS_ARRIVED || (CURRENT_VAMPIRES == 0 && REMAINING_VAMPIRES == 0);
	}
	
	public void advance() {
		if (vampireCanAdvance() && !game.usedPosition(xPosition - 1, yPosition)) {
			xPosition--;
			hasArrived();
		}
	}
	
	
	public void attack() {
		if(isAlive()) {
			IAttack other = game.getAttackableInPosition(xPosition - 1, yPosition);
			if (other != null) other.receiveVampireAttack(HARM); 
		}
	}
	
	public boolean receiveSlayerAttack(int damage) {
		health -= damage;
		return true;
	}
	
	public boolean receiveGarlicPush() {
		if (turn == 0) {
			turn++;
		}
		if (!game.usedPosition(xPosition + 1, yPosition)) {
			xPosition++;
			if (xPosition >= game.getDimX()) {
				health = 0;
			}
			return true;
		}
		return false;
	}
	
	public boolean receiveLightFlash() {
		health = 0;
		return true;
	}
	
	public void die() {
		CURRENT_VAMPIRES--;
	}
	
	public boolean turnToAdvance() {
		turn--;
		if (game.usedPosition(xPosition-1, yPosition)) {
			return false;
		}
		if (turn < 0) {
			turn = advanceSpeed - 1;
			return true;
		}
		return false;	
	}
	
	public String seralization() {
		return super.seralization() + ";" + turn;
	}
	

}
