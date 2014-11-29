package board;

import gameObjects.SpaceObject;
import gameObjects.Weapon;
import java.util.ArrayList;
import java.util.Random;

import settings.Configs;

/**
 * @author sukhenka (Sukhenko Artur)
 * @version Fri Nov 28 5:45 PM
 */
public class Quadrant implements Positionable {
	protected final Position position = new Position(-1, -1);
	/**
	 * sectors[][] array of Sectors(Sector contain SpaceObject
	 * like Ship, Jovian etc)
	 */
	private final Sector[][] sectors;
	private boolean initialized = false;
	private ArrayList<SpaceObject> quadrantPopulation;
	private Random rand = new Random();
	private ArrayList<Weapon> weaponList;
	private final int xCoord;
	private final int yCoord;
	/**
	 * If ship is in this Quadrant active = true
	 */
	public boolean active = false;
	
	public Quadrant(int x, int y){
		this.xCoord = x;
		this.yCoord = y;
		quadrantPopulation = new ArrayList<SpaceObject>();
		weaponList = new ArrayList<Weapon>();
		sectors = new Sector[Configs.QUADRANT_SIZE][Configs.QUADRANT_SIZE];
		for(int i = 0; i < Configs.QUADRANT_SIZE; i++){
			for(int j = 0; j < Configs.QUADRANT_SIZE; j++){
				sectors[i][j] = new Sector(i, j);
			}
		}
	}

	public Quadrant(Position p) {
		this.xCoord = p.getCol();
		this.yCoord = p.getRow();
		quadrantPopulation = new ArrayList<SpaceObject>();
		weaponList = new ArrayList<Weapon>();
		sectors = new Sector[Configs.QUADRANT_SIZE][Configs.QUADRANT_SIZE];
		for(int i = 0; i < Configs.QUADRANT_SIZE; i++){
			for(int j = 0; j < Configs.QUADRANT_SIZE; j++){
				sectors[i][j] = new Sector(i, j);
			}
		}
	}

	/**
	 * @return the sectors[][]
	 */
	public Sector[][] getSectors() {
		return sectors;
	}
	
	public Sector getAbsSector(int x, int y){
		return this.sectors[x][y];		
	}
	
	public ArrayList<SpaceObject> getPopulation(){
		return quadrantPopulation;		
	}
	
	public ArrayList<Weapon> getActiveWeapons(){
		return weaponList;		
	}

	private void init() {
		if (!initialized) {
			for (int i = 0; i < sectors.length; i++) {
				for (int j = 0; j < sectors.length; j++) {
					sectors[i][j] = new Sector(new Position(i, j), this); // is
																			// it
																			// right
																			// order?

				}
			}
			initialized = true;
		}
	}

	/**
	 * returns the position of the Quadrant in Space
	 */
	@Override
	public Position getPosition() {
		return position;
	}

	/**
	 * method put generated spaceObjects to the sectors.
	 */
	public void populate() {
		for (SpaceObject obj : quadrantPopulation) {
			populateSector(obj);
		}

	}

	private void populateSector(SpaceObject obj) {
		Position p = randomPosition();
		if (getSector(p).getInhabitant() == null) {
			getSector(p).setInhabitant(obj);
		} else {
			populateSector(obj);
		}
	}

	private Position randomPosition() {
		return (new Position(rand.nextInt(Configs.QUADRANT_SIZE),
				rand.nextInt(Configs.QUADRANT_SIZE)));
	}

	public ArrayList<SpaceObject> getAllObjectsFromQuadrant() {
		ArrayList<SpaceObject> obcts = new ArrayList<SpaceObject>();
		for (Sector[] sctrs : sectors) {
			for (Sector sector : sctrs) {
				obcts.add(sector.getInhabitant());
			}
		}
		return obcts;

	}

	public Sector getSector(Position p) {
		return sectors[p.getRow()][p.getCol()];

	}

	public void unpopulate() {
		weaponList.clear();
		for (Sector[] sctrs : sectors) {
			for (Sector sector : sctrs) {
				sector.setInhabitant(null);
			}

		}
	}

	public Sector getSector(int row, int col) {
		Position p = new Position(row, col);
		return sectors[p.getRow()][p.getCol()];

	}

	//public Quadrant(Position p) {
	//	position.setPositionAt(p);
	//	init();
	//}

	public Sector getNext(Sector sector, int direction) {
		int nextRow = sector.getPosition().getRow();
		int nextCol = sector.getPosition().getCol();
		Position pos = new Position();
		switch (direction) {
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
		pos.setCol(nextCol);
		pos.setRow(nextRow);
		if (!pos.isValid()) {
			return null;
		}
		return getSector(pos);
	}

	public ArrayList<SpaceObject> getGeneratedObjects() {
		return quadrantPopulation;
	}

	public void setGeneratedObjects(ArrayList<SpaceObject> generatedObjects) {
		this.quadrantPopulation = generatedObjects;
	}

	public ArrayList<Weapon> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(ArrayList<Weapon> weaponList) {
		this.weaponList = weaponList;
	}

	public int getyCoord() {
		return yCoord;
	}

	public int getxCoord() {
		return xCoord;
	}
}
