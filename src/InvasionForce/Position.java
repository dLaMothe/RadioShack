/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvasionForce;

import static  InvasionForce.Constants.*;

/**
 *
 * @author Michael Koonts
 */
class Position {
    
    private final int[] position;
    
//    private double xVelocity;
//    private double yVelocity;

    public Position(int sx, int sy, int qx, int qy) {
        position = new int[4];
        position[SECTOR_X] = sx;
        position[SECTOR_Y] = sy;
        position[QUADRENT_X] = qx;
        position[QUADRENT_Y] = qy;
    }
    
    public Position(Position p) {
        position = p.getPosition().clone();
    }
    
    public int[] getSector(){
        
        int[] sector = new int[2];
        sector[X] = position[SECTOR_X];
        sector[Y] = position[SECTOR_Y];
        
        return sector;
    }
    
    public int[] getQuadrent(){
        
        int[] quadrent = new int[2];
        quadrent[X] = position[QUADRENT_X];
        quadrent[Y] = position[QUADRENT_Y];
        
        return quadrent;
    }
    
    public int[] getPosition(){
        return position;
    }
    public void setSector(int[] newSector){
        position[SECTOR_X] = newSector[X];
        position[SECTOR_Y] = newSector[Y];
    }
    
    public void setQuadrent(int[] newQuadrent){
        position[QUADRENT_X] = newQuadrent[X];
        position[QUADRENT_Y] = newQuadrent[Y];
    }
}
