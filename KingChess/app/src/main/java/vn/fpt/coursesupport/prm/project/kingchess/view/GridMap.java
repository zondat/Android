package vn.fpt.coursesupport.prm.project.kingchess.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class GridMap implements UIComponent {
	private final List<IGridMapObserver> observerList;
	private final List<Cell> cellList;
	private GridLayout gridLayout;
	private final int defaultBackgroundColor = Color.TRANSPARENT;
	protected Context context;
	private int nRows, nCols;
	private final int pad = 2, margin = 2;

	public GridMap(Context context, int nbRows, int nbCols) {
		observerList = new ArrayList<>();
		cellList = new ArrayList<>();
		this.context = context;
		this.nRows = nbRows;
		this.nCols = nbCols;

		for (int r=0; r<nRows; r++) {
			for (int c=0; c<nCols; c++) {
				createCell(r, c);
			}
		}
	}

	@Override
	public void init() {
		gridLayout = new GridLayout(context);
		gridLayout.setColumnCount(nCols);
		gridLayout.setRowCount(nRows);
		gridLayout.setPadding(pad, pad, pad, pad);
		gridLayout.setBackgroundColor(defaultBackgroundColor);

		for (Cell cell : cellList) {
			cell.init();
			GridLayout.LayoutParams params = new GridLayout.LayoutParams(
					GridLayout.spec(cell.getRow()),
					GridLayout.spec(cell.getCol())
			);

			int minWidth 	= computeWidth();
			params.width 	= minWidth;
			params.height 	= minWidth;
			params.setGravity(Gravity.FILL);
			params.setMargins(margin, margin, margin, margin);

			gridLayout.addView(cell.getView(), params);
		}
	}

	@Override
	public View getView() {
		return gridLayout;
	}

	@Override
	public List<UIComponent> getSubcomponents() {
		return new ArrayList<UIComponent>(cellList);
	}

	@Override
	public UIComponent getParent() {
		return null;
	}

	@Override
	public void enable() {
		for (Cell cell : cellList) {
			cell.disable();
			cell.unlock();
		}
	}

	@Override
	public void disable() {
		for (Cell cell : cellList) {
			cell.enable();
			cell.lock();
		}
	}

	public Cell getCell(int row, int col) {
		int index = row * nCols + col;
		return cellList.get(index);
	}

	public Cell createCell(int row, int col) {
		Cell cell = new Cell(this, row, col);
		cellList.add(cell);
		return cell;
	}

	public Cell createCell(int row, int col, int defaultBackground) {
		Cell cell = new Cell(this, row, col, defaultBackground);
		cellList.add(cell);
		return cell;
	}

	public void clearBackground() {
		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nCols; c++) {
				getCell(r, c).restoreBackground();
			}
		}
	}
	
	public void clear() {
		for (int r = 0; r < nRows; r++) {
			for (int c = 0; c < nCols; c++) {
				getCell(r, c).clear();
			}
		}
	}

	public void setImage(int row, int col, String drawableId) {
		getCell(row, col).setImage(drawableId);
	}

	public void notifyClicked(int row, int col) {
		for (IGridMapObserver observer : observerList) {
			observer.updateClickOn(row, col);
		}
	}

	public void unlockAll() {
		for (Cell cell : cellList) {
			cell.unlock();
		}
	}

	public void clickOn(int row, int col) {
		notifyClicked(row, col);
	}

	private int computeWidth() {
		int p = pad + margin;
		int width = (Util.getScreenWidth() - p) / nCols - p;
		return width;
	}

	private int computeHeight() {
		int p = pad + margin;
		int height = (Util.getScreenHeight() - p) / nRows - p;
		return height;
	}

	public void addObserver(IGridMapObserver obs) {
		if (!observerList.contains(obs))
			observerList.add(obs);
	}

	public void removeObserver(IGridMapObserver obs) {
		observerList.remove(obs);
	}

}
