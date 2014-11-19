package board;
import gameObjects.SpaceObject;
import settings.Configs;


public class Space {
 private final Quadrant quadrant[][] = new Quadrant[Configs.SPACE_SIZE][Configs.SPACE_SIZE];

/**
 * @return the quadrant
 */
public Quadrant[][] getQuadrants() {
	return quadrant;
}
/**
 * static Singleton instance
 */
private static Space instance;

/**
 * Private constructor for singleton
 */
private Space() {
}
public board.Quadrant getQuadrantOfObject(gameObjects.SpaceObject spaceObject){
	for (board.Quadrant[] quadrants : quadrant) {
		for (board.Quadrant quadrant : quadrants) {
			for(board.Sector[] secs : quadrant.getSectors()){
				for(board.Sector sec : secs){
					if(sec.getInhabitant() == spaceObject){
						return quadrant;
					}
				}
			}
		}
	}
	return null;
}
public Quadrant getQuadrant(Position p){
	for (Quadrant[] quadrants : quadrant) {
		for (Quadrant quadrant : quadrants) {
			if(p == quadrant.getPosition()){
				return quadrant;
			}
		}
	}
	return null;
	
}

/**
 * Static getter method for retrieving the singleton instance
 */
public static Space getInstance() {
	if (instance == null) {
		instance = new Space();
	}
	return instance;
}
 
}