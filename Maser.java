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
		sectors.add(curSector);
		Sector nextSector = curSector.getNext(velocity[0]);
		if(hit){
			destroyItself();
			return;
		}
		if(nextSector == null || !(nextSector.object instanceof Emptiness)){
			hit = true;
		}
		if(nextSector != null){
			nextSector.putObject(this);
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