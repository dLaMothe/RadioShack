package gameObjects;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static settings.Configs.*;

class PowerSystem {
//overview: Holds all the different powers
	
	private static final int NUMBER_OF_POWERS = 10;
	private Power[] myPowers;	
	
	//Constructor
    PowerSystem()
    //REQUIRES: NONE
    //MODIFIES: NONE
    //EFFECTS: set myPowers[POWER_AVAILABLE] to MAX_POWER and initialize myPowers array
    {
    	myPowers = new Power[NUMBER_OF_POWERS];
    	myPowers[POWER_AVAILABLE].setMyPower(MAX_POWER) ;
    }
   
   
	public double getPowerLevels(int type)
	//REQUIRES: NONE
	//MODIFIES: NONE
	//EFFECTS: Returns the power levels of the selected power 
	{
		return myPowers[type].getMyPower();
	}
	
	public void setPowerLevels(int type, double powerLevel)throws CriticalPowerException
	//REQUIRES: NONE 
	//MODIFIES: myPowers[HYPRION, LRSENSOR, SRSENSOR, DEFLECTORS, MASERS, TRTMISSL, POWER_AVAILABLE]
	//EFFECTS: Set the power level of a specified power by the type given and checks if power level is critical  
	{
		myPowers[type].setMyPower(powerLevel);
		powerLevelCritical();
	}
	
	public void increasePowerLevels(int type, double powerLevel)
	//REQUIRES: NONE 
	//MODIFIES: myPowers[HYPRION, LRSENSOR, SRSENSOR, DEFLECTORS, MASERS, TRTMISSL, POWER_AVAILABLE]
	//EFFECTS: Increase the power level of a specified power by the type given and checks if power level is critical
	{
		myPowers[type].increaseMyPower(powerLevel);
	}
	   
	public void decreasePowerLevels(int type, double powerLevel)throws CriticalPowerException
	//REQUIRES: NONE 
	//MODIFIES: myPowers[HYPRION, LRSENSOR, SRSENSOR, DEFLECTORS, MASERS, TRTMISSL, POWER_AVAILABLE]
	//EFFECTS: Decrease the power level of a specified power by the type given
	{
		myPowers[type].decreaseMyPower(powerLevel);
		powerLevelCritical();
	}
	   
	private void powerLevelCritical() throws CriticalPowerException
	//REQUIRES: NONE 
	//MODIFIES: NONE
	//EFFECTS: Check if more power is being use then available return true then throws an exception
	{
		double totalPowerUsed = 0;
		for (int i = 0; i < NUMBER_OF_POWERS-1; i++)
		{
			totalPowerUsed += getPowerLevels(i);
		}
		if (myPowers[POWER_AVAILABLE].getMyPower() < totalPowerUsed)
		{
			throw new CriticalPowerException();
		}
	}
	   
	public void resetPowerAvailable()
	//REQUIRES: NONE 
	//MODIFIES: myPowers[POWER_AVAILABLE]
	//EFFECTS: Reset myPowers[POWER_AVAILABLE] to MAX_POWER
	{
		myPowers[POWER_AVAILABLE].setMyPower(MAX_POWER) ;
	}
    
}
