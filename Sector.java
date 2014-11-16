import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Sector extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public int row = 0;
	public int col = 0;
	public Quadrant quadrant = null;
	public SpaceObject object = null; 
	public JLabel label = null;
	
	public Sector(int row, int col, Quadrant quadrant){
		super();
		this.row = row;
		this.col = col;
		this.quadrant = quadrant;
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.setLayout(new BorderLayout());
		this.label = new JLabel("",JLabel.CENTER);
		this.add(label, BorderLayout.CENTER);
		this.putObject(new Emptiness());
	}
	
	public Sector getNext(int direction){
		int nextRow = row;
		int nextCol = col;
		switch(direction){
			case Config.NORTH: 
				nextRow -= 1;
				break;
			case Config.NORTH_EAST:
				nextRow -= 1;
				nextCol += 1;
				break;
			case Config.EAST:
				nextCol += 1;
				break;
			case Config.SOUTH_EAST:
				nextRow += 1;
				nextCol += 1;
				break;
			case Config.SOUTH:
				nextRow += 1;
				break;
			case Config.SOUTH_WEST:
				nextRow += 1;
				nextCol -= 1;
				break;
			case Config.WEST:
				nextCol += 1;
				break;
			case Config.NORTH_WEST:
				nextRow -= 1;
				nextCol -= 1;
				break;
			default:
				return this;
		}
		if(nextRow < 0 || nextRow >= Config.SECTORS_PER_SIDE
				|| nextCol < 0 || nextCol >= Config.SECTORS_PER_SIDE){
			return null;
		}
		return quadrant.grid[nextRow][nextCol];
	}
	
	public void putObject(SpaceObject object){
		object.curSector = this;
		this.object = object;
		label.setText(object.label);
	}
	
	public void blowUp(Weapon weapon){
		if(weapon instanceof TritonMissile || weapon instanceof Maser){
			quadrant.blowUp(this);
		} else { //That's an AntimaterPod
			ArrayList<Sector> sectors = new ArrayList<Sector>();			
			for(int i = 1; i < 10; i++){
				Sector sector  = this.getNext(i);
				if(sector != null){
					sectors.add(sector);
				}
			}
			quadrant.blowUp(sectors);
		}
	}
}
