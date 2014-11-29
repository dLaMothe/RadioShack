package board;

import settings.Configs;

/**
 * 
 * @author sukhenka (Sukhenko Artur)
 * @version Fri Nov 28 5:45 PM
 */
public class Space {
	private final Quadrant quadrant[][];
	private boolean initialized = false;
	public boolean debug = false;

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
	{
		init();
	}

	private Space() {
		quadrant = new Quadrant[Configs.SPACE_SIZE][Configs.SPACE_SIZE];
		for(int i = 0; i < Configs.SPACE_SIZE; i++){
			for(int j = 0; j < Configs.SPACE_SIZE; j++){
				quadrant[i][j] = new Quadrant(i, j);
			}
		}
	}

	public void init() {
		if (!initialized) {
			for (int i = 0; i < quadrant.length; i++) {
				for (int j = 0; j < quadrant.length; j++) {
					//quadrant[i][j] = new Quadrant(new Position(i, j)); // is it
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
	
	public Quadrant getAbsQuadrant(int x, int y){
		return this.quadrant[x][y];
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
	 * Static getter method for retrieving the singleton instance
	 */
	public static Space getInstance() {
		if (instance == null) {
			instance = new Space();
		}
		return instance;
	}

}