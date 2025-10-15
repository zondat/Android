package vn.fpt.coursesupport.prm.project.gamecaro.view;

public class Enabled extends State {

	public Enabled(Cell owner) {
		super(owner);
	}

	@Override
	public void lock() {
		owner.disable();
		owner.setCurrentState(owner.getDisabledState());
	}
	
}
