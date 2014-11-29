package logic;

import board.*;
import gui.GamePanels;
import gameObjects.*;
import static settings.Configs.*;

public class GameEngine {
	
	public GamePanels panels;
	public Ship ship;
	public Space space;
	public Quadrant quad;
	
	
	public GameEngine(GamePanels newPanel) {
		panels = newPanel;
		space = new Space();
		quad = new Quadrant();
		ship = new Ship(quad.getSector(new Position(5,5)));
		populatePanel();
	}
	

	
	public void setPower(int type, double value) {
		ship.adjustPower(type,value);
		panels.powerLabels[type].setText(String.valueOf(ship.getPower(type)));
		panels.powerAvailLabel.setText(String.valueOf(ship.getPower()));
	}
	
	private void populatePanel() {
		for(int i = 0; i < TOTAL_POWERS; i++) {
			panels.powerLabels[i].setText(String.valueOf(ship.getPower(i)));
		}
		panels.powerAvailLabel.setText(String.valueOf(ship.getPower()));
		panels.antimatterPodsLabel.setText(String.valueOf(ship.getNumAntimatterPods()));
		panels.tritonMislsLabel.setText(String.valueOf(ship.getNumTrtMissiles()));
		panels.sectorLabel
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
