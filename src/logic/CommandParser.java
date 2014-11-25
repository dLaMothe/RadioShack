package logic;

public class CommandParser {
	
	private static final int firstIndex = 0;
	private static final int subCommand = 1;
	private GameEngine game;
	
	public CommandParser(GameEngine newGame) {
		game = newGame;
	}
	
	public void parseCommand(String command) {
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
				break;
			case 'A':
				handlePod(command.substring(subCommand));
				break;
			case 'X':
				break;
			default:
				invalidCommand();
				break;
		}
	}

	private void handleIon(String command) {
		
	}
	
	private void handleMazer(String substring) {
		// TODO Auto-generated method stub
		
	}

	private void handleTriton(String substring) {
		// TODO Auto-generated method stub
		
	}
	
	private void handlePower(String substring) {
		// TODO Auto-generated method stub
		
	}

	private void handleDestruct(String substring) {
		// TODO Auto-generated method stub
		
	}
	
	private void handlePod(String substring) {
		// TODO Auto-generated method stub
		
	}
	
	private void invalidCommand() {
		// TODO Auto-generated method stub
		
	}
}
