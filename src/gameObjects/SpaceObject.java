package gameObjects;

import board.Position;
import board.Positionable;
import board.Sector;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Observable;

/**
 *
 * @author Michael Koonts
 */

public class SpaceObject extends Observable implements Positionable{
    
	public String label;
    protected Sector sector;
    protected boolean detectable = false;

    /**
     *
     * @param p
     */
    public SpaceObject(Sector sec){
        sector = sec;
        sector.setInhabitant(this);
    }
    
    /**
     *
     * @return
     */
    @Override
    public Position getPosition(){
        return sector.getPosition();
    }
    
    //REQUIRES: nothing
    //MODIFFIES: this
    //EFFECTS: sets this object in the sector provided
    public void setSector(Sector sec){
        // TODO set the object's position on the board
    	// check if the sector can be this objects position
    	// set both pointers this.sector & sector.inhabitant
    }
    
    
    public void setDetectable(boolean det){
        detectable = det;
    }
    
    public boolean getDetectable(){
        return detectable;
    }
}
