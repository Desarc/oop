package sokoban1;

public enum Dir {
	UP (0, -1, "u"),
	DOWN (0, 1, "d"),
	LEFT (-1, 0, "l"),
	RIGHT (1, 0, "r");
	
	private final int x;
	private final int y;
	private final String dir;

	Dir(int x, int y, String dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public int x() { return x; }
	public int y() { return y; }
	public String dir() { return dir; }
}
