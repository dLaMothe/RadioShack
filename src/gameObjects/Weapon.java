package gameObjects;

import board.*;

/**
 * The abstract super class for all types of weapon
 */
public abstract class Weapon 
	extends SpaceObject
	implements Movable{
	
	/**
	 * The flag to indicate whether the weapon hit another object.
	 */
	protected Boolean hit = false;
	
	/**
	 * REQUIRES: The ship's current sector and the direction of the shoot.
	 * MODIFIES: This.
	 * EFFECTS: Creates an instance of weapon and puts it into a sector
	 * next to the ship depending on the direction of the shoot;
	 * sets the weapon's initial direction; makes the weapon detectable.
	 * @param sector The current ship's sector.
	 * @param direction The direction of the shoot.
	 */
	public Weapon(Sector sector, int direction){		
		super(Space.getInstance().
				getQuadrant(sector.getPosition()).
				getNext(sector, direction));
		setSpeed(new int[]{direction, 1});
		setDetectable(true);
	}

	/**
	 * EFFECTS: Retrieves the weapon's current quadrant
	 * @return The weapon's current quadrant
	 */
	protected Quadrant getCurQuadrant(){
		return Space.getInstance().getQuadrantOfObject(this);
	}
	
	/**
	 * REQUIRES: The necessary velocity in the form of an array, 
	 * in which the first item is direction and the second is speed
	 * MODIFIES: This.
	 * EFFECTS: Changes its direction or/and speed.
	 * @param vel The necessary weapons's velocity 
	 */
	public void setSpeed(int[] vel){
		velocity[0] = vel[0];
		velocity[1] = vel[1];
	}
	
	/**
	 * REQUIRES: The valid sector to be blown up.
	 * EFFECTS: 'Blows up' the sector; actually, put a Void into it. 
	 * @param sector The sector to be blown up.
	 */
	protected void blowUp(Sector sector){
		new Void(sector);
	}
}
