package csci.project;

import board.Position;
import board.Positionable;

public abstract class SpaceObject implements Positionable{
	protected final Position position = new Position(-1, -1);
}
