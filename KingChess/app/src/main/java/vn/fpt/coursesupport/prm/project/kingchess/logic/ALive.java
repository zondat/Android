package vn.fpt.coursesupport.prm.project.kingchess.logic;

public abstract class ALive extends State {

	public ALive(APiece owner) {
		super(owner);
	}

	@Override
	public void die() {
		owner.setCurrentState(owner.getDeadState());
		owner.notifyDead();
	}
}
