
    
package gameObjects;

import static settings.Configs.*;
import java.util.ArrayList;
import settings.Configs;
import board.Sector;

/**
 * Overview: This class provides a SpaceObject that the player can move around
 * and use to shoot at other SpaceObjects.  This provides an interface for the 
 * game logic to convert user input into the corresponding behavior
 * @author Michael Koonts
 *
 */
class Ship extends SpaceObject implements Movable{
    
    private static final int MAGNITUDE = 0;
	private static final int MAX_ION = 10;
	public static final int DIRECTION = 0;
	private final PowerSystem systems;
    /**
     * REQUIRES: @param sec - see super class for requirements
     * MODIFIES: the game state
     * EFFECTS: Creates a SpaceObject which the player will be in control of
     */
    public Ship(Sector sec) {
        super(sec);
        systems = new PowerSystem(this);
        label = "^";
        
    }
    
    /**
     * REQUIRES:  @param type - be a valid weapon type *see settings.Configs
 	 *			  @param direction - be a valid direction *see settings.Configs
     * MODIFIES: game state
     * EFFECTS: shoots a weapon of the given type in the given direction
     *      A typical call would look like shootWeapon(MASER, NW);
     *      using the class settings.Configs to value to MASER and NW
     *     if there is sufficient power the weapon system will shoot
     */
    public void shootWeapon(int type, int direction){
        if(Configs.INIT_MASER == type){
        	((MaserCannon) systems.getSystem(type)).arm(direction);
        }else{
        	((Launchers) systems.getSystem(LAUNCHER)).loadtube(type, direction);
        }
    }
    
    /**
     * REQUIRES: @param type - be a valid engine type *see settings.Configs
     * 			 @param velocity[MAGNITUDE] >= 0
     * 			 @param velocity[DIRECTION] - be a valid direction *see settings.Configs
     * MODIFIES: this, game state
     * EFFECTS: if the given engine has enough power it will be activated and the ship 
     * will begin moving on the given vector.
     *       Note: To stop the ship's current movement:  @param type - the active engine
	 *									      			 @param velocity[MAGNITUDE] = 0
	 *  								      			 @param velocity[DIRECTION] = Configs.NEUTRAL
     */
    private void setEngineState(int type, int[] velocity){
        if(systems.getSystem(type).getPower() > MIN_SYSTEM_POWER){
        	((Engine) systems.getSystem(type)).setActive(velocity);
        	//Check and see if the other engine is on, and if it is turn it off
        	if(Configs.ION == type && ((Engine) systems.getSystem(Configs.HYPER)).getActive()){
        		((Engine) systems.getSystem(Configs.HYPER)).setActive(new int[]{0, Configs.NEUTRAL});
        	}else if(Configs.HYPER == type && ((Engine) systems.getSystem(Configs.ION)).getActive()){
        		((Engine) systems.getSystem(Configs.ION)).setActive(new int[]{0, Configs.NEUTRAL});
        	}
        }
    }
    
    /**
     * REQUIRES: nothing
     * MODIFIES: this
     * EFFECTS: restores the number of missiles and antimatter pods to maximum capacity
     *          and replenishes the power system
     */
    public void reload(){
        ((Launchers) systems.getSystem(LAUNCHER)).reload();
        systems.recharge(MAX_POWER);
    }
    
