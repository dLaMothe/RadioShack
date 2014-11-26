package gui; // <---- ? Interface , GUI, ...?
import board.*;
import static settings.Configs.*;
/*Author Matti
 * 
 * WHAT IS STILL MISSING
 *   There is a prepared and ready to go Integer array shipLRangeSenGrid[j][n];
 *   that array's j-index from 0,1,2,...,9 indicates as follows the 
 *   player-ships-LongRangeSensores : 
 *   
 *   Top Left : 0,  		Top Middle : 1, 			Top Right : 2,
 *   Middle Left : 3, 	PlayerShip's Location : 4, 	Middle Right : 5,
 *   Bottom Left : 6, 	Bottom Middle : 7, 			Middle Left :8.
 *   
 *  Where as, the n-index from 0,1,2 indicates as follows the number of 
 *   Station(s), Star(s), Jovian(s) in each respective j-index. 
 *   Yet, should that quadrant grid not exist (index value was outside quadrant[][])
 *   then all values for Station, Star, and Jovian is set to 
 *   Configs.SENSOR_SCAN_SENTINEL.
 *   
 *    The Short Range Sensors were a byproduct from calculating Lone Range 
 *   Sensors: They can be located as the above mentioned "PlayerShip's Location"
 *   ; additionally, PlayerShip's Location is the j-index value 4. 
 * 
 * --START OF OVERVIEW
 * 
 * Overview: " How to use " in steps 
 * 1) Call the function Sense(); There are 3 versions for convenience sakes:
 * a) takes 2 int as parameters : row, column location of 
 * 		player ship in quadrant grid
 * b) takes 1 int array as parameters : index 0 defines the row, index 1 defines
 * 		the column; and, together they define the player ship locations in 
 * 		quadrant grid * 		
 * c) takes the Object player_ship as a parameter. It relies on that the 
 *  	Object "player_ship" has Position : player_ship.Position; in that way
 *  	player_ship.Position.getRow() & ... .getCol() can be utilised 
 *  	to define the player ship location in quadrant grid.
 * 
 * THE REST IS AUTOMATIC: steps 2 is automation . 
 * 
 * 2) the rest is completed by functions that are invoked by "Sense()"  
 * 2.1) function 1: fetchSurQuadInexes() :
 * 	the int array shipSensorGrid[][] defines all quadrant grids around 
 * 	player's ship; more precisely, any quadrant grid cell that is connected
 *  to the quadrant grid cell that the player ship is in is calculated 
 *  and the row index value , thus, placed in shipSensorGrid[][]
 * 	 	
 * 2.2) function 2: tagArrayBorderLimits() :
 *  For all values captured into fetchSurQuadInexes[][i] 
 *  should any value "i" be outside larger than SPACE_SIZE or smaller than 0 
 *  then that value gets replaced with Configs.SENSOR_SENTINEL value.
 * 2.3) function 3: FillSurQuad() : 
 *  Depending on content of the array localShipQuadGrid[10][3] the array
 *  shipLRangeSenGrid[10][3] is populated with the numbers of sought
 *  items in space. Example (3, 2, 0) indicates 3 stations, 2 stars, 0 Jovian.
 * 	
 * TESTING
 * 
 * Testing: 
 * 	tagArrayBorderLimits() has been tested with test benching of values
 * testing limits;
 * 	fetchSurQuadInexes() has been tested via theory (math) yet not 
 * test benching.
 * 
 * Unable to Test:
 *  FillSurQuad(), for I require external parts of project: 
 *  	does "Space.getInstance().getQuadrant(new Position(i,k))
 *				.getQuadrantData().getStations();"
 *		really work or is it just theory. 
 * */
 
//REQUIRED IN CONFIGS file
//int Configs.NUM_SENSED_GRIDS = 9;
//int Configs.DIMENTIONS = 2;
//int Configs.NUM_ITEM_PER_SENORS_SLOT = 3; 
//int Configs.SENSOR_SCAN_SENTINEL = -1;
//int Configs.SPACE_SIZE = 10 

 
public class Sensors {
	/*Presumed constants exist within Configs.Configs*/
		
