/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

import static settings.Configs.*;
import board.Position;
import board.Sector;

class Ship extends SpaceObject implements Movable{
    
    private final PowerSystem systems;
    private final int missleCap = TRT_MISSILES;
    private final int antimatterPodCap = ANTIMATTER_PODS;
    private int misslesLeft;
    private int amPodsLeft;

    /**
     *
     * @param p
     */
    public Ship(Sector sec) {
        super(sec);
        systems = new PowerSystem();
        misslesLeft = TRT_MISSILES;
        amPodsLeft = ANTIMATTER_PODS;
        label = "^";
    }
    
    //REQUIRES: nothing
    //MODIFIES: game state
    //EFFECTS: shoots a weapon of the given type in the given direction
    //      A typical call would look like shootWeapon(MASER, NW);
    //      using the class invasionforce.Constants to value to MASER and NW
    //      if there is sufficient power the weapon system will shoot
    public void shootWeapon(int type, int direction){
        
    }
    
    //REQUIRES: nothing
    //MODIFIES: this, game state
    //EFFECTS: if there is sufficient power in the power system the appropriate engine 
    //      will come on and the ship will begin moving on the given vector
    //                      vector[0] = magnitude
    //                      vector[1] = direction
    //      for best results use the directions declared in invasionforce.Constants
    public void activateEngine(int type, int[] vector){
        
    }
    
    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds the given number of missiles to the missiles available and adds
    //      the amount of power given to the power available
    public void reload(int missles, double power){
        
    }
    
    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: reduces the amount of available power.  The amount of power drained 
    //      depends on the amount of power directed to the shields
    public void sapPower(){
        
    }
    
    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: changes the amount of power available to the given system
    public void adjustPower(int system, double level){
        
    }

    @Override
    public void move() {
        
    }

    @Override
    public void setSpeed(int[] velocity) {
        
    }
}
