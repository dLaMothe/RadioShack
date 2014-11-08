
public class TritonMissile 
	extends Weapon 
	implements Movable {
	
	public TritonMissile(){
		//super();
		setVelocity(new int[] {Config.SOUTH, 0});
		label = "$";
	}
	
	public void Move(){
		curSector.putObject(new Emptiness());
		Sector nextSector = curSector.getNext(velocity[0]);
		if(nextSector == null || !(nextSector.object instanceof Emptiness)){
			return;
		}
		else nextSector.putObject(this);
		
		//this.curSector.quadrant.blowup(this);
		
	}
	
	public void setVelocity(int[] velocity){
		Movable.velocity[0] = velocity[0];
		Movable.velocity[1] = velocity[1];
	}
}
