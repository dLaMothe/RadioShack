import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class InvadeGame extends JFrame {

	private static final long serialVersionUID = 1L;
	public Quadrant quadrant;
	
	public class Tick extends TimerTask{
		private Movable[] objects = null;
		
		public Tick(Movable[] objects){
			this.objects = objects;
		}
		
		public void run(){
			for(Movable object: objects){
				object.Move();
			}
			
		}
	}
	
	public InvadeGame(){
		quadrant = new Quadrant();
		AntimatterPod pod = new AntimatterPod();
		quadrant.grid[5][5].putObject(pod);
		quadrant.grid[0][5].putObject(new Star());
		quadrant.grid[5][0].putObject(new Star());
		quadrant.grid[9][5].putObject(new Star());
		quadrant.grid[5][9].putObject(new Star());
		
		TritonMissile missile = new TritonMissile();
		quadrant.grid[2][3].putObject(missile);
		
		initUI();
		Timer timer = new Timer();
		Movable[] objects =  new Movable[] {pod, missile};
		timer.schedule(new Tick(objects), 1000, 500);
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