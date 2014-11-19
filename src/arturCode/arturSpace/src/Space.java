package arturCode.arturSpace.src;

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