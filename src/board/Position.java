package board;
/**
 * 
 * @author sukhenka (Sukhenko Artur)
 *
 */
public class Position {
	private int row;
	private int col;
	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}
	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	public boolean equals(Position pos) {
		if(pos.getCol() == col && pos.getRow() == row){
			return true;
		}
		return false;
	}

	public void setPositionAt(int col,int row){
		this.col = col;
		this.row = row;
	}
	public void setPositionAt(Position p){
		this.col = p.getCol();
		this.row = p.getRow();
	}
	/**
	 * @param col
	 * @param row
	 */
	public Position(int row, int col) {
		this.col = col;
		this.row = row;
	}
	/**
	 * @param col
	 * @param row
	 */
	public Position() {
		this.col = -1;
		this.row = -1;
	}
	public boolean isSet(){
		if(col == -1 || row == -1){
			return false;
		}
		return true;
	}
	public boolean isValid(){
		return(col < 10 && row < 10);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "("+col + "," + row+")";
	}
}
