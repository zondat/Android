package vn.fpt.coursesupport.prm.project.gamecaro.logic;

public class Player {
	
	private GameCaro game;
	private int score;
	private int mark;
	
	public Player(GameCaro game) {
		this.game = game;
	}
	
	public Player(GameCaro game, int mark) {
		this(game);
		setMark(mark);
	}
	
	public boolean click(int row, int col) {
		Cell onCell = game.getMatrix().getCell(row, col);
		if (!onCell.isMarked()) {
			onCell.setValue(mark);
			game.notifyMove(row, col, mark);
			return true;
		}
		return false;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
	
	
}
