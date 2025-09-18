package vn.fpt.coursesupport.prm.project.kingchess.view;

public abstract class State {

	protected Cell owner;
	
	public State(Cell owner) {
		this.owner = owner;
	}
	
	public void unlock() {}
	public void lock() {}
}
