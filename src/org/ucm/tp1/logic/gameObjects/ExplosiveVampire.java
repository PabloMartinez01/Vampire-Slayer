package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public class ExplosiveVampire extends Vampire{
	
	private static final String explosiveVampireSymbol = "EV";

	
	public ExplosiveVampire(int xPosition, int yPosition, Game game) {
		super (xPosition, yPosition, game, explosiveVampireSymbol);
	}
	
	public boolean receiveSlayerAttack(int damage) {
		super.receiveSlayerAttack(damage);
		if (health <= 0) {
			doExplosiveAttack();
		}
		return true;
	}
	
	public void doExplosiveAttack() {
		for (int i = this.getXPosition() - 1; i <= this.getXPosition() + 1; i++ ) {
			for (int j = this.getYPosition() - 1; j <= this.getYPosition() + 1; j++) {
				
				IAttack attackable = game.getAttackableInPosition(i, j);
				if (attackable != null && !this.isInPosition(i, j)) {
					attackable.receiveSlayerAttack(1);
				}
			}
		}
	}
}
