package vn.fpt.coursesupport.prm.project.kingchess.logic;

public interface IPieceObserver {
	public void updateSelection(APiece piece);
	public void updateUnSelect(APiece piece);
	public void updateMove(APiece object, Position previousPosition, Position nextPosition);
	public void updateDead(APiece object);
}
