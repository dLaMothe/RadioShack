package gameObjects;

import board.Sector;
import board.Space;
import board.Quadrant;

public abstract class Weapon 
	extends SpaceObject
	implements Movable{
	
	public int[] velocity = new int[]{0, 0};
	
	public Weapon(Sector sector, int direction){		
		super(Space.getInstance().
				getQuadrant(sector.getPosition()).
				getNext(sector, direction));
		setSpeed(new int[]{direction, 1});	
	}

	protected Quadrant getCurQuadrant(){
		return Space.getInstance().getQuadrantOfObject(this);
	}

	public void move(){
		sector.setInhabitant(new Void());
		Sector nextSector = getCurQuadrant().getNext(sector, velocity[0]);
		if(nextSector == null || !(nextSector.getInhabitant() instanceof Void)){
			sector.setInhabitant(new Void());
			if(nextSector != null) {
				blowUp(nextSector);
			}
		}
		else nextSector.setInhabitant(this);		
	}
	
	public void setSpeed(int[] velocity){
		this.velocity[0] = velocity[0];
		this.velocity[1] = velocity[1];
	}
	
	private void blowUp(Sector sector){
		sector.setInhabitant(new Void());
	}
}
