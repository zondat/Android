package vn.fpt.coursesupport.prm.mvvm.gamedama.model;

import java.util.ArrayList;
import java.util.List;

public class Piece {

	protected Game game;
	protected Player player;
	protected boolean isDead = false;
	protected Location currentLocation;
	protected State stateDame, stateNormal, currentState;
	
	public Piece(Game game, Location defaultLocation) {
		this.game = game;
		this.currentLocation = defaultLocation;
		stateNormal = new State(this);
		stateDame = new StateDame(this);
		currentState = stateNormal;
	}
	
	public boolean getDirection() {
		return player.getDirection();
	}
	
	public boolean move(Location target) {
		boolean canMoveNext = false;
		if (isMovable(target))  {
			List<Piece> opponentPieces = game.getOpponentPieces(this , target);
			Location oldLocation = currentLocation;
			if (opponentPieces.isEmpty()) {
				setLocation(target);
			} 
			else if (opponentPieces.size()==1) {
				game.notifyDead(opponentPieces.get(0));
				setLocation(target);
				canMoveNext = true;
			}
			
			// Check for dame transform
			if (getDirection() && currentLocation.getRow() == 0
								||!getDirection() && currentLocation.getRow() == 7) {
				setDameState();
				game.notifyDameTransform(this);
			}
			
			// Notify move
			game.notifyMove(this, oldLocation, currentLocation);
		}
		return canMoveNext;
	}
	
	public boolean move(int row, int col) {
		return move(game.getLocation(row, col));
	}
	
	public boolean isMovable(Location loc) {
		return currentState.isMovable(loc);
	}
	
	public List<Location> getMovableZone() {
		return currentState.getMovableZone();
	}
	
	public void setPlayer(Player p) {
		player = p;
	}

	public List<Location> getAheadLocations() {
		List<Location> locations = new ArrayList<>();
		if (!getDirection()) {
			for (int r=getRow()+1; r<8; r++) {
				locations.add(game.getLocation(r, getCol()));
			}
		}
		else {
			for (int r=getRow()-1; r>=0; r--) {
				locations.add(game.getLocation(r, getCol()));
			}
		}
		return locations;
	}
	
	public List<Location> getBehindLocations() {
		List<Location> locations = new ArrayList<>();
		if (getDirection()) {
			for (int r=getRow()+1; r<8; r++) {
				locations.add(game.getLocation(r, getCol()));
			}
		}
		else {
			for (int r=getRow()-1; r>=0; r--) {
				locations.add(game.getLocation(r, getCol()));
			}
		}
		return locations;
	}
	
	public List<Location> getLeftSideLocations() {
		List<Location> locations = new ArrayList<>();
		if (getDirection()) {
			for (int c=getCol()-1; c>=0; c--) {
				locations.add(game.getLocation(getRow(), c));
			}
		}
		else {
			for (int c=getCol()+1; c<8; c++) {
				locations.add(game.getLocation(getRow(), c));
			}
		}
		return locations;
	}
	
	public List<Location> getRightSideLocations() {
		List<Location> locations = new ArrayList<>();
		if (!getDirection()) {
			for (int c=getCol()-1; c>=0; c--) {
				locations.add(game.getLocation(getRow(), c));
			}
		}
		else {
			for (int c=getCol()+1; c<8; c++) {
				locations.add(game.getLocation(getRow(), c));
			}
		}
		return locations;
	}
	
	public Location getLeft() {
		if (getDirection()) return currentLocation.getWest();
		else return currentLocation.getEast();
	}
	
	public Location getRight() {
		if (getDirection()) return currentLocation.getEast(); 
		else return currentLocation.getWest();
	}
	
	public Location getFront() {
		if (getDirection()) return currentLocation.getNorth();
		else return currentLocation.getSouth();
	}
	
	public Location getBack() {
		if (getDirection()) return currentLocation.getSouth();
		else return currentLocation.getNorth();
	}
	
	public Location getLocation() {
		return currentLocation;
	}

	public void setLocation(Location location) {
		this.currentLocation = location;
	}
	
	public void setDameState() {
		currentState = stateDame;
	}

	public boolean isTeammate(Piece other) {
		return player == other.player;
	}

	public boolean isDame() {
		return currentState == stateDame;
	}

	public Player getPlayer() {
		return player;
	}
	
	public int getRow() {
		return currentLocation.getRow();
	}
	
	public int getCol() {
		return currentLocation.getCol();
	}


	public void setDead(boolean b) {
		isDead = b;
	}
}
