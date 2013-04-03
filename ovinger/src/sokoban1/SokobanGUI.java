package sokoban1;

public interface SokobanGUI extends SokobanListener {

	public void paintLevel(SokobanLevel level);
	
	public void labelChanged();
	
}