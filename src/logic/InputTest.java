package logic;
import java.awt.KeyboardFocusManager;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class InputTest {
	
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//Hijack the keyboard manager
            	KeyboardFocusManager manager =
            	         KeyboardFocusManager.getCurrentKeyboardFocusManager();
            	manager.addKeyEventDispatcher( new KeyDispatcher() );
            	 
                createAndShowGUI();
            }
        });
    }
    
    public static void createAndShowGUI() {
        //Create and set up the window.
        InputListener frame = new InputListener();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
