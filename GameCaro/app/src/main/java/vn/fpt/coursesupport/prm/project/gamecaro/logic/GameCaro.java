package vn.fpt.coursesupport.prm.project.gamecaro.logic;

import java.util.ArrayList;
import java.util.List;

public class GameCaro {
	private Matrix matrix;
	private List<Player> players;
	private Player currentPlayer, winner;
	private List<ICaroGameObserver> observerList;
	
	public GameCaro() {
		players = new ArrayList<Player>();
		observerList = new ArrayList<>();
	}
	
	public Matrix createMap(int nRows, int nCols) {
		matrix = new Matrix(this, nRows, nCols);
		return matrix;
	}
	
	public Matrix getMatrix() {
		return matrix;
	}
	
	public Player createPlayer(int m) {
		Player newPlayer = new Player(this, m);
		players.add(newPlayer);
		return newPlayer;
	}
	
	public void setCurrentPlayer(int index) {
		currentPlayer = players.get(index);
	}
	
	public void setCurrentPlayer(Player p) {
		currentPlayer = p;
	}

	public void switchPlayer() {
		int currentIndex = players.indexOf(currentPlayer);
		if (currentIndex == players.size()-1) {
			currentPlayer = players.get(0);
		} else {
			currentPlayer = players.get(currentIndex+1);
		}
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public int getCurrentPlayerIndex() {
		return players.indexOf(currentPlayer);
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	public void notifySwitchPlayer() {
		for (ICaroGameObserver observer : observerList) {
			observer.updateCurrentPlayer(getCurrentPlayerIndex());
		}
	}
	
	public void notifyWinner() {
		for (ICaroGameObserver observer : observerList) {
			observer.updateWinner(getCurrentPlayerIndex());
		}
	}
	
	public void notifyMove(int row, int col, int mark) {
		for (ICaroGameObserver observer : observerList) {
			observer.updateMove(row, col, mark);
		}
	}
	
	public void addObserver(ICaroGameObserver observer) {
		observerList.add(observer);
	}
	
	public void removeObserver(ICaroGameObserver observer) {
		observerList.remove(observer);
	}
	
	public void handleClick(int row, int col) {
		boolean validMove = currentPlayer.click(row, col);
		
		if (validMove) {
			boolean isFinished = matrix.check(row, col, currentPlayer.getMark());
			if (isFinished) {
				setWinner(currentPlayer);
				notifyWinner();
			}
			else {
				switchPlayer();
				notifySwitchPlayer();
			}
		}
		
	}

	public void reset() {
		matrix.clear();
	}

	public boolean check(int row, int col) {
		return matrix.check(row, col, currentPlayer.getMark());
	}


}
