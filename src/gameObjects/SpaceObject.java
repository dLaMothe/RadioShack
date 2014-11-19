package gameObjects;
import Sector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package invasionforce;

import java.util.Observable;
*/
/**
 *
 * @author Michael Koonts
 */

//class SpaceObject extends Observable{
    
//    protected Position position;
//    protected boolean detectable = false;

    /**
     *
     * @param p
     */
//    public SpaceObject(Position p){
//        position = p;
//    }
    
    /**
     *
     * @return
     */
//    public Position getPosition(){
//        return new Position(position);
//    }
    
    //REQUIRES: nothing
    //MODIFFIES: this
    //EFFECTS: 
//    public void setPosition(int x, int y){
        // TODO set the object's position within the sector
//    }
    
    /**
     *
     * @param p
     */
//    public void setPosition(Position p){
        // TODO setter logic here
//    }
    
    
//    public void setDetectable(boolean b){
//        detectable = b;
//    }
    
//    public boolean getDetectable(){
//        return detectable;
//    }

public abstract class SpaceObject {
	
	public String label;
	public Sector curSector = null;
	
	public SpaceObject(){
		
	}
	
	public void destroyItself(){
		curSector = null;
	}
}
