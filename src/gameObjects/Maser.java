package gameObjects;

import board.Sector;
import java.util.ArrayList;

public class Maser
	extends Weapon{
	
	private ArrayList<Sector> sectors = null;
	private Boolean hit = false;
	
	public Maser(Sector sector, int direction){
		super(sector, direction);
		label = "~";
		sectors = new ArrayList<Sector>();
	}
	
	@Override
	public void move(){
		Sector nextSector = getCurQuadrant().getNext(sector, velocity[0]);	
		sectors.add(sector);
		if(hit){
			sector.setInhabitant(new Void());
			return;
		}
		if(nextSector == null || !(nextSector.getInhabitant() instanceof Void)){
			hit = true;
		}
		if(nextSector != null){
			nextSector.setInhabitant(this);
		}
	}
	
	private Sector getNext(){
		if(sectors.size() == 0){
			return null;
		} 
		Sector sector = sectors.get(0);
		sectors.remove(0);
		return sector;	
	}
}