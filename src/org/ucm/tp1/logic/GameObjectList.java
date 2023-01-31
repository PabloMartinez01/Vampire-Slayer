package org.ucm.tp1.logic;

import java.util.ArrayList;
import org.ucm.tp1.logic.gameObjects.GameObject;
import org.ucm.tp1.logic.gameObjects.IAttack;
import org.ucm.tp1.logic.gameObjects.Vampire;

public class GameObjectList {
	
	private ArrayList<GameObject> gameObjects;
	
	public GameObjectList() {
		gameObjects = new ArrayList<GameObject>();
		Vampire.CURRENT_VAMPIRES = 0;
	}
	
	public String getPositionToString(int x, int y) {
		if (usedPosition(x, y)) {
			return getObjectInPosition(x, y).toString();
		}
		return "";
	}
	
	public GameObject getObjectInPosition(int x, int y) {
		for (GameObject object : gameObjects) {
			if (object.isInPosition(x, y)) {
				return object;
			}
		}
		return null;
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		return getObjectInPosition(x, y);
	}
	
	public boolean usedPosition(int x, int y) {
		return getObjectInPosition(x, y) != null;
	}
	
	public void addGameObject(GameObject newObject) {
		gameObjects.add(newObject);
	}
	
	public void advance() {
		for (GameObject object : gameObjects) {
			object.advance();
		}
	}
	
	public void attack() {
		for (GameObject object : gameObjects) {
			object.attack();
		}
	}
	
	public void doGarlicPushAttack() {
		for (IAttack object : gameObjects) {
			object.receiveGarlicPush();
		}
	}

	public void doLightFlash() {
		for (IAttack object : gameObjects) {
			object.receiveLightFlash();
		}
		removeDeadObjects();
	}
	
	public void update() {
		advance();
		attack();
		removeDeadObjects();
	}
	
	public void removeDeadObjects() {
		ArrayList<GameObject> aux = new ArrayList<GameObject>();
		for (GameObject object : gameObjects) {
			if (!object.isAlive()) {
				object.die();
			}
			else {
				aux.add(object);
			}
		}
		gameObjects = aux;
	}
	
	public String serializeObjects() {
		String seralization = "";
		for (GameObject object : gameObjects) {
			seralization += object.seralization() + "\n";
		}
		return seralization;
	}
	
}
