

//
//Written By: Mandip Sangha
//


public class Power {
//overview: Power holds power level of a power and set it or get it as a double. 
//  ie. LRSensor power level equal 10

	private static final double START_POWER_LEVEL = 10;
	private double myPowerLevel;
	
	
	//Constructor
	public Power()
	//REQUIRES: NONE
	//MODIFIES: myPowerLevel
	//EFFECTS: Sets myPowerLevel to START_POWER_LEVEL
	{
		myPowerLevel = START_POWER_LEVEL;
	}
	
	public double getMyPower()
	//REQUIRES: NONE
	//MODIFIES: NONE
	//EFFECTS: Returns the value in myPowerLevel
	{
		return myPowerLevel;
	}
	
	public void setMyPower(double powerLevel)
	//REQUIRES: NONE
	//MODIFIES: myPowerLevel
	//EFFECTS: Sets the value in myPowerLevel to powerLevel
	{
		myPowerLevel = powerLevel;
	}
	
	public void increaseMyPower(double increasePowerLevel)
	//REQUIRES: NONE
	//MODIFIES: myPowerLevel
	//EFFECTS: increase the value in myPowerLevel by increasePowerLevel
	{
		myPowerLevel += increasePowerLevel;
	}
	
	public void decreaseMyPower(double decreasePowerLevel)
	//REQUIRES: NONE
	//MODIFIES: myPowerLevel
	//EFFECTS: decrease the value in myPowerLevel by decreasePowerLevel
	{
		myPowerLevel -= decreasePowerLevel;
	}

}
