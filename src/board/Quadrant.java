package board;

import settings.Configs;



public class Quadrant implements Positionable{
	protected final Position position = new Position(-1, -1);
private final Sector sectors[][] = new Sector[Configs.QUADRANT_SIZE][Configs.QUADRANT_SIZE];

/**
 * @return the sectors
 */
public Sector[][] getSectors() {
	return sectors;
}

@Override
public Position getPosition() {
	return position;
} 
public Sector getSector(Position p){
	for (Sector[] sctrs : sectors) {
		for (Sector sector : sctrs) {
			if(p == sector.getPosition()){
				return sector;
			}
		}
	}
	return null;
	
}

public Sector getNext(Sector sector,int direction){
	int nextRow = sector.getPosition().getRow();
	int nextCol = sector.getPosition().getCol();
	switch(direction){
		case Configs.NORTH: 
			nextRow -= 1;
			break;
		case Configs.NORTH_EAST:
			nextRow -= 1;
			nextCol += 1;
			break;
		case Configs.EAST:
			nextCol += 1;
			break;
		case Configs.SOUTH_EAST:
			nextRow += 1;
			nextCol += 1;
			break;
		case Configs.SOUTH:
			nextRow += 1;
			break;
		case Configs.SOUTH_WEST:
			nextRow += 1;
			nextCol -= 1;
			break;
		case Configs.WEST:
			nextCol += 1;
			break;
		case Configs.NORTH_WEST:
			nextRow -= 1;
			nextCol -= 1;
			break;
		default:
			return sector;
	}
	return getSector(new Position(nextRow, nextCol));
}
}
