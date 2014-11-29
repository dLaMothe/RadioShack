package logic;

import gameObjects.CollissionException;

import java.util.*;


public class DeltaLoop{
	private static final int FPS = 3;
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
			System.out.println("HELLO");
			try {
				game.ship.action();
			} catch (CollissionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			game.populateQuadrant();
	        if (!isRunning)
	        {
	            timer.cancel();
	        }
		}
	}
}

	

