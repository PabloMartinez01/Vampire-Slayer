package org.ucm.tp1.logic;

import org.ucm.tp1.logic.gameObjects.GameObject;
import org.ucm.tp1.logic.gameObjects.IAttack;


public class GameObjectBoard {
	
	private GameObjectList gameList;
	
	public GameObjectBoard() {
		gameList = new GameObjectList();
	}
	
	public String getPositionToString(int x, int y) {
		return gameList.getPositionToString(x, y);
	}
	
	public GameObject getObjectInPosition(int x, int y) {
		return gameList.getObjectInPosition(x, y);
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		return gameList.getAttackableInPosition(x, y);
	}
	
	public boolean usedPosition(int x, int y) {
		return gameList.usedPosition(x, y);
	}
	
	public void addGameObject(GameObject newObject) {
		gameList.addGameObject(newObject);
	}

	public void advance() {
		gameList.advance();
	}
	
	public void attack() {
		gameList.attack();
	}

	public void doGarlicPushAttack() {
		gameList.doGarlicPushAttack();
	}

	public void doLightFlash() {
		gameList.doLightFlash();
	}
	
	public void update() {
		gameList.update();
	}
	
	public void removeDeadObjects() {
		gameList.removeDeadObjects();
	}
	
	public String serializeObjects() {
		return gameList.serializeObjects();
	}
		
	
	

	
}
