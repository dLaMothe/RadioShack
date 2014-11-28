
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import settings.Configs;

public class InvadeGame extends JFrame {

	private static final long serialVersionUID = 1L;
	public QuadrantTest quadrant;
	
	AntimatterPodTest antimatter = null;
	MaserTest maser = null;
	TritonMissileTest triton = null;
	
	
	public class Tick extends TimerTask{	
		
		public void run(){
			
			if(antimatter != null && antimatter.getDetectable()){
				antimatter.move();
			}
			
			if(maser != null && maser.getDetectable()){
				maser.move();
			}
			
			if(triton != null && triton.getDetectable()){
				triton.move();
			}
		}
	}
	
	public InvadeGame(){
		quadrant = new QuadrantTest();
		
		new AntimatterPodTest(quadrant, quadrant.getSectors()[0][7], 5);
		
		//antimatter = new AntimatterPodTest(quadrant, quadrant.getSectors()[4][2], 2);
		
		//triton = new TritonMissileTest(quadrant, quadrant.getSectors()[0][7], 2);
		
		maser = new MaserTest(quadrant, quadrant.getSectors()[0][7], 1);	
	
		//new AntimatterPodTest(quadrant, quadrant.getSectors()[7][7], 5);
		
		
		initUI();
		Timer timer = new Timer();
		Tick tick = new Tick();
		timer.schedule(tick, 1000, 200);
	}
	
	private void initUI(){
		setTitle("Invade");
		setContentPane(quadrant);
		setSize(Configs.WINDOW_SIZE, Configs.WINDOW_SIZE);
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		InvadeGame window = new InvadeGame();		
		window.setVisible(true);
	}
}