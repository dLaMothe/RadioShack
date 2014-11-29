
import java.util.*;
import java.util.Timer;

import settings.Configs;
import board.*;
import gameObjects.*;

public class InvadeGame {

	public Quadrant quadrant;
	
	AntimatterPod antimatter = null;
	Maser maser = null;
	TritonMissile triton = null;
	
	
	public class Tick extends TimerTask{	
		
		public void run(){
			/*if(antimatter != null && antimatter.getDetectable()){
				antimatter.move();
			}
			
			if(maser != null && maser.getDetectable()){
				maser.move();
			}
			
			if(triton != null && triton.getDetectable()){
				triton.move();
			}*/
		}
	}
	
	public InvadeGame(){
		quadrant = new Quadrant();
		
		new AntimatterPod(quadrant.getSectors()[0][7], 5);
		
		//antimatter = new AntimatterPodTest(quadrant, quadrant.getSectors()[4][2], 2);
		
		//triton = new TritonMissileTest(quadrant, quadrant.getSectors()[0][7], 2);
		
		maser = new Maser(quadrant.getSectors()[0][7], 1);	
	
		//new AntimatterPodTest(quadrant, quadrant.getSectors()[7][7], 5);
		
		Timer timer = new Timer();
		Tick tick = new Tick();
		timer.schedule(tick, 1000, 200);
	}

	
	public static void main(String[] args){
		InvadeGame window = new InvadeGame();		
		//window.setVisible(true);
	}
}