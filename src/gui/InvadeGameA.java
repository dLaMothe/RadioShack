package gui;

import logic.*;

import java.awt.KeyboardFocusManager;

import javax.swing.*;

import logic.KeyDispatcher;

public class InvadeGameA extends JFrame {
	
	private static KeyDispatcher dispatcher;
	private GameEngine game;
	private GamePanels panel;
	
	InvadeGameA() {
		initUI();
		initGame();
		dispatcher = new KeyDispatcher(game,panel.getTextField());
    	//Hijack the keyboard manager
    	KeyboardFocusManager manager =
    	         KeyboardFocusManager.getCurrentKeyboardFocusManager();
    	manager.addKeyEventDispatcher( dispatcher );
	}
	
	private void initUI() {
    	panel = new GamePanels();
		setTitle("Test");
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	      
		setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridConstraint = new java.awt.GridBagConstraints();
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.weightx =.10;
	    gridConstraint.weighty = 1;
		gridConstraint.fill = java.awt.GridBagConstraints.BOTH;
		add(panel.getLeftPanel(),gridConstraint);
	      
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 0;
		gridConstraint.weightx =.5;
		add(panel.getMiddlePanel(),gridConstraint);
	      
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 0;
		gridConstraint.weightx =.10;
		add(panel.getRightPanel(),gridConstraint);
		
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;
		gridConstraint.weightx =1;
		gridConstraint.gridwidth = 3;
		add(panel.getBottomPanel(),gridConstraint);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;
		   
		//+++++++++++++++++++++++++++++
		//File Menu
		//+++++++++++++++++++++++++++++
		menu = new JMenu("File");
		menuBar.add(menu);
		menuItem = new JMenuItem(new AbstractAction("New Game") {
			public void actionPerformed(java.awt.event.ActionEvent e) {
		       
			}
		});
		menu.add(menuItem);
		menuItem = new JMenuItem(new AbstractAction("Exit Game") {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}
		});
		menu.add(menuItem);
		 
		setJMenuBar(menuBar);
		
		setSize(575,300);
		setLocationRelativeTo(null);
		
		
	}
	
	private void initGame() {
		//Initialize Game
		game = new GameEngine(panel);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
				public void run() {

					InvadeGameA game = new InvadeGameA();
					game.setVisible(true);
				}
		});
	}
}
