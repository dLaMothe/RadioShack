package gameObjects;

import board.Sector;

/**
 * The Triton Missile weapon which can move straight and whether hit an object or fly away.
 */
public class TritonMissile 
	extends Weapon {
	
	/**
	 * REQUIRES: The ship's current sector and the direction of the shoot.
	 * MODIFIES: This.
	 * EFFECTS: Creates an instance of a missile and puts it into a sector
	 * next to the ship depending on the direction of the shoot; sets the missile's label
	 * and initial direction; makes the missile detectable.
	 * @param sector The current ship's sector.
	 * @param direction The direction of the shoot.
	 */
	public TritonMissile(Sector sector, int direction){
		super(sector, direction);
		label = "$";
	}
	
	/**
	 * MODIFIES: This. The missile's current sector, detectability, and flag of hitting.
	 * EFFECTS: Checks what is in the next sector; makes the missile undetectable
	 * if it has reached the quadrant border; puts the missile into the next sector
	 * if it contains Void; blows up the next sector and makes the missile undetectable 
	 * if the next sector contains any other object.
	 */
	public void move(){
		new Void(sector);
		if(hit){
			blowUp(sector);
			setDetectable(false);
			return;
		}
		Sector nextSector = getCurQuadrant().getNext(sector, velocity[0]);	
		if(nextSector == null){
			setDetectable(false);
		} else {
			if(!(nextSector.getInhabitant() instanceof Void)) {
				hit = true;
			}
			setSector(nextSector);
		} 	
	}
}
