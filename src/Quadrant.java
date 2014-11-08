import java.awt.*;
import javax.swing.*;

public class Quadrant extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public Sector[][] grid = null; 
	
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
	}
}
