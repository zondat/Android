package vn.fpt.coursesupport.prm.project.kingchess.logic;

public class Match extends AComponent implements IPieceObserver {
	private Board board;
	private Team black, white;
	
	public Match() {}
	
	public void init() {
		black = new Team();
		white = new Team();
		board = new Board(black, white);
		initPieces();
	}
	
	private void initPieces() {
		black.createKing(board, board.getPosition(0, 4), "b_king_48x48");
		black.createQueen(board, board.getPosition(0, 3), "b_queen_48x48");
		black.createBishop(board, board.getPosition(0, 5), "b_bishop_48x48");
		black.createBishop(board, board.getPosition(0, 2), "b_bishop_48x48");
		black.createKnight(board, board.getPosition(0, 1), "b_knight_48x48");
		black.createKnight(board, board.getPosition(0, 6), "b_knight_48x48");
		black.createRook(board, board.getPosition(0, 0), "b_rook_48x48");
		black.createRook(board, board.getPosition(0, 7), "b_rook_48x48");
		black.createPawn(board, board.getPosition(1, 0), "b_pawn_48x48");
		black.createPawn(board, board.getPosition(1, 1), "b_pawn_48x48");
		black.createPawn(board, board.getPosition(1, 2), "b_pawn_48x48");
		black.createPawn(board, board.getPosition(1, 3), "b_pawn_48x48");
		black.createPawn(board, board.getPosition(1, 4), "b_pawn_48x48");
		black.createPawn(board, board.getPosition(1, 5), "b_pawn_48x48");
		black.createPawn(board, board.getPosition(1, 6), "b_pawn_48x48");
		black.createPawn(board, board.getPosition(1, 7), "b_pawn_48x48");

		// Team white
		white.createKing(board, board.getPosition(7, 4), "w_king_48x48");
		white.createQueen(board, board.getPosition(7, 3), "w_queen_48x48");
		white.createBishop(board, board.getPosition(7, 5), "w_bishop_48x48");
		white.createBishop(board, board.getPosition(7, 2), "w_bishop_48x48");
		white.createKnight(board, board.getPosition(7, 1), "w_knight_48x48");
		white.createKnight(board, board.getPosition(7, 6), "w_knight_48x48");
		white.createRook(board, board.getPosition(7, 0), "w_rook_48x48");
		white.createRook(board, board.getPosition(7, 7), "w_rook_48x48");
		Pawn wPawn0 = white.createPawn(board, board.getPosition(6, 0), "w_pawn_48x48");
		Pawn wPawn1 = white.createPawn(board, board.getPosition(6, 1), "w_pawn_48x48");
		Pawn wPawn2 = white.createPawn(board, board.getPosition(6, 2), "w_pawn_48x48");
		Pawn wPawn3 = white.createPawn(board, board.getPosition(6, 3), "w_pawn_48x48");
		Pawn wPawn4 = white.createPawn(board, board.getPosition(6, 4), "w_pawn_48x48");
		Pawn wPawn5 = white.createPawn(board, board.getPosition(6, 5), "w_pawn_48x48");
		Pawn wPawn6 = white.createPawn(board, board.getPosition(6, 6), "w_pawn_48x48");
		Pawn wPawn7 = white.createPawn(board, board.getPosition(6, 7), "w_pawn_48x48");
		wPawn0.setDirection(false);
		wPawn1.setDirection(false);
		wPawn2.setDirection(false);
		wPawn3.setDirection(false);
		wPawn4.setDirection(false);
		wPawn5.setDirection(false);
		wPawn6.setDirection(false);
		wPawn7.setDirection(false);
		
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
