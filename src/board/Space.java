package board;

import gameObjects.*;

import java.util.Random;

import settings.Configs;

/**
 * 
 * @author sukhenka (Sukhenko Artur)
 * @version Fri Nov 28 5:45 PM
 */
public class Space {
	private final Quadrant quadrant[][] = new Quadrant[Configs.SPACE_SIZE][Configs.SPACE_SIZE];
	private boolean initialized = false;
	public boolean debug = false;
	public int TotalJovian = Configs.TOTAL_JOVIANS;
	public int TotalStars = Configs.TOTAL_STARS;
	public int TotalStations = Configs.TOTAL_STATIONS;

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
		init();
	}

	public void init() {
		if (!initialized) {
			for (int i = 0; i < quadrant.length; i++) {
				for (int j = 0; j < quadrant.length; j++) {
					quadrant[i][j] = new Quadrant(new Position(i, j)); // is it
																		// right
																		// order?
					if (debug) {
						System.out.println("init: quadrant row: " + i + " col:"
								+ j);
					}
				}
			}
			initialized = true;
			if (debug) {
				System.out.println("Initialized space");
			}
		}
	}

	/**
	 * @param spaceObject
	 *            (object you want to find)
	 * @return Quadrant where spaceObject is.
	 */
	public board.Quadrant getQuadrantOfObject(
			gameObjects.SpaceObject spaceObject) {
		for (board.Quadrant[] quadrants : quadrant) {
			for (board.Quadrant quadrant : quadrants) {
				for (board.Sector[] secs : quadrant.getSectors()) {
					for (board.Sector sec : secs) {
						if (sec.getInhabitant() == spaceObject) {
							return quadrant;
						}
					}
				}
			}
		}
		return null; // if not find returns null
	}

	/**
	 * @param p
	 *            - Use Position instead of row,col if you want.
	 * @return the quadrant in Space with position (row,col)
	 */
	public Quadrant getQuadrant(Position p) {
		return quadrant[p.getRow()][p.getCol()];

	}

	/**
	 * @param row
	 * @param col
	 * @return the quadrant in Space with position (row,col)
	 */
	public Quadrant getQuadrant(int row, int col) {
		Position p = new Position(row, col);
		return quadrant[p.getRow()][p.getCol()];

	}

	/**
	 * REQUIRES: nothing
	 * MODIFIES: space
	 * EFFECTS: Initializes the population of space.  Create an appropriate number of
	 * jovians, stars and space stations and distribute them throughout the space
	 */
	public void initPopulation(Ship ship){
		int TotalJovianInGame = Configs.TOTAL_JOVIANS;
		int TotalStarsInGame = Configs.TOTAL_STARS;
		int TotalStationsInGame = Configs.TOTAL_STATIONS;
		Random random = new Random();
		Sector [][]tempSec;
		for (int i = 0; i < quadrant.length; i++) {
			for (int j = 0; j < quadrant.length; j++) {
				int numJovianInSec = 0;
				int numStarsinSec = 0;
				int numStationInSec = 0;
				if(TotalJovianInGame >0 ){
					numJovianInSec = random.nextInt(5);
					TotalJovianInGame -= numJovianInSec;
				}
				if(TotalStarsInGame > 0) {
					numStarsinSec = random.nextInt(5);
					TotalStarsInGame -= numStarsinSec;
					
				}
				if(TotalStationsInGame > 0) {
					numStationInSec = random.nextInt(2);
					TotalStationsInGame -= numStationInSec;
				}
				for(int k =0; k < numJovianInSec; k++){
					tempSec = quadrant[i][j].getSectors();
					quadrant[i][j].getAllObjectsFromQuadrant().add(new JovianBattleCruiser(tempSec[0][k],ship));
				}
				
				for(int k = 0; k < numStarsinSec; k++) {
					tempSec = quadrant[i][j].getSectors();
					quadrant[i][j].getAllObjectsFromQuadrant().add(new Star(tempSec[1][k]));
				}
				for(int k = 0; k < numStationInSec; k++) {
					tempSec = quadrant[i][j].getSectors();
					quadrant[i][j].getAllObjectsFromQuadrant().add(new SpaceStation(tempSec[2][k]));
				}
				quadrant[i][j].unpopulate();
			}
		}
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
	
	public static void clear() {
		instance = null;
	}
	
	public void decrementJovian() {
		TotalJovian--;
	}

}