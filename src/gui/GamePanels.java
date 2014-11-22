package gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*import java.awt.Point;
import java.util.*;
import javax.swing.*;
import java.awt.Color;*/

public class GamePanels {

	private JFrame myFrame;
	private JPanel leftPanel;
	private JPanel middlePanel;
	private JPanel rightPanel;
	   
	GamePanels(JFrame frame) {
	      //super();
		myFrame = frame;
		            
		createLeftPanel();
		createMiddlePanel();
		createRightPanel();
	}
	private void createLeftPanel()
	{
		JPanel test = new JPanel();
		test.setLayout(new java.awt.GridBagLayout());
		      
		java.awt.GridBagConstraints gridConstraint = new java.awt.GridBagConstraints();
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.weightx =.5;
		gridConstraint.weighty = .5;
		gridConstraint.fill = java.awt.GridBagConstraints.BOTH;
		
		JLabel energyLabel = new JLabel("Hails to you left");
		test.add(energyLabel,gridConstraint);
		test.setOpaque(true);
		test.setBackground(Color.BLUE);
		leftPanel = test;
	}
	   
	private void createMiddlePanel()
	{
		JPanel test = new JPanel();
		test.setLayout(new java.awt.GridBagLayout());
		      
		java.awt.GridBagConstraints gridConstraint = new java.awt.GridBagConstraints();
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.weightx =.5;
		gridConstraint.weighty = .5;
		gridConstraint.fill = java.awt.GridBagConstraints.BOTH;
		
		JLabel energyLabel = new JLabel("Hails to you middle");
		test.add(energyLabel,gridConstraint);
		test.setOpaque(true);
		test.setBackground(Color.WHITE);
		middlePanel = test;
	}
	   
	private void createRightPanel()
	{
		JPanel test = new JPanel();
		test.setLayout(new java.awt.GridBagLayout());
		      
		java.awt.GridBagConstraints gridConstraint = new java.awt.GridBagConstraints();
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.weightx =.5;
		gridConstraint.weighty = .5;
		gridConstraint.fill = java.awt.GridBagConstraints.BOTH;
		
		JLabel energyLabel = new JLabel("Hails to you right");
		test.add(energyLabel,gridConstraint);
		test.setOpaque(true);
		test.setBackground(Color.RED);
		rightPanel = test;
	}
	   
	public JPanel getLeftPanel()
	{
		return leftPanel;
	}
	   
	public JPanel getMiddlePanel()
	{
		return middlePanel;
	}
	   
	public JPanel getRightPanel()
	{
		return rightPanel;
	}
	
	
}
