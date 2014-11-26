package gameObjects;

import board.Sector;

public class TritonMissile 
	extends Weapon {
	
	public TritonMissile(Sector sector, int direction){
		super(sector, direction);
		label = "$";
	}
}
