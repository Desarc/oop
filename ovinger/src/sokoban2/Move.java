package sokoban2;

import sokoban1.Dir;
import sokoban1.SokobanLevel;
import sokoban1.Tile;

public class Move {

	public static boolean validMove(int fromX, int fromY, int toX, int toY, SokobanLevel level) {
		int dimX = level.getDimX(), dimY = level.getDimY();
		if (toX >= dimX || toY >= dimY || toY < 0 || toX < 0) {
			return false;
		}
		Tile next = level.getTile(toX, toY);
		if (next == Tile.WALL) {
			return false;
		}
		if (next == Tile.MOVABLE || next == Tile.MOVABLE_ON_TARGET) {
			int newMovableY = toY+(toY-fromY);
			int newMovableX = toX+(toX-fromX);
			Tile newMovablePos = level.getTile(newMovableX, newMovableY);
			if (newMovablePos == Tile.WALL || newMovablePos == Tile.MOVABLE || 
					newMovablePos == Tile.MOVABLE_ON_TARGET) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean moveTo(int fromX, int fromY, int toX, int toY, SokobanLevel level) {
		boolean box = false;
		level.setTile(fromX, fromY, level.getGridTile(fromX, fromY));
		Tile next = level.getTile(toX, toY);
		if (next == Tile.TARGET) {
			level.setTile(toX, toY, Tile.MOVER_ON_TARGET);
		}
		if (next == Tile.MOVABLE || next == Tile.MOVABLE_ON_TARGET) {
			box = true;
			next = level.getGridTile(toX, toY);
			if (next == Tile.BLANK) {
				level.setTile(toX, toY, Tile.MOVER);
			}
			else if (next == Tile.TARGET) {
				level.setTile(toX, toY, Tile.MOVER_ON_TARGET);
				level.increaseRemaining();
			}
			int newMovableY = toY+(toY-fromY);
			int newMovableX = toX+(toX-fromX);
			Tile newMovablePos = level.getTile(newMovableX, newMovableY);
			if (newMovablePos == Tile.BLANK) {
				level.setTile(newMovableX, newMovableY, Tile.MOVABLE);
			}
			else if (newMovablePos == Tile.TARGET) {
				level.setTile(newMovableX, newMovableY, Tile.MOVABLE_ON_TARGET);
				level.decreaseRemaining();
			}
		}
		else {
			level.setTile(toX, toY, Tile.MOVER);
		}
		return box;
	}
	
	public static void undoBox(Dir dir, SokobanLevel level) {
		int boxX = level.getX()+2*dir.x();
		int boxY = level.getY()+2*dir.y();
		if (level.getTile(boxX, boxY) == Tile.MOVABLE || level.getTile(boxX, boxY) == Tile.MOVABLE_ON_TARGET) {
			int newX = level.getX()+dir.x();
			int newY = level.getY()+dir.y();
			level.setTile(boxX, boxY, level.getGridTile(boxX, boxY));
			if (level.getGridTile(newX, newY) == Tile.TARGET) {
				level.setTile(newX, newY, Tile.MOVABLE_ON_TARGET);
			}
			else {
				level.setTile(newX, newY, Tile.MOVABLE);
			}
		}
	}
}
