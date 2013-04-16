package sokoban1;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import sokoban1.SokobanGUISwing.ReplayTask;
import sokoban2.Move;

public class SokobanCore {
	
	SokobanGUI GUI;

	private SokobanLevel level;
	
	private ArrayList<Dir> path;
	private String previousMoves;
	private ArrayList<Dir> undoneMoves;
	
	private int currentLevel = 7;
	
	private boolean replaying;
	private boolean playing;
	
	
	public SokobanCore() {
		previousMoves = "";
		path = new ArrayList<Dir>();
		undoneMoves = new ArrayList<Dir>();
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
		Dir undone = path.remove(path.size()-1);
		undoneMoves.add(undone);
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
	
	public void redoLastMove() {
		if (undoneMoves.size() > 0) {
			Dir redo = undoneMoves.remove(undoneMoves.size()-1);
			move(redo, false);
		}
	}
	
	public void clickToMove(int toX, int toY) {
		ArrayList<Dir> path = level.findShortestPath(level.getX(), level.getY(), toX, toY);
		if (path != null) {
			Timer timer = new Timer();
			timer.schedule(new MoveTask(0, path), 100);
		}
		else {
		}
	}
	

	public int numberOfMoves() {
		return path.size();
	}
	
	class MoveTask extends TimerTask {

		int n;
		ArrayList<Dir> path;
		
		public MoveTask(int n, ArrayList<Dir> path) {
			this.n = n;
			this.path = path;
		}
		
		public void run() {
			if (n < path.size()) {
				move(path.get(n));
				n++;
				Timer timer = new Timer();
				timer.schedule(new MoveTask(n, path), 100);
			}
			else {
				setReplaying(false);
			}
		}
	}
}
