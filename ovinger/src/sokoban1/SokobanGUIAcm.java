package sokoban1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class SokobanGUIAcm extends GraphicsProgram implements KeyListener, SokobanGUI {

	final String wallImage = "sokoban/wall16x16.png";
	final String blankImage = "sokoban/blank16x16.png";
	final String movableImage = "sokoban/movable16x16.png";
	final String movable_on_targetImage = "sokoban/movable_on_target16x16.png";
	final String mover_on_targetImage = "sokoban/mover_on_target16x16.png";
	final String moverImage = "sokoban/mover16x16.png";
	final String targetImage = "sokoban/target16x16.png";
	
	final int tileSize = 16;
	
	private ArrayList<SokobanListener> listeners;
	
	private SokobanCore core;
	
	public void init() {
		setSize(800, 400);
		listeners = new ArrayList<SokobanListener>();
		core = new SokobanCore(this);
		addKeyListeners();
	}
	
	
	
	public void paintLevel(ArrayList<ArrayList<Tile>> level) {
		System.out.println("painting level");
		for (int i=0;i<level.size();i++) {
			for (int j=0;j<level.get(i).size();j++) {
				Tile tile = level.get(i).get(j);
				GImage tileImage = getImage(tile);
				add(tileImage, j*tileSize, i*tileSize);
			}
		}
	}
	
	private GImage getImage(Tile tile) {
		GImage image;
		switch(tile) {
			case WALL:
				image = new GImage(wallImage);
				break;
			case BLANK:
				image = new GImage(blankImage);
				break;
			case MOVER:
				image = new GImage(moverImage);
				break;
			case MOVABLE:
				image = new GImage(movableImage);
				break;
			case TARGET:
				image = new GImage(targetImage);
				break;
			case MOVER_ON_TARGET:
				image = new GImage(mover_on_targetImage);
				break;
			case MOVABLE_ON_TARGET:
				image = new GImage(movable_on_targetImage);
				break;
			default:
				image = new GImage(blankImage);
		}
		return image;
	}
	
	public void keyPressed(KeyEvent event) {
        int key;
        if (Character.isLetter(event.getKeyChar())) {
            key = event.getKeyChar();
        }
        else {
            key = event.getKeyCode();
        }
       
        switch (key) {
        case KeyEvent.VK_UP:
        	notifyListeners(Dir.UP);
            break;
        case KeyEvent.VK_DOWN:
        	notifyListeners(Dir.DOWN);
            break;
        case KeyEvent.VK_LEFT:
        	notifyListeners(Dir.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
        	notifyListeners(Dir.RIGHT);
            break;
        }
        /*if (complete) {
        	core.replay();
        }*/
    }
	
	private void notifyListeners(Dir direction) {
		for (int i=0;i<listeners.size();i++) {
			listeners.get(i).move(direction);
		}
	}
	
	public void addListener(SokobanListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(SokobanListener listener) {
		listeners.remove(listener);
	}
	
	
}
