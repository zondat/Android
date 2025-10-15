package vn.fpt.coursesupport.swd.example.gamememory.logic;

public interface IMatchObserver {
	public void updateWinner(String name);
	public void updateMatchingCard();
	public void updateSwitchPlayer();
}
