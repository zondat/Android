package vn.fpt.coursesupport.prm.project.gamecaro.logic;

public class Cell {
	
	private Matrix owner;
	private int row, col;
	private int value; // 0: unclicked, 1->vocung: clicked
	
	public Cell(Matrix owner) {
		this.owner = owner;
	}
	
	public Cell(Matrix owner, int row, int col) {
		this(owner);
		setCol(col);
		setRow(row);
	}
	
	public Cell getUpCell() {
		if (row == 0) return null;
		return owner.getCell(row-1, col);
	}
	
	public Cell getDownCell() {
		if (row == owner.getNrows() - 1) return null;
		return owner.getCell(row+1, col);
	}
	
	public Cell getLeftCell() {
		if (col == 0) return null;
		return owner.getCell(row, col-1);
	}
	
	public Cell getRightCell() {
		if (row == owner.getNcols()-1) return null;
		return owner.getCell(row, col+1);
	}
	
	public Cell getUpLeftCell() {
		if (row == 0 || col == 0) return null;
		return owner.getCell(row-1, col-1);
	}
	
	public Cell getUpRightCell() {
		if (row == 0|| col+1 == owner.getNcols()) return null;
		return owner.getCell(row-1, col+1);
	}
	
	public Cell getDownLeftCell() {
		if (col == 0 || row+1 == owner.getNrows()) return null;
		return owner.getCell(row+1, col-1);
	}
	
	public Cell getDownRightCell() {
		if (row+1 == owner.getNrows()|| col+1 == owner.getNcols()) return null;
		return owner.getCell(row+1, col+1);
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isMarked() {
		return value != 0;
	}
	
}
