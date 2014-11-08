package testCase;

import java.util.Hashtable;
/* Class ShipSensors
 * Overview:
 * [more description coming soon(tm)] 
 * 	sensorGrid contains an array of 9 hashTables; each index, 
 * as shown below, indicates surrounding area around ship.
 * example KEY	: "E", "*", "&" ;
 * example value:  0    3    2
 * */
public class ShipSensors extends Ship {	
	
	// 7 8 9 
	// 4 5 6 
	// 1 2 3  ; where 5 is ships location
	Hashtable[] sensorGrid = new Hashtable[9];
		
	private int[] curQuadLoc = new int[2];
	
	public void action(int row, int column) {		
		//REQUIRES 
		//	row & column are valid values within the Quadrant matrix 
		//MODIFiES
		//	this.curQuadLoc
		//		[0] = row;	[1] = column;
		//EFFECTS
		//	action() is externally invoked when ship changes quadrant.
		//	receives 2 integers indicating its relative location within quadrant grid. 
		//	action() updates curQuadLoc with said parameters: row, column.
		//	invoked the local function gatherIntel()
		this.curQuadLoc[0] = row;
		this.curQuadLoc[1] = column;
		this.gatherIntel();
	}

	private void gatherIntel(){
		//REQUIRES
		//MODIFIES this.sensorGrid
		//EFFECTS 
		//	for each surrounding area around location defined by this.curQuadLoc
		//	fetch from quadrant relevant data containing sensed objects in said area;
		//	that data gets placed in this.sensorGrid		
	}
	private void setSensors(){
		//REQUIRES
		//MODIFIES
		//EFFECTS  notifies ship that there exists a ready sensor grid
		//	 
	}
}
