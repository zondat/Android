package vn.fpt.coursesupport.swd.example.dama.logic;

public interface IGameDamaObserver {
	public void updateMove(Piece piece, Location src, Location dest);
	public void updateDameTransform(Piece piece);
	public void updateDead(Piece piece);
}
