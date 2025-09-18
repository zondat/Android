package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public class Team extends AComponent {
	
	private String player;
	private List<APiece> allPieces;
	private List<Turn> allTurns;
	
	public Team() {
		allPieces = new ArrayList<APiece>();
		allTurns  = new ArrayList<Turn>();
	}
	
	public Team(String player) {
		this();
		setPlayer(player);
	}

	public String getPlayer() {
		return player;
	}

	public void addPiece(APiece piece) {
		if (!allPieces.contains(piece)) allPieces.add(piece);
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}

	public King createKing(Board board, Position pos) {
		King piece = new King(board, this);
		setBasicInfo(piece, pos);
		return piece;
	}
	
	public Queen createQueen(Board board, Position pos) {
		Queen piece = new Queen(board, this);
		setBasicInfo(piece, pos);
		return piece;
	}
	
	public Bishop createBishop(Board board, Position pos) {
		Bishop piece = new Bishop(board, this);
		setBasicInfo(piece, pos);
		return piece;
	}
	
	public Knight createKnight(Board board, Position pos) {
		Knight piece = new Knight(board, this);
		setBasicInfo(piece, pos);
		return piece;
	}
	
	public Rook createRook(Board board, Position pos) {
		Rook piece = new Rook(board, this);
		setBasicInfo(piece, pos);
		return piece;
	}
	
	public Pawn createPawn(Board board, Position pos, boolean direction) {
		Pawn piece = new Pawn(board, this);
		setBasicInfo(piece, pos);
		piece.setDirection(direction);
		return piece;
	}
	
	private void setBasicInfo(APiece piece, Position pos) {
		piece.setCurrentPosition(pos);
		addPiece(piece);
	}
	
	public List<APiece> getCurrentPieces() {
		List<APiece> alivePieces = new ArrayList<>();
		for (APiece piece : allPieces) {
			if (piece.isAlive()) alivePieces.add(piece);
		}
		return alivePieces;
	}
	
	public APiece getPiece(int index) {
		return allPieces.get(index);
	}
	
	public List<APiece> getAllPieces() {
		return allPieces;
	}
	
	public int getIndexOf(APiece piece) {
		return allPieces.indexOf(piece);
	}

	public Turn createTurn(APiece actor, Position nextPosition) {
		Turn turn = new Turn(actor, nextPosition);
		allTurns.add(turn);
		return turn;
	}
	
	public List<Turn> getAllTurns() {
		return allTurns;
	}
}
