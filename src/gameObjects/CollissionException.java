package gameObjects;

/**
 * OverView: This exception is intended to be generated when two SpaceObjects
 * collide during the normal execution of this game.  It's intended purpose is to 
 * communicate to the game engine that two objects are about to collide and which two.
 * This will allow the game engine to determine the correct outcome of this collision.  
 * @author Michael Koonts
 *
 */
public class CollissionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5808023828110607483L;
	private final SpaceObject moving;
	private final SpaceObject inhabitant;

	public CollissionException(SpaceObject moving, SpaceObject inhabitant) {
		this.moving = moving;
		this.inhabitant = inhabitant;
	}

	public SpaceObject getMoving() {
		return moving;
	}

	public SpaceObject getInhabitant() {
		return inhabitant;
	}

}
