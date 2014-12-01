package gameObjects;

import settings.Configs;
import board.*;

/**
 * The Space Station is a stationary space object.
 * @author Laurens van Wingerden, Vitalii Egorchatov
 */
public class SpaceStation 
	extends SpaceObject{

	/**
	 * REQUIRES: The Space Station's current sector.
	 * MODIFIES: This.
	 * EFFECTS: Creates an instance of Space Station and puts it into a sector;
	 * sets the station's label.
	 * @param sector The current station's sector.
	 */
	public SpaceStation(Sector sector) {
		super(sector);
		label = Configs.SSTN;
	}
	
	/**
	 * EFFECTS: Does nothing since the Space Station is an stationary object.
	 */
	@Override
	public void action(){

	}
	
	/**
	 * PURPOSE: This has been hit by other SpaceObject so it can reload the ship 
	 * if the object is Ship; it destroys itself if the object is an Antimatter Pod
	 * and object's bumped() called so that they can deal with this particular impact;
	 * EFFECTS: Reloads the ship or destroys the Space Station only by an Antimatter Pod.
	 * @param object The object that collided with this.
	 */
	@Override
	public void bump(SpaceObject object) {
		object.bumped(this);
		if(object instanceof Ship){
			((Ship) object).reload();
		} else if(object instanceof AntimatterPod){
			selfDestruct();
		}
	}
	
	/**
	 * PURPOSE: Nothing happens because the Space Station doesn't move.
	 * @param object The object that collided with this.
	 */
	@Override
	public void bumped(SpaceObject object) {
		
	}	
}
