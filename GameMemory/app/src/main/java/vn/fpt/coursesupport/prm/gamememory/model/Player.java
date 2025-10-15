package vn.fpt.coursesupport.swd.example.gamememory.logic;

public class Player {

	private String name;
	private int mark;
	
	public Player() {
		mark = 0;
	}
	
	public Player(String name) {
		this();
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public void winPoint() {
		mark++;
	}

	
}
