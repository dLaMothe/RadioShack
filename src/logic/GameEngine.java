package logic;

import board.Sector;
import gui.GamePanels;
import gameObjects.*;
import static settings.Configs.*;

public class GameEngine {
	
	public GamePanels panels;
	public Ship ship;
	public Sector sec;
	
	
	public GameEngine(GamePanels newPanel) {
		panels = newPanel;
		sec = new Sector();
		ship = new Ship(sec);
		//populatePanel();
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
	}
}
