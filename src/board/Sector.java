package board;

import gameObjects.SpaceObject;

/**
 * 
 * @author sukhenka (Sukhenko Artur)
 * 
 */
public class Sector implements Positionable {
	protected final Position sectorPosition = new Position(-1, -1);
	protected final Position quadrantPosition = new Position(-1, -1);
	private SpaceObject inhabitant;
	private final int xCoord;
	private final int yCoord;

	public Sector(Position p, Quadrant q) {
		quadrantPosition.setPositionAt(q.getPosition());
		sectorPosition.setPositionAt(p);
		inhabitant = null;
		this.xCoord = p.getCol();
		this.yCoord = p.getRow();
	}

	public Sector(int x, int y) {
		xCoord = x;
		yCoord = y;
		inhabitant = null;
	}

	/**
	 * @return the inhabitant
	 */
	public SpaceObject getInhabitant() {
		return inhabitant;
	}

	/**
	 * @param inhabitant
	 *            the inhabitant to set
	 */
	public void setInhabitant(SpaceObject inhabitant) {
		this.inhabitant = inhabitant;
	}

	@Override
	public Position getPosition() {
		return sectorPosition;
	}

	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

}
