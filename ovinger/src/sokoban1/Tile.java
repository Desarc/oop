package sokoban1;

public enum Tile {
	
	WALL ('#'),
	BLANK (' '),
	TARGET ('.'),
	MOVER ('@'),
	MOVABLE ('$'),
	MOVER_ON_TARGET ('+'),
	MOVABLE_ON_TARGET ('*');
	
	private char code;
	
	Tile(char code) {
		this.code = code;
	}
	
	public char code() {
		return code;
	}
}
