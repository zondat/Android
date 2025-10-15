package vn.fpt.coursesupport.swd.example.gamememory.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {

	private int currentIndex = 0;
	private List<Player> playerList;
	private List<Card> cardList;
	private int nRows, nCols;
	private List<IMatchObserver> observerList;
	
	public Match(int rows, int cols) {
		nRows = rows;
		nCols =  cols;
		playerList = new ArrayList<>();
		cardList = new ArrayList<>();
		observerList = new ArrayList<>();
		
		init();
	}
	
	private void init() {
		List<Integer> assignValues = new ArrayList<>();
		int nbValues = nRows * nCols / 2;
		for (int i=0; i<nbValues; i++) {
			assignValues.add(i);
			assignValues.add(i);
		}
		
		for (int r=0; r<nRows; r++) {
			for (int c=0; c<nCols; c++) {
				int rand = new Random().nextInt(0, assignValues.size());
				createCard(r, c, assignValues.remove(rand));
			}
		}
	}
	
	public void checkMatchingCard(Card card1, Card card2) {
		boolean isMatched = card1.matches(card2);
		if (isMatched) {
			card1.disable();
			card2.disable();
			getCurrentPlayer().winPoint();
			notifyMatchingCard();
		} else {
			nextPlayer();
			notifySwitchPlayer();
		}
	}
	
	public Card getCard(int row, int col) {
		int index = row*nCols + col;
		return cardList.get(index);
	}
	
	public Card createCard(int row, int col, int value) {
		Card card = new Card(row, col, value);
		cardList.add(card);
		return card;
	}

	
	public Player getCurrentPlayer() {
		return playerList.get(currentIndex);
	}
	
	public Player createPlayer(String name) {
		Player player = new Player(name);
		playerList.add(player);
		return player;
	}

	public Player nextPlayer() {
		if (currentIndex<playerList.size()-1) currentIndex ++;
		else currentIndex = 0;
		return getCurrentPlayer();
	}
		
	public List<Card> getAllCards() {
		return cardList;
	}
	
	public void notifyWinner(String name) {
		for (IMatchObserver observer : observerList) {
			observer.updateWinner(name);
		}
	}
	
	public void notifyMatchingCard() {
		for (IMatchObserver observer : observerList) {
			observer.updateMatchingCard();
		}
	}
	
	public void notifySwitchPlayer() {
		for (IMatchObserver observer : observerList) {
			observer.updateSwitchPlayer();
		}
	}
	
	public void addObserver(IMatchObserver observer) {
		if (!observerList.contains(observer)) observerList.add(observer);
	}
	
	public void removeObserver(IMatchObserver observer) {
		observerList.remove(observer);
	}
}
