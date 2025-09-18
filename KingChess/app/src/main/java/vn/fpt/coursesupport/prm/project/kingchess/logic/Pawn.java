package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends APiece {

	private boolean direction = true;
	
	public Pawn() {
		super();
	}
	
	public Pawn(Board board) {
		super(board);
	}
	
	public Pawn(Board board, Team team) {
		super(board, team);
	}
	
	public APiece promote() {
		return new Queen(board);
	}

	@Override
	public List<Position> getMovableZone() {
		List<Position> movableZone = new ArrayList<Position>();
		if (direction) {
			Position pos = currentPosition.getSouth();
			if (currentPosition.getRow() == 1) {
				if (board.getPieceAt(pos) == null) {
					movableZone.add(pos);
					pos = pos.getSouth();
					if (pos!=null && board.getPieceAt(pos) == null) movableZone.add(pos);
				}
			}
			else {
				if (board.getPieceAt(pos) == null) movableZone.add(pos);
			}
		} else {
			Position pos = currentPosition.getNorth();
			if (currentPosition.getRow() == 6) {
				if (board.getPieceAt(pos) == null) {
					movableZone.add(pos);
					pos = pos.getNorth();
					if (pos!=null && board.getPieceAt(pos) == null) movableZone.add(pos);
				}
			}
			else {
				if (board.getPieceAt(pos) == null) movableZone.add(pos);
			}
		}
		
		return movableZone;
	}
	
	@Override
	public List<Position> getKillingZone() {
		List<Position> killingZone = new ArrayList<Position>();
		if (direction) {
			Position pos = currentPosition.getSouthEast();
			APiece piece = board.getPieceAt(pos);
			if (piece!=null && piece.isEnemy(this)) killingZone.add(pos);
		
			pos = currentPosition.getSouthWest();
			piece = board.getPieceAt(pos);
			if (piece!=null && piece.isEnemy(this)) killingZone.add(pos);
		}
		else {
			Position pos = currentPosition.getNorthEast();
			APiece piece = board.getPieceAt(pos);
			if (piece!=null && piece.isEnemy(this)) killingZone.add(pos);
		
			pos = currentPosition.getNorthWest();
			piece = board.getPieceAt(pos);
			if (piece!=null && piece.isEnemy(this)) killingZone.add(pos);
		}
		return killingZone;
	}
	
	public void setDirection(boolean b) {
		direction = b;
	}
	
}
