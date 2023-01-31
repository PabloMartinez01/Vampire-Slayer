package org.ucm.tp1.logic;

import java.util.Random;
import org.ucm.tp1.view.*;
import org.ucm.tp1.exceptions.*;
import org.ucm.tp1.logic.gameObjects.*;



public class Game implements IPrintable{
	private Random rand;
	private Level level;
	private long seed;
	private GameObjectBoard gameBoard;
	private Player player;
	private GamePrinter printer;
	private int cycle;
	private boolean exit;
	
	public static final String vampiresWinMsg = "Vampires win!";
	public static final String playerWinsMsg= "Player wins";
	public static final String exitMsg = "Nobody wins...";
	
	public static final String numberOfCycleMsg = "Number of cycles: ";
	public static final String numberOfCoinsMsg = "Coins: ";
	public static final String remainingVampiresMsg = "Remaining vampires: ";
	public static final String vampiresOnTheBoardMsg = "Vampires on the board: ";
	
	public static final String draculaIsAliveMsg = "Dracula is alive";
	public static final String unvalidPositionMsg = "Unvalid position";
	public static final String positionMsg = "Position ";
	public static final String defenderCostMsg = "Defender cost is ";
	public static final String notEnoughCoinsMsg = ": Not enough coins";
	public static final String noRemainingVampiresMsg = "No more remaining vampires left";
	public static final String draculaAlreadyAliveMsg = "Dracula is already on board";
	public static final String garlicPushCostMsg  = "Garlic push cost is ";
	public static final String lightFlashCostMsg = "Light Flash cost is ";
	
	public static final String cyclesSaveMsg = "Cycles: ";
	public static final String levelSaveMsg = "Level: ";
	public static final String gameObjectListSaveMsg= "Game Object List:";
	public static final String remainingVampiresSaveMsg = "Remaining Vampires: ";	
	public static final String vampiresOnBoardSaveMsg = "Vampires on Board: ";
	
	
	public static final int CYCLE_COINS = 10;
	public static final int SLAYER_PRICE = 50;
	public static final int GARLIC_PUSH_PRICE = 10;
	public static final int LIGHT_FLASH_PRICE = 50;
	public static final double COINS_FREQUENCE = 0.5;
	
	
	public Game(long seed, Level level) {
		this.seed = seed;
		this.level = level;
		printer = new GamePrinter(this, level.getDimX(), level.getDimY());
		startNewGame(); 
		
	}
	
	public void startNewGame() {
		rand = new Random(seed);
		gameBoard = new GameObjectBoard();
		player = new Player();
		cycle = 0;
		exit = false;
		Vampire.REMAINING_VAMPIRES = level.getNumberOfVampires();
		Dracula.DRACULAONBOARD = false;
		
	}
	
	public String toString() {
		return printer.toString();
	}
	
	public int getDimX() {
		return level.getDimX();
	}
	
	public String getPositionToString(int x, int y) {
		return gameBoard.getPositionToString(x, y);
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		return gameBoard.getAttackableInPosition(x, y);
	}
	
	
	public String getInfo() {
		String msg = numberOfCycleMsg + cycle + "\n" +
				   numberOfCoinsMsg + player.getCoins() + "\n" +
				   remainingVampiresMsg + Vampire.REMAINING_VAMPIRES + "\n"+
				   vampiresOnTheBoardMsg + Vampire.CURRENT_VAMPIRES;
		
		if (draculaOnBoard()) return msg + "\n" + draculaIsAliveMsg;
		return msg; 
	
	}
	
	
	public String getEndGameInfo() {
		if (exit) return exitMsg;
		else if (Vampire.VAMPIRE_HAS_ARRIVED) return vampiresWinMsg;
		return playerWinsMsg;	
	}
	
	public boolean vampireProbability() {
		if (remainingVampires()) {
			return rand.nextDouble() < level.getVampireFrequency();
		}
		return false;
	}
		
	public boolean canAddVampire(int x, int y) {
		return  validBoardPosition(x, y) && !usedPosition(x, y);
	}
	
	public boolean canAddSlayer(int x, int y) throws UnvalidPositionException{
		if (!( validSlayerPosition(x, y) && !usedPosition(x, y))) {
			throw new UnvalidPositionException(positionMsg + "(" + x +", " + y +"): " + unvalidPositionMsg);
		}
		return true;
	}
	
	public boolean validSlayerPosition(int x, int y){
		return (validBoardPosition(x, y) && x != level.getDimX() - 1);	
	}
	
	public boolean validBoardPosition(int x, int y){
		return (x >= 0 && x < level.getDimX()) && (y >= 0 && y < level.getDimY());
	}
	
	public boolean usedPosition(int x, int y){
		return gameBoard.usedPosition(x, y);
	}
	
	public boolean draculaOnBoard() {
		return Dracula.DRACULAONBOARD;
	}
	
	public boolean remainingVampires() {
		return Vampire.REMAINING_VAMPIRES > 0;
	}
	
	public boolean gameIsFinished() {
		return exit || Vampire.vampireEnds();
	}
	
	public void addPlayerCoins(int coins) {
		player.addPlayerCoins(coins);
	}
	
	public void addCoins() {
		if (rand.nextFloat() > COINS_FREQUENCE) {
			this.player.addPlayerCoins(CYCLE_COINS);
		}
	}
	
