package vn.fpt.coursesupport.prm.project.kingchess.logic;

import java.util.ArrayList;
import java.util.List;

public abstract class APiece extends AComponent {

	protected Position currentPosition;
	protected Board board;
	protected Team team;
	protected State currentState, unSelected, selected, dead;
	protected List<IPieceObserver> observerList;
	protected List<Position> lastPositions;
	
	public APiece() {
		observerList = new ArrayList<IPieceObserver>();
		lastPositions = new ArrayList<Position>();
	}
	
	public APiece(Board board) {
		this();
		setBoard(board);
		board.addPiece(this);
		unSelected = new UnSelected(this);
		selected = new Selected(this);
		dead = new Dead(this);
		currentState = unSelected;
	}
	
	public APiece(Board board, Team team) {
		this(board);
		setTeam(team);
	}

	public abstract List<Position> getMovableZone();
	
	public List<Position> getKillingZone() {
		List<Position> killingZone = new ArrayList<>();
		for (Position pos : getMovableZone()) {
			APiece piece = board.getPieceAt(pos);
			if (piece!=null && this.isEnemy(piece)) killingZone.add(pos);
		}
		return killingZone;
	}
	
	public boolean isEnemy(APiece other) {
		return other.getTeam()!=this.getTeam();
	}
	
	public void move(Position position) {
		Position previousPosition = currentPosition;
		lastPositions.add(previousPosition);
		currentPosition = position;
		notifyMove(previousPosition, currentPosition);
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position position) {
		this.currentPosition = position;
	}
	
	public List<Position> getLastPositions() {
		return lastPositions;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
		
	public void addObserver(IPieceObserver obs) {
		observerList.add(obs);
	}
	
	public void removeObserver(IPieceObserver obs) {
		observerList.remove(obs);
	}
	
	public void notifyUnselected() {
		for (IPieceObserver observer : observerList) {
			observer.updateUnSelect(this);
		}
	}
	
	public void notifySelected() {
		for (IPieceObserver observer : observerList) {
			observer.updateSelection(this);
		}
	}
	
	public void notifyMove(Position previousPosition, Position nextPosition) {
		for (IPieceObserver observer : observerList) {
			observer.updateMove(this, previousPosition, nextPosition);
		}
	}
	
	public void notifyDead() {
		for (IPieceObserver observer : observerList) {
			observer.updateDead(this);
		}
	}
	
	public void setCurrentState(State aState) {
		currentState = aState;
	}

	public State getDeadState() {
		return dead;
	}
	
	public State getSeletedState() {
		return selected;
	}
	
	public State getUnselectedState() {
		return unSelected;
	}

	public boolean isDead() {
		return currentState == dead;
	}
	
	public void die() {
		currentState.die();
	}

	public boolean isAlive() {
		return !isDead();
	}

	public void move(int row, int col) {
		move(board.getPosition(row, col));
	}

	public void isSelected() {
		currentState.isSelected();
	}

	public void isUnselected() {
		currentState.isUnselected();
	}

	public boolean isTeam(Team team) {
		return this.team == team;
	}

	public List<APiece> getTeammates() {
		List<APiece> teammates = team.getCurrentPieces();
		teammates.remove(this);
		return teammates;
	}

}
