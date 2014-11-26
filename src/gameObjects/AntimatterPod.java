package gameObjects;

import java.util.Random;
import board.*;

public class AntimatterPod 
	extends Weapon{
	
	private Random random;
	
	public AntimatterPod(Sector sector, int direction){
		super(sector, direction);
		label = "#";
		random = new Random();
	}
	
	@Override
	public void move(){				
		Quadrant quadrant = getCurQuadrant();
		Sector nextSector = quadrant.getNext(sector, velocity[0]);
		if(nextSector == null || !(nextSector.getInhabitant() instanceof Void)){
			do{
				int direction = 0;
				do{
					direction = 1 + random.nextInt(9);
				}while(direction == 5);
				setSpeed(new int[]{direction, 1});
				nextSector = quadrant.getNext(sector, velocity[0]);
			}while(nextSector == null || !(nextSector.getInhabitant() instanceof Void));
		}
		nextSector.setInhabitant(this);	
	}
	
	public void detonate(){	
		Quadrant quadrant = getCurQuadrant(); 
		for(int i = 1; i < 10; i++){			
			Sector nextSector  = quadrant.getNext(sector, i);
			if(nextSector != null){
				blowUp(nextSector);
			}
		}
	}
}
