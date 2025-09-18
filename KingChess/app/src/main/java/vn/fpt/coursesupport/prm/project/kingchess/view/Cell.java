package vn.fpt.coursesupport.prm.project.kingchess.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

public class Cell implements UIComponent {

	private GridMap parent;
	private ImageButton button;
	private int row, col;
	private int defaultBackground = Color.TRANSPARENT;
	protected Context context;
	protected View.OnClickListener mouseListener;
	private State currentState, disabled, enabled;
	
	public Cell(GridMap map, int row, int col, int defaultBackground) {
		this(map, row, col);
		this.defaultBackground = defaultBackground;

	}
	
	public Cell(GridMap map, int row, int col) {
		parent = map;
		this.row = row;
		this.col = col;
		context = parent.context;

		enabled = new Enabled(this);
		disabled = new Disabled(this);
		currentState = enabled;

		mouseListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				parent.clickOn(row, col);
			}
		};
    }

	@Override
	public void init() {
		button = new ImageButton(context);
		button.setOnClickListener(mouseListener);
		button.setBackgroundColor(defaultBackground);
	}

	@Override
	public void enable() {
		button.setOnClickListener(mouseListener);
	}

	@Override
	public void disable() {
		button.setOnClickListener(null);
	}

	public void setImage(String drawableId) {
		if (drawableId!=null) {
			Drawable img = Util.getDrawable(context, drawableId);
			button.setScaleType(ImageView.ScaleType.CENTER_CROP);
			button.setBackground(img);
		}
	}

	public void restoreBackground() {
		button.setBackgroundColor(defaultBackground);
	}
	
	public void clear() {
		setImage(null);
		restoreBackground();
	}
	
	public void lock() {
		currentState.lock();
	}

	public void unlock() {
		currentState.unlock();
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
	
	public void setCurrentState(State state) {
		currentState = state;
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
	public State getDisabledState() {
		return disabled;
	}
	
	public State getEnabledState() {
		return enabled;
	}

	@Override
	public View getView() {
		return button;
	}

	@Override
	public List<UIComponent> getSubcomponents() {
		return Collections.emptyList();
	}

	@Override
	public UIComponent getParent() {
		return parent;
	}

	public void setBackground(int color) {
		button.setBackgroundColor(color);
	}
}
