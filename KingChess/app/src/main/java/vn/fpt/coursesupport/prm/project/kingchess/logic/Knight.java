package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public class Knight extends APiece {

	public Knight() {
		super();
	}
	
	public Knight(Board board) {
		super(board);
	}

	public Knight(Board board, Team team) {
		super(board, team);
	}

	@Override
	public List<Position> getMovableZone() {
		List<Position> movableZone = new ArrayList<Position>();
		for (Position pos: currentPosition.getLShapeBoundary()) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null||piece.isEnemy(this)) movableZone.add(pos);
		}
		return movableZone;
	}
	
}
