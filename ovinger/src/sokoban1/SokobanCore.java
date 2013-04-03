package sokoban1;

import java.util.ArrayList;

import sokoban2.Move;

public class SokobanCore {
	
	SokobanGUI GUI;

	private SokobanLevel level;
	
	private ArrayList<Dir> path;
	private String previousMoves;
	
	private int currentLevel = 7;
	
	private boolean replaying;
	private boolean playing;
	
	
	public SokobanCore() {
		previousMoves = "";
		path = new ArrayList<Dir>();
		replaying = false;
		playing = true;
	}
	
	public SokobanCore(SokobanGUI GUI) {
		this();
		this.GUI = GUI;
		level = new SokobanLevel(currentLevel, GUI);
		GUI.paintLevel(level);
	}
	
	public void move(Dir direction) {
		move(direction, false);
	}
	
	
	private void move(Dir direction, boolean undo) {
		if (!playing) {
			return;
		}
		int posX = level.getX(), posY = level.getY();
		if (Move.validMove(posX, posY, posX+direction.x(), posY+direction.y(), level)) {
			if (!replaying && !undo) {
				path.add(direction);
			}
			boolean box = Move.moveTo(posX, posY, posX+direction.x(), posY+direction.y(), level);
			if (!undo) {
				if (box) {
					previousMoves += direction.dir().toUpperCase();
					GUI.labelChanged();
				}
				else {
					previousMoves += direction.dir();
					GUI.labelChanged();
				}
			}
		}
		if (level.getRemaining() == 0) {
			System.out.println("Success!");
			playing = false;
		}
	}
	
	public String getPreviousMoves() {
		return previousMoves;
	}
	
	public void reset() {
		reset(currentLevel);
	}
	
	public void reset(int n) {
		currentLevel = n;
		level = new SokobanLevel(currentLevel, GUI);
		path = new ArrayList<Dir>();
		previousMoves = "";
		playing = true;
		replaying = false;
		GUI.paintLevel(level);
	}
	
	public void softReset() {
		level = new SokobanLevel(currentLevel, GUI);
		previousMoves = "";
		playing = true;
		replaying = true;
		GUI.paintLevel(level);
	}
	
	public SokobanLevel getLevel() {
		return level;
	}
	
	public void setGUI(SokobanGUI gui) {
		this.GUI = gui;
		level = new SokobanLevel(currentLevel, GUI);
		GUI.paintLevel(level);
	}
	
	public ArrayList<Dir> getPath() {
		return path;
	}
	
	public void setReplaying(boolean replaying) {
		this.replaying = replaying;
	}
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
	public void undoLastMove() {
		if (!playing || path.size() == 0) {
			return;
		}
		Dir dir = path.get(path.size()-1);
		path.remove(path.size()-1);
		previousMoves = previousMoves.substring(0, previousMoves.length()-1);
		if (dir == Dir.LEFT) {
			move(Dir.RIGHT, true);
			Move.undoBox(Dir.LEFT, level);
		}
		else if (dir == Dir.RIGHT) {
			move(Dir.LEFT, true);
			Move.undoBox(Dir.RIGHT, level);
		}
		else if (dir == Dir.UP) {
			move(Dir.DOWN, true);
			Move.undoBox(Dir.UP, level);
		}
		else {
			move(Dir.UP, true);
			Move.undoBox(Dir.DOWN, level);
		}
	}
	

	public int numberOfMoves() {
		return path.size();
	}
}
