package logic;
import static settings.Configs.*;

public class CommandParser {
	
	private static final int firstIndex = 0;
	private static final int subCommand = 1;
	private GameEngine game;
	
	public CommandParser(GameEngine newGame) {
		game = newGame;
	}

	public void parseCommand(String command) {
		game.clearInvalidCommand();
		switch(command.charAt(firstIndex)) {
			case 'I':
				handleIonDirection(command.substring(subCommand));
				break;
			case 'M':
				handleMazer(command.substring(subCommand));
				break;
			case 'T':
				handleTriton(command.substring(subCommand));
				break;
			case 'D':
				handlePower(command.substring(subCommand));
				break;
			case 'S':
				handleDestruct(command.substring(subCommand));
				break;
			case 'E':
				handleEx(command.substring(subCommand));
				break;
			case 'A':
				handlePod(command.substring(subCommand));
				break;
			case 'X':
				handleExperiment(command.substring(subCommand));
				break;
			default:
				invalidCommand();
				break;
				
		}
	}



	private void handleIonDirection(String substring) {
		try {
			switch(Integer.parseInt(substring.substring(firstIndex, firstIndex + 1) )) {
				case NORTH: 
					handleIonSpeed(substring.substring(subCommand), NORTH);
					break;
				case SOUTH:
					handleIonSpeed(substring.substring(subCommand), SOUTH);
					break;
				case WEST:
					handleIonSpeed(substring.substring(subCommand), WEST);
					break;
				case EAST:
					handleIonSpeed(substring.substring(subCommand), EAST);
					break;
				case NORTH_WEST:
					handleIonSpeed(substring.substring(subCommand), NORTH_WEST);
					break;
				case NORTH_EAST:
					handleIonSpeed(substring.substring(subCommand), NORTH_EAST);
					break;
				case SOUTH_WEST:
					handleIonSpeed(substring.substring(subCommand), SOUTH_WEST);
					break;
				case SOUTH_EAST:
					handleIonSpeed(substring.substring(subCommand), SOUTH_EAST);
					break;
				case NEUTRAL:
					handleIonSpeed(substring.substring(subCommand), NEUTRAL);
					break;
				default: 
					invalidCommand();
					break;
					
			}
		} catch(NumberFormatException e) {
			invalidCommand();
		}
	}
	
	private void handleIonSpeed(String substring, int direction) {
		try {
			switch(Integer.parseInt(substring)) {
				case SONE: 
					game.setVelocity(SONE,direction);
					break;
				case STWO:
					game.setVelocity(STWO,direction);
					break;
				case STHREE:
					game.setVelocity(STHREE,direction);
					break;
				case SFOUR:
					game.setVelocity(SFOUR,direction);
					break;
				case SFIVE:
					game.setVelocity(SFIVE,direction);
					break;
				case SSIX:
					game.setVelocity(SSIX,direction);
					break;
				case SSEVEN:
					game.setVelocity(SSEVEN,direction);
					break;
				case SEIGHT:
					game.setVelocity(SEIGHT,direction);
					break;
				case SNINE:
					game.setVelocity(SNINE,direction);
					break;
				case STEN:
					game.setVelocity(STEN,direction);
					break;
				default: 
					invalidCommand();
					break;
					
			}
		} catch(NumberFormatException e) {
			invalidCommand();
		}
	}
	
	private void handleMazer(String substring) {
		// TODO Auto-generated method stub
		System.out.println("M Process: " + substring);
		
	}

	private void handleTriton(String substring) {
		// TODO Auto-generated method stub
		System.out.println("T Process: " + substring);	
	}
	
	private void handlePower(String cmd) {
		try {
			switch(cmd.charAt(firstIndex)) {
				case 'H':
					game.setPower(HYPER, Double.parseDouble((cmd.substring(subCommand))));
					break;
				case 'I':
					game.setPower(ION, Double.parseDouble((cmd.substring(subCommand))));
					break;
				case 'L':
					game.setPower(LRSENSOR, Double.parseDouble((cmd.substring(subCommand))));
					break;
				case 'S':
					game.setPower(SRSENSOR, Double.parseDouble((cmd.substring(subCommand))));
					break;
				case 'D':
					game.setPower(SHIELD, Double.parseDouble((cmd.substring(subCommand))));
					break;
				case 'M':
					game.setPower(MASER, Double.parseDouble((cmd.substring(subCommand))));
					break;
				case 'T':
					game.setPower(LAUNCHER, Double.parseDouble((cmd.substring(subCommand))));
					break;
				default:
					invalidCommand();
					break;
			}
		} catch(NumberFormatException e) {
			invalidCommand();
		}
	}

	private void handleDestruct(String substring) {
		// TODO Auto-generated method stub
		System.out.println("S Process: " + substring);
		
	}
	
	private void handleEx(String substring) {
		// TODO Auto-generated method stub
		System.out.println("E Process: " + substring);
		
	}
	
	private void handlePod(String substring) {
		// TODO Auto-generated method stub
		System.out.println("A Process: " + substring);
		
	}
	
	private void handleExperiment(String substring) {
		// TODO Auto-generated method stub
		System.out.println("X Process");
		
	}
	
	private void invalidCommand() {
		// TODO Auto-generated method stub
		System.out.println("Invalid Command");
		game.invalidCommand();
		
	}
}
