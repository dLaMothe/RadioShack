package logic;

import java.util.ArrayList;

import board.*;
import gui.GamePanels;
import gameObjects.*;
import static settings.Configs.*;

public class GameEngine {
	
	public GamePanels panels;
	public Ship ship;
	public Quadrant quad;
	public DeltaLoop deltaLoop;
	private int starTime;
	private int criticalCounter;
	private int destructCounter;
	private boolean selfDestructActive;
	
	private ArrayList<SpaceObject> quadObjects;
	private ArrayList<Weapon> weaponObjects;
	
	
	public GameEngine(GamePanels newPanel) {
		criticalCounter = INITCRITICALTIMER;
		destructCounter = INITDESTRUCTTIMER;
		selfDestructActive = false;
		starTime = INITTIME;
		panels = newPanel;
		quad = Space.getInstance().getQuadrant(5, 5);
		ship = new Ship(quad.getSector(new Position(5,5)));
		quadObjects = ship.getQuadrant().getGeneratedObjects();
		weaponObjects = ship.getQuadrant().getWeaponList();
		deltaLoop = new DeltaLoop(this);
		populateSidePanel();
		update();
	}
	
	public void setVelocity(int speed, int direction) {
		int[] velocity;
		velocity = new int[2];
		velocity[SPEED] = speed;
		velocity[DIRECTION] = direction;
		ship.setSpeed(velocity);
	}
	
	private void populateSidePanel() {
		for(int i = 0; i < TOTAL_POWERS; i++) {
			panels.powerLabels[i].setText(String.valueOf(ship.getPower(i)));
		}
		panels.powerAvailLabel.setText(String.valueOf(ship.getUnusedPower()) + "%");
		panels.totalPowerLabel.setText(String.valueOf(ship.getPower()) + "%");
		panels.antimatterPodsLabel.setText(String.valueOf(ship.getNumAntimatterPods()));
		panels.tritonMislsLabel.setText(String.valueOf(ship.getNumTrtMissiles()));
		panels.quadrantLabel.setText(String.valueOf(quad.getPosition().getRow()) + "-" + String.valueOf(quad.getPosition().getCol()));
		panels.sectorLabel.setText(String.valueOf(ship.getSector().getPosition().getRow()) + "-" + String.valueOf(ship.getSector().getPosition().getCol()));
	}
	
	public void update() {
		Position pos = new Position(0,0);
		if(quad != ship.getQuadrant()) {
			quad = ship.getQuadrant();
			quadObjects = ship.getQuadrant().getGeneratedObjects();
			weaponObjects = ship.getQuadrant().getWeaponList();
			panels.quadrantLabel.setText(String.valueOf(quad.getPosition().getRow()) + "-" + String.valueOf(quad.getPosition().getCol()));
		}
		
		clearBoard();
		for(int i= 0; i < quadObjects.size(); i++){
			pos = quadObjects.get(i).getPosition();
			panels.grid[pos.getCol()][pos.getRow()].setText(quadObjects.get(i).getLabel());
		}
		
		for(int i= 0; i < weaponObjects.size(); i++){
			weaponObjects.get(i).action();
			pos = weaponObjects.get(i).getPosition();
			panels.grid[pos.getCol()][pos.getRow()].setText(weaponObjects.get(i).getLabel());
		}
		
		if(ship.getUnusedPower() < MIN_TOTAL_POWER) {
			criticalCounter--;
			panels.invalidCommandLabel.setText("Critical power: Self destruct in " + criticalCounter);
		}
		
		if(selfDestructActive) {
			destructCounter--;
			panels.invalidCommandLabel.setText("Self destruct in " + destructCounter);
		}
		
		if(criticalCounter == CRITICALMASS || destructCounter == SELFDESTRUCTMAX) {
			endGame();
		}
		
		
		
		ship.action();

		panels.sectorLabel.setText(String.valueOf(ship.getSector().getPosition().getRow()) + "-" + String.valueOf(ship.getSector().getPosition().getCol()));
		isActiveSR(ship.getPower(SRSENSOR) > MIN_SYSTEM_POWER);
		isActiveLR(ship.getPower(LRSENSOR) > MIN_SYSTEM_POWER);
		panels.powerAvailLabel.setText(String.valueOf(ship.getUnusedPower()) + "%");
		panels.totalPowerLabel.setText(String.valueOf(ship.getPower()) + "%");
		panels.starTimeLabel.setText(String.valueOf(starTime));
		starTime += TIMEINCREMENT;
		updateCondition();
	}
	
	public void setPower(int type, double value){

		try{
			ship.adjustPower(type,value);
		}
		catch (CriticalPowerException e){
			criticalCounter = INITCRITICALTIMER;
			panels.invalidCommandLabel.setText("Captian the power levels are going critical");
		}
		panels.powerLabels[type].setText(String.valueOf(ship.getPower(type)));
		panels.powerAvailLabel.setText(String.valueOf(ship.getUnusedPower()) + "%");
		panels.totalPowerLabel.setText(String.valueOf(ship.getPower()) + "%");
	}


	public void selfDestruct(){
		if(selfDestructActive) {
			selfDestructActive = false;
			panels.invalidCommandLabel.setText("Self destruct de-actived");
		} else {
			selfDestructActive=true;
			destructCounter=INITDESTRUCTTIMER;
			panels.invalidCommandLabel.setText("Self destruct active");
		}
	}
	
	private void endGame() {
		ship.selfDestruct();
	}
	
	public void invalidCommand()
	{
		panels.invalidCommandLabel.setText("Captain your an idiot");
	}
	
	public void clearInvalidCommand()
	{
		panels.invalidCommandLabel.setText("");
	}
	
	public void shootWeapon(int type, int direction) {
		ship.shootWeapon(type, direction);
		updateResource();
	}
	
	private void clearBoard(){
		for(int i = 0; i < QUADRANT_SIZE; i++) {
			for(int j = 0; j < QUADRANT_SIZE; j++) {
				panels.grid[i][j].setText(EMPTY);
			}
		}
	}
	
	private void isActiveSR(boolean isActive) {
		for(int i = 0; i < QUADRANT_SIZE; i++) {
			for(int j = 0; j < QUADRANT_SIZE; j++) {
					panels.grid[i][j].setVisible(isActive);
			}
		}
	}

	private void isActiveLR(boolean isActive) {
		for(int i = 0; i < LRARRSIZE; i++) {
			panels.lRSensorLabel[i].setVisible(isActive);
		}
	}

	
	private void updateResource()
	{
		panels.antimatterPodsLabel.setText(String.valueOf(ship.getNumAntimatterPods()));
		panels.tritonMislsLabel.setText(String.valueOf(ship.getNumTrtMissiles()));
	}
	
	private void updateCondition()
	{
		for (int i = CONDITIONLEVELAMOUNT; i >= 0; i--){
			if (ship.getPower() >= CONDITIONLEVELS[i]){
				panels.conditionLabel.setText(CONDITIONSTRINGVALUES[i]);
				panels.conditionLabel.setForeground(CONDITIONCOLOR[i]);
			}
		}
	}
}
