package logic;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class KeyDispatcher extends JFrame implements KeyEventDispatcher {

	private JTextField typingArea;
	private CommandParser parser;
	
	public KeyDispatcher(GameEngine newGame, JTextField textField) {
		typingArea = textField;
		parser = new CommandParser(newGame);
	}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
        //Allow the event to be redispatched
		keyPressed(e);
        return false;
    }


	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(e.getID() == KeyEvent.KEY_PRESSED) {
				String cmd = typingArea.getText();
				processCommand(cmd);
				typingArea.setText("");	
			}
		} 
	}

	public void processCommand(String command) {
		parser.parseCommand(command);
	}
	
	public JTextField getTextField() {
		return typingArea;
	}
}
