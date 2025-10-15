package vn.fpt.coursesupport.prm.mvvm.gamedama.model;

public class Player {

	private Game owner;
	private String name;
	private boolean direction = false;
	
	public Player(Game game) {
		owner = game;
	}
	
	public Player(Game game, String name) {
		this(game);
		setName(name);
	}

	public Game getOwner() {
		return owner;
	}

	public void setOwner(Game owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getDirection() {
		return direction;
	}
	
	public void setDirection(boolean b) {
		direction = b;
	}

}