	// Via ship's location in quadrant fill long range Sensors
	int shipCurLocByQuad[];// = {0,0}; // getShipLocation();
	
	// for calculating what the surrounding quadrants are 
	/*Integer shipsQuadArea[][] =	{
			{},{},	{},
			{},{0,0},{}, //< compared surrounded by adjacent sectors 
			{},{},	{}
		}; 
	*/
	Integer localShipQuadGrid[][] 
		= new Integer[NUM_SENSED_GRIDS][DIMENTIONS];
	Integer shipLRangeSenGrid[][] 
		= new Integer[NUM_SENSED_GRIDS]
				[NUM_ITEM_PER_SENORS_SLOT];
	
	
	public void Sense(int rowByCol[]){
		/* REQUIRES : int array containing by incrementing index: row , column;
		 * 	that row and column define the location of player ship in Quadrant
		 * grid.
		 * MODIFIES : this. shipCurLocByQuad
		 * EFFECTS : defines the current location of player ship into 
		 * 	this.shipCurLocByQuad;
		 * 	Then it invokes  fetchSurQuadInexes followed by tagArrayBorderLimits
		 * that in short fetch the values of the surrounding Quadrant grids 
		 * with respect to player ship
		 * */
		shipCurLocByQuad[0] = rowByCol[0];
		shipCurLocByQuad[1] = rowByCol[1];
		this.fetchSurQuadInexes();
		this.tagArrayBorderLimits();
		this.FillSurQuad();
	}
	
	public void Sense(int row, int column){
		/* REQUIRES : two int values defining row and column; that row and 
		 * 	column define the location of player ship in Quadrant grid.
		 * MODIFIES : this. shipCurLocByQuad;
		 * EFFECTS : word by word exactly the same effect as 
		 * this.Sense(int rowByCol[]){...};
		 * */
		shipCurLocByQuad[0] = row;
		shipCurLocByQuad[1] = column;
		this.fetchSurQuadInexes();
		this.tagArrayBorderLimits();
		this.FillSurQuad();
	}
	
	/*public void Sense(Object PlayerShip){
		 /*REQUIRES : the Playerships itself such that it contains Position:
		 * PlayerShip.Position. Assuming that is true then Position should
		 * offer access to Posistion.getRow() and Position.getCol();
		 * MODIFIES : this. shipCurLocByQuad;
		 * EFFECTS : word by word exactly the same effect as 
		 * this.Sense(int rowByCol[]){...};
		 *
		this.shipCurLocByQuad[0] 
			= PlayerShip.Position.getRow(); //may be improper use of "Position"
		this.shipCurLocByQuad[1] = 
			= PlayerShip.Position.getCol(); //may be improper use of "Position"
		this.fetchSurQuadInexes();
		this.tagArrayBorderLimits();
		this.FillSurQuad();
	}*/
	
	private void FillSurQuad(){
		/* REQUIRES: to be called after the 2 following functions 
		 * 	this.fetchSurQuadInexes() & this.tagArrayBorderLimits();
		 * MODIFIES: this.shipLRangeSenGrid[][] .
		 * EFFECTS : shipSensorGrid is populated with relevant data.
		 *   */
		for (int i = 0; i < NUM_SENSED_GRIDS; i++){
			for (int k = 0; k < DIMENTIONS; k++){			
				/* 2 Possibilities : 1) index defined by i and k and outside 
				 * quadrant grid : */
				if(localShipQuadGrid[i][0] == SENSOR_SCAN_SENTINEL
				|| localShipQuadGrid[i][1] == SENSOR_SCAN_SENTINEL){
					/* if either index is outside of grid: */
					for(int o = 0; o < NUM_ITEM_PER_SENORS_SLOT; o++){
						/*shipLRangeSenGrid[i][o] = Configs.OUTER_LIM_OF_MAP_STR;*/
						shipLRangeSenGrid[i][o] = SENSOR_SCAN_SENTINEL;
					}
					k = DIMENTIONS; // done once : exits k-for-loop.
				}
				else{
					/* possibility 2: i & k are both inside of quadrant gird; 
					 * i = row , and k = column entry in said grid */
					shipLRangeSenGrid[i][0] = 
						Space.getInstance().getQuadrant(new Position(i,k))
							.getQuadrantData().getStations();					
					shipLRangeSenGrid[i][1] = 
						Space.getInstance().getQuadrant(new Position(i,k))
							.getQuadrantData().getStars();
					shipLRangeSenGrid[i][2] = 
						Space.getInstance().getQuadrant(new Position(i,k))
							.getQuadrantData().getJovians();					
				}
			}	
		}
	}		
	
