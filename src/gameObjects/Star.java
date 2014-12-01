package gameObjects;

import settings.Configs;
import board.*;

/**
 * The Star is a stationary space object.
 * @author Laurens van Wingerden, Vitalii Egorchatov
 */
public class Star 
	extends SpaceObject{

	/**
	 * REQUIRES: The Star's current sector.
	 * MODIFIES: This.
	 * EFFECTS: Creates an instance of Star and puts it into a sector;
	 * sets the Star's label.
	 * @param sector The current Star's sector.
	 */
	public Star(Sector sector) {
		super(sector);
		label = Configs.STAR;
	}
	
	/**
	 * EFFECTS: Does nothing since the Star is an stationary object.
	 */
	@Override
	public void action(){

	}
	
	/**
	 * PURPOSE: This has been hit by other SpaceObject so it destroys itself 
	 * if the object is an Antimatter Pod and object's bumped() called so that they can 
	 * deal with this particular impact;
	 * EFFECTS: Destroys the Star only by an Antimatter Pod.
	 * @param object The object that collided with this.
	 */
	@Override
	public void bump(SpaceObject object) {
		object.bumped(this);
		if(object instanceof AntimatterPod){
			selfDestruct();
		}
	}
	
	/**
	 * PURPOSE: Nothing happens because the Star doesn't move.
	 * @param object The object that collided with this.
	 */
	@Override
	public void bumped(SpaceObject object) {
		
	}	
}

