
/*Author Matti
 * Overview
 * 1) Call function "Sense"; it still requires some location that 
 *  defines ship's location (i.e. getShipIndexLocation() ) within 
 *  quadrant array (row index, column index).
 * 2) the rest is completed by functions that are invoked by "Sense"  
 * 2.1) fetchSurQuadInexes:
 * 	compared to ships location all adjacent quadrant indexes are calculated
 * 2.2) tagArrayBorderLimits:
 *  should any index value be outside SPACE_SIZE or 0 then it is replaced
 *  with SENSOR_SENTINEL value.
 * 3) FillSurQuad : INCOMPLETE
 * 	depending on the amount of "& * E" in each adjacent quadrant 
 * the ship-interface long range sensors are populated with how 
 * many E, *, or, & are in, said, quadrant; yet, SENSOR_SENTINEL 
 * values are given the string value defined by OUTER_LIMIT_OF_MAP
 * which is something like "???". 
 * 
 * V2 there are now 3 variations to initiate Sensors; this is to 
 * accommodate simpler integration : Sense(), Sense(Int[]), or 
 * Sense(int row, int column)
 * 
 * Testing: None.
 * V3 implement test benching.
 * */
public class Sensors {
	/*Presumed constants exist*/
	public static final int SPACE_SIZE = 10;
	public static final int surArrSize = 9;
	
	// SENSOR_SENTINEL specifies that an adjacent quadrants is not 
	// on quadrant array: row -1 by column -1, or
	// row (SPACE_SIZE +1) by column 2.
	public static final int SENSOR_SENTINEL = -1; 
	public static final int MOVEMENT_DIMENTIONS = 2;
	public static final String OUTER_LIMIT_OF_MAP = "?"; 
	
	// Via ship's location in quadrant fill long range Sensors
	int shipCurLocByQuad[];// = {0,0}; // getShipLocation();
	
	// for calculating what the surrounding quadrants are 
	/*Integer shipsQuadArea[][] =	{
			{},{},	{},
			{},{0,0},{}, //< compared surrounded by adjacent sectors 
			{},{},	{}
		}; 
	*/
	Integer shipsQuadArea[][] 
			= new Integer[surArrSize][MOVEMENT_DIMENTIONS];
	
	public void Sense(int rowByCol[]){
		shipCurLocByQuad[0] = rowByCol[0];
		shipCurLocByQuad[1] = rowByCol[1];
		this.Sense();
	}
	
	public void Sense(int row, int column){
		shipCurLocByQuad[0] = row;
		shipCurLocByQuad[1] = column;
		this.Sense();
	}
	
	public void Sense(){
		this.shipCurLocByQuad[0] = 1; // getShipLocation();
		this.shipCurLocByQuad[1] = 1; // getShipLocation();
		this.fetchSurQuadInexes();
		this.tagArrayBorderLimits();
		this.FillSurQuad();
	}
	
	private void FillSurQuad(){
		//scans adjacent quadrants and pushes values into 
		// interface's long range sensors.
		//should shipsQuadArea[][] == SENSOR_SENTINEL
		//then the location's sensor is given the value 
		// OUTER_LIMIT_OF_MAP
		
		//if row by column index is NOT within quadrant array
		//then place OUTER_LIMIT_OF_MAP value into all 
		//interface sensor integer slots
		
		//if row by column index is within quadrant array
		//query that quadrant for amount of  * & E
		//that is placed in interface long range 
		//sensor integer slots
		
		//done
	}	
	
	private void FillCurSector(){
		//scans local quadrant and pushes values into 
		// interface short range sensors
				
		//query local quadrant for amount of  * & E
		//that is placed in interface short range 
		// sensor integer slots
		
		//done
	}
	
	
	//1
	private void fetchSurQuadInexes(){
		//REQUIRES that the ships location has been defined   
		//MODIFIES this.shipsQuadArea
		//EFFECTS depending on ships index location in quadrant array,
		// the quadrants surrounding player ship are calculated.		
		
		// top left sensor/quadrant
		shipsQuadArea[0][0] =  (shipCurLocByQuad[0] - 1);
		shipsQuadArea[0][1] =  (shipCurLocByQuad[0] - 1);
		// top middle sensor/quadrant
		shipsQuadArea[1][0] =  (shipCurLocByQuad[1] - 1);
		shipsQuadArea[1][1] =  (shipCurLocByQuad[1] - 0);
		// top right-side sensor/quadrant
		shipsQuadArea[2][0] =  (shipCurLocByQuad[2] - 1);
		shipsQuadArea[2][1] =  (shipCurLocByQuad[2] + 1);
		// middle left sensor/quadrant
		shipsQuadArea[3][0] =  (shipCurLocByQuad[3] + 0);
		shipsQuadArea[3][1] =  (shipCurLocByQuad[3] - 1);
		// middle : the ship is in this quadrant
		shipsQuadArea[4][0] =  (shipCurLocByQuad[4] + 0);
		shipsQuadArea[4][1] =  (shipCurLocByQuad[4] + 0);
		// middle right-side sensor/quadrant
		shipsQuadArea[5][0] =  (shipCurLocByQuad[5] + 0);
		shipsQuadArea[5][1] =  (shipCurLocByQuad[5] + 1);
		// bottom left sensor/quadrant
		shipsQuadArea[6][0] =  (shipCurLocByQuad[6] + 1);
		shipsQuadArea[6][1] =  (shipCurLocByQuad[6] - 1);
		// bottom middle sensor/quadrant
		shipsQuadArea[7][0] =  (shipCurLocByQuad[7] + 1);
		shipsQuadArea[7][1] =  (shipCurLocByQuad[7] + 0);
		// bottom right-side sensor/quadrant
		shipsQuadArea[8][0] =  (shipCurLocByQuad[8] + 1);
		shipsQuadArea[8][1] =  (shipCurLocByQuad[8] + 1);			
		return;
	}
	
	//2
	private void tagArrayBorderLimits(){
		//REQUIRES 
		//MODIFIES this.shipsQuadArea
		//EFFECTS depending on Quadrant array size;
		// the lower and upper limits are tagged with 
		for(int i = 0; i < surArrSize; i++){
			// was the index value outside of map (too small)
			for(int k = 0; k < MOVEMENT_DIMENTIONS; k++){
				if (shipsQuadArea[i][k] > SPACE_SIZE){
					shipsQuadArea[i][k] = SENSOR_SENTINEL;
				} 				 
				// was the index value outside of map (too large)			
				if (shipsQuadArea[i][k] > SPACE_SIZE){
					shipsQuadArea[i][k] = SENSOR_SENTINEL;
				} 			
			}			 
		}	
	}
}

