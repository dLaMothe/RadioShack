
public abstract class SpaceObject {
	
	public String label;
	public Sector curSector = null;
	
	public SpaceObject(){
		
	}
	
	public void destroyItself(){
		curSector = null;
	}

}