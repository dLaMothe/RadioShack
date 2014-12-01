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
			case 'H':
				handleHyperDirection(command.substring(subCommand));
				break;
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

				System.out.println("IC 0");
				break;
				
		}
	}

	private void handleHyperDirection(String substring) {
		try {
			switch(Integer.parseInt(substring.substring(firstIndex) )) {
				case NORTH: 
					handleSpeed(String.valueOf(STEN), NORTH);
					break;
				case SOUTH:
					handleSpeed(String.valueOf(STEN), SOUTH);
					break;
				case WEST:
					handleSpeed(String.valueOf(STEN), WEST);
					break;
				case EAST:
					handleSpeed(String.valueOf(STEN), EAST);
					break;
				case NORTH_WEST:
					handleSpeed(String.valueOf(STEN), NORTH_WEST);
					break;
				case NORTH_EAST:
					handleSpeed(String.valueOf(STEN), NORTH_EAST);
					break;
				case SOUTH_WEST:
					handleSpeed(String.valueOf(STEN), SOUTH_WEST);
					break;
				case SOUTH_EAST:
					handleSpeed(String.valueOf(STEN), SOUTH_EAST);
					break;
				case NEUTRAL:
					handleSpeed(String.valueOf(SZERO), NEUTRAL);
					break;
				default: 
					invalidCommand();
					System.out.println("IC 1");
					break;
					
			}
		} catch(NumberFormatException e) {
			invalidCommand();

			System.out.println("IC 2");
		}
	}
	private void handleIonDirection(String substring) {
		try {
			switch(Integer.parseInt(substring.substring(firstIndex, firstIndex + 1) )) {
				case NORTH: 
					handleSpeed(substring.substring(subCommand), NORTH);
					break;
				case SOUTH:
					handleSpeed(substring.substring(subCommand), SOUTH);
					break;
				case WEST:
					handleSpeed(substring.substring(subCommand), WEST);
					break;
				case EAST:
					handleSpeed(substring.substring(subCommand), EAST);
					break;
				case NORTH_WEST:
					handleSpeed(substring.substring(subCommand), NORTH_WEST);
					break;
				case NORTH_EAST:
					handleSpeed(substring.substring(subCommand), NORTH_EAST);
					break;
				case SOUTH_WEST:
					handleSpeed(substring.substring(subCommand), SOUTH_WEST);
					break;
				case SOUTH_EAST:
					handleSpeed(substring.substring(subCommand), SOUTH_EAST);
					break;
				case NEUTRAL:
					handleSpeed(substring.substring(subCommand), NEUTRAL);
					break;
				default: 
					invalidCommand();
					break;
					
			}
		} catch(NumberFormatException e) {
			invalidCommand();
		}
	}
	
	private void handleSpeed(String substring, int direction) {
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
				case STEN:
					game.setVelocity(STEN, direction);
					break;
				default: 
					invalidCommand();

					System.out.println("IC 3");
					break;
					
			}
		} catch(NumberFormatException e) {
			invalidCommand();

			System.out.println("IC 4");
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
