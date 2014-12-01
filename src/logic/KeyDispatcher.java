package logic;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

/*
 * AUTHOR: DAVID LAMOTHE
 * OVERVIEW: Handles the key input listener event and 
 * passes it to the command parser 
 */

public class KeyDispatcher extends JFrame implements KeyEventDispatcher {
	
	private JTextField typingArea;
	private CommandParser parser;
	
	
	/*
	 * REQUIRES: New GameEngine, and textField
	 * MODIFIES: NONE
	 * EFFECTS: Initializes objects in game
	 */
	public KeyDispatcher(GameEngine newGame, JTextField textField) {
		typingArea = textField;
		parser = new CommandParser(newGame);
	}
	
	/*
	 * REQUIRES: KeyEvent
	 * MODIFIES: None
	 * EFFECTS: Passes KeyEvent to keypressed
	 * 
	 * (non-Javadoc)
	 * @see java.awt.KeyEventDispatcher#dispatchKeyEvent(java.awt.event.KeyEvent)
	 */
	public boolean dispatchKeyEvent(KeyEvent e) {
        //Allow the event to be redispatched
		keyPressed(e);
        return false;
    }

	/*
	 * REQUIRES: KeyEvent
	 * MODIFIES: TextField
	 * EFFECTS: If key event pressed is an enter
	 * Process the command currently in the text are
	 * 
	 */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(e.getID() == KeyEvent.KEY_PRESSED) {
				String cmd = typingArea.getText();
				processCommand(cmd);
				typingArea.setText("");	
			}
		} 
	}

	/*
	 * REQUIRES: String Command
	 * MODIFIES: NONE
	 * EFFECTS: Sends the command from the textfield
	 * to the commanderParser for parsing
	 * 
	 */
	public void processCommand(String command) {
		parser.parseCommand(command);
	}
	
	
	/*
	 *	REQUIRES: NONE 
	 *	MODIFIES: NONE
	 *	EFFECTS: Returns the textField
	 */
	public JTextField getTextField() {
		return typingArea;
	}
}
