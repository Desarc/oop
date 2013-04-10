package oving9;

import java.util.ArrayList;

import sun.misc.Cleaner;

import acm.program.ConsoleProgram;

public class HighscoreView extends ConsoleProgram implements ListListener {

	HighscoreList highScore;
	
	
	public void init() {
		setSize(200, 200);
		highScore = new HighscoreList(5);
		highScore.addResult(new SokobanResult("Mal", 5));
		highScore.addResult(new SokobanResult("Jayne", 7));
		highScore.addResult(new SokobanResult("Kaylee", 5));
		highScore.addResult(new SokobanResult("Inara", 3));
		highScore.addResult(new SokobanResult("Wash", 10));
		highScore.addListListener(this);
		printScores();
	}
	
	public void run() {
		askForInput();
	}
	
	
	public void printScores() {
		ArrayList<Comparable> scores = (ArrayList<Comparable>)highScore.getList();
		println("Sokoban Highscores\n");
		for (Comparable score : scores) {
			println(score.toString());
		}
		println("\n");
	}
	
	public void askForInput() {
		println("Enter a new highscore:");
		String name = readLine("Name: ");
		int score = readInt("Score: ");
		highScore.addResult(new SokobanResult(name, score));
	}
	
	public void refreshScreen() {
		printScores();
		askForInput();
	}


	@Override
	public void listChanged(ObservableList changedList, int lowIndex,
			int highIndex) {
		if (changedList.equals(highScore)) {
			refreshScreen();
		}
		
	}
	
}
