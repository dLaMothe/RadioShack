import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class InvadeGame extends JFrame {

	private static final long serialVersionUID = 1L;
	public Quadrant quadrant;
	
	public class Tick extends TimerTask{
		private ArrayList<SpaceObject> objects = null;
		
		public Tick(ArrayList<SpaceObject> objects){
			this.objects = objects;
			
		}
		
		public void run(){
			for(Iterator<SpaceObject> iterator = objects.iterator(); iterator.hasNext();){
				SpaceObject object = iterator.next();
				if(object instanceof Movable){
					if(object.curSector != null){
						((Movable)object).Move();
					}
					if(object.curSector == null){
						if(object instanceof Maser){
							Sector sector = ((Maser)object).getNext();
							if(sector == null){
								iterator.remove();
							} else {
								sector.putObject(new Emptiness());
							}
						}
						else {
							iterator.remove();
						}
					}
				}
			}
		}
	}
	
	public InvadeGame(){
		quadrant = new Quadrant();
		quadrant.addOject(new Star(), quadrant.grid[0][5]);
		quadrant.addOject(new Star(), quadrant.grid[5][0]);
		quadrant.addOject(new Star(), quadrant.grid[8][5]);
		quadrant.addOject(new Star(), quadrant.grid[5][9]);
		quadrant.addOject(new Maser(new int[] {Config.SOUTH, 0}), quadrant.grid[2][5]);
		quadrant.addOject(new AntimatterPod(new int[] {Config.EAST,0}), quadrant.grid[2][2]);
		
		initUI();
		Timer timer = new Timer();
		Tick tick = new Tick(quadrant.objects);
		timer.schedule(tick, 500, 300);
	}
	
	private void initUI(){
		setTitle("Invade");
		setContentPane(quadrant);
		setSize(Config.WINDOW_SIZE, Config.WINDOW_SIZE);
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		InvadeGame window = new InvadeGame();		
		window.setVisible(true);
	}
}