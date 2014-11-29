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
	private final Sector sectors[][] = new Sector[Configs.QUADRANT_SIZE][Configs.QUADRANT_SIZE];
	private boolean initialized = false;
	private ArrayList<SpaceObject> generatedObjects = new ArrayList<SpaceObject>();
	private Random rand = new Random();
	private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
	/**
	 * If ship is in this Quadrant active = true
	 */
	public boolean active = false;

	/**
	 * @return the sectors[][]
	 */
	public Sector[][] getSectors() {
		return sectors;
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
		for (SpaceObject obj : generatedObjects) {
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

	public Quadrant() {
		init();
	}

	public Quadrant(Position p) {
		position.setPositionAt(p);
		init();
	}

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
		return generatedObjects;
	}

	public void setGeneratedObjects(ArrayList<SpaceObject> generatedObjects) {
		this.generatedObjects = generatedObjects;
	}

	public ArrayList<Weapon> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(ArrayList<Weapon> weaponList) {
		this.weaponList = weaponList;
	}
}
