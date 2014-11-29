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
	
	
	public GameEngine(GamePanels newPanel) {
		panels = newPanel;
		quad = Space.getInstance().getQuadrant(5, 5);
		ship = new Ship(quad.getSector(new Position(5,5)));
		deltaLoop = new DeltaLoop(this);
		populateSidePanel();
		populateQuadrant();
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
		panels.powerAvailLabel.setText(String.valueOf(ship.getPower()));
		panels.antimatterPodsLabel.setText(String.valueOf(ship.getNumAntimatterPods()));
		panels.tritonMislsLabel.setText(String.valueOf(ship.getNumTrtMissiles()));
		panels.quadrantLabel.setText(String.valueOf(quad.getPosition().getRow()) + "-" + String.valueOf(quad.getPosition().getCol()));
		panels.sectorLabel.setText(String.valueOf(ship.getSector().getPosition().getRow()) + "-" + String.valueOf(ship.getSector().getPosition().getCol()));
	}
	
	public void populateQuadrant() {
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
	}
	
	public void setPower(int type, double value) {
		ship.adjustPower(type,value);
		panels.powerLabels[type].setText(String.valueOf(ship.getPower(type)));
		panels.powerAvailLabel.setText(String.valueOf(ship.getPower()));
	}
	
	public void updateResource()
	{
		panels.antimatterPodsLabel.setText(String.valueOf(ship.getNumAntimatterPods()));
		panels.tritonMislsLabel.setText(String.valueOf(ship.getNumTrtMissiles()));
	}
	
	public void invalidCommand()
	{
		panels.invalidCommandLabel.setText("Invalid command");
	}
	
	public void clearInvalidCommand()
	{
		panels.invalidCommandLabel.setText("");
	}
}
