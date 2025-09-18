package vn.fpt.coursesupport.prm.project.gamecaro.logic;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	
	private GameCaro game;
	private int Nrows, Ncols;
	private List<Cell> ownedCells;
	
	public Matrix(GameCaro game) {
		this.game = game;
		ownedCells = new ArrayList<Cell>();
	}
	
	public Matrix(GameCaro owner, int nRows, int nCols) {
		this(owner);
		setNcols(nCols);
		setNrows(nRows);
		
		for (int r=0; r<nRows; r++) {
			for (int c=0; c<nCols; c++) {
				createCell(r, c);
			}
		}
	}

	public Cell createCell(int r, int c) {
		Cell cell = new Cell(this, r, c);
		int index = r * Ncols + c;
		try {
			Cell existingCell = ownedCells.get(index);
			if (existingCell != null) ownedCells.set(index, cell);
		} catch (IndexOutOfBoundsException e) {
			ownedCells.add(cell);
        }
		
		return cell;
	}
	
	public int getNrows() {
		return Nrows;
	}

	public void setNrows(int nrows) {
		Nrows = nrows;
	}

	public int getNcols() {
		return Ncols;
	}

	public void setNcols(int ncols) {
		Ncols = ncols;
	}

	public List<Cell> getOwnedCells() {
		return ownedCells;
	}

	public void setOwnedCells(List<Cell> ownedCells) {
		this.ownedCells = ownedCells;
	}

	public boolean check(int row, int col, int mark) {
		return check(getCell(row, col), mark);
	}
	
	public boolean check(Cell basedCell, int mark) {
		boolean isFinished = false;
		int markCount = 0;
		
		// Xet ngang
		Cell cell = basedCell;
		while (cell!=null && cell.getValue()==mark) {
			markCount++;
			cell = cell.getLeftCell();
		}
		
		cell = basedCell.getRightCell();
		while (cell!=null && cell.getValue()==mark) {
			markCount++;
			cell = cell.getRightCell();
		}
		if (markCount >=5) isFinished = true;
		
		// Xet doc
		markCount = 0;
		cell = basedCell;
		while (cell!=null && cell.getValue()==mark) {
			markCount++;
			cell = cell.getUpCell();
		}
		
		cell = basedCell.getDownCell();
		while (cell!=null && cell.getValue()==mark) {
			markCount++;
			cell = cell.getDownCell();
		}
		if (markCount >=5) isFinished = true;
		
		// Xet cheo len
		markCount = 0;
		cell = basedCell;
		while (cell!=null && cell.getValue()==mark) {
			markCount++;
			cell = cell.getUpRightCell();
		}
		
		cell = basedCell.getDownLeftCell();
		while (cell!=null && cell.getValue()==mark) {
			markCount++;
			cell = cell.getDownLeftCell();
		}
		if (markCount >=5) isFinished = true;
		
		// Xet cheo xuong
		markCount = 0;
		cell = basedCell;
		while (cell!=null && cell.getValue()==mark) {
			markCount++;
			cell = cell.getUpLeftCell();
		}
		
		cell = basedCell.getDownRightCell();
		while (cell!=null && cell.getValue()==mark) {
			markCount++;
			cell = cell.getDownRightCell();
		}
		if (markCount >=5) isFinished = true;
		
		return isFinished;
	}

	public Cell getCell(int row, int col) {
		int index = row * Ncols + col;
		return ownedCells.get(index);
	}

	public void clear() {
		for (int r=0; r<Nrows; r++) {
			for (int c=0; c<Ncols; c++) {
				getCell(r, c).setValue(0);
			}
		}
	}
	
	
}
