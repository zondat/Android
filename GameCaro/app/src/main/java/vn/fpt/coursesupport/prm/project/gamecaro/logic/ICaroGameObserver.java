package vn.fpt.coursesupport.prm.project.gamecaro.logic;

public interface ICaroGameObserver {
	public void updateCurrentPlayer(int playerIndex);
	public void updateMove(int row, int col, int mark);
	public void updateWinner(int playerIndex);
}
