package vn.fpt.coursesupport.prm.project.kingchess.logic;

public abstract class State {
	
	protected APiece owner;
	
	public State(APiece owner) {
		this.owner = owner;
	}
	
	public void isSelected() { }
	
	public void isUnselected() { }
	
	public void die() { }
}