	public boolean addVampire(int x, int y) {
		if (canAddVampire(x, y)) {
			GameObject object = new Vampire(x, y, this); 
			gameBoard.addGameObject(object);
			return true;
		}
		return false;
	}
	
	public boolean addDracula(int x, int y) {
		if (canAddVampire(x, y)) {
			GameObject object = new Dracula(x, y, this); 
			gameBoard.addGameObject(object);
			return true;
		}
		return false;		
	}
	
	public boolean addExplosiveVampire(int x, int y) {
		if (canAddVampire(x, y)) {
			GameObject object = new ExplosiveVampire(x, y, this); 
			gameBoard.addGameObject(object);
			return true;
		}
		return false;
	}
	
	public void addRandomVampire() {
		if (remainingVampires()) {
			if (vampireProbability()) {
				int yPosition = rand.nextInt(level.getDimY());
				addVampire(level.getDimX() - 1,yPosition);	
			}
		}
		
	}
	
	public void addRandomDracula() {
		if (!draculaOnBoard()) {
			if (vampireProbability()) {
				int yPosition = rand.nextInt(level.getDimY());
				addDracula(level.getDimX() - 1,yPosition);
			}
		}
		
	}
	
	public void addRandomExplosiveVampire() {
		if (vampireProbability()) {
			int yPosition = rand.nextInt(level.getDimY());
			addExplosiveVampire(level.getDimX() - 1,yPosition);
		}
		
	}
	
	public void addVampireTypes() {
		addRandomVampire();
		addRandomDracula();
		addRandomExplosiveVampire();
	}
	
	
	public void addSlayer(int x, int y) throws CommandExecuteException {
		if (canAddSlayer(x, y) && player.enoughCoins(SLAYER_PRICE)) {
			GameObject object = new Slayer (x, y, this);
			gameBoard.addGameObject(object);
			player.subCoins(SLAYER_PRICE);
		}
		else {
			throw new NotEnoughCoinsException(defenderCostMsg + SLAYER_PRICE + notEnoughCoinsMsg );
		}
	}
	
	public void addBloodBank(int x, int y, int cost) throws CommandExecuteException {
		if (canAddSlayer(x, y) && player.enoughCoins(cost)) {
			GameObject object = new BloodBank(x, y, cost, this);
			gameBoard.addGameObject(object);
			player.subCoins(cost);
		}
		else {
			throw new NotEnoughCoinsException(defenderCostMsg + cost + notEnoughCoinsMsg);
		}
	}
	
	public void update() { 
		addCoins();
		gameBoard.update();
		addVampireTypes();
		if (!gameIsFinished()) {
			cycle++;
		}
	}
	
	public void addVampireFromCommand(int x, int y) throws CommandExecuteException {
		if (remainingVampires()) {
			if (!addVampire(x, y)) throw new UnvalidPositionException(positionMsg + "(" + x + ", "+ y + "):" + unvalidPositionMsg);	
		}
		else throw new NoMoreVampiresException(noRemainingVampiresMsg);
	}
	
	public void addExplosiveFromCommand(int x, int y) throws CommandExecuteException {
		if (remainingVampires()) {
			if (!addExplosiveVampire(x, y)) throw new UnvalidPositionException(positionMsg + "(" + x + ", "+ y + "):" + unvalidPositionMsg);	
		}
		else throw new NoMoreVampiresException(noRemainingVampiresMsg);
	}
	
	public void addDraculaFromCommand(int x, int y) throws CommandExecuteException {
		if (remainingVampires()) {
			if (!draculaOnBoard()) {
				if (!addDracula(x, y)) throw new UnvalidPositionException(positionMsg + "(" + x + ", "+ y + "):" + unvalidPositionMsg);	
			}
			else throw new DraculaIsAliveException(draculaAlreadyAliveMsg);
			
		}
		else throw new NoMoreVampiresException(noRemainingVampiresMsg);
	}
	
	
	
	public void doGarlicPushAttack() throws NotEnoughCoinsException {
		if (player.enoughCoins(GARLIC_PUSH_PRICE)) {
			gameBoard.doGarlicPushAttack();
			player.subCoins(GARLIC_PUSH_PRICE);
		}	 
		else {
			throw new NotEnoughCoinsException ( garlicPushCostMsg + GARLIC_PUSH_PRICE + notEnoughCoinsMsg);
		}
	}

	public void doLightFlash() throws NotEnoughCoinsException{
		if (player.enoughCoins(LIGHT_FLASH_PRICE)) {
			gameBoard.doLightFlash();
			player.subCoins(LIGHT_FLASH_PRICE);	
		}
		else {
			throw new NotEnoughCoinsException (lightFlashCostMsg + LIGHT_FLASH_PRICE + notEnoughCoinsMsg);
		}
	}
	
	public String doSerialize() {
		return (cyclesSaveMsg + cycle + "\n" + numberOfCoinsMsg + player.getCoins() + "\n" + levelSaveMsg + level.getLevelName().toUpperCase()
		+ "\n" + remainingVampiresSaveMsg + Vampire.REMAINING_VAMPIRES + "\n" + vampiresOnBoardSaveMsg + Vampire.CURRENT_VAMPIRES
		+ "\n\n" +gameObjectListSaveMsg + "\n" + gameBoard.serializeObjects());
	
	}
	
	public void doExit() {
		exit = true;
	}
	
}
