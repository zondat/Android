package vn.fpt.coursesupport.prm.project.kingchess.logic;

public class Selected extends ALive {

	public Selected(APiece owner) {
		super(owner);
	}

	@Override
	public void isUnselected() {
		owner.setCurrentState(owner.getUnselectedState());
		owner.notifyUnselected();
	}
}
