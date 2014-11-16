package csci.project;

public class Sector extends SpacePosition{
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
