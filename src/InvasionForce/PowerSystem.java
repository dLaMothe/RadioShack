/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InvasionForce;

import static  InvasionForce.Constants.*;

class PowerSystem {
//overview: Holds all the different powers
	
	private static final int NUMBER_OF_POWERS = 9;
	private Power[] myPowers;
	private double powerAvail;		
	
	//Constructor
    PowerSystem(double maxPower)
    //REQUIRES: NONE
    //MODIFIES: powerAvail
    //EFFECTS: set powerAvail to maxPower and initilize myPowers array
    {
    	powerAvail = maxPower;
    	myPowers = new Power[NUMBER_OF_POWERS];
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
	//MODIFIES: HYPRION, LRSENSOR, SRSENSOR, DEFLECTORS, MASERS, TRTMISSL, powerAvail
	//EFFECTS: Set the power level of a specficed power by the type given and checks if power level is critical  
	{
		myPowers[type].setMyPower(powerLevel);
		powerLevelCritical();
	}
	
	public void increasePowerLevels(int type, double powerLevel)
	//REQUIRES: NONE 
	//MODIFIES: HYPRION, LRSENSOR, SRSENSOR, DEFLECTORS, MASERS, TRTMISSL, powerAvail
	//EFFECTS: Increase the power level of a specficed power by the type given and checks if power level is critical
	{
		myPowers[type].increaseMyPower(powerLevel);
	}
	   
	public void decreasePowerLevels(int type, double powerLevel)throws CriticalPowerException
	//REQUIRES: NONE 
	//MODIFIES: HYPRION, LRSENSOR, SRSENSOR, DEFLECTORS, MASERS, TRTMISSL, powerAvail
	//EFFECTS: Decrease the power level of a specficed power by the type given
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
		for (int i = 0; i < NUMBER_OF_POWERS; i++)
		{
			totalPowerUsed += getPowerLevels(i);
		}
		if (powerAvail < totalPowerUsed)
		{
			throw new CriticalPowerException();
		}
	}
	   
	public void resetPowerAvailable()
	//REQUIRES: NONE 
	//MODIFIES: powerAvail
	//EFFECTS: Reset powerAvail to 100
	{
		powerAvail = MAX_POWER;
	}
    
}
