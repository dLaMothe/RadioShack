/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObjects;

/**
 *
 * @author Michael Koonts
 */
public interface Movable {

    /** Velocity is a vector it requires two scalar values: a magnitude and a direction
     *
     */
    int[] velocity = new int[2];

    public void move() throws CollissionException;

    /**
     *
     * @param velocity
     */
    public void setSpeed(int[] velocity);
    
}
