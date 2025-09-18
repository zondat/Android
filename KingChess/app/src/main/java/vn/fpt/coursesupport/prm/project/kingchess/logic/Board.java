package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public class Board extends AComponent {

	private Team black, white;
	private List<Position> allPositions;
	private List<APiece> allPieces;
	private APiece selectedPiece = null;
	private Team activeTeam = null;
	
	public Board(Team black, Team white) {
		allPositions = new ArrayList<>();
		allPieces = new ArrayList<>();
		this.black = black;
		this.white = white;
		this.activeTeam = this.black;
		initPosition();
	}
	
	public Position getPosition(int row, int col) {
		if (row<0 || col<0 || row>7 || col>7) return null;
		int index = row*8 + col;
		return allPositions.get(index);
	}
	
	private void initPosition() {
		for (int r=0; r<8; r++) {
			for (int c=0; c<8; c++) {
				Position pos = new Position(this, r, c);
				allPositions.add(pos);
			}
		}
	}
	
	public List<APiece> getAllPieces() {
		return allPieces;
	}
	
	public APiece getPieceAt(Position pos) {
		for (APiece piece : allPieces) {
			if (piece.isAlive() && piece.getCurrentPosition().equals(pos)) return piece;
		}
		return null;
	}
	
	public APiece getPieceAt(int row, int col) {
		return getPieceAt(getPosition(row, col));
	}

	public APiece getSelectedPiece() {
		return selectedPiece;
	}
	
	public void selectPiece(APiece piece) {
		if (piece!=null) {
			for (APiece other : allPieces) {
				if (other!=piece) other.isUnselected();
			}
			selectedPiece = piece;	
			selectedPiece.isSelected();
		}
	}
	
	public Team switchTeam() {
		activeTeam =  (activeTeam == black) ? white :  black;
		selectedPiece = null;
		return activeTeam;
	}

	public Team getActiveTeam() {
		return activeTeam;
	}
	
	public Team getUnactiveTeam() {
		return activeTeam == black ? white : black;
	}

	public List<Position> getAllPositions() {
		return allPositions;
	}
	
	public Team getBlackTeam() {
		return black;
	}
	
	public Team getWhiteTeam() {
		return white;
	}
	
	public void addPiece(APiece piece) {
		if (!allPieces.contains(piece)) allPieces.add(piece);
	}
}
