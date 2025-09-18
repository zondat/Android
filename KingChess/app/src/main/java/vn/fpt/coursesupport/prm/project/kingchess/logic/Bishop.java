package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends APiece {

	public Bishop() {
		super()
;	}
	
	public Bishop(Board board) {
		super(board);
	}

	public Bishop(Board board, Team team) {
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
		
		return movableZone;
	}
	
}
