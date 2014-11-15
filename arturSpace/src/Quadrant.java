
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
public int getStarCount(){
	int i = 0;
	for (Sector[] sectors2 : sectors) {
		for (Sector sector : sectors2) {
			if(sector.getInhabitant() instanceof Star){
				i++;
			}
		}
	}
	return i;
}
}