package org.ucm.tp1.logic;

public class Player {
	private static final int INITIAL_COINS = 50;
	
	
	private int coins;
	
	public Player() {
		coins = INITIAL_COINS;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public boolean enoughCoins(int price){
		return coins >= price;
	}
	
	public void subCoins(int amount) {
		coins -= amount;
	}
	
	public void addPlayerCoins(int coins) {
		this.coins += coins;
	}

}
