package gameObjects;

import board.Position;
import board.Positionable;
import board.Quadrant;
import board.Sector;
import board.Space;

import java.util.Observable;

/**
 * Overview: This class is intended to serve as a base class for all the space objects needed in
 * this game.  It should serve as the top of the inheritance tree for:
 * 								Player controlled - Ship
 *  							Player produced - Weapons - all types
 *  							Player friendly - Space Stations
 * 								All active obstacles - Jovians - all types
 *  							All inactive obstacles - Stars
 *  This class provides the basic functionality that all the listed objects require to be represented 
 *  in a meaningful way in the game.  It also extends Observable which provides an easy interface to the 
 *  GUI to keep updated on state changes.					
 * @author Michael Koonts
 */

public abstract class SpaceObject extends Observable implements Positionable{
    
	public String label;
    protected Sector sector;
    protected Quadrant quadrant;
    protected boolean detectable = false;

    /**
     * REQUIRES: @param sec - a valid board.Sector of which the inhabitant is either
     * null or ok to overwrite
     * MODIFIES: game state
     * EFFECTS: returns a new object at the given sector
     */
    public SpaceObject(Sector sec){
        sector = sec;
        sector.setInhabitant(this);
        quadrant = Space.getInstance().getQuadrant(sector.getQuadPosition());
    }
    
    /**
     * REQUIRES: nothing
     * MODIFIES: nothing
     * EFFECTS: @return a valid board.Position object
     * 			Note: a null value indicates a problem with either this
     *			or the sector this is located in
     */
    @Override
    public Position getPosition(){
        return sector.getPosition();
    }
    
    /**
     * REQUIRES: the caller to ensure that the given sector is an 
     * 			 appropriate place to set this object
     * MODIFFIES: this and @param sec
     * EFFECTS: sets this object in the sector provided
     */
    public void setSector(Sector sec){
    	if(null != sector) sector.setInhabitant(null);
        sector = sec;
        if(null != sector) sector.setInhabitant(this);
    }
    /**
     * REQUIRES: nothing
     * MODIFIES: this
     * EFFECTS: sets the detectable state of this to @param det
     */
    public void setDetectable(boolean det){
        detectable = det;
    }
    /**
     * REQUIRES: nothing
     * MODIFIES: nothing
     * EFFECTS: This method is intended for use with the short range sensors
     * ie. if @returns is false this should show up as an 'X' on the display
    */
    public boolean getDetectable(){
        return detectable;
    }
    
    /**
     * REQUIRES: nothing
     * MODIFIES: this
     * EFFECTS: effects very depending on the implementation of the SpaceObject
     * @throws CollissionException if action causes the SpaceObject to move this
     * exception can be generated in the event that the place this is moving to 
     * is already occupied. 
     */
    public abstract void action();
    
    public abstract void bumped();
    
    public abstract void bump(SpaceObject sb);
    
}
