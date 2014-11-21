package logic;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class KeyDispatcher extends JFrame implements KeyEventDispatcher, ActionListener {

	JTextField typingArea;
	JTextArea displayArea;
	
	KeyDispatcher() {
		JButton button = new JButton("Clear");
		button.addActionListener(this);
		
        displayArea = new JTextArea();
        displayArea.setEditable(false);
		
		typingArea = new JTextField();
		
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(375, 125));
		
		getContentPane().add(typingArea,BorderLayout.PAGE_START);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		getContentPane().add(button, BorderLayout.PAGE_END);
	}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
        //Allow the event to be redispatched
		keyPressed(e);
        return false;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        //Clear the text components.
        typingArea.setText("");
        displayArea.setText("");

         
        //Return the focus to the typing area.
        typingArea.requestFocusInWindow();
	}

	public void keyPressed(KeyEvent e) {
        if(e.getID() == KeyEvent.KEY_TYPED) {
            typingArea.setText(typingArea.getText() + String.valueOf(e.getKeyChar()));
        }	
	}
}
