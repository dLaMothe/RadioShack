package board;

import gameObjects.SpaceObject;

public class Sector implements Positionable{
	protected final Position position = new Position(-1, -1);	
private SpaceObject inhabitant;

/**
 * @return the inhabitant
 */
public SpaceObject getInhabitant() {
	return inhabitant;
}

/**
 * @param inhabitant the inhabitant to set
 */
public void setInhabitant(SpaceObject inhabitant) {
	this.inhabitant = inhabitant;
}

@Override
public Position getPosition() {
	return position;
}

}
