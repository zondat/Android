package vn.fpt.coursesupport.prm.mvvm.gamedama.model;

import java.util.ArrayList;
import java.util.List;

public class StateDame extends State {
	
	public StateDame(Piece piece) {
		super(piece);
	}
	
	@Override
	public List<Location> getMovableZone() {
		List<Location> movableZone = new ArrayList<>();
		checkForMovable(movableZone, owner.getAheadLocations());
		checkForMovable(movableZone, owner.getBehindLocations());
		checkForMovable(movableZone, owner.getLeftSideLocations());
		checkForMovable(movableZone, owner.getRightSideLocations());
		return movableZone;
	}
	
	private void checkForMovable(List<Location> movableZone, List<Location> locations) {
		int opponentCount = 0;
		for (int i=0; i<locations.size(); i++) {
			Location loc = locations.get(i);
			Piece piece = owner.game.getPiece(loc);
			if (i==0) {
				if (piece==null) movableZone.add(loc);
				else {
					if (owner.isTeammate(piece)) break;
					else opponentCount++;
				}
			}
			else {
				if (piece==null) {
					if (opponentCount==1) movableZone.add(loc);
				}
				else {
					if (owner.isTeammate(piece)) break;
					else opponentCount++;
				}
			}
			if (opponentCount>1) break;
		}
	}
}
