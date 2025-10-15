package vn.fpt.coursesupport.swd.example.dama.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private List<Location> locationList;
	private Player player1, player2, currentPlayer;
	private List<Piece> allPieces;
	private List<IGameDamaObserver> observerList;

	public Game() {
		observerList = new ArrayList<>();
		locationList = new ArrayList<>();
		allPieces = new ArrayList<>();

		player1 = new Player(this, "Công Trà");
		player2 = new Player(this, "Hải Nam");
		player2.setDirection(true);
		currentPlayer = player1;

		initGame();
	}

	private void initGame() {
		// create location
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				createLocation(r, c);
			}
		}

		// create piece
		for (int r = 1; r < 3; r++) {
			for (int c = 0; c < 8; c++) {
				Piece piece = createPiece(r, c);
				piece.setPlayer(player1);
			}
		}

		for (int r = 5; r < 7; r++) {
			for (int c = 0; c < 8; c++) {
				Piece piece = createPiece(r, c);
				piece.setPlayer(player2);
			}
		}
	}

	public List<Piece> getAllPieces() {
		return allPieces;
	}

	public void setAllPieces(List<Piece> allPieces) {
		this.allPieces = allPieces;
	}

	public Piece createPiece(int row, int col) {
		Piece piece = new Piece(this, getLocation(row, col));
		allPieces.add(piece);
		return piece;
	}

	public void removePiece(Piece piece) {
		allPieces.remove(piece);
		notifyDead(piece);
	}

	public Location createLocation(int row, int col) {
		Location location = new Location(this, row, col);
		locationList.add(location);
		return location;
	}

	public Location getLocation(int row, int col) {
		if (row < 0 || row > 7 || col < 0 || col > 7)
			return null;
		int index = row * 8 + col;
		return locationList.get(index);
	}

	public Piece getPiece(int row, int col) {
		return getPiece(getLocation(row, col));
	}

	public Piece getPiece(Location location) {
		for (Piece piece : allPieces) {
			if (piece.getLocation().equals(location))
				return piece;
		}
		return null;
	}

	public Player getOpponent() {
		return currentPlayer == player1 ? player2 : player1;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public List<Piece> getOpponentPieces(Piece piece, Location target) {
		List<Piece> opponents = new ArrayList<>();
		int srcRow = piece.getLocation().getRow();
		int targetRow = target.getRow();
		int srcCol = piece.getLocation().getCol();
		int targetCol = target.getCol();

		int minRow = srcRow < targetRow ? srcRow : targetRow;
		int maxRow = srcRow < targetRow ? targetRow : srcRow;
		int minCol = srcCol < targetCol ? srcCol : targetCol;
		int maxCol = srcCol < targetCol ? targetCol : srcCol;

		if (srcRow == targetRow) {
			for (int c = minCol + 1; c < maxCol; c++) {
				Piece p = getPiece(srcRow, c);
				if (p != null && !piece.isTeammate(p))
					opponents.add(p);
			}
		}

		if (srcCol == targetCol) {
			for (int r = minRow + 1; r < maxRow; r++) {
				Piece p = getPiece(r, srcCol);
				if (p != null && !piece.isTeammate(p))
					opponents.add(p);
			}
		}

		return opponents;
	}

	public void addObserver(IGameDamaObserver observer) {
		observerList.add(observer);
	}

	public void removeObserver(IGameDamaObserver observer) {
		observerList.remove(observer);
	}

	public void notifyMove(Piece piece, Location src, Location dest) {
		for (IGameDamaObserver observer : observerList) {
			observer.updateMove(piece, src, dest);
		}
	}

	public void notifyDead(Piece piece) {
		for (IGameDamaObserver observer : observerList) {
			observer.updateDead(piece);
		}
	}
	
	public void notifyDameTransform(Piece piece) {
		for (IGameDamaObserver observer : observerList) {
			observer.updateDameTransform(piece);
		}
	}

	public void switchPlayer() {
		currentPlayer = getOpponent();

	}
}
