package gameObjects;

import board.Sector;
import java.util.ArrayList;

public class Maser
	extends Weapon{
	
	private ArrayList<Sector> sectors = null;
	private Boolean hit = false;
	
	public Maser(int[] velocity){
		super(velocity);
		label = "~";
		sectors = new ArrayList<Sector>();
	}
	
	@Override
	public void Move(){
		Sector curSector = getCurSector();
		Sector nextSector = getCurQuadrant().getNext(curSector, velocity[0]);	
		sectors.add(curSector);
		if(hit){
			curSector.setInhabitant(new Void());
			return;
		}
		if(nextSector == null || !(nextSector.getInhabitant() instanceof Void)){
			hit = true;
		}
		if(nextSector != null){
			nextSector.setInhabitant(this);
		}
	}
	
	public Sector getNext(){
		if(sectors.size() == 0){
			return null;
		} 
		Sector sector = sectors.get(0);
		sectors.remove(0);
		return sector;	
	}
}