package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public class Queen extends APiece {

	public Queen() {
		super();
	}
	
	public Queen(Board board) {
		super(board);
	}

	public Queen(Board board, Team team) {
		super(board, team);
	}

	@Override
	public List<Position> getMovableZone() {
		List<Position> movableZone = new ArrayList<>();
		
		Position pos = currentPosition.getNorthEast();
		while (pos!=null) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null) movableZone.add(pos);
			else {
				if (piece.isEnemy(this)) movableZone.add(pos);
				break;
			}
			pos = pos.getNorthEast();
		}
		
		pos = currentPosition.getSouthEast();
		while (pos!=null) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null) movableZone.add(pos);
			else {
				if (piece.isEnemy(this)) movableZone.add(pos);
				break;
			}
			pos = pos.getSouthEast();
		}
		
		pos = currentPosition.getSouthWest();
		while (pos!=null) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null) movableZone.add(pos);
			else {
				if (piece.isEnemy(this)) movableZone.add(pos);
				break;
			}
			pos = pos.getSouthWest();
		}
		
		pos = currentPosition.getNorthWest();
		while (pos!=null) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null) movableZone.add(pos);
			else {
				if (piece.isEnemy(this)) movableZone.add(pos);
				break;
			}
			pos = pos.getNorthWest();
		}
		
		pos = currentPosition.getNorth();
		while (pos!=null) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null) movableZone.add(pos);
			else {
				if (piece.isEnemy(this)) movableZone.add(pos);
				break;
			}
			pos = pos.getNorth();
		}
		
		pos = currentPosition.getSouth();
		while (pos!=null) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null) movableZone.add(pos);
			else {
				if (piece.isEnemy(this)) movableZone.add(pos);
				break;
			}
			pos = pos.getSouth();
		}
		
		pos = currentPosition.getWest();
		while (pos!=null) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null) movableZone.add(pos);
			else {
				if (piece.isEnemy(this)) movableZone.add(pos);
				break;
			}
			pos = pos.getWest();
		}
		
		pos = currentPosition.getEast();
		while (pos!=null) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null) movableZone.add(pos);
			else {
				if (piece.isEnemy(this)) movableZone.add(pos);
				break;
			}
			pos = pos.getEast();
		}
		
		return movableZone;
	}
}
