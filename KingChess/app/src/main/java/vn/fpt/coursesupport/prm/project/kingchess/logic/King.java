package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public class King extends APiece {

	public King() {
		super();
	}
	
	public King(Board board) {
		super(board);
	}

	public King(Board board, Team team) {
		super(board, team);
	}

	@Override
	public List<Position> getMovableZone() {
		List<Position> movableZone = new ArrayList<Position>();
		for (Position pos : currentPosition.getBoundary()) {
			APiece piece = board.getPieceAt(pos);
			if (piece==null || piece.isEnemy(this)) movableZone.add(pos);
		}
		return movableZone;
	}

}
