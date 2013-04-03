package sokoban1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class SokobanGUISwing extends JPanel implements SokobanGUI, ActionListener {
	
	final String filepath = "/home/desarc/projects/ovinger/resources/sokoban/";
	final String wallImage = filepath+"wall16x16.png";
	final String blankImage = filepath+"blank16x16.png";
	final String movableImage = filepath+"movable16x16.png";
	final String movable_on_targetImage = filepath+"movable_on_target16x16.png";
	final String mover_on_targetImage = filepath+"mover_on_target16x16.png";
	final String moverImage = filepath+"mover16x16.png";
	final String targetImage = filepath+"target16x16.png";
	
	final String UNDO = "undo";
	
	final int tileSize = 16;
	
	private SokobanCore core;
	private GridBagConstraints c;
	
	private ArrayList<ArrayList<JLabel>> levelGraphics;
	
	private JButton replay, undo, reset;
	private JButton level1, level2, level3, level4, level5, level6, level7;
	private JLabel pathLabel, movesLabel, levelLabel;
	private JTextField path, moves;
	
	private int panelLevelStartX, panelLevelStartY;
	
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("Sokoban");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		SokobanCore core = new SokobanCore();
		SokobanGUISwing gui = new SokobanGUISwing(core);
		core.setGUI(gui);
		container.add(gui);
		mainFrame.setContentPane(container);
        mainFrame.pack();
        mainFrame.setVisible(true);
	}
	
	public SokobanGUISwing(SokobanCore core) {
		
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        getActionMap().put("left", new MoveAction(Dir.LEFT));
        getActionMap().put("right", new MoveAction(Dir.RIGHT));
        getActionMap().put("up", new MoveAction(Dir.UP));
        getActionMap().put("down", new MoveAction(Dir.DOWN));
        
		this.core = core;
		
    	setLayout(new GridBagLayout());
    	c = new GridBagConstraints();
    	
		replay = new JButton("Replay");
		replay.addActionListener(this);
		replay.setFocusable(false);
		
		level1 = new JButton("1");
		level1.addActionListener(this);
		level1.setFocusable(false);
		
		level2 = new JButton("2");
		level2.addActionListener(this);
		level2.setFocusable(false);
		
		level3 = new JButton("3");
		level3.addActionListener(this);
		level3.setFocusable(false);
		
		level4 = new JButton("4");
		level4.addActionListener(this);
		level4.setFocusable(false);
		
		level5 = new JButton("5");
		level5.addActionListener(this);
		level5.setFocusable(false);
		
		level6 = new JButton("6");
		level6.addActionListener(this);
		level6.setFocusable(false);
		
		level7 = new JButton("7");
		level7.addActionListener(this);
		level7.setFocusable(false);
		
		undo = new JButton("Undo");
		undo.addActionListener(this);
		undo.setFocusable(false);
		
		reset = new JButton("Reset");
		reset.addActionListener(this);
		reset.setFocusable(false);
		
		pathLabel = new JLabel("Path: ");
		movesLabel = new JLabel("Number of moves: ");
		levelLabel = new JLabel("Choose level:");
		
		path = new JTextField();
		path.setColumns(25);
		path.setEditable(false);
		
		moves = new JTextField();
		moves.setColumns(4);
		moves.setEditable(false);
		//moves.setText("0");
		
		//initPanel();
		
	}
	
	private void initPanel() {
		c.gridheight = 1;

		c.gridx = 0;
		c.gridy = 0;
		add(pathLabel, c);
		
		c.gridwidth = 20;

		c.gridx = 1;
		c.gridy = 0;
		add(path, c);
		
		c.gridwidth = 1;
		c.gridheight = 2;
		
		c.gridx = 0;
		c.gridy = 1;
		add(replay, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(undo, c);
		
		c.gridx = 1;
		c.gridy = 1;
		add(reset, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(movesLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		add(moves, c);
		
		c.gridheight = 1;
		c.gridwidth = 2;
		
		c.gridx = 0;
		c.gridy = 7;
		add(levelLabel, c);
		
		c.gridheight = 2;
		c.gridwidth = 1;
		
		c.gridx = 0;
		c.gridy = 8;
		add(level1, c);
		
		c.gridx = 1;
		c.gridy = 8;
		add(level2, c);
		
		c.gridx = 0;
		c.gridy = 10;
		add(level3, c);
		
		c.gridx = 1;
		c.gridy = 10;
		add(level4, c);
		
		c.gridx = 0;
		c.gridy = 12;
		add(level5, c);
		
		c.gridx = 1;
		c.gridy = 12;
		add(level6, c);
		
		c.gridx = 0;
		c.gridy = 14;
		add(level7, c);
		
		c.gridheight = 1;
		
		panelLevelStartX = 2;
		panelLevelStartY = 1;
	}

	private ImageIcon getImage(Tile tile) {
		ImageIcon image;
		switch(tile) {
			case WALL:
				image = new ImageIcon(wallImage);
				break;
			case BLANK:
				image = new ImageIcon(blankImage);
				break;
			case MOVER:
				image = new ImageIcon(moverImage);
				break;
			case MOVABLE:
				image = new ImageIcon(movableImage);
				break;
			case TARGET:
				image = new ImageIcon(targetImage);
				break;
			case MOVER_ON_TARGET:
				image = new ImageIcon(mover_on_targetImage);
				break;
			case MOVABLE_ON_TARGET:
				image = new ImageIcon(movable_on_targetImage);
				break;
			default:
				image = new ImageIcon(blankImage);
		}
		return image;
	}
	
	public void paintLevel(SokobanLevel level) {
		initPanel();
		updateLabels();
		levelGraphics = new ArrayList<ArrayList<JLabel>>();
		ArrayList<ArrayList<Tile>> levelGrid = level.getLevel();
		
		for (int i=0;i<levelGrid.size();i++) {
			ArrayList<JLabel> line = new ArrayList<JLabel>();
			for (int j=0;j<levelGrid.get(i).size();j++) {
				Tile tile = levelGrid.get(i).get(j);
				ImageIcon tileImage = getImage(tile);
				JLabel imageLabel = new JLabel(tileImage);
				line.add(imageLabel);
				c.gridx = j+panelLevelStartX;
				c.gridy = i+panelLevelStartY;
				add(imageLabel, c);
			}
			levelGraphics.add(line);
		}
		validate();
	}
	
	private void updateLabels() {
		path.setText(core.getPreviousMoves());
		moves.setText(""+core.getPreviousMoves().length());
	}
	
	public void setModel(SokobanCore core) {
		this.core = core;
	}
	
	public void tileChanged(int x, int y, Tile tile) {
		levelGraphics.get(y).get(x).setIcon(getImage(tile));
		updateLabels();
	}

	private void replay() {
		softReset();
		Timer timer = new Timer();
		timer.schedule(new ReplayTask(0, core.getPath()), 500);
	}
	
	private void softReset() {
		removeAll();
		validate();
		setLayout(new GridBagLayout());
    	c = new GridBagConstraints();
    	initPanel();
    	core.softReset();
    	updateLabels();
		getTopLevelAncestor().repaint();
	}
	
	private void reset(int n) {
		removeAll();
		validate();
		setLayout(new GridBagLayout());
    	c = new GridBagConstraints();
    	initPanel();
    	if (n == 0) {
    		core.reset();
    	}
    	else {
    		core.reset(n);
    	}
    	updateLabels();
		getTopLevelAncestor().repaint();
	}
	
	private void undo() {
		core.undoLastMove();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == undo) {
			undo();
			updateLabels();
		}
		else if (e.getSource() == replay) {
			replay();
		}
		else if (e.getSource() == reset) {
			reset(0);
		}
		else if (e.getSource() == level1) {
			reset(1);
		}
		else if (e.getSource() == level2) {
			reset(2);
		}
		else if (e.getSource() == level3) {
			reset(3);
		}
		else if (e.getSource() == level4) {
			reset(4);
		}
		else if (e.getSource() == level5) {
			reset(5);
		}
		else if (e.getSource() == level6) {
			reset(6);
		}
		else if (e.getSource() == level7) {
			reset(7);
		}
	}
	
	class MoveAction extends AbstractAction {
		
		Dir dir;
		
		public MoveAction(Dir dir) {
			this.dir = dir;
		}
		
		public void actionPerformed(ActionEvent e) {
			core.move(dir);
		}
	}

	class ReplayTask extends TimerTask {

		int n;
		ArrayList<Dir> path;
		
		public ReplayTask(int n, ArrayList<Dir> path) {
			this.n = n;
			this.path = path;
		}
		
		public void run() {
			if (n < path.size()) {
				core.move(path.get(n));
				n++;
				Timer timer = new Timer();
				timer.schedule(new ReplayTask(n, path), 100);
			}
			else {
				core.setReplaying(false);
			}
		}
	}

	public void labelChanged() {
		updateLabels();
	}
}