	//1
	private void fetchSurQuadInexes(){
		//REQUIRES that the ships location has been defined   
		//MODIFIES this.shipsQuadArea
		//EFFECTS depending on ships index location in quadrant array,
		// the quadrants surrounding player ship are calculated.		
		
		// top left sensor/quadrant
		localShipQuadGrid[0][0] =  (shipCurLocByQuad[0] - 1);
		localShipQuadGrid[0][1] =  (shipCurLocByQuad[0] - 1);
		// top middle sensor/quadrant
		localShipQuadGrid[1][0] =  (shipCurLocByQuad[1] - 1);
		localShipQuadGrid[1][1] =  (shipCurLocByQuad[1] - 0);
		// top right-side sensor/quadrant
		localShipQuadGrid[2][0] =  (shipCurLocByQuad[2] - 1);
		localShipQuadGrid[2][1] =  (shipCurLocByQuad[2] + 1);
		// middle left sensor/quadrant
		localShipQuadGrid[3][0] =  (shipCurLocByQuad[3] + 0);
		localShipQuadGrid[3][1] =  (shipCurLocByQuad[3] - 1);
		// middle : the ship is in this quadrant
		localShipQuadGrid[4][0] =  (shipCurLocByQuad[4] + 0);
		localShipQuadGrid[4][1] =  (shipCurLocByQuad[4] + 0);
		// middle right-side sensor/quadrant
		localShipQuadGrid[5][0] =  (shipCurLocByQuad[5] + 0);
		localShipQuadGrid[5][1] =  (shipCurLocByQuad[5] + 1);
		// bottom left sensor/quadrant
		localShipQuadGrid[6][0] =  (shipCurLocByQuad[6] + 1);
		localShipQuadGrid[6][1] =  (shipCurLocByQuad[6] - 1);
		// bottom middle sensor/quadrant
		localShipQuadGrid[7][0] =  (shipCurLocByQuad[7] + 1);
		localShipQuadGrid[7][1] =  (shipCurLocByQuad[7] + 0);
		// bottom right-side sensor/quadrant
		localShipQuadGrid[8][0] =  (shipCurLocByQuad[8] + 1);
		localShipQuadGrid[8][1] =  (shipCurLocByQuad[8] + 1);			
		return;
	}
	
	//2
	private void tagArrayBorderLimits(){
		//REQUIRES 
		//MODIFIES this.shipsQuadArea
		//EFFECTS depending on Quadrant array size;
		// the lower and upper limits are tagged with 
		for(int i = 0; i < 	NUM_SENSED_GRIDS; i++){
			// was the index value outside of map (too small)
			for(int k = 0; k < DIMENTIONS; k++){
				if (localShipQuadGrid[i][k] < 0){ /* > 10-1*/
					localShipQuadGrid[i][k] = SENSOR_SCAN_SENTINEL;
				} 				 
				// was the index value outside of map (too large)			
				if (localShipQuadGrid[i][k] > SPACE_SIZE-1){
					localShipQuadGrid[i][k] = SENSOR_SCAN_SENTINEL;
				} 			
			}			 
		}	
	}
}
