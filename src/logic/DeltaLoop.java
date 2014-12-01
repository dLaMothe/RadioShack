package logic;
import java.util.*;


public class DeltaLoop{
	private static final int FPS = 1;
	private Timer timer;
	private GameEngine game;
	private boolean isRunning;
	private GameLoop gameLoop;
	
	DeltaLoop(GameEngine newGame){
		game = newGame;
		isRunning = true;
		timer = new Timer();
		gameLoop = new GameLoop();
		timer.schedule(gameLoop, 0, 1000 / FPS);
	}
	
	private class GameLoop extends TimerTask{
		public void run()
		{
			//game.ship.sapPower();
			game.update();
	        if (!isRunning)
	        {
	            timer.cancel();
	        }
		}
	}
}

	

