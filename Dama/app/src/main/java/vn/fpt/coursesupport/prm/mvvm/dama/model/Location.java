package vn.fpt.coursesupport.swd.example.dama.logic;

public class Location {

	private Game owner;
	private int row, col;
	
	public Location(Game game, int row, int col) {
		this.owner = game;
		this.row = row;
		this.col = col;
	}

	public Location getWest() {
		return owner.getLocation(row, col-1);
	}
	
	public Location getEast() {
		return owner.getLocation(row, col+1);
	}
	
	public Location getNorth() {
		return owner.getLocation(row-1, col);
	}
	
	public Location getSouth() {
		return owner.getLocation(row+1, col);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	@Override
	public boolean equals(Object other) {
		return other!=null && other instanceof Location 
				&& this.row==((Location)other).row && this.col==((Location)other).col;
	}
}
