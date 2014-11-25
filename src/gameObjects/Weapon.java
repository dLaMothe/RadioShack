package invade.src.gameObjects;
import invade.src.board.Sector;
import invade.src.board.Space;
import invade.src.board.Quadrant;

public abstract class Weapon 
	extends SpaceObject
	implements Movable{
	
	public int[] velocity = new int[]{0, 0};
	
	public Weapon(int[] velocity){
		setVelocity(velocity);
	}
	
	protected Sector getCurSector(){
		return getCurQuadrant().getSector(position);
	}
	
	protected Quadrant getCurQuadrant(){
		return Space.getInstance().getQuadrantOfObject(this);
	}

	public void Move(){
		Sector curSector = getCurSector();
		curSector.setInhabitant(new Void());
		Sector nextSector = getCurQuadrant().getNext(curSector, velocity[0]);
		if(nextSector == null || !(nextSector.getInhabitant() instanceof Void)){
			curSector.setInhabitant(new Void());
			if(nextSector != null) {
				nextSector.blowUp(this);
			}
		}
		else nextSector.setInhabitant(this);		
	}
	
	public void setVelocity(int[] velocity){
		this.velocity[0] = velocity[0];
		this.velocity[1] = velocity[1];
	}
}
