
public class AntimatterPod 
	extends Weapon 
	implements Movable{
	
	public AntimatterPod(){
		//super();
		setVelocity(new int[] {/*(int)(1 + (int)(Math.random() * 9))*/Config.EAST, 0});
		label = "#";
	}
	
	public void Move(){
		curSector.putObject(new Emptiness());
		Sector nextSector = curSector.getNext(velocity[0]);
		if(nextSector == null || !(nextSector.object instanceof Emptiness)){
			do{
				int direction = 0;
				do{
					direction = 1 + (int)(Math.random() * 9);
				}while(direction == 5);
				setVelocity(new int[]{direction, 0});
				nextSector = curSector.getNext(velocity[0]);
			}while(nextSector == null || !(nextSector.object instanceof Emptiness));
		}
		nextSector.putObject(this);
		
		//this.curSector.quadrant.blowup(this);
		
	}
	
	public void setVelocity(int[] velocity){
		Movable.velocity[0] = velocity[0];
		Movable.velocity[1] = velocity[1];
	}
}
