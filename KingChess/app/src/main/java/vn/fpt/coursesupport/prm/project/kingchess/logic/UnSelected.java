package vn.fpt.coursesupport.prm.project.kingchess.logic;

public class UnSelected extends ALive {

	public UnSelected(APiece owner) {
		super(owner);
	}

	@Override
	public void isSelected() {
		owner.setCurrentState(owner.getSeletedState());
		owner.notifySelected();
	}
}
