package logic;

import gui.GamePanels;
import gameObjects.*;


public class GameEngine {
	
	public GamePanels panels;
	public Ship ship;
	
	
	public GameEngine(GamePanels newPanel) {
		panels = newPanel;
		//ship = new Ship();
	}
	

	
	public void setPower(int type, int value) {
		//ship.adjustPower(type,value);
		panels.powerLabels[type].setText(String.valueOf(value));
		
	}
}
