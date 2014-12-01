package gameObjects;

import java.util.Random;
import board.*;

import settings.Configs;
/**
* The Antimatter Pod weapon which can move straight and change its direction
* after running into any other object or the quadrant border.
* @author Laurens van Wingerden, Vitalii Egorchatov
*/
public class AntimatterPod 
	extends Weapon{
	
	/**
	 * The antimatter pod's random roaming generator.
	 */
	private Random random;
	
	/**
	 * REQUIRES: The ship's current sector and the direction of the shoot.
	 * MODIFIES: This.
	 * EFFECTS: Creates an instance of a antimatter pod and puts it into a sector
	 * next to the ship depending on the direction of the shoot; sets the antimatter pod's label
	 * and initial direction and speed; initializes the random roaming generator.
	 * @param sector The current ship's sector.
	 * @param direction The direction of the shoot.
	 */
	public AntimatterPod(Sector sector, int direction){
		super(sector, direction);
		label = Configs.ANTM_POD;
		random = new Random();
	}
	
	/**
	 * MODIFIES: This. The antimatter pod's current sector and direction.
	 * EFFECTS: Checks what is in the next sector; changes the antimatter pod's direction
	 * if it has reached the quadrant border or any other object; puts the antimatter 
	 * pod into the next sector if it's empty. 
	 */
	@Override
	public void move(){				
		sector.setInhabitant(null);
		Quadrant quadrant = getCurQuadrant();
		Sector nextSector = quadrant.getNext(sector, velocity[0]);		
		if(nextSector == null || nextSector.getInhabitant() != null){
			int direction = 0;
			do{
				do{
					direction = 1 + random.nextInt(9);
				}while(direction == 5);
				nextSector = quadrant.getNext(sector, direction);
			}while(nextSector == null || nextSector.getInhabitant() != null);
			setSpeed(new int[]{direction, 1});
		}	
		setSector(nextSector);	
	}
	
	/**
	 * EFFECTS: Blows up all sectors around the antimatter pod on a command from the ship 
	 * and destroys the antimatter pod.
	 */
	public void detonate(){	
		Quadrant quadrant = getCurQuadrant(); 
		for(int i = 1; i < 10; i++){			
			Sector nextSector  = quadrant.getNext(sector, i);
			if(nextSector != null){
				SpaceObject object = nextSector.getInhabitant();
				if(object != null){
					object.bump(this);
				}
			}
		}
	}
	
	/**
	 * PURPOSE: This has been hit by other SpaceObject so it bounces itself
	 * EFFECTS: Moves this to any other random sector.
	 */
	@Override
	public void bump(SpaceObject object) {
		if(object != this) {
			this.move();
		}
		selfDestruct();
	}
	
	/**
	 * PURPOSE: Nothing happens because this has bounced away.
	 * @param object The object that collided with this.
	 */
	@Override
	public void bumped(SpaceObject object) {
		
	}
}
