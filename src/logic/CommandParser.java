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
				handleIon(command.substring(subCommand));
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



	private void handleIon(String substring) {
		System.out.println("I Process: " + substring);
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
