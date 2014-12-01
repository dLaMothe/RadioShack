package gameObjects;

import board.*;

/**
 * The abstract super class for all types of weapon
 * @author Laurens van Wingerden, Vitalii Egorchatov
 */
public abstract class Weapon 
	extends SpaceObject
	implements Movable{
	
	/**
	 * REQUIRES: The ship's current sector and the direction of the shot.
	 * MODIFIES: This.
	 * EFFECTS: Creates an instance of weapon and puts it into a sector
	 * next to the ship depending on the direction of the shoot;
	 * sets the weapon's initial direction and speed.
	 * @param sector The current ship's sector.
	 * @param direction The direction of the shot.
	 */
	public Weapon(Sector sector, int direction){		
		super(Space.getInstance().
				getQuadrant(sector.getPosition()).
				getNext(sector, direction));
		setSpeed(new int[]{direction, 1});
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
	 * in which the first item is direction and the second is speed.
	 * MODIFIES: This: Velocity.
	 * EFFECTS: Changes its direction or/and speed.
	 * @param vel The necessary weapons's velocity 
	 */
	public void setSpeed(int[] vel){
		velocity[0] = vel[0];
		velocity[1] = vel[1];
	}
	
	/**
	 * EFFECTS: Moves the weapon.
	 */
	@Override
	public void action(){
		this.move();
	}
}
