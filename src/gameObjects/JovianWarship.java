package gameObjects;

import java.util.Random;
import settings.Configs;
import board.*;

/**
 * The abstract super class for all types of Jovian
 * @author Laurens van Wingerden, Vitalii Egorchatov
 */
public abstract class JovianWarship
	extends SpaceObject
	implements Movable {
	
	/**
	 * The Jovian's random roaming generator.
	 */
	private int DELTA;
	protected Random random;
	/**
	 * The ship is the target of this Jovian.
	 */
	private Ship ship;
	
	/**
	 * REQUIRES: The Jovian's current sector and the ship as a target.
	 * MODIFIES: This.
	 * EFFECTS: Creates an instance of Jovian and puts it into a sector;
	 * sets the Jovian's initial speed; passes the ship as a target.
	 * @param sector The current Jovian's sector.
	 * @param ship The reference to the ship.
	 */
	public JovianWarship(Sector sector, Ship ship){
		super(sector);
		random = new Random();
		DELTA = Configs.JOVIAN_DELTA;
		setSpeed(new int[]{Configs.NEUTRAL, 1});
		this.ship = ship;
	}
	
	/**
	 * EFFECTS: Moves the Jovian and saps the ship's power.
	 * @throws CriticalPowerException 
	 */
	@Override
	public void action() throws CriticalPowerException{
		if(DELTA == 0) {
			DELTA = Configs.JOVIAN_DELTA;
			move();
			ship.sapPower();
		} else {
			DELTA--;
		}
	}
	
	/**
	 * MODIFIES: This. The Jovian's current sector and direction.
	 * EFFECTS: Changes the Jovian's direction randomly every turn;
	 * puts the Jovian into the next sector if it's empty. 
	 */
	@Override
	public void move(){	

			sector.setInhabitant(null);
			Sector nextSector = null;
			int direction = 0;
			do{
				do{
					direction = 1 + random.nextInt(9);
				}while(direction == 5);
				nextSector = quadrant.getNext(sector, direction);
			}while(nextSector == null || nextSector.getInhabitant() != null);
			setSpeed(new int[]{direction, 1});
			setSector(nextSector);	
	}
	
	/**
	 * REQUIRES: The necessary velocity in the form of an array, 
	 * in which the first item is direction and the second is speed.
	 * MODIFIES: This: Velocity.
	 * EFFECTS: Changes its direction or/and speed.
	 * @param vel The necessary Jovian's velocity 
	 */
	public void setSpeed(int[] vel){
		velocity[0] = vel[0];
		velocity[1] = vel[1];
	}
}
