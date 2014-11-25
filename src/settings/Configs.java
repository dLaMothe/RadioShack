package settings;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Michael Koonts
 */
public class Configs {
	public static final  int SPACE_SIZE = 10;
	public static final int QUADRANT_SIZE = 10;
    //Ship systems
    public static final int SHIELD = 0;
    public static final int LRSENSOR = 1;
    public static final int SRSENSOR = 2;
    public static final int MASER = 3;
    public static final int LAUNCHER = 4;
    public static final int ION = 5;
    public static final int HYPER = 6;
    public static final int POWER_AVAILABLE = 7;
    public static final int ENGINE = 8;
    public static final int TRT_MISSILE = 9;
    public static final int ANITMATTER_POD = 10;

    //default power levels
    public static final double INIT_SHIELD = 20.0;
    public static final double INIT_ENGINE = 20.0;
    public static final double INIT_LRSENSOR = 10.0;
    public static final double INIT_SRSENSOR = 20.0;
    public static final double INIT_MASER = 9.0;
    public static final double INIT_TRT_MISSILE = 11.0;
    //minimum system power(This is the minimum power a system must have in order to function)
    public static final double MIN_SYSTEM_POWER = 2.0;
    public static final double POWER_SAP = 0.5;
    
    //Capacities
    public static final int TRT_MISSILES = 3;
    public static final int ANTIMATTER_PODS = 3;
    public static final double MAX_POWER = 99.0;
    
    //Navigational directions
	public static final int WINDOW_SIZE = 500;
	public static final int SECTORS_PER_SIDE = 10;
	
	public static final int NORTH = 8;
	public static final int SOUTH = 2;
	public static final int WEST = 4;
	public static final int EAST = 6;
	public static final int NORTH_WEST = 7;
	public static final int NORTH_EAST = 9;
	public static final int SOUTH_WEST = 1;
	public static final int SOUTH_EAST = 3;
	public static final int NEUTRAL = 5;
    
    //Location names
    public static final int SECTOR_X = 0;
    public static final int SECTOR_Y = 1;
    public static final int QUADRENT_X = 2;
    public static final int QUADRENT_Y = 3;
    public static final int X = 0;
    public static final int Y = 1;
}
