package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public class BloodBank extends GameObject {
	
	private final static String bloodBankSymbol = "B";
	
	private int cost;
	
	public BloodBank(int xPosition, int yPosition, int amount, Game game) {
		super(xPosition, yPosition, 1, game, bloodBankSymbol);
		cost  = amount;
	}
	
	public String toString() {
		return symbol + " " + "[" + cost + "]";
	}

	
	public void attack() {}
	public void die() {}
	
	public boolean receiveVampireAttack(int damage) {
		health = 0;
		cost = 0;
		return true;
	}
	
	public boolean receiveDraculaAttack() {
		health = 0;
		cost = 0;
		return true;
	}
	
	public void advance() {
		int i = (int) (cost * 0.1);
		game.addPlayerCoins(i);
	}
	
	public String seralization() {
		return super.seralization() + ";" + cost;
	}
	
}
