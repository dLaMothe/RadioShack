package logic;

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
	
	
	public GameEngine(GamePanels newPanel) {
		starTime = INITTIME;
		panels = newPanel;
		quad = Space.getInstance().getQuadrant(5, 5);
		ship = new Ship(quad.getSector(new Position(5,5)));
		deltaLoop = new DeltaLoop(this);
		populateSidePanel();
		update();
	}
	
	public void setVelocity(int speed, int direction) {
		int[] velocity;
		velocity = new int[2];
		velocity[0] = speed;
		velocity[1] = direction;
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
		for(int i = 0; i < QUADRANT_SIZE; i++) {
			for(int j = 0; j < QUADRANT_SIZE; j++) {
				pos.setPositionAt(i, j);
				if(ship.getPosition().equals(pos)) {
					panels.grid[i][j].setText(SHIP);
				} else {
					panels.grid[i][j].setText(EMPTY);
				}
			}
		}
		ship.action();
		isActiveSR(ship.getPower(SRSENSOR) > MIN_SYSTEM_POWER);
		isActiveLR(ship.getPower(LRSENSOR) > MIN_SYSTEM_POWER);
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
			criticalPowerLevelCommand();
		}
		panels.powerLabels[type].setText(String.valueOf(ship.getPower(type)));
		panels.powerAvailLabel.setText(String.valueOf(ship.getUnusedPower()) + "%");
		panels.totalPowerLabel.setText(String.valueOf(ship.getPower()) + "%");
	}

	public void selfDestruct(){
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
	
	private void criticalPowerLevelCommand()
	{
		panels.invalidCommandLabel.setText("Captian the power levels are going critical");
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
