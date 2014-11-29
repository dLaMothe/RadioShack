//package console; // <---- ? Interface , GUI, ...?
import board.Quadrant;
import board.Space;
import settings.Configs.*;

/*Author Matti
 * 
 *  --START OF OVERVIEW
 * 
 * 	  Invoke this via 3 different possible approaches of Sense():
 * 	 2 integer paramters (row by column),
 * 	 an array that contains 2 integer variables (index 0: row. index 1: column),
 * 	 ship itself as an object (assuming that ship has possition : Ship.Postion)	
 *    
 *   after that call there is a prepared and ready to go Integer array 
 *   shipLRangeSenGrid[j][n];
 *   
 *   that array's j-index from 0,1,2,...,9 indicates as follows the 
 *   player-ships-LongRangeSensores : 
 *   
 *   Top Left : 0,  		Top Middle : 1, 			Top Right : 2,
 *   Middle Left : 3, 	PlayerShip's Location : 4, 	Middle Right : 5,
 *   Bottom Left : 6, 	Bottom Middle : 7, 			Middle Left :8.
 *   
 *   that arrays's n-index from 0 to 2 indeicates as follows the 
 *   content of each j-indexed items (Jovian, Station, Stars) as an integer.
 *   
 *    Should that quadrant grid not exist (index value was outside quadrant[][])
 *   then all values for Jovian, Station, and Star is set to 
 *   Configs.SENSOR_SCAN_SENTINEL.
 *   
 *    The Short Range Sensors were a byproduct from calculating Lone Range 
 *   Sensors: They can be located as the above mentioned "PlayerShip's Location"
 *   ; additionally, PlayerShip's Location is the j-index value 4. 
 * 
	** SPECIFICS ARE BELOW AND DO NOT HAVE TO BE READ ** * 
 * Overview: " How to use " in steps 
 * 1) Call the function Sense(); There are 3 versions for convenience sakes:
 * 
 * * THE REST IS AUTOMATIC: steps 2 is automation . 
 * 
 * 2) the rest is completed by functions that are invoked by the 
 * function "Sense()"
 *   
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
 
//COPIES OF REQUIRED VALUES IN CONFIGS FILE; DELETE Comment
//COPIES OF REQUIRED VALUES IN CONFIGS FILE DELETE Comment
//int Configs.NUM_SENSED_GRIDS = 9;  DELETE Comment
//int Configs.DIMENTIONS = 2;  DELETE Comment
//int Configs.NUM_ITEM_PER_SENORS_SLOT = 3; DELETE Comment 
//int Configs.SENSOR_SCAN_SENTINEL = -1; DELETE Comment
//int Configs.SPACE_SIZE = 10 DELETE Comment

public class Sensors {	
	private int shipCurLocByQuad[];	
	
	private int localShipQuadGrid[][] 
		= new int[Configs.NUM_SENSED_GRIDS][Configs.DIMENTIONS];
	
	public int shipLRangeSenGrid[][] 
		= new int[Configs.NUM_SENSED_GRIDS]
				[Configs.NUM_ITEM_PER_SENORS_SLOT];	
	
	private ArrayList<SpaceObject> Stuff;
	
	public void Sense(int rowByCol[]){
		/* REQUIRES : int array containing by incrementing index: row , column;
		 * 	that row and column define the location of player ship in Quadrant
		 * grid.
		 * MODIFIES : this. shipCurLocByQuad
		 * EFFECTS : defines the current location of player ship into 
		 * 	this.shipCurLocByQuad;
		 * 	Then it invokes  fetchSurQuadInexes followed by tagArrayBorderLimits
		 * that in short fetch the values of the surrounding Quadrant grids 
		 * with respect to player ship */
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
		 * this.Sense(int rowByCol[]){...}; */
		shipCurLocByQuad[0] = row;
		shipCurLocByQuad[1] = column;
		this.fetchSurQuadInexes();
		this.tagArrayBorderLimits();
		this.FillSurQuad();
	}
	
	public void Sense(Ship PlaShip){	//MISSING IMPORT?
		/* REQUIRES : the Playerships itself such that it contains Position:
		 * PlayerShip.Position. Assuming that is true then Position should
		 * offer access to Posistion.getRow() and Position.getCol();
		 * MODIFIES : this. shipCurLocByQuad;
		 * EFFECTS : word by word exactly the same effect as 
		 * this.Sense(int rowByCol[]){...};
		 * */		
		this.shipCurLocByQuad[0] 
			= PlayerShip.Position.getRow();  //NOT TESTED
		this.shipCurLocByQuad[1] = 
			= PlayerShip.Position.getCol();  //NOT TESTED
		this.fetchSurQuadInexes();
		this.tagArrayBorderLimits();
		this.FillSurQuad();
	}
	
	private void FillSurQuad(){				
		/* REQUIRES: to be invoked after the 2 following functions 
		 * 	this.fetchSurQuadInexes() & this.tagArrayBorderLimits();
		 * MODIFIES: this.shipLRangeSenGrid[][] .
		 * EFFECTS : shipLRangeSenGrid[][] is populated with the following:
		 *  if the i (row) j (column) index values are outside 
		 *  minimum or maximum Quadrant[i][j] array index then
		 *  the value Configs.SENSOR_SCAN_SENTINEL is placed in that
		 *  sensor location.
		 *  ELSE
		 *  The index is inside Quadrant[i][j] are querried for
		 *  ammount of Jovians, Stations, and Stars.
		 * */
		for (int i = 0; i < Configs.NUM_SENSED_GRIDS; i++){
			for (int j = 0; j < Configs.DIMENTIONS; j++){			
				/* 2 Possibilities : 
				 * 1) index defined by i and j and outside 
				 * Quadrant[][] grid : */
				if(localShipQuadGrid[i][0] == Configs.SENSOR_SCAN_SENTINEL
				|| localShipQuadGrid[i][1] == Configs.SENSOR_SCAN_SENTINEL){
					/* if either index is outside of grid: */
					for(int k = 0; k < Configs.NUM_ITEM_PER_SENORS_SLOT; k++){						
						shipLRangeSenGrid[i][k] = Configs.SENSOR_SCAN_SENTINEL;
					}
					/* only needs to be done once */
					j = Configs.DIMENTIONS; 
				}
				else{
					/* 2) i & j are both inside of quadrant gird; 
					 * i = row , and j = column entry in said grid */
					Stuff = Space.getInstance()
							.getQuadrant(i, j)
							.getGeneratedObjects();
					for (SpaceObject i: Stuff){
						if(i isinstanceof Jovian){
							shipLRangeSenGrid[i][0]++;
						}if(i isinstanceof Station){
							shipLRangeSenGrid[i][1]++;
						}if(i isinstanceof Star){
							shipLRangeSenGrid[i][2]++;
						}
					}  							
				}
			}	
		}
	}		
	
	private void fetchSurQuadInexes(){
		/* REQUIRES that the ships location has been defined :
		 *  shipCurLocByQuad[] .
		 * MODIFIES this.localShipQuadGrid[][] . 
		 * EFFECTS depending on the defined ship's index 
		 *  location in within Quadrant[][] array, 
		 *  the immedate Quadrants surrounding player's 
		 *  ship are calculated. */		
		/* top left sensor/quadrant */
		localShipQuadGrid[0][0] =  (shipCurLocByQuad[0] - 1);
		localShipQuadGrid[0][1] =  (shipCurLocByQuad[0] - 1);
		/* top middle sensor/quadrant */
		localShipQuadGrid[1][0] =  (shipCurLocByQuad[1] - 1);
		localShipQuadGrid[1][1] =  (shipCurLocByQuad[1] - 0);
		/* top right-side sensor/quadrant */
		localShipQuadGrid[2][0] =  (shipCurLocByQuad[2] - 1);
		localShipQuadGrid[2][1] =  (shipCurLocByQuad[2] + 1);
		/* middle left sensor/quadrant */
		localShipQuadGrid[3][0] =  (shipCurLocByQuad[3] + 0);
		localShipQuadGrid[3][1] =  (shipCurLocByQuad[3] - 1);
		/* middle : the ship is in this quadrant */
		localShipQuadGrid[4][0] =  (shipCurLocByQuad[4] + 0);
		localShipQuadGrid[4][1] =  (shipCurLocByQuad[4] + 0);
		/* middle right-side sensor/quadrant */
		localShipQuadGrid[5][0] =  (shipCurLocByQuad[5] + 0);
		localShipQuadGrid[5][1] =  (shipCurLocByQuad[5] + 1);
		/* bottom left sensor/quadrant */
		localShipQuadGrid[6][0] =  (shipCurLocByQuad[6] + 1);
		localShipQuadGrid[6][1] =  (shipCurLocByQuad[6] - 1);
		/* bottom middle sensor/quadrant */
		localShipQuadGrid[7][0] =  (shipCurLocByQuad[7] + 1);
		localShipQuadGrid[7][1] =  (shipCurLocByQuad[7] + 0);
		/* bottom right-side sensor/quadrant */
		localShipQuadGrid[8][0] =  (shipCurLocByQuad[8] + 1);
		localShipQuadGrid[8][1] =  (shipCurLocByQuad[8] + 1);			
		return;
	}
	
	private void tagArrayBorderLimits(){
		/* REQUIRES this.fetchSurQuadInexes() has been invoked by 
		 *  one of the this.Sense() functions.
		 * MODIFIES this.localShipQuadGrid[][] .
		 * EFFECTS depending on values stored in localShipQuadGrid[][]; 
		 *  should those values be outside of Quadrant[][] array indexs
		 *  then the content is changed to Configs.SENSOR_SCAN_SENTINEL.
		 *  Example: 
		 *    localShipQuadGrid[2][1] is 11; thus it becomes 
		 *      = Configs.SENSOR_SCAN_SENTINEL.
	     *    localShipQuadGrid[1][0] is -1; thus it becomes 
		 *      = Configs.SENSOR_SCAN_SENTINEL.
		 *    both 11 and -1 would have later been ued to request
		 *    indexed on Quadrant[][] .		 */		
		for(int i = 0; i < Configs.NUM_SENSED_GRIDS; i++){			
			for(int k = 0; k < Configs.DIMENTIONS; k++){
				if (localShipQuadGrid[i][k] < 0){ 
					localShipQuadGrid[i][k] = Configs.SENSOR_SCAN_SENTINEL;
				}							
				if (localShipQuadGrid[i][k] > Configs.SPACE_SIZE-1){
					localShipQuadGrid[i][k] = Configs.SENSOR_SCAN_SENTINEL;
				} 			
			}			 
		}	
	}
}