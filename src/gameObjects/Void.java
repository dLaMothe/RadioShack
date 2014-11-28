package gameObjects;

import board.*;

public class Void extends SpaceObject{
	
	public Void(Sector sector){
		super(sector);
		label = "";
	}

	@Override
	public void action() throws CollissionException {
		this.sector.setInhabitant(null);
	}
}