    /**
     * REQUIRES: nothing
     * MODIFIES: this
     * EFFECTS: reduces the amount of available power.  The amount of power drained 
     *      depends on the amount of power directed to the shields and the constant 
     *  	amount declared in settings.Configs
     * 
     */
    public void sapPower(){
        try {
			systems.sapPower();
		} catch (CriticalPowerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * REQUIRES: @param system - be a valid system id *see settings.Configs
     *			 @param level - be a value such that the net power drain 
     *							will be less than the power available
     * MODIFIES: this
     * EFFECTS: changes the amount of power available to the given system
     */
    public void adjustPower(int system, double level){
        this.systems.setSystemLevel(system, level);
    }

    /**
     * REQUIRES: nothing
     * MODIFIES: nothing
     * EFFECTS: @return a pointer to the ships current sector
     */
    public Sector getSector(){
        return sector;
    }
    
    /**
     * REQUIRES: nothing
     * MODIFIES: game state
     * EFFECTS: implements all player commands currently queued up 
     * This will have different results depending on what the player has 
     * asked the ship to do ie. move, shoot, etc.
     */
    @Override
    public void move() {
        systems.cycle();
    }
    
    /**
     * REQUIRES: @param velocity[MAGNITUDE] >= 0
     * 			 @param velocity[DIRECTION] - be a valid direction *see settings.Configs
     * MODIFIES: this
     * EFFECTS: The next time Ship.move() is called on this it will begin moving on the given vector
     * Note: To stop the ship's current movement:  @param type - the active engine
	 *									      	   @param velocity[MAGNITUDE] = 0
	 *  								      	   @param velocity[DIRECTION] = Configs.NEUTRAL
     */
    @Override
    public void setSpeed(int[] velocity) {
    	if(velocity[MAGNITUDE] < MAX_ION){
    		this.setEngineState(ION, velocity);
    	}else{
    		this.setEngineState(HYPER, velocity);
    	}
    }
    
    private class PowerSystem {
    	/**
    	 * Overview: This class provides an abstraction to the ship that encapsulates
    	 * everything that requires power in order to function.  It provides a simple set 
    	 * of methods that allow the ship to function intuitively.  If the system 
    	 * requested to act doesn't have sufficient power it simply does nothing.  If the 
    	 * normal progression of the game causes the power system to become overloaded 
    	 * (the total power usage by all systems exceeds the power available)an exception
    	 * is thrown
    	 */
    	private double powerAvailable;
    	private double powerConsumed;
    	private ArrayList<ShipSystem> systems;
    	private Ship ship;
    	
    	/**
    	 * REQUIRES: @param ship - a pointer to the ship that this is a part of
    	 * MODIFIES: the state of @param ship
    	 * EFFECTS: Initializes the ships power system with the default values in 
    	 * settings.Configs
    	 */
    	public PowerSystem(Ship ship){
    		this.ship = ship;
    		this.powerAvailable = MAX_POWER;
    		this.powerConsumed = 0.0;
    		systems = new ArrayList<ShipSystem>();
    		systems.add(SHIELD, new Ship.Sheilds(INIT_SHIELD));
    		systems.add(LRSENSOR, new Ship.LRSensors(INIT_LRSENSOR));
	        systems.add(SRSENSOR, new Ship.SRSensors(INIT_SRSENSOR));
	        systems.add(MASER, new Ship.MaserCannon(INIT_MASER));
	        systems.add(LAUNCHER, new Ship.Launchers(INIT_TRT_MISSILE));
			systems.add(ION, new Ship.IonEngines (INIT_ENGINE));
			systems.add(HYPER, new Ship.HypEngines (INIT_ENGINE));
	        
    	}
    	
    	/**
         * REQUIRES: nothing
         * MODIFIES: game state
         * EFFECTS: cycles through all ship systems and calls their act method.
         * 			This will have different results depending on what the player has 
         * 			asked the ship to do ie. move, shoot, etc.
         */
    	public void cycle() {
			for(ShipSystem sys: systems){
				sys.act();
			}
		}

    	/**
         * REQUIRES: nothing
         * MODIFIES: nothing
         * EFFECTS: @returns the amount of power currently available to the overall system
         */
    	public double getPowerAvailable(){
    		return this.powerAvailable;
    	}
    	
    	/**
         * REQUIRES: @param power >= 0
         * MODIFIES: this
         * EFFECTS: Adds the amount given the the power available to the overall system
         */
    	public void recharge(double power){
    		if(this.powerAvailable + power <= MAX_POWER){
    			this.powerAvailable += power;
    		}else if(this.powerAvailable + power > MAX_POWER){
    			this.powerAvailable = MAX_POWER;
    		}
    		
    	}
    	
    	/**
         * REQUIRES: nothing
         * MODIFIES: nothing
         * EFFECTS: returns the amount of power currently being used by all ship systems
         */
    	public double getPowerConsumed(){
    		return this.powerConsumed;
    	}
    	
    	/**
         * REQUIRES: @param system - a valid system id *see settings.Configs
         * 			 @param powerLevel - be a value that won't cause the power 
         * 								 consumed to exceed the current power available
         * MODIFIES: the given ship system
         * EFFECTS: Sets power available to the given system to the given value and recalculates
         * the current drain on the overall system.
         */
    	public void setSystemLevel(int system, double powerLevel){
    		double loadIncrease = powerLevel - systems.get(system).getPower();
    		if(this.powerAvailable > (this.powerConsumed + loadIncrease)){
    			systems.get(system).setPower(powerLevel);
    			try {
					this.calculateSystemLoad();
				} catch (CriticalPowerException e) {
					e.printStackTrace();
					System.exit(1);
				}
    		}
    	}
    	
    	/**
         * REQUIRES: @param type - be a valid system id *see settings.Configs
         * MODIFIES: nothing
         * EFFECTS: return a pointer to the system requested in type
         */
		public ShipSystem getSystem(int type) {
			return systems.get(type);
		}
		
		/**
	     * REQUIRES: @param type - a valid system id *see settings.Configs
	     * 			 @param system - an initialized Ship.ShipSystem
	     * MODIFIES: this
	     * EFFECTS: replace system with the given one
	     */
		public void setSystem(int type, ShipSystem system) {
			this.systems.add(type, system);
		}
		
		/**
	     * REQUIRES: nothing
	     * MODIFIES: this
	     * EFFECTS: drains the amount Configs.POWER_SAP from the power available
	     * If the ship's shields are active both the shields power and the power available
	     * are drained, but by a lesser amount.  If the sap results in the load on the power system 
	     * exceeding the power available a criticalPowerException will be thrown
	     * 
	     */
		public void sapPower() throws CriticalPowerException{
			this.powerAvailable -= Configs.POWER_SAP;
			this.calculateSystemLoad();
		}
		
		/**
	     * REQUIRES: nothing
	     * MODIFIES: this.powerConsumed
	     * EFFECTS: Calculate the gross load on the power system.  Iterates
	     * through all systems and adds up their power setting.  If that number exceeds
	     * the power available, this throws the exception 
	     */
		private void calculateSystemLoad() throws CriticalPowerException{
			double load = 0;
			for(ShipSystem i: systems){
				load += i.getPower();
			}
			this.powerConsumed = load;
			if(this.powerConsumed > this.powerAvailable){
				throw new CriticalPowerException();
			}
		}
    }
    
    private abstract class ShipSystem{
    	/**
    	 * Overview: This class provides a common interface for all ship systems to the 
    	 * the power system.  All systems need to store the amount of power available to 
    	 * them, and need an interface to check and change that value. They also require 
    	 * a common interface that will cause the specific ship system to do whatever it 
    	 * is intended to do.  ie. the engines move the ship
    	 */
    	private double power;
    	
    	/**
	     * REQUIRES: @param powerLevel >= 0
	     * MODIFIES: this
	     * EFFECTS: initializes a new ShipSystem and sets the amount of 
	     * power available to it to value given
	     */
    	public ShipSystem(double powerLevel){
    		power = powerLevel;
    	}
    	
    	/**
         * REQUIRES: nothing
         * MODIFIES: nothing
         * EFFECTS: @returns the amount of power assigned to this
         */
		public double getPower() {
			return power;
		}
		
		/**
	     * REQUIRES: @param powerLevel >= 0
	     * MODIFIES: this
	     * EFFECTS: sets the amount of power available to this to value given
	     */
		public void setPower(double powerLevel) {
			power = powerLevel;			
		}
		
		/**
	     * REQUIRES: nothing
	     * MODIFIES: see individual overrides
	     * EFFECTS: see individual overrides
	     */
    	public abstract void act();
    }
    
    private class Sheilds extends ShipSystem{

    	/**
	     * REQUIRES:
	     * MODIFIES:
	     * EFFECTS:
	     */
		public Sheilds(double powerLevel) {
			super(powerLevel);
			// TODO Auto-generated constructor stub
		}
		/**
	     * REQUIRES:
	     * MODIFIES:
	     * EFFECTS:
	     */
		@Override
		public void act() {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    private abstract class Engine extends ShipSystem{

    	/**
    	 * Overview: This class provides a common engine interface.  All 
    	 * engines no matter their type need to be turned on, and be pointed in a
    	 * useful direction.  They also need to provide their user with the 
    	 * ability to check their state.  However, different engines will have different 
    	 * consequences resulting from their active state.
    	 */
		protected boolean active;
		protected int direction;

		/**
	     * REQUIRES: @param powerLevel - see super
	     * MODIFIES: this
	     * EFFECTS: returns an initialized engine that is currently not active
	     */
		public Engine(double powerLevel) {
			super(powerLevel);
			this.active = false;
			this.direction = NEUTRAL;
		}
		
		/**
	     * REQUIRES: @param velocity[Ship.MAGNITUDE] >= 0
	     * 			 @param velocity[Ship.DIRECTION] - must be a valid value as 
	     * 											   outlined in settings.Configs
	     * MODIFIES: this
	     * EFFECTS: Changes the state of this to reflect the given vector
	     * Note: the vector {0, Configs.NEUTRAL} sets this inactive ie. turns the engine off
	     */
		public void setActive(int[] velocity){
			if (0 == velocity[Ship.MAGNITUDE]) {
				this.active = false;
			}else{
				this.active = true;
			}
			this.direction = velocity[Ship.DIRECTION];
		}
		
		/**
	     * REQUIRES: nothing
	     * MODIFIES: nothing
	     * EFFECTS: @returns the active state of this
	     */
		public boolean getActive(){
			return active;
		}
		
		/**
	     * REQUIRES: nothing
	     * MODIFIES: nothing
	     * EFFECTS: @returns the direction this is currently pointed in
	     */
		public int getDirection(){
			return this.direction;
		}
    	
    }
    
    private class IonEngines extends Engine{

    	/**
    	 * Overview: This class implements the abstract class Engine and 
    	 * if given sufficient power and a valid vector will move the ship 
    	 * within the current quadrant.  If this is left active long enough 
    	 * it will move the ship to the next quadrant on that vector before 
    	 * deactivating itself.  The value of this.throttle effects the rate
    	 * that the ship will move through the current quadrant.
    	 */
    	private int throttle;
    	
    	/**
	     * REQUIRES: see super @param powerLevel
	     * MODIFIES: this
	     * EFFECTS: Initializes a new this and sets the throttle to 0
	     */
		public IonEngines(double powerLevel) {
			super(powerLevel);
			this.throttle = 0;
		}
		
		/**
	     * REQUIRES: nothing
	     * MODIFIES: ship location
	     * EFFECTS: the location of the ship in the current quadrant
	     * If the current vector takes the ship out of the current quadrant moves to the appropriate
	     * quadrent and deactivates this 
	     */
		@Override
		public void act() {
			// TODO Auto-generated method stub
			
		}
		
		/**
	     * REQUIRES: see super
	     * MODIFIES: this
	     * EFFECTS: see super, sets values outside the scope of the super method
	     */
		@Override
		public void setActive(int[] velocity){
			super.setActive(velocity);
			this.throttle = velocity[Ship.MAGNITUDE];
		}
		
		/**
	     * REQUIRES: nothing
	     * MODIFIES: nothing
	     * EFFECTS: @returns the current throttle position
	     */
		public int getThrottle(){
			return this.throttle;
		}
    }
    
    private class HypEngines extends Engine{

    	/**
    	 * Overview: This class implements the abstract class Engine and 
    	 * if given sufficient power and a valid vector will move the ship 
    	 * to the next quadrant in the direction indicated.  This engine will
    	 * remain active until the user shuts it off.
    	 */
    	
    	/**
	     * REQUIRES: see super @param powerLevel
	     * MODIFIES: this
	     * EFFECTS: Initializes a new this
	     */
		public HypEngines(double powerLevel) {
			super(powerLevel);
		}
		
		/**
	     * REQUIRES: nothing
	     * MODIFIES: the ships location
	     * EFFECTS: If this has sufficient power move the ship to the next 
	     * quadrant in the current direction
	     */
		@Override
		public void act() {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    private class LRSensors extends ShipSystem{

    	/**
	     * REQUIRES:
	     * MODIFIES:
	     * EFFECTS:
	     */
		public LRSensors(double powerLevel) {
			super(powerLevel);
			// TODO Auto-generated constructor stub
		}

		/**
	     * REQUIRES:
	     * MODIFIES:
	     * EFFECTS:
	     */
		@Override
		public void act() {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    private class SRSensors extends ShipSystem{

    	/**
	     * REQUIRES:
	     * MODIFIES:
	     * EFFECTS:
	     */
		public SRSensors(double powerLevel) {
			super(powerLevel);
			// TODO Auto-generated constructor stub
		}

		/**
	     * REQUIRES:
	     * MODIFIES:
	     * EFFECTS:
	     */
		@Override
		public void act() {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    private class Launchers extends ShipSystem{
    	
    	/**
    	 * Overview: This class provides the user with the ability to 
    	 * launch different types of projectile weapons in a given 
    	 * direction.  This is the storage and launching mechanism for 
    	 * weapons like triton missiles.
    	 */
    	private static final int NONE = 0;
		private int antimatterPods;
    	private int trtmissiles;
		private int type;
		private int direction;
		
		/**
	     * REQUIRES: see super @param powerLevel
	     * MODIFIES: this
	     * EFFECTS: create a new this a load it to the capacity set in 
	     * settings.Configs
	     */
		public Launchers(double powerLevel) {
			super(powerLevel);
			antimatterPods = ANTIMATTER_PODS;
			trtmissiles = TRT_MISSILES;
			type = NONE;
			direction = NEUTRAL;
		}
		
		/**
	     * REQUIRES: nothing
	     * MODIFIES: game state
	     * EFFECTS: If there is a weapon "in the tube" launch it
	     */
		@Override
		public void act() {
			if (this.getPower() >= Configs.MIN_SYSTEM_POWER) {
				this.launch();
			}			
		}
		
		/**
	     * REQUIRES: nothing (future: this should check if there is 
	     * a space station in an adjoining sector) 
	     * MODIFIES: this
	     * EFFECTS: resets the number of projectile weapons available to their
	     * maximum values as defined in settings.Configs
	     */
		public void reload(){
			antimatterPods = ANTIMATTER_PODS;
			trtmissiles = TRT_MISSILES;
		}
		
		/**
	     * REQUIRES: @param type - must be a valid projectile type *see settings.Configs
	     * 			 @param direction - must be a valid direction *see settings.Configs
	     * 							  - must not be settings.Configs.NEUTRAL 
	     * MODIFIES: this
	     * EFFECTS: Prepare the system to launch a projectile weapon in the direction indicated
	     * ie. "load the tube"
	     */
    	public void loadtube(int type, int direction){
    		this.type = type;
    		this.direction = direction;
    	}
		
    	/**
	     * REQUIRES: nothing
	     * MODIFIES: game state
	     * EFFECTS: Instantiate the weapon "in the tube" with the information available 
	     * ie. shoot the weapon
	     */
		private void launch(){
			if(Configs.ANITMATTER_POD == this.type && 0 < this.antimatterPods){
				//TODO call antimatterPod constructor
				this.antimatterPods--;
			}else if(Configs.TRT_MISSILE == this.type && Configs.NEUTRAL != this.direction && 0 < this.trtmissiles){
				//TODO new TritonMissile(direction);
				this.trtmissiles--;
			}
			this.type = Launchers.NONE;
			this.direction = Configs.NEUTRAL;
		}
    }
    
    private class MaserCannon extends ShipSystem{
    	
    	/**
    	 * Overview: This class provides the user with the ability to shoot
    	 * maser shots in the direction that they choose.  If they have allocated 
    	 * sufficient power to this.
    	 */

		private int direction;

		/**
	     * REQUIRES: see super @param powerLevel
	     * MODIFIES: this
	     * EFFECTS: Initializes a new this in a safe state
	     */
		public MaserCannon(double powerLevel) {
			super(powerLevel);
			this.direction = Configs.NEUTRAL;
		}

		/**
	     * REQUIRES: nothing
	     * MODIFIES: game state
	     * EFFECTS: If there is sufficient power in this and this is "armed", shoot this
	     */
		@Override
		public void act() {
			if(this.getPower() >= Configs.MIN_SYSTEM_POWER){
				this.shoot();
			}	
		}
    	
		/**
	     * REQUIRES: caller must check the power level before calling
	     * MODIFIES: game state and this
	     * EFFECTS: If this is "armed" instantiate a maser shot in the this.direction
	     * and then disarm this.
	     * Note: "armed" means that Configs.NEUTRAL != this.direction
	     */
		private void shoot() {
			if(Configs.NEUTRAL != direction){
				//new Maser(direction, sector);
			}
			this.direction = Configs.NEUTRAL;
		}
		
		/**
	     * REQUIRES: @param direction - must be a valid direction as defined in settings.Configs
	     * MODIFIES: this
	     * EFFECTS: Sets this to armed status
	     */
		public void arm(int direction) {
			this.direction = direction;		
		}
    }
}
