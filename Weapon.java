
public abstract class Weapon 
	extends SpaceObject
	implements Movable{
	
	public int[] velocity = new int[]{0, 0};
	
	public Weapon(int[] velocity){
		/*int direction = 0;
		int velocity = 0;
		do{
			direction = 1 + (int)(Math.random() * 9);
		}while(direction == 5);*/
		setVelocity(velocity);
	}

	public void Move(){
		curSector.putObject(new Emptiness());
		Sector nextSector = curSector.getNext(velocity[0]);
		if(nextSector == null || !(nextSector.object instanceof Emptiness)){
			destroyItself();
			if(nextSector != null) {
				nextSector.blowUp(this);
			}
		}
		else nextSector.putObject(this);		
	}
	
	public void setVelocity(int[] velocity){
		this.velocity[0] = velocity[0];
		this.velocity[1] = velocity[1];
	}
}
