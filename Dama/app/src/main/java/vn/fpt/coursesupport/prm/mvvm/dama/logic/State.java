package vn.fpt.coursesupport.prm.mvvm.gamedama.model;

import java.util.ArrayList;
import java.util.List;

public class State {
	protected Piece owner;
	
	public State(Piece piece) {
		owner = piece;
	}

	public boolean isMovable(Location loc) {
		return getMovableZone().contains(loc);
	}

	public List<Location> getMovableZone() {
		List<Location> movableZone = new ArrayList<>();
		
		Location front = owner.getFront();
		Piece piece = owner.game.getPiece(front);
		if (piece!=null) {
			if (!owner.isTeammate(piece)) {
				Piece opponent = piece;
				Location back = opponent.getBack();
				if (back!=null && owner.game.getPiece(back)==null) {
					movableZone.add(back);
				}	
			}	
		} else {
			movableZone.add(front);
		}
		
		Location left = owner.getLeft();
		piece = owner.game.getPiece(left);
		if (piece!=null) {
			if (!owner.isTeammate(piece)) {
				Piece opponent = piece;
				Location outLeft = opponent.getRight();
				if (outLeft!=null && owner.game.getPiece(outLeft)==null) {
					movableZone.add(outLeft);
				}	
			}	
		} else {
			movableZone.add(left);
		}
		
		Location right = owner.getRight();
		piece = owner.game.getPiece(right);
		if (piece!=null) {
			if (!owner.isTeammate(piece)) {
				Piece opponent = piece;
				Location outRight = opponent.getLeft();
				if (outRight!=null && owner.game.getPiece(outRight)==null) {
					movableZone.add(outRight);
				}	
			}	
		} else {
			movableZone.add(right);
		}
		
		return movableZone;
	}
}
