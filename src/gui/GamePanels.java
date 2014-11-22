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
		int X=0;
		int Y=0;
		   
		JPanel test = new JPanel();
		test.setLayout(new java.awt.GridBagLayout());
		      
		java.awt.GridBagConstraints gridConstraint = new java.awt.GridBagConstraints();
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		gridConstraint.weightx =1;
		gridConstraint.weighty = 1;
		gridConstraint.gridwidth = 2;
		//gridConstraint.fill = java.awt.GridBagConstraints.BOTH;
		gridConstraint.anchor = java.awt.GridBagConstraints.NORTHWEST;
		  
		//Filler to move other label to right positions
		test.add(new JLabel(""),gridConstraint);
		  
		//Column Zero 
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		gridConstraint.weighty = 0;
		gridConstraint.gridwidth = 1;
		test.add(new JLabel("L-R SENSOR"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		JLabel lRSensorRowOneLabel = new JLabel("002-003-004");
		test.add(lRSensorRowOneLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("----------------"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		JLabel lRSensorRowTwoLabel = new JLabel("005-006-007");
		test.add(lRSensorRowTwoLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("----------------"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		JLabel lRSensorRowThreeLabel = new JLabel("008-009-000");
		test.add(lRSensorRowThreeLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("PWR DIST"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("HYPR&ION"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("LR SENSOR"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("SR SENSOR"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("DEFLECTORS"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("MASERS"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("TRT MISSL"),gridConstraint);
		  
		//Column Two
		X=1;
		Y=1;
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("%-MINE"),gridConstraint);
		 
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("^-HEPHS"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("0-SSTN"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("@-JCC"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("&-JBC"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("X-UNKN"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("%"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		JLabel hYPRIONLabel = new JLabel("20");
		test.add(hYPRIONLabel,gridConstraint);
		 
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		JLabel lRSENSORLabel = new JLabel("10");
		test.add(lRSENSORLabel,gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		JLabel sRSENSORLabel = new JLabel("20");
		test.add(sRSENSORLabel,gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		JLabel dEFLECTORSLabel = new JLabel("20");
		test.add(dEFLECTORSLabel,gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		JLabel mASERLabel = new JLabel("09");
		test.add(mASERLabel,gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		JLabel tRTMISSLLabel = new JLabel("11");
		test.add(tRTMISSLLabel,gridConstraint);
		
		//Filler to move other label to right positions
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		gridConstraint.weighty = 1;
		gridConstraint.gridwidth = 2;
		test.add(new JLabel(""),gridConstraint);
		
		test.setOpaque(true);
		test.setBackground(Color.PINK);
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
