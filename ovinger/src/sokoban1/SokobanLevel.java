package sokoban1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public class SokobanLevel implements sokoban2.Level {

	private int posX, posY;
	private int remaining;

	private String[] levelArray;
	private ArrayList<ArrayList<Tile>> level;
	private ArrayList<ArrayList<Tile>> grid;

	private ArrayList<SokobanListener> listeners;

	public SokobanLevel(int n, SokobanListener listener) {
		listeners = new ArrayList<SokobanListener>();
		listeners.add(listener);
		loadLevel(n);
	}

	public ArrayList<ArrayList<Tile>> getLevel() {
		return level;
	}

	public String[] getLines() {
		return levelArray;
	}

	public Tile setTile(int x, int y, Tile tile) {
		Tile old = getTile(x, y);
		level.get(y).remove(x);
		level.get(y).add(x, tile);
		if (tile == Tile.MOVER || tile == Tile.MOVER_ON_TARGET) {
			posX = x;
			posY = y;
		}
		notifyListeners(x, y, tile);
		return old;
	}

	public Tile getTile(int x, int y) {
		return level.get(y).get(x);
	}

	public Tile getGridTile(int x, int y) {
		return grid.get(y).get(x);
	}

	public int getRemaining() {
		return remaining;
	}

	public void increaseRemaining() {
		remaining++;
	}

	public void decreaseRemaining() {
		remaining--;
	}

	private String[] loadFromFile(String file) {
		File levelFile = new File(file);
		ArrayList<String> lines = new ArrayList<String>();
		int n = 0;
		try {
			Scanner scanner = new Scanner(levelFile, "UTF-8");
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lines.add(line);
				n++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] levelArray = new String[n];
		for (int i = 0; i < n; i++) {
			levelArray[i] = lines.get(i);
		}
		return levelArray;
	}

	private void loadLevel(int num) {
		level = new ArrayList<ArrayList<Tile>>();
		grid = new ArrayList<ArrayList<Tile>>();
		String filePath = "resources/sokoban_levels/";
		String[] levelArray;
		switch(num) {
		case 1:
			levelArray = loadFromFile(filePath+"001.txt");
			break;
		case 2:
			levelArray = loadFromFile(filePath+"002.txt");
			break;
		case 3:
			levelArray = loadFromFile(filePath+"003.txt");
			break;
		case 4:
			levelArray = loadFromFile(filePath+"004.txt");
			break;
		case 5:
			levelArray = loadFromFile(filePath+"005.txt");
			break;
		case 6:
			levelArray = loadFromFile(filePath+"006.txt");
			break;
		case 7:
			levelArray = loadFromFile(filePath+"007.txt");
			break;
		default:
			levelArray = loadFromFile(filePath+"001.txt");	
		}
		for (int i=0;i<levelArray.length;i++) {
			ArrayList<Tile> levelLine = new ArrayList<Tile>();
			ArrayList<Tile> gridLine = new ArrayList<Tile>();
			for (int j=0;j<levelArray[i].length();j++) {
				char code = levelArray[i].charAt(j);
				Tile tile = convertToTile(code);
				levelLine.add(tile);
				if (tile == Tile.MOVABLE || tile == Tile.MOVER) {
					gridLine.add(Tile.BLANK);
				}
				else if (tile == Tile.MOVABLE_ON_TARGET || tile == Tile.MOVER_ON_TARGET) {
					gridLine.add(Tile.TARGET);
				}
				else {
					gridLine.add(tile);
				}
				if (tile == Tile.MOVER) {
					posX = j;
					posY = i;
				}
				else if (tile == Tile.MOVER_ON_TARGET) {
					posX = j;
					posY = i;
					remaining++;
				}
				else if (tile == Tile.TARGET) {
					remaining++;
				}
			}
			level.add(levelLine);
			grid.add(gridLine);
		}
	}

	private Tile convertToTile(char code) {
		switch (code) {
		case ' ':
			return Tile.BLANK;
		case '#':
			return Tile.WALL;
		case '@':
			return Tile.MOVER;
		case '$':
			return Tile.MOVABLE;
		case '+':
			return Tile.MOVER_ON_TARGET;
		case '.':
			return Tile.TARGET;
		case '*':
			return Tile.MOVABLE_ON_TARGET;
		default:
			return Tile.BLANK;
		}
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public int getDimX() {
		return level.get(0).size();
	}

	public int getDimY() {
		return level.size();
	}

	public void reset(int num) {
		loadLevel(num);
	}

	public void notifyListeners(int x, int y, Tile tile) {
		for (SokobanListener listener : listeners) {
			listener.tileChanged(x, y, tile);
		}
	}

	public boolean findShortestPath() {
		return true;
	}

	public ArrayList<Dir> findShortestPath(int fromX, int fromY, int toX, int toY) {

		Tile target = getTile(toX, toY);
		if (target == Tile.BLANK || target == Tile.TARGET) {
			GraphElement origin = new GraphElement(getTile(fromX, fromY), fromX, fromY, null);
			GraphElement current = buildGraph(origin, toX, toY);
			if (current == null) {
				return null;
			}
			ArrayList<Dir> path = new ArrayList<Dir>();
			while (current != origin) {
				GraphElement parent = current.parent;
				int dirX = current.x-parent.x;
				int dirY = current.y-parent.y;
				Dir dir = null;
				if (dirX == 1) {
					dir = Dir.RIGHT;
				}
				else if (dirX == -1) {
					dir = Dir.LEFT;
				}
				else if (dirY == 1) {
					dir = Dir.DOWN;
				}
				else if (dirY == -1) {
					dir = Dir.UP;
				}
				path.add(0, dir);
				current = parent;
			}
			return path;
		}
		return null;
	}

	private GraphElement buildGraph(GraphElement origin, int toX, int toY) {
		int nTiles[][] = {{0,1},{0,-1},{1,0},{-1,0}};
		boolean done = false;
		ArrayList<GraphElement> queue = new ArrayList<GraphElement>();
		ArrayList<GraphElement> checked = new ArrayList<GraphElement>();
		queue.add(origin);
		while (queue.size() != 0) {
			GraphElement next = queue.remove(0);
			if (next.x == toX && next.y == toY) {
				return next;
			}
			for (int n[] : nTiles) {
				int nx = next.x+n[0], ny = next.y+n[1];
				Tile neighbour = getTile(nx, ny);
				if (neighbour == Tile.BLANK || neighbour == Tile.TARGET) {
					if (!containsPos(nx, ny, queue) && !containsPos(nx, ny, checked)) {
						GraphElement ge = new GraphElement(neighbour, nx, ny, next);
						queue.add(ge);
						checked.add(next);
					}
				}
			}
		}
		return null;
	}

	private boolean containsPos(int x, int y, ArrayList<GraphElement> list) {
		for (GraphElement element : list) {
			if (element.x == x && element.y == y) {
				return true;
			}
		}
		return false;
	}


	public void addListener(SokobanListener listener) {
		listeners.add(listener);
	}

	class GraphElement {
		GraphElement parent;
		Tile tile;
		int x, y;

		public GraphElement(Tile tile, int x, int y, GraphElement parent) {
			this.tile = tile;
			this.x = x;
			this.y = y;
			this.parent = parent;
		}

	}

}
