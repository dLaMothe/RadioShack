package gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*import java.awt.Point;
import java.util.*;
import javax.swing.*;
import java.awt.Color;*/

public class GamePanels {

	private static final int GRID_WIDTH = 10;
	private static final int GRID_HEIGHT = 10;
	
	private JFrame myFrame;
	private JPanel leftPanel;
	private JPanel middlePanel;
	private JPanel rightPanel;
	private JPanel bottomPanel;
	   
	//Left Panel Labels
	private JLabel lRSensorRowOneLabel;
	private JLabel lRSensorRowTwoLabel;
	private JLabel lRSensorRowThreeLabel;
	private JLabel hYPRIONLabel;
	private JLabel lRSENSORLabel;
	private JLabel sRSENSORLabel;
	private JLabel dEFLECTORSLabel;
	private JLabel mASERLabel;
	private JLabel tRTMISSLLabel;
	 
	//Right Panel Labels
	private JLabel starTimeLabel;
	private JLabel conditionLabel;
	private JLabel quadrantLabel;
	private JLabel sectorLabel;
	private JLabel tritonMislsLabel;
	private JLabel powerAvailLabel;
	private JLabel joviansLeftLabel;
	private JLabel antimatterPodsLabel;
	private JTextField textField;
	  
	//Middle Panel Labels
	private JLabel[][] grid;
	
