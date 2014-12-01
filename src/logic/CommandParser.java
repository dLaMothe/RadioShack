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
				handleWeapon(command.substring(subCommand),MASER);
				break;
			case 'T':
				handleWeapon(command.substring(subCommand),TRT_MISSILE);
				break;
			case 'D':
				handlePower(command.substring(subCommand));
				break;
			case 'S':
				handleDestruct(command.substring(subCommand));
				break;
			case 'E':
				handleExperiment(command.substring(subCommand));
				break;
			case 'A':
				handleWeapon(command.substring(subCommand),ANITMATTER_POD);
				break;
			case 'X':
				handleEx();
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
				case SZERO: 
					game.setVelocity(SZERO,direction);
					break;

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
				default: 
					invalidCommand();
					break;
					
			}
		} catch(NumberFormatException e) {
			invalidCommand();
		}
	}
	
	private void handleWeapon(String substring, int type) {
		try {
			switch(Integer.parseInt(substring.substring(firstIndex, firstIndex + 1) )) {
				case NORTH: 
					game.shootWeapon(type, NORTH);
					break;
				case SOUTH:
					game.shootWeapon(type, SOUTH);
					break;
				case WEST:
					game.shootWeapon(type, WEST);
					break;
				case EAST:
					game.shootWeapon(type, EAST);
					break;
				case NORTH_WEST:
					game.shootWeapon(type, NORTH_WEST);
					break;
				case NORTH_EAST:
					game.shootWeapon(type, NORTH_EAST);
					break;
				case SOUTH_WEST:
					game.shootWeapon(type, SOUTH_WEST);
					break;
				case SOUTH_EAST:
					game.shootWeapon(type, SOUTH_EAST);
					break;
				default: 
					invalidCommand();
					break;
					
			}
		} catch(NumberFormatException e) {
			invalidCommand();
		}
	}
	
	private void handlePower(String cmd) {
		try {
			if(Double.parseDouble((cmd.substring(subCommand))) >= 0) {
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
			} else {
					invalidCommand();
			}
		} catch(NumberFormatException e) {
			invalidCommand();
		}
	}

	private void handleDestruct(String substring) {
		// TODO Auto-generated method stub
		System.out.println("S Process: " + substring);
		game.selfDestruct();
	}
	
	private void handleEx() {
		// TODO Auto-generated method stub
		game.explodePod();
		
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
