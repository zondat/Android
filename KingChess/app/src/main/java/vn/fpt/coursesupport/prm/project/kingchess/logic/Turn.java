package vn.fpt.coursesupport.prm.project.kingchess.logic;

public class Turn extends AComponent {
	
	private int id;
	private APiece actor;
	private Position position;
	
	public Turn() {}
		
	public Turn(APiece actor, Position pos) {
		setActor(actor);
		setPosition(pos);
	}
	
	public APiece getActor() {
		return actor;
	}

	public void setActor(APiece actor) {
		this.actor = actor;
	}

	public void setPosition(Position pos) {
		this.position = pos;
	}
	
	public Position getPosition() {
		return position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
