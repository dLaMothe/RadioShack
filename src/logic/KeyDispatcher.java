package logic;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class KeyDispatcher extends JFrame implements KeyEventDispatcher, ActionListener {

	private JTextField typingArea;
	private GameEngine game;
	
	public KeyDispatcher(GameEngine newGame) {
		typingArea = new JTextField(5);
		game = newGame;
	}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
        //Allow the event to be redispatched
		keyPressed(e);
        return false;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			processCommand(typingArea.getText());
			typingArea.setText("");	
		} 
	}

	public void processCommand(String command) {
		
		System.out.println(command);
	}
	
	public JTextField getTextField() {
		return typingArea;
	}
}
