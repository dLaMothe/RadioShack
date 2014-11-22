package holder;

public class QuadrantData {
	public QuadrantData(int stars, int stations, int jovians) {
		super();
		this.stars = stars;
		this.stations = stations;
		this.jovians = jovians;
	}
	private int stars;
	private int stations;
	private int jovians;
	public int getStations() {
		return stations;
	}
	public void setStations(int stations) {
		this.stations = stations;
	}
	public int getJovians() {
		return jovians;
	}
	public void setJovians(int jovians) {
		this.jovians = jovians;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	
	
	
}
