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
     * @return a valid position object
     * 			Note: a null value indicates a problem with this
     */
    @Override
    public Position getPosition(){
        return sector.getPosition();
    }
    
    //REQUIRES: the caller to ensure that the given sector is an 
    // appropriate place to set this object
    //MODIFFIES: this, sec
    //EFFECTS: sets this object in the sector provided
    public void setSector(Sector sec){
        sector = sec;
        sector.setInhabitant(this);
    }
    
    
    public void setDetectable(boolean det){
        detectable = det;
    }
    
    public boolean getDetectable(){
        return detectable;
    }
}