	GamePanels(JFrame frame) {
	      //super();
		myFrame = frame;
		            
		createLeftPanel();
		createMiddlePanel();
		createRightPanel();
		createBottomPanel();
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
		lRSensorRowOneLabel = new JLabel("002-003-004");
		test.add(lRSensorRowOneLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("----------------"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		lRSensorRowTwoLabel = new JLabel("005-006-007");
		test.add(lRSensorRowTwoLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("----------------"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		lRSensorRowThreeLabel = new JLabel("008-009-000");
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
		hYPRIONLabel = new JLabel("20");
		test.add(hYPRIONLabel,gridConstraint);
		 
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		lRSENSORLabel = new JLabel("10");
		test.add(lRSENSORLabel,gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		sRSENSORLabel = new JLabel("20");
		test.add(sRSENSORLabel,gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		dEFLECTORSLabel = new JLabel("20");
		test.add(dEFLECTORSLabel,gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		mASERLabel = new JLabel("09");
		test.add(mASERLabel,gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		tRTMISSLLabel = new JLabel("11");
		test.add(tRTMISSLLabel,gridConstraint);

		test.setOpaque(true);
		test.setBackground(Color.PINK);
		leftPanel = test;
	}
	   
	private void createMiddlePanel()
	{
		int X=0;
		int Y=0;
		grid = new JLabel[GRID_HEIGHT][GRID_WIDTH];
		JPanel test = new JPanel();
		test.setLayout(new java.awt.GridBagLayout());
		  
		java.awt.GridBagConstraints gridConstraint = new java.awt.GridBagConstraints();
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		gridConstraint.weightx =1;
		gridConstraint.weighty = 1;
		gridConstraint.gridwidth = 2;
		gridConstraint.anchor = java.awt.GridBagConstraints.NORTHWEST;
		  
		//Filler to move other label to right postions
		test.add(new JLabel(""),gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		gridConstraint.weighty = 0;
		gridConstraint.gridwidth = GRID_WIDTH;
		gridConstraint.anchor = java.awt.GridBagConstraints.CENTER;
		test.add(new JLabel("-----RADIO SHACK-----"),gridConstraint);
		 
		gridConstraint.gridwidth = 1;
		 
		for(int i = 0; i < GRID_HEIGHT; i++)
		{
			gridConstraint.gridx = X;
			gridConstraint.gridy = Y+i;
			test.add(new JLabel(i+"I"),gridConstraint);
		}
		 
		X++;
		  
		for(int i = 0; i < GRID_HEIGHT; i++)
		{
			for(int j = 0; j < GRID_WIDTH; j++)
			{
				gridConstraint.gridx = X+j;
				gridConstraint.gridy = Y+i;
				grid[i][j] = new JLabel(""+j);
				test.add(grid[i][j],gridConstraint);
			}
		}
		  
		for(int i = 0; i < GRID_HEIGHT; i++)
		{
			gridConstraint.gridx = X+GRID_WIDTH;
			gridConstraint.gridy = Y+i;
			test.add(new JLabel("I"),gridConstraint);
		}
		  
		--X;
		Y+=GRID_HEIGHT;
		 
		for(int i = 0; i < GRID_WIDTH+2; i++)
		{
			gridConstraint.gridx = X+i;
			gridConstraint.gridy = Y;
			if(i == 0 || i == GRID_WIDTH +1) test.add(new JLabel("I"),gridConstraint);
			else test.add(new JLabel("-"),gridConstraint);
			
			gridConstraint.gridx = X+i;
			gridConstraint.gridy = Y+1;
			if(i == 0 || i == GRID_WIDTH +1) test.add(new JLabel("I"),gridConstraint);
			else test.add(new JLabel(""+(i-1)),gridConstraint);
		}
		 
		test.setOpaque(true);
		test.setBackground(Color.WHITE);
		middlePanel = test;
	}
	   
	private void createRightPanel()
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
		gridConstraint.gridwidth = 2;
		gridConstraint.anchor = java.awt.GridBagConstraints.CENTER;
		test.add(new JLabel("^ USS HEPHAESTUS ^"),gridConstraint);
		 
		gridConstraint.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		gridConstraint.gridwidth = 1;
		test.add(new JLabel("STAR_TIME"),gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("CONDITION"),gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("QUADRANT"),gridConstraint);
		 
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("SECTOR"),gridConstraint);
		 
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("TRITON MISLS"),gridConstraint);
		 
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("POWER AVAIL"),gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("JOVIANS LEFT"),gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("ANTIMATTER PODS"),gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel(" "),gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("SCDM: "),gridConstraint);
		
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel(" "),gridConstraint);
		 
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("COMMAND:"),gridConstraint);
		  
		//Column Two
		X=1;
		Y=2;
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		starTimeLabel = new JLabel("300200");
		test.add(starTimeLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		conditionLabel = new JLabel("GREEN");
		test.add(conditionLabel,gridConstraint);
		 
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		quadrantLabel = new JLabel("1-3");
		test.add(quadrantLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		sectorLabel = new JLabel("9-2");
		test.add(sectorLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		tritonMislsLabel = new JLabel("10");
		test.add(tritonMislsLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		powerAvailLabel = new JLabel("99%");
		test.add(powerAvailLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		joviansLeftLabel = new JLabel("44");
		test.add(joviansLeftLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		antimatterPodsLabel = new JLabel("3");
		test.add(antimatterPodsLabel,gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("7 0 1"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("6 - 2"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		test.add(new JLabel("5 4 3"),gridConstraint);
		  
		gridConstraint.gridx = X;
		gridConstraint.gridy = Y++;
		textField = new JTextField(4);
		test.add(textField,gridConstraint);
		
		test.setOpaque(true);
		test.setBackground(Color.RED);
		rightPanel = test;
	}
	
	private void createBottomPanel()
	{ 
		JPanel test = new JPanel();
		test.setLayout(new java.awt.GridBagLayout());
		 
		java.awt.GridBagConstraints gridConstraint = new java.awt.GridBagConstraints();
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.weightx =1;
		gridConstraint.weighty = 0;
		gridConstraint.anchor = java.awt.GridBagConstraints.CENTER;
		  
		test.add(new JLabel("HYPR=H,ION=I,MSR=M,TRM=T,PWRDST=D,SELFD=S,EXPRAY=E,POD=A,XPOD=X"),gridConstraint);
		  
		//Filler to move other label to right postions
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;
		gridConstraint.weighty = 1;
		test.add(new JLabel(""),gridConstraint);
		  
		test.setOpaque(true);
		test.setBackground(Color.MAGENTA);
		bottomPanel = test;
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
	
	public JPanel getBottomPanel()
	{
		return bottomPanel;
	}
}
