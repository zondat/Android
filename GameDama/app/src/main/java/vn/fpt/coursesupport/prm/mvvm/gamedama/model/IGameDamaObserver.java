package vn.fpt.coursesupport.prm.mvvm.gamedama.model;

public interface IGameDamaObserver {
	public void updateMove(Piece piece, Location src, Location dest);
	public void updateDameTransform(Piece piece);
	public void updateDead(Piece piece);
}
