package vn.fpt.coursesupport.prm.project.gamecaro.view;

public class Disabled extends State {

	public Disabled(Cell owner) {
		super(owner);
	}

	@Override
	public void unlock() {
		owner.enable();
		owner.setCurrentState(owner.getEnabledState());
	}
}
