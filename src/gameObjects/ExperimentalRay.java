package gameObjects;

import java.util.Random;

/**
* The Experimental Ray weapon which has 50% chance to damage the ship more than enemies
* and vice versa.
* @author Laurens van Wingerden, Vitalii Egorchatov
*/
public class ExperimentalRay 
	extends Weapon{
	
	/**
	 * The ExperimentalRay's random damage chance generator.
	 */
	private Random random;
	
	private Ship ship;
	
	/**
	 * MODIFIES: This.
	 * EFFECTS: Creates an instance of an Experimental Ray; 
	 * initializes the random damage chance generator.
	 * @param sector The current ship's sector.
	 * @param direction The direction of the shoot.
	 */
	public ExperimentalRay(Ship ship){
		random = new Random();
		this.ship = ship;
	}
	
	/**
	 * EFFECTS: Calculates random 50/50 chance to damage the ship more than enemies
	 * and vice versa; in the first case, the ship loses 50% of the total power;
	 * in the second case, all the Jovians in the quadrant are destroyed.
	 */
	public void detonate(){	
		//If it's 0, the ship gets hurt
		if(random.nextInt(2) == 0){
			//ship
		} 
		//If it's 1, all the Jovians get killed
		else {
			for(SpaceObject object : getCurQuadrant().getGeneratedObjects()){
				if(object instanceof JovianWarship){
					
				}
			}
		}
	}
	
	public void move(){
		
	}
	
	/**
	 * PURPOSE: Does nothing because it doesn't move.
	 * @param object The object that collided with this.
	 */
	@Override
	public void bump(SpaceObject object) {

	}
	
	/**
	 * PURPOSE: Does nothing because it has no position.
	 * @param object The object that collided with this.
	 */
	@Override
	public void bumped(SpaceObject object) {
		
	}
}
