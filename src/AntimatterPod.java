
public class AntimatterPod 
	extends Weapon{
	
	public AntimatterPod(){
		label = "#";
	}
	
	@Override
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
	}
}
