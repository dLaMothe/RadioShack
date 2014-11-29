package gameObjects;

import board.*;

public class Void extends SpaceObject{
	
	public Void(Sector sector){
		super(sector);
		label = "";
	}

	@Override
	public void action(){
		this.sector.setInhabitant(null);
	}

	@Override
	public void bumped() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bump(SpaceObject sb) {
		// TODO Auto-generated method stub
		
	}
}
