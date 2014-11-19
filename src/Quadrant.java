import gameObjects.Emptiness;
import gameObjects.SpaceObject;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Quadrant extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public Sector[][] grid = null;
	public ArrayList<SpaceObject> objects = null;
	
	public Quadrant (){
		super();
		this.setLayout(new GridLayout(10, 10));
		grid = new Sector[Config.SECTORS_PER_SIDE][Config.SECTORS_PER_SIDE];
		for(int i = 0; i < Config.SECTORS_PER_SIDE; i++){
			for(int j = 0; j < Config.SECTORS_PER_SIDE; j++){
				grid[i][j] = new Sector(i, j, this);
				this.add(grid[i][j]);
			}
		}
		objects = new ArrayList<SpaceObject>();
	}
	
	public void addOject(SpaceObject object, Sector sector){
		objects.add(object);
		sector.putObject(object);
	}
	
	public void removeOject(SpaceObject object){	
		objects.remove(object);
	}
	
	public void blowUp(Sector sector){
		sector.object.destroyItself();
		sector.putObject(new Emptiness());
	}
	
	public void blowUp(ArrayList<Sector> sectors){
		for(Sector sector: sectors){
			sector.object.destroyItself();
			sector.putObject(new Emptiness());
		}
	}
}
