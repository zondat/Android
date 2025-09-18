package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public class Position {

	private Board board;
	private int row;
	private int col;
	
	public Position(Board board, int row, int col) {
		setRow(row);
		setCol(col);
		this.board = board;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public Position getNorth() {
		return board.getPosition(row-1, col);
	}
	
	public Position getSouth() {
		return board.getPosition(row+1, col);
	}
	
	public Position getEast() {
		return board.getPosition(row, col+1);
	}
	
	public Position getWest() {
		return board.getPosition(row, col-1);
	}
	
	public Position getNorthEast() {
		return board.getPosition(row-1, col+1);
	}
	
	public Position getSouthEast() {
		return board.getPosition(row+1, col+1);
	}
	
	public Position getSouthWest() {
		return board.getPosition(row+1, col-1);
	}
	
	public Position getNorthWest() {
		return board.getPosition(row-1, col-1);
	}
	
	public List<Position> getBoundary() {
		List<Position> boundary = new ArrayList<Position>();
		if (getNorth()!=null) boundary.add(getNorth());
		if (getNorthEast()!=null) boundary.add(getNorthEast());
		if (getEast()!=null) boundary.add(getEast());
		if (getSouthEast()!=null) boundary.add(getSouthEast());
		if (getSouth()!=null) boundary.add(getSouth());
		if (getSouthWest()!=null) boundary.add(getSouthWest());
		if (getWest()!=null) boundary.add(getWest());
		if (getNorthWest()!=null) boundary.add(getNorthWest());
		return boundary;
	}
	
	public List<Position> getLShapeBoundary() {
		List<Position> lShapeBound = new ArrayList<Position>();
		Position pos = board.getPosition(row-1, col-2);
		if (pos!=null) lShapeBound.add(pos);
		pos = board.getPosition(row-1, col+2);
		if (pos!=null) lShapeBound.add(pos);
		pos = board.getPosition(row+1, col+2);
		if (pos!=null) lShapeBound.add(pos);
		pos = board.getPosition(row+1, col-2);
		if (pos!=null) lShapeBound.add(pos);
		pos = board.getPosition(row-2, col-1);
		if (pos!=null) lShapeBound.add(pos);
		pos = board.getPosition(row-2, col+1);
		if (pos!=null) lShapeBound.add(pos);
		pos = board.getPosition(row+2, col-1);
		if (pos!=null) lShapeBound.add(pos);
		pos = board.getPosition(row+2, col+1);
		if (pos!=null) lShapeBound.add(pos);
		return lShapeBound;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj!=null && obj instanceof Position) {
			return ((Position)obj).row == this.row
					&& ((Position)obj).col == this.col;			
		}
		return false;
	}
	
}
