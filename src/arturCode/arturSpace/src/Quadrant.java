
public class Quadrant extends SpacePosition{
private final Sector sectors[][] = new Sector[SpaceConfig.QUADRANT_SIZE][SpaceConfig.QUADRANT_SIZE];

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
}