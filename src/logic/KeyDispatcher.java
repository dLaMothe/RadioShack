package logic;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;


public class KeyDispatcher implements KeyEventDispatcher, KeyListener, ActionListener {

	JTextField typingArea;
	
	public boolean dispatchKeyEvent(KeyEvent e) {
        //Allow the event to be redispatched
		keyPressed(e);
        return false;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_TYPED) {
            System.out.println( "TEST" + e.getKeyCode() );
        }	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
