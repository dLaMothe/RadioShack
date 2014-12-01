package logic;
import java.util.*;

/*
 * AUTHOR: DAVID LAMOTHE, MANDIP SANGHA
 * OVERVIEW: RUNS GAMES LOOP
 */

public class DeltaLoop{
	private static final int FPS = 3;
	private Timer timer;
	private GameEngine game;
	private boolean isRunning;
	private GameLoop gameLoop;
	
	/*
	 * REQUIRES: New Game Object
	 * MODIFIES: isRunning, gameLoop, game, timer
	 * EFFECTS: Initializes game object and set isRunning true and starts 
	 * gameloop 
	 */
	DeltaLoop(GameEngine newGame){
		game = newGame;
		isRunning = true;
		timer = new Timer();
		gameLoop = new GameLoop();
		timer.schedule(gameLoop, 0, 1000 / FPS);
	}
	
	/*
	 * REQUIRES: NONE
	 * MODIFIES: NONE
	 * EFFECTS: calls game objects update and
	 * if running is false then stop timer 
	 */
	private class GameLoop extends TimerTask{
		public void run()
		{
			game.update();
	        if (!isRunning)
	        {
	            timer.cancel();
	        }
		}
	}
	
	/*
	 * REQUIRES: NONE
	 * MODIFIES: isRunning
	 * EFFECTS: set isRunning to false
	 */
	public void stopRunning() {
		isRunning = false;
	}
}

	

