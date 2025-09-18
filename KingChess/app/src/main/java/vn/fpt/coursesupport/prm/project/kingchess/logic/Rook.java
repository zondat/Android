package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public class Rook extends APiece {

	public Rook() {
		super();
	}
	
	public Rook(Board board) {
		super(board);
	}
	
	public Rook(Board board, Team team) {
		super(board, team);
	}

	@Override
	public List<Position> getMovableZone() {
		List<Position> movableZone = new ArrayList<>();
		
		Position pos = currentPosition.getNorth();
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
