package invade.src.gameObjects;

import invade.src.board.*;

public class AntimatterPod 
	extends Weapon{
	
	public AntimatterPod(int[] velocity){
		super(velocity);
		label = "#";
	}
	
	@Override
	public void Move(){		
		Sector curSector = getCurSector();
		Quadrant quadrant = getCurQuadrant();
		Sector nextSector = quadrant.getNext(curSector, velocity[0]);
		if(nextSector == null || !(nextSector.getInhabitant() instanceof Void)){
			do{
				int direction = 0;
				do{
					direction = 1 + (int)(Math.random() * 9);
				}while(direction == 5);
				setVelocity(new int[]{direction, 0});
				nextSector = quadrant.getNext(curSector, velocity[0]);
			}while(nextSector == null || !(nextSector.getInhabitant() instanceof Void));
		}
		nextSector.setInhabitant(this);	
	}
}
