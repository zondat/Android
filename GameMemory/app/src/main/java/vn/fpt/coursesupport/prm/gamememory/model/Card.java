package vn.fpt.coursesupport.swd.example.gamememory.logic;

public class Card {
	private int value;
	private boolean disabled = false;
	private int row, col;
		
	public Card(int value) {
		setValue(value);
	}
	
	public Card( int row, int col, int value) {
		this(value);
		setRow(row);
		setCol(col);
	}

	public boolean matches(Card other) {
		return other!=null && this.value==other.value;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void disable() {
		disabled = true;
	}
	
	public boolean isDiabled() {
		return disabled;
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
	
	
}
