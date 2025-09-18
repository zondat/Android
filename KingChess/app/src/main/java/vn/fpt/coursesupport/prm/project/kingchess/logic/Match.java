package vn.fpt.coursesupport.prm.project.kingchess.logic;

public class Match extends AComponent implements IPieceObserver {
	private Board board;
	private Team black, white;
	
	public Match() {
		black = new Team();
		white = new Team();
		board = new Board(black, white);

		// Team black
		black.createKing(board, board.getPosition(0, 4));
		black.createQueen(board, board.getPosition(0, 3));
		black.createBishop(board, board.getPosition(0, 5));
		black.createBishop(board, board.getPosition(0, 2));
		black.createKnight(board, board.getPosition(0, 1));
		black.createKnight(board, board.getPosition(0, 6));
		black.createRook(board, board.getPosition(0, 0));
		black.createRook(board, board.getPosition(0, 7));
		black.createPawn(board, board.getPosition(1, 0), true);
		black.createPawn(board, board.getPosition(1, 1), true);
		black.createPawn(board, board.getPosition(1, 2), true);
		black.createPawn(board, board.getPosition(1, 3), true);
		black.createPawn(board, board.getPosition(1, 4), true);
		black.createPawn(board, board.getPosition(1, 5), true);
		black.createPawn(board, board.getPosition(1, 6), true);
		black.createPawn(board, board.getPosition(1, 7), true);

		// Team white
		white.createKing(board, board.getPosition(7, 4));
		white.createQueen(board, board.getPosition(7, 3));
		white.createBishop(board, board.getPosition(7, 5));
		white.createBishop(board, board.getPosition(7, 2));
		white.createKnight(board, board.getPosition(7, 1));
		white.createKnight(board, board.getPosition(7, 6));
		white.createRook(board, board.getPosition(7, 0));
		white.createRook(board, board.getPosition(7, 7));
		white.createPawn(board, board.getPosition(6, 0), false);
		white.createPawn(board, board.getPosition(6, 1), false);
		white.createPawn(board, board.getPosition(6, 2), false);
		white.createPawn(board, board.getPosition(6, 3), false);
		white.createPawn(board, board.getPosition(6, 4), false);
		white.createPawn(board, board.getPosition(6, 5), false);
		white.createPawn(board, board.getPosition(6, 6), false);
		white.createPawn(board, board.getPosition(6, 7), false);

		for (APiece piece : board.getAllPieces()) {
			piece.addObserver(this);
		}
	}
		
	public Board getBoard() {
		return board;
	}
	
	public void setBlack(Team team) {
		this.black = team;
	}
	
	public void setWhite(Team team) {
		this.white = team;
	}
	
	public Team getBlackTeam() {
		return black;
	}
	
	public Team getWhiteTeam() {
		return white;
	}

	@Override
	public void updateMove(APiece object, Position previousPosition, Position nextPosition) {
		object.getTeam().createTurn(object, nextPosition);
	}

	@Override
	public void updateDead(APiece object) {}

	@Override
	public void updateSelection(APiece object) {}

	@Override
	public void updateUnSelect(APiece object) {}
}
