package gui;

import javax.swing.*;

public class InvadeGameA extends JFrame {
	
	InvadeGameA() {
		initUI();
	}
	
	private void initUI() {
		final GamePanels game = new GamePanels(this);
		setTitle("Test");
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	      
		setLayout(new java.awt.GridBagLayout());
		java.awt.GridBagConstraints gridConstraint = new java.awt.GridBagConstraints();
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.weightx =.5;
		gridConstraint.weighty = .5;
		gridConstraint.fill = java.awt.GridBagConstraints.BOTH;
		add(game.getLeftPanel(),gridConstraint);
	      
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 0;
		add(game.getMiddlePanel(),gridConstraint);
	      
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 0;
		add(game.getRightPanel(),gridConstraint);
		
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;
		gridConstraint.weightx =1;
		gridConstraint.gridwidth = 3;
		add(game.getBottomPanel(),gridConstraint);

		setSize(550,550);
		setLocationRelativeTo(null);
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
