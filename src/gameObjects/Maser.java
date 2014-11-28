package gameObjects;

import board.Sector;
import java.util.ArrayList;

/**
* The Maser weapon which can move straight leaving its tail
* and whether hit an object or fly away.
*/
public class Maser
	extends Weapon{
	
	/**
	 * The Maser's tail
	 */
	private ArrayList<Sector> sectors = null;
	
	/**
	 * REQUIRES: The ship's current sector and the direction of the shoot.
	 * MODIFIES: This.
	 * EFFECTS: Creates an instance of a maser and puts it into a sector
	 * next to the ship depending on the direction of the shoot; sets the maser's label
	 * and initial direction; makes the maser detectable; initializes the maser's tail.
	 * @param sector The current ship's sector.
	 * @param direction The direction of the shoot.
	 */
	public Maser(Sector sector, int direction){
		super(sector, direction);
		label = "~";
		sectors = new ArrayList<Sector>();
	}
	
	/**
	 * MODIFIES: This. The maser's current sector and tail, detectability, and flag of hitting.
	 * EFFECTS: Checks what is in the next sector; puts the maser into the next sector
	 * if it contains Void; blows up the next sector if the next sector contains any other object;
	 * removes one piece of the maser's tail if the maser has blown up the next sector or reached
	 * the quadrant border; makes the maser undetectable if its tail is completely removed.
	 */
	@Override
	public void move(){
		Sector tail = null;
		if(hit){
			tail = getNext();
			if(tail == null){	
				blowUp(sector);
				setDetectable(false);
			} else {		
				new Void(tail);
			}
			return;
		}
		sectors.add(sector);
		Sector nextSector = getCurQuadrant().getNext(sector, velocity[0]);	
		if(nextSector == null){
			hit = true;
		} else {
			if(!(nextSector.getInhabitant() instanceof Void)) {
				hit = true;
			}
			setSector(nextSector);
		} 
	}
	
	/**
	 * MODIFIES: This. THe maser's tail.
	 * EFFECTS: Returns the first sector with the maser's tail and removes it from
	 * the tail if the tail still exists or null otherwise.
	 * @return The first sector with the maser's tail.
	 */
	private Sector getNext(){
		if(sectors.size() == 0){
			return null;
		} 
		Sector sector = sectors.get(0);
		sectors.remove(0);
		return sector;	
	}
}