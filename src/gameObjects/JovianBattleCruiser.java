package gameObjects;

import settings.Configs;
import board.*;

/**
 * @author sukhenka
 *
 */
public class JovianBattleCruiser 
	extends JovianWarship{

	public JovianBattleCruiser(Sector sec) {
		super(sec);
		label = Configs.JBC;
	}

	/**
	 * EFFECTS: Moves the Jovian Battle Cruiser and makes it sap.
	 */
	@Override
	public void action(){
		this.move();
	}
	
	/**
	 * EFFECTS: Retrieves the weapon's current quadrant
	 * @return The weapon's current quadrant
	 */
	protected Quadrant getCurQuadrant(){
		return Space.getInstance().getQuadrantOfObject(this);
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

	@Override
	public void bumped() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bump(SpaceObject sb) {
		// TODO Auto-generated method stub
		
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

}
