package logic;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import board.*;
import gui.GamePanels;
import gameObjects.*;
import static settings.Configs.*;

/*
 * 
 * 
 * 
 * NOTE: Round() Method taken from http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
 */

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
		quad = Space.getInstance().getQuadrant(1, 1);
		ship = new Ship(quad.getSector(new Position(0,0)));
		Space.getInstance().initPopulation(ship);
		quad.populate();
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
		panels.powerAvailLabel.setText(String.valueOf(round(ship.getUnusedPower(),2)) + "%");
		panels.totalPowerLabel.setText(String.valueOf(round(ship.getPower(),2)) + "%");
		panels.antimatterPodsLabel.setText(String.valueOf(ship.getNumAntimatterPods()));
		panels.tritonMislsLabel.setText(String.valueOf(ship.getNumTrtMissiles()));
		panels.quadrantLabel.setText(String.valueOf(quad.getPosition().getRow()) + "-" + String.valueOf(quad.getPosition().getCol()));
		panels.sectorLabel.setText(String.valueOf(ship.getSector().getPosition().getRow()) + "-" + String.valueOf(ship.getSector().getPosition().getCol()));
		panels.joviansLeftLabel.setText(String.valueOf(Space.getInstance().TotalJovian));
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
			try {
				quadObjects.get(i).action();
			} catch (CriticalPowerException e){
				panels.invalidCommandLabel.setText("Captian the power levels are going critical");
			}
		}
		
		for(int i= 0; i < weaponObjects.size(); i++){
			if(weaponObjects.get(i) instanceof Maser) {
				for (Sector sector : ((Maser)weaponObjects.get(i)).getTail()){
					panels.grid[sector.getPosition().getCol()][sector.getPosition().getRow()].setText(weaponObjects.get(i).getLabel());
				}
				weaponObjects.get(i).action();
			} else {
				pos = weaponObjects.get(i).getPosition();
				panels.grid[pos.getCol()][pos.getRow()].setText(weaponObjects.get(i).getLabel());
				weaponObjects.get(i).action();
			}
		}
		
		if(ship.getUnusedPower() < MIN_TOTAL_POWER) {
			criticalCounter--;
			panels.invalidCommandLabel.setText("Critical power: Self destruct in " + criticalCounter);
		} else {
			criticalCounter = INITCRITICALTIMER;
		}
		
		if(selfDestructActive) {
			destructCounter--;
			panels.invalidCommandLabel.setText("Self destruct in " + destructCounter);
		}

		
		
		//SHIP ACTION
		ship.action();
		
		
		updateResource();
		panels.sectorLabel.setText(String.valueOf(ship.getSector().getPosition().getRow()) + "-" + String.valueOf(ship.getSector().getPosition().getCol()));
		isActiveSR(ship.getPower(SRSENSOR) > MIN_SYSTEM_POWER);
		isActiveLR(ship.getPower(LRSENSOR) > MIN_SYSTEM_POWER);
		panels.powerAvailLabel.setText(String.valueOf(round(ship.getUnusedPower(),2)) + "%");
		panels.totalPowerLabel.setText(String.valueOf(round(ship.getPower(),2)) + "%");
		panels.starTimeLabel.setText(String.valueOf(starTime));
		panels.joviansLeftLabel.setText(String.valueOf(Space.getInstance().TotalJovian));
		starTime += TIMEINCREMENT;
		updateCondition();
		
		if(criticalCounter == CRITICALMASS || destructCounter == SELFDESTRUCTMAX || ship.getPower() == 0) {
			endGame();
		}
	}
	
	public void setPower(int type, double value){

		try{
			ship.adjustPower(type,value);
		}
		catch (CriticalPowerException e){
			panels.invalidCommandLabel.setText("Captian the power levels are going critical");
		}
		panels.powerLabels[type].setText(String.valueOf(round(ship.getPower(type),2)));
		panels.powerAvailLabel.setText(String.valueOf(round(ship.getUnusedPower(),2)) + "%");
		panels.totalPowerLabel.setText(String.valueOf(round(ship.getPower(),2)) + "%");
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
	}
	
	public void explodePod() {
		ship.detonateAntiPod();
	}
	
	private void endGame() {
		deltaLoop.stopRunning();
		ship.selfDestruct();
		JOptionPane.showMessageDialog(null,"You have lost the game");
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
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
