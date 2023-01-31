package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public abstract class GameObject implements IAttack {
	protected int xPosition;
	protected int yPosition;
	protected int health;
	protected Game game;
	protected String symbol;
	
	protected int HARM = 1;
	
	
	public GameObject(int xPosition, int yPosition, int health, Game game, String symbol) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.health = health;
		this.game = game;
		this.symbol = symbol;
	}
	
	public abstract void attack();
	public abstract void advance(); 
	public abstract void die();
	
	public String toString() {
		return symbol + " " + "[" + health + "]";
	}
	
	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
	public boolean isInPosition(int x, int y) {
		return x == xPosition && y == yPosition;
	}
	
	public String seralization() {
		return (symbol + ";" + xPosition + ";" + yPosition + ";" + health);
	}
	
	
	public boolean isAlive() {
		return health > 0;
	}
}
