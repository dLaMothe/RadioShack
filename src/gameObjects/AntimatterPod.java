package gameObjects;

import java.util.Random;
import board.*;

/**
* The Antimatter Pod weapon which can move straight and change its direction
* after running into any other object or the quadrant border.
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
	 * and initial direction; makes the antimatter pod detectable; initializes the random
	 * roaming generator.
	 * @param sector The current ship's sector.
	 * @param direction The direction of the shoot.
	 */
	public AntimatterPod(Sector sector, int direction){
		super(sector, direction);
		label = "#";
		random = new Random();
	}
	
	/**
	 * MODIFIES: This. The antimatter pod's current sector and direction.
	 * EFFECTS: Checks what is in the next sector; changes the antimatter pod's direction
	 * if it has reached the quadrant border or any other object; puts the antimatter 
	 * pod into the next sector if it contains Void. 
	 */
	@Override
	public void move(){				
		new Void(sector);
		Quadrant quadrant = getCurQuadrant();
		Sector nextSector = quadrant.getNext(sector, velocity[0]);		
		if(nextSector == null || !(nextSector.getInhabitant() instanceof Void)){
			int direction = 0;
			do{
				do{
					direction = 1 + random.nextInt(9);
				}while(direction == 5);
				nextSector = quadrant.getNext(sector, direction);
			}while(nextSector == null || !(nextSector.getInhabitant() instanceof Void));
			setSpeed(new int[]{direction, 1});
		}	
		setSector(nextSector);	
	}
	
	/**
	 * MODIFIES: This. The flag of the detectability.
	 * EFFECTS: Blows up all sectors around the antimatter pod on a command from the ship 
	 * and makes the antimatter pod undetectable.
	 */
	public void detonate(){	
		Quadrant quadrant = getCurQuadrant(); 
		for(int i = 1; i < 10; i++){			
			Sector nextSector  = quadrant.getNext(sector, i);
			if(nextSector != null){
				blowUp(nextSector);
			}
		}
		setDetectable(false);
	}

	@Override
	public void action(){
		this.move();
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
